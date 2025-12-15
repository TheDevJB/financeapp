export interface Account {
    accountId?: number;
    userId: number;
    balance: number;
    amount?: number;
    accountType: 'CHECKING' | 'SAVINGS' | 'CREDIT'; // Add other types as needed
    nickname?: string;
    interestRate?: number;
    minimumPayment?: number;
    dueDay?: number;
    debtAccount?: boolean;
}
