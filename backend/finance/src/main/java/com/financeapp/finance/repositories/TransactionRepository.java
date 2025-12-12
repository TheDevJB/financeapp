package com.financeapp.finance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionId(Long transactionId);

    Optional<Transaction> findByAccountId(Long accountId);

    Optional<Transaction> findByTransactionType(Long transactionType);

    Optional<Transaction> findByAccountIdOrTransactionId(Long accountId, Long transactionId);
}
