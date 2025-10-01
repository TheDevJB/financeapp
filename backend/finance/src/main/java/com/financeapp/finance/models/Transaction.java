package com.financeapp.finance.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String transactionId;

    @Id
    @ManyToOne
    @Column
    private Long userId;

    @Column(name = "dollar_amount")
    private BigDecimal dollarAmount;

    @Column(name = "description")
    private String description;

    private LocalDateTime timestamp;
    private String sourceAccountId;
    private String transactionType;

}