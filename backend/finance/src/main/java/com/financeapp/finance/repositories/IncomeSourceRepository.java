package com.financeapp.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeapp.finance.model.IncomeSource;
import com.financeapp.finance.model.User;

public interface IncomeSourceRepository extends JpaRepository<IncomeSource, Long> {
    Optional<IncomeSource> findByIncomeSourceId(Long incomeSourceId);

    List<IncomeSource> findAllByUser(User user);

    List<IncomeSource> findAllByUserAndIsActiveTrue(User user);
}
