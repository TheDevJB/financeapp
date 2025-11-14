package com.financeapp.finance.service;

import java.math.BigDecimal;

import com.financeapp.finance.exception.AccountDoesNotExistException;
import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.AccountType;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repositories.AccountRepository;


public class AccountService {

    private final AccountRepository accountRepo;
    private final AccountType accountType;
    private final User user;

    public AccountService(AccountRepository accountRepo, AccountType accountType, User user){
        this.accountRepo = accountRepo;
        this.accountType = accountType;
        this.user = user;
    }
    
    public Account createAccount(Integer accountId, User user, AccountType accountType, BigDecimal balance){
        //TODO: Utilize a UUID here I am just thinking for the future but I will think of myself and one user right now
        Account newAccount = new Account();

        newAccount.setAccountId(accountId);
        newAccount.setUser(user);
        newAccount.setAccountType(accountType);
        newAccount.setBalance(balance != null ? balance : BigDecimal.ZERO);

        accountRepo.save(newAccount);

        return newAccount;
    }

    public Account getAccountById(Integer accountId){
        return accountRepo.findByAccountId(accountId).orElseThrow(AccountDoesNotExistException::new);
    }

    public Account getAccountByUserAndAccountType(User user, AccountType accountType){
        return accountRepo.findByUserOrAccountType(user, accountType).orElseThrow(AccountDoesNotExistException::new);
    }

    public Account deposit(Integer accountId, BigDecimal dollarAmount){
        Account account = accountRepo.findByAccountId(accountId).orElseThrow(AccountDoesNotExistException::new);
    
        if(dollarAmount == null || dollarAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Dollar amount is not valid or below 0");
        }
    
        account.setBalance(account.getBalance().add(dollarAmount));
        accountRepo.save(account);
    
        return account;
    }
}
