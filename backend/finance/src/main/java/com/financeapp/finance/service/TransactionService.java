package com.financeapp.finance.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.financeapp.finance.exception.AccountDoesNotExistException;
import com.financeapp.finance.exception.TransactionDoesNotExistException;
import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.Transaction;
import com.financeapp.finance.model.TransactionType;
import com.financeapp.finance.repositories.AccountRepository;
import com.financeapp.finance.repositories.TransactionRepository;
import com.financeapp.finance.dto.TransferDTO;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

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
            Account account, String description) {
        Transaction transaction = new Transaction();

        if (transactionType == null) {
            throw new IllegalArgumentException("Transaction type is not valid");
        }

        transaction.setTransactionType(transactionType);
        transaction.setDescription(description);
        transaction.setAccount(account);
        transaction.setDollarAmount(dollarAmount);

        transactionRepo.save(transaction);

        return transaction;
    }

    public Transaction transfer(TransferDTO transferDTO) {
        if (transferDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Dollar amount is below 0");
        }
        Transaction transaction = new Transaction();

        Account sourceAccount = accountRepo.findByAccountId(transferDTO.getSourceAccountId())
                .orElseThrow(AccountDoesNotExistException::new);

        Account destAccount = accountRepo.findByAccountId(transferDTO.getDestAccountId())
                .orElseThrow(AccountDoesNotExistException::new);

        if (sourceAccount.getBalance().compareTo(transferDTO.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferDTO.getAmount()));
        destAccount.setBalance(destAccount.getBalance().add(transferDTO.getAmount()));

        accountRepo.save(sourceAccount);
        accountRepo.save(destAccount);

        LOGGER.info("Transfer of " + sourceAccount + "to" + destAccount + "successful");

        return transaction;
    }
}
