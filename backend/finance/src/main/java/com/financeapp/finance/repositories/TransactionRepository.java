package com.financeapp.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByUserId(Long userId);
    
    List<Transaction> findByUserIdAndChecking(Long userId, String checking);
    List<Transaction> findByUserIdAndSavings(Long userId, String savings);

    Optional<Transaction> findByTransactionIdAndUserId(Long transactionId, Long userId);

}
