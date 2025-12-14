package com.financeapp.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeapp.finance.model.RecurringExpense;
import com.financeapp.finance.model.User;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, Long> {
    Optional<RecurringExpense> findByRecurringExpenseId(Long recurringExpenseId);

    List<RecurringExpense> findAllByUser(User user);

    List<RecurringExpense> findAllByUserAndIsActiveTrue(User user);

    List<RecurringExpense> findByDueDayBetween(int startDay, int endDay);
}
