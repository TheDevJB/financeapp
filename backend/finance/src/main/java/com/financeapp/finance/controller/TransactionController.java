package com.financeapp.finance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeapp.finance.model.Transaction;
import com.financeapp.finance.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/user/{userId}/transaction/{transactionId}")
    public Transaction getTransactionByTransactionId(@PathVariable Integer userId, @PathVariable Long transactionId){
        return transactionService.getTransactionByTransactionId(transactionId);
    }
}
