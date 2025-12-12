package com.financeapp.finance.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "minimum_payment")
    private BigDecimal minimumPayment;

    @Column(name = "due_day")
    private Integer dueDay;

    @Column(name = "is_debt_account")
    private Boolean isDebtAccount;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
