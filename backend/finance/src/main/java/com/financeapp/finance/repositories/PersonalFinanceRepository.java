package com.financeapp.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.PersonalFinance;
import com.financeapp.finance.model.User;

@Repository
public interface PersonalFinanceRepository extends JpaRepository<PersonalFinance, Long>{

    List<PersonalFinance> findByUserUserId(Long userId);
    Optional<PersonalFinance> findByUser(User user);
    Optional<PersonalFinance> findByIdAndUserUserId(Long id, Long userId);
}
