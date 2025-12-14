package com.financeapp.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeapp.finance.model.BudgetCategory;
import com.financeapp.finance.model.User;

public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
    List<BudgetCategory> findAllByUser(User user);

    Optional<BudgetCategory> findByUserAndCategoryName(User user, String categoryName);
}
