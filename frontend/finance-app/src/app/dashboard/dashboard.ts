import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AccountService } from '../services/account.service';
import { AuthService } from '../services/auth.service';
import { Account } from '../models/account.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent {
  accounts: Account[] = [];
  totalBalance: number = 0;
  userName: string = '';
  private userId: number = 0;

  constructor(
    private authService: AuthService,
    private accountService: AccountService
  ) { }

  ngOnInit() {
    const user = this.authService.currentUser;
    if (user) {
      this.userName = user.firstName;
      this.userId = user.userId ?? 0;
      this.loadAccounts();
    }
  }

  loadAccounts() {
    this.accountService.getAccountsByUser(this.userId).subscribe({
      next: (accounts) => {
        this.accounts = accounts;
        this.totalBalance = this.accounts.reduce((total, account) => total + account.balance, 0);
      },
      error: (error) => {
        console.error('Error loading accounts:', error);
      }
    });
  }
}