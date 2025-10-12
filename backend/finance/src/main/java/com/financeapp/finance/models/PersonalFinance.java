package com.financeapp.finance.models;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "personal_finance")
@Data
public class PersonalFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Column(name = "monthly_income")
    private BigDecimal monthlyIncome;

    @Column(name = "savings_goal")
    private BigDecimal savingsGoal;

    @Column(name = "current_savings")
    private BigDecimal currentSavings;

    @Column(name = "food_budget")
    private BigDecimal foodBudget;

    @Column(name = "entertainment_budget")
    private BigDecimal entertainmentBudget;

    @Column(name = "utilities_budget")
    private BigDecimal utilitiesBudget;

    @Column(name = "gas_budget")
    private BigDecimal gasBudget;

    @Column(name = "emergency_fund_budget")
    private Integer emergencyFundBudget;

    public BigDecimal getMonthlyIncome(){
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome){
        this.monthlyIncome = monthlyIncome;
    }

    public BigDecimal getCurrentSavings(){
        return currentSavings;
    }

    public void setCurrentSavings(BigDecimal currentSavings){
        this.currentSavings = currentSavings;
    }

    public BigDecimal getSavingsGoal(){
        return savingsGoal;
    }

    public void setSavingsGoal(BigDecimal savingsGoal){
        this.savingsGoal = savingsGoal;
    }

    public BigDecimal getFoodBudget(){
        return foodBudget;
    }

    public void setFoodBudget(BigDecimal foodBudget){
        this.foodBudget = foodBudget;
    }

    public BigDecimal getEntertainmentBudget(){
        return entertainmentBudget;
    }  

    public void setEntertainmentBudget(BigDecimal entertainmentBudget){
        this.entertainmentBudget = entertainmentBudget;
    }

    public BigDecimal getUtilitiesBudget(){
        return utilitiesBudget;
    }

    public void setUtilitiesBudget(BigDecimal utilitiesBudget){
        this.utilitiesBudget = utilitiesBudget;
    }

    public BigDecimal getGasBudget(){
        return gasBudget;
    } 

    public void setGasBudget(BigDecimal gasBudget){
        this.gasBudget = gasBudget;
    }

    public Integer getEmergencyFundBudget(){
        return emergencyFundBudget;
    }

    public void setEmergencyFundBudget(Integer emergencyFundBudget){
        this.emergencyFundBudget = emergencyFundBudget;
    }
}
