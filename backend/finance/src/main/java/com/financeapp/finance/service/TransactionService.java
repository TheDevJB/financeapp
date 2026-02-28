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
import com.financeapp.finance.model.BudgetCategory;
import com.financeapp.finance.repositories.AccountRepository;
import com.financeapp.finance.repositories.TransactionRepository;
import com.financeapp.finance.repositories.BudgetCategoryRepository;
import com.financeapp.finance.dto.TransferDTO;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final BudgetCategoryRepository budgetCategoryRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository transactionRepo, AccountRepository accountRepo,
            BudgetCategoryRepository budgetCategoryRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.budgetCategoryRepo = budgetCategoryRepo;
    }

    public Transaction getTransactionByTransactionId(Long transactionId) {
        return transactionRepo.findByTransactionId(transactionId).orElseThrow(TransactionDoesNotExistException::new);
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

        Account sourceAccount = accountRepo.findByAccountId(transferDTO.getSourceAccountId())
                .orElseThrow(AccountDoesNotExistException::new);
        Account destAccount = accountRepo.findByAccountId(transferDTO.getDestAccountId())
                .orElseThrow(AccountDoesNotExistException::new);

        BudgetCategory category = budgetCategoryRepo.findByUserAndCategoryName(sourceAccount.getUser(), "Transfers")
                .orElseGet(() -> {
                    BudgetCategory newCategory = new BudgetCategory();
                    newCategory.setUser(sourceAccount.getUser());
                    newCategory.setCategoryName("Transfers");
                    newCategory.setIsFixed(false);
                    return budgetCategoryRepo.save(newCategory);
                });

        BudgetCategory destCategory = budgetCategoryRepo.findByUserAndCategoryName(destAccount.getUser(), "Transfers")
                .orElseGet(() -> {
                    BudgetCategory newCategory = new BudgetCategory();
                    newCategory.setUser(destAccount.getUser());
                    newCategory.setCategoryName("Transfers");
                    newCategory.setIsFixed(false);
                    return budgetCategoryRepo.save(newCategory);
                });

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferDTO.getAmount()));
        LOGGER.info("Transfer from: " + sourceAccount);

        destAccount.setBalance(destAccount.getBalance().add(transferDTO.getAmount()));
        LOGGER.info("Transfer received to: " + destAccount);

        Transaction outTransaction = new Transaction();
        outTransaction.setAccount(sourceAccount);
        outTransaction.setUser(sourceAccount.getUser());
        outTransaction.setDollarAmount(transferDTO.getAmount().negate());
        outTransaction.setCategory(category);
        outTransaction.setTransactionType(TransactionType.TRANSFER);
        outTransaction.setDescription(transferDTO.getDescription());

        Transaction inTransaction = new Transaction();
        inTransaction.setAccount(destAccount);
        inTransaction.setUser(destAccount.getUser());
        inTransaction.setDollarAmount(transferDTO.getAmount());
        inTransaction.setCategory(destCategory);
        inTransaction.setTransactionType(TransactionType.TRANSFER);
        inTransaction.setDescription(transferDTO.getDescription());

        transactionRepo.save(outTransaction);
        transactionRepo.save(inTransaction);

        accountRepo.save(sourceAccount);
        accountRepo.save(destAccount);

        return outTransaction;
    }
}
