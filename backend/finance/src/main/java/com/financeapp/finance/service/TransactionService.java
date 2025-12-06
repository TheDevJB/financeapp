package com.financeapp.finance.service;

import java.math.BigDecimal;

import com.financeapp.finance.exception.AccountDoesNotExistException;
import com.financeapp.finance.exception.TransactionDoesNotExistException;
import com.financeapp.finance.model.Transaction;
import com.financeapp.finance.repositories.TransactionRepository;

public class TransactionService {

    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo){
        this.transactionRepo = transactionRepo;
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
}
