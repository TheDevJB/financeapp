package com.financeapp.finance.service;

import java.math.BigDecimal;

import com.financeapp.finance.exception.AccountDoesNotExistException;
import com.financeapp.finance.exception.TransactionDoesNotExistException;
import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.Transaction;
import com.financeapp.finance.repositories.AccountRepository;
import com.financeapp.finance.repositories.TransactionRepository;

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

    public Account deposit(String accountId, BigDecimal dollarAmount){
        Account account = accountRepo.findByAccountId(accountId).orElseThrow(AccountDoesNotExistException::new);

        if(dollarAmount == null || dollarAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Dollar amount is not valid or below 0");
        }

        account.setBalance(account.getBalance().add(dollarAmount));
        accountRepo.save(account);

        return account;
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
    //TODO: Create a withdraw method
    //TODO: Create an AccountService to handle transfers, balance changes and figure out more.
    //TODO: Search more ways how transacations work
}
