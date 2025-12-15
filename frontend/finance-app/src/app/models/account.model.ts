export interface Account {
    accountId?: number;
    userId: number;
    balance: number;
    amount?: number;
    accountType: 'CHECKING' | 'SAVINGS' | 'CREDIT CARD' | 'PERSONAL LOAN' | 'SCHOOL LOAN' | 'INVESTMENT' | 'CAR LOAN' | 'MORTGAGE' | 'RENT';
    nickname?: string;
    interestRate?: number;
    minimumPayment?: number;
    dueDay?: number;
    debtAccount?: boolean;
}
