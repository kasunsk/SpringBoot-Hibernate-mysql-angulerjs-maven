package com.crossover.techtrial.java.se.model.account;


import javax.persistence.*;

/**
 * Created by kasun on 2/4/17.
 */
@Entity
@Table(name="ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long accountId;

    @Column(name = "USER_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CURRENCY", nullable = false)
    private Currency currency;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
