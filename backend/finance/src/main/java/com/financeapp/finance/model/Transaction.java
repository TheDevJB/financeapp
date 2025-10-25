package com.financeapp.finance.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = true)
@Data
public class Transaction extends AccountEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "dollar_amount")
    private BigDecimal dollarAmount;

    @Column(name = "description")
    private String description;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "category")
    private String category;

    @CreationTimestamp
    @Column(name = "transaction_date")
    private Instant transactionDate;
}