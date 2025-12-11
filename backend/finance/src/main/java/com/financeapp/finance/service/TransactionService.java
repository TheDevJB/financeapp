package com.financeapp.finance.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.financeapp.finance.exception.AccountDoesNotExistException;
import com.financeapp.finance.exception.TransactionDoesNotExistException;
import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.AccountType;
import com.financeapp.finance.model.Transaction;
import com.financeapp.finance.repositories.AccountRepository;
import com.financeapp.finance.repositories.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo){
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public Transaction getTransactionByTransactionId(Long transactionId){
        return transactionRepo.findByTransactionId(transactionId).orElseThrow(TransactionDoesNotExistException::new);
    } 

    public Transaction getAccountByAccountId(String accountId){
        return transactionRepo.findByAccountId(accountId).orElseThrow(AccountDoesNotExistException::new);
    }

    public Transaction getTransactionByAccountIdOrTransactionId(String accountId, Long transactionId){
        return transactionRepo.findByAccountIdOrTransactionId(accountId, transactionId).orElseThrow(TransactionDoesNotExistException::new);
    }

    public Transaction getTransactionType(String transactionType, BigDecimal dollarAmount, String category, String accountId, String description){
        Transaction transaction = new Transaction();

        if(transactionType == null || transactionType.isEmpty()){
            throw new IllegalArgumentException("Transaction type is not valid");
        }
        if(category == null || category.isEmpty()){
            throw new IllegalArgumentException("Category is invalid");
        }

        transaction.setTransactionType(transactionType);
        transaction.setCategory(category);
        transaction.setDescription(description);
        transaction.setAccountId(accountId);
        transaction.setDollarAmount(dollarAmount);
        
        transactionRepo.save(transaction);

        return transaction;
    }
    
    public void balanceAmount(BigDecimal dollarAmount, AccountType accountType, Integer accountId){
        Account  account = accountRepo.findByAccountId(accountId).orElseThrow(() -> new RuntimeException("Account not found with id" + accountId));

        account.setBalance(dollarAmount);
        accountRepo.save(account);
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
