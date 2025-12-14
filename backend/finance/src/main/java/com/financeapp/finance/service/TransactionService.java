package com.financeapp.finance.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.financeapp.finance.exception.AccountDoesNotExistException;
import com.financeapp.finance.exception.TransactionDoesNotExistException;
import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.AccountType;
import com.financeapp.finance.model.BudgetCategory;
import com.financeapp.finance.model.Transaction;
import com.financeapp.finance.model.TransactionType;
import com.financeapp.finance.repositories.AccountRepository;
import com.financeapp.finance.repositories.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public Transaction getTransactionByTransactionId(Long transactionId) {
        return transactionRepo.findByTransactionId(transactionId).orElseThrow(TransactionDoesNotExistException::new);
    }

    public Transaction getTransactionByAccountAndTransactionId(Account account, Long transactionId) {
        return transactionRepo.findByAccountAndTransactionId(account, transactionId)
                .orElseThrow(TransactionDoesNotExistException::new);
    }

    public Transaction getTransactionType(TransactionType transactionType, BigDecimal dollarAmount,
            BudgetCategory category,
            Account account, String description) {
        Transaction transaction = new Transaction();

        if (transactionType == null) {
            throw new IllegalArgumentException("Transaction type is not valid");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category type is invalid");
        }

        transaction.setTransactionType(transactionType);
        transaction.setCategory(category);
        transaction.setDescription(description);
        transaction.setAccount(account);
        transaction.setDollarAmount(dollarAmount);

        transactionRepo.save(transaction);

        return transaction;
    }

    public void balanceAmount(BigDecimal dollarAmount, AccountType accountType, Long accountId) {
        Account account = accountRepo.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id" + accountId));

        account.setBalance(dollarAmount);
        accountRepo.save(account);
    }

    public Transaction deposit(Account account, BigDecimal dollarAmount, TransactionType transactionType,
            BigDecimal balance) {
        Transaction transaction = getTransactionType(transactionType, dollarAmount, null, account, "Deposit");

        if (dollarAmount == null || dollarAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Dollar amount is not valid or below 0");
        }

        account.setBalance(account.getBalance().add(dollarAmount));
        accountRepo.save(account);

        return transaction;
    }

    public Account transfer(Long accountId, BigDecimal dollarAmount, AccountType accountType, BigDecimal balance) {
        Account account = accountRepo.findByAccountId(accountId).orElseThrow(AccountDoesNotExistException::new);

        if (dollarAmount == null || dollarAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Dollar amount is not valid");
        }

        account.setAccountType(accountType);
        account.setBalance(account.getBalance().add(dollarAmount));
        accountRepo.save(account);

        return account;
    }
}
