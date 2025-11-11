package com.financeapp.finance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    Optional<Transaction> findByTransactionId(Long transactionId);
    Optional<Transaction> findByAccountId(String accountId);
    Optional<Transaction> findByTransactionType(String transactionType);
    Optional<Transaction> findByAccountIdOrTransactionId(String accountId, Long transactionId);
}
