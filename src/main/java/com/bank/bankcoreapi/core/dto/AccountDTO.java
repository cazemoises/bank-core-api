package com.bank.bankcoreapi.core.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountDTO {
    private UUID id;
    private String ownerName;
    private BigDecimal balance;

    public AccountDTO() {}

    public AccountDTO(UUID id, String ownerName, BigDecimal balance) {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
