export interface AccountInfo{
    accountId: string;
    accountName: string;
    balance: number;
    isActive: boolean;
}

export class Account{
    accountId: string;
    accountName: string;
    balance: number;
    isActive: boolean;

    constructor(data: AccountInfo){
        if(!data){
            throw new Error('Account information must be provided');
        }

        this.accountId = data.accountId;
        this.accountName = data.accountName;
        this.balance = data.balance;
        this.isActive = data.isActive;
    }

    public isValid(): boolean{
        return !!this.accountId && !!this.accountName && this.balance != null;
    }

    public hasPositiveBalance(): boolean{
        return this.balance >= 0;
    }

    public getFormattedDetails(): string{
        return 'Account: ${this.accountName} (${this.accountId}), Balance: $${this.balance.toFixed(2)}';
    }

    public deposit(amount: number): void{
        if(amount > 0){
            this.balance += amount;
        }
    }

    public withdraw(amount: number): boolean{
        if(amount > 0 && this.balance >= amount){
            this.balance -= amount;
            return true;
        }
        return false;
    }
}