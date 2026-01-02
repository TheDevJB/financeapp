import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../models/account.model';

@Injectable({
    providedIn: 'root'
})
export class AccountService {
    private apiUrl = 'http://localhost:8080/api/v1/accounts';

    constructor(private http: HttpClient) { }

    createAccount(account: Account): Observable<Account> {
        return this.http.post<Account>(this.apiUrl, account);
    }

    getAccount(accountId: number): Observable<Account> {
        return this.http.get<Account>(`${this.apiUrl}/${accountId}`);
    }

    getAllAccountsByUser(userId: number): Observable<Account[]> {
        return this.http.get<Account[]>(`${this.apiUrl}/user/${userId}`);
    }

    updateAccount(account: Account): Observable<Account> {
        return this.http.put<Account>(`${this.apiUrl}/${account.accountId}`, account);
    }

    deleteAccount(accountId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${accountId}`);
    }
}