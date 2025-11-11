package com.financeapp.finance.service;


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

}
