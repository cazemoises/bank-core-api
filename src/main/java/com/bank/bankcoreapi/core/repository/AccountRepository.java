package com.bank.bankcoreapi.core.repository;

import com.bank.bankcoreapi.core.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void create(Account account) {
        String sql = "INSERT INTO accounts (id, owner_name, balance) VALUES (?, ?, ?)";
        jdbc.update(sql, account.getId(), account.getOwnerName(), account.getBalance());
    }

    public Account findById(UUID id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        return jdbc.queryForObject(sql, rowMapper, id);
    }

    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        return jdbc.query(sql, rowMapper);
    }

    private final RowMapper<Account> rowMapper = (rs, rowNum) -> new Account(
        UUID.fromString(rs.getString("id")),
        rs.getString("owner_name"),
        rs.getBigDecimal("balance")
    );
}
