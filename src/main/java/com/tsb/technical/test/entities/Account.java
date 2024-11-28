package com.tsb.technical.test.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountHolderId;

    private Long accountNumber;
    private Long balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(Long accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Account() {}

    public Account(Long id, Long accountHolderId, Long accountNumber, Long balance) {
        this.id = id;
        this.accountHolderId = accountHolderId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
