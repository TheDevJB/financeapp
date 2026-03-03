export interface Transaction {
  transactionId?: number;
  userId?: number;
  dollarAmount: number;
  description: string;
  accountId?: number;
  categoryId?: number;
}
