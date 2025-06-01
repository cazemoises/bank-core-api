package com.bank.bankcoreapi.core.service;

import com.bank.bankcoreapi.core.dto.AccountDTO;
import com.bank.bankcoreapi.core.model.Account;
import com.bank.bankcoreapi.core.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void create(AccountDTO dto) {
        Account account = new Account(
            UUID.randomUUID(),
            dto.getOwnerName(),
            dto.getBalance() != null ? dto.getBalance() : BigDecimal.ZERO
        );
        repository.create(account);
    }

    public List<AccountDTO> getAll() {
        return repository.findAll().stream().map(account ->
            new AccountDTO(account.getId(), account.getOwnerName(), account.getBalance())
        ).collect(Collectors.toList());
    }

    public AccountDTO getById(UUID id) {
        Account acc = repository.findById(id);
        return new AccountDTO(acc.getId(), acc.getOwnerName(), acc.getBalance());
    }
}
