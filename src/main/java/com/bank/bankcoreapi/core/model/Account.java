// model/Account.java
package com.bank.bankcoreapi.core.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID id;
    private String ownerName;
    private BigDecimal balance;

    public Account(UUID id, String ownerName, BigDecimal balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}
