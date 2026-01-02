import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../services/account.service';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../services/auth.service';
import { Account } from '../models/account.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent {
  accounts: Account[] = [];
  totalBalance: number = 0;
  userName: string = '';
  private userId: number = 0;
  showAddModal: boolean = false;
  showDeleteModal: boolean = false;
  accountToDeleteId: number | null = null;

  account: Account | null = null;
  isLoading: boolean = false;
  formattedBalance: string = '';
  formattedMinPayment: string = '';
  accountTypes: string[] = [
    'CHECKING', 'SAVINGS', 'CREDIT_CARD', 'AFFIRM', 'AFTER_PAY',
    'KLARNA', 'PERSONAL_LOAN', 'SCHOOL_LOAN', 'INVESTMENT',
    'CAR_LOAN', 'MORTGAGE', 'RENT'
  ];

  constructor(
    private authService: AuthService,
    private accountService: AccountService,
    private toastr: ToastrService,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone
  ) { }

  ngOnInit() {
    const user = this.authService.currentUser;
    console.log('Dashboard ngOnInit - Current User:', user);
    if (user) {
      this.userName = user.firstName;
      this.userId = user.userId ?? 0;
      this.loadAccounts();
    }
  }

  loadAccounts() {
    console.log('Loading accounts for userId:', this.userId);
    this.accountService.getAllAccountsByUser(this.userId).subscribe({
      next: (accounts) => {
        console.log('Accounts loaded successfully:', accounts);
        this.accounts = accounts;
        this.totalBalance = this.accounts.reduce((total, account) => total + account.balance, 0);
      },
      error: (error: any) => {
        console.error('Error loading accounts:', error);
        this.toastr.error('Failed to load accounts', 'Error');
      }
    });
  }

  validateAccount(account: Account): boolean {
    if (!account.accountType) {
      this.toastr.error('Account type is required');
      return false;
    }
    if (this.isLiabilityAccount(account.accountType)) {
      if (account.dueDay === undefined || account.dueDay === null) {
        this.toastr.error('Due day is required for this account type');
        return false;
      }
      if (this.hasInterestRate(account.accountType) && (account.interestRate === undefined || account.interestRate === null)) {
        this.toastr.error('Interest rate is required for this account type');
        return false;
      }
    }
    return true;
  }

  addAccount(account: Account) {
    if (!this.validateAccount(account)) return;
    if (this.isLoading) return;
    this.isLoading = true;
    this.accountService.createAccount(account).subscribe({
      next: (response) => {
        this.ngZone.run(() => {
          console.log('Account created successfully:', response);
          this.isLoading = false;
          this.toastr.success('Account added successfully', 'Success');

          // Small delay before closing to let them see the success state
          setTimeout(() => {
            this.showAddModal = false;
            this.account = null;
            this.formattedBalance = '';
            this.formattedMinPayment = '';
            this.loadAccounts();
            this.cdr.detectChanges();
          }, 1500);
        });
      },
      error: (error: any) => {
        this.ngZone.run(() => {
          console.error('Error adding account:', error);
          this.isLoading = false;
          this.toastr.error('Failed to add account', 'Error');
          this.cdr.detectChanges();
        });
      }
    });
  }

  openAddAccountModal() {
    this.account = {
      userId: this.userId,
      accountType: '' as any,
      balance: 0,
      amount: 0,
      nickname: ''
    } as Account;
    this.formattedBalance = '';
    this.formattedMinPayment = '';
    this.showAddModal = true;
  }

  deleteAccount(accountId: number) {
    this.accountService.deleteAccount(accountId).subscribe({
      next: () => {
        this.loadAccounts();
        this.toastr.success('Account deleted successfully', 'Success');
      },
      error: (error: any) => {
        console.error('Error deleting account:', error);
        this.toastr.error('Failed to delete account', 'Error');
      }
    });
  }

  deleteAccountModal(accountId: number) {
    this.accountToDeleteId = accountId;
    this.showDeleteModal = true;
  }

  isLiabilityAccount(type: string): boolean {
    const liabilities = [
      'CREDIT_CARD', 'AFFIRM', 'AFTER_PAY', 'KLARNA',
      'PERSONAL_LOAN', 'SCHOOL_LOAN', 'CAR_LOAN',
      'MORTGAGE', 'RENT'
    ];
    return liabilities.includes(type);
  }

  hasInterestRate(type: string): boolean {
    return type !== 'MORTGAGE' && type !== 'RENT';
  }

  hasMinimumPayment(type: string): boolean {
    return type !== 'MORTGAGE' && type !== 'RENT';
  }

  formatBalance(event: any) {
    const { formatted, raw } = this.applyCurrencyFormatting(event.target.value);
    this.formattedBalance = formatted;
    event.target.value = formatted;
    // Update the actual numeric balance for the model
    if (this.account) {
      this.account.balance = raw;
      this.account.amount = raw;
    }
  }

  formatMinPayment(event: any) {
    const { formatted, raw } = this.applyCurrencyFormatting(event.target.value);
    this.formattedMinPayment = formatted;
    event.target.value = formatted;
    if (this.account) this.account.minimumPayment = raw;
  }

  private applyCurrencyFormatting(input: string): { formatted: string, raw: number } {
    let value = input.replace(/[^0-9.]/g, '');

    // Ensure only one decimal point
    const parts = value.split('.');
    if (parts.length > 2) {
      value = parts[0] + '.' + parts.slice(1).join('');
    }

    if (!value || value === '.') {
      return {
        formatted: value === '.' ? '$0.' : '',
        raw: 0
      };
    }

    let integerPart = parts[0] || '0';
    let decimalPart = parts.length > 1 ? parts[1].substring(0, 2) : null;

    integerPart = integerPart.replace(/^0+(?=\d)/, '');
    if (integerPart === '') integerPart = '0';

    const commaInteger = integerPart.replace(/\B(?=(\d{3})+(?!\d))/g, ",");

    let formatted = '$' + commaInteger;
    if (decimalPart !== null) {
      formatted += '.' + decimalPart;
    }

    return {
      formatted: formatted,
      raw: parseFloat(value) || 0
    };
  }
}