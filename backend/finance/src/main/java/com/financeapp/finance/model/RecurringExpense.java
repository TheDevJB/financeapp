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
@Table(name = "recurring_expense")
@Data
public class RecurringExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurring_expense_id")
    private Long recurringExpenseId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "expense_name")
    private String expenseName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "due_day")
    private Integer dueDay;

    @Column(name = "payment_account")
    private String paymentAccount;

    @Column(name = "is_auto_pay")
    private Boolean isAutoPay;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "notification_day_before")
    private Integer notificationDayBefore;

    @Column(name = "created_at")
    private Instant createdAt;
}
