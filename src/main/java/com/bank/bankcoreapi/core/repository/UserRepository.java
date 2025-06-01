package com.bank.bankcoreapi.core.repository;

import com.bank.bankcoreapi.core.model.Role;
import com.bank.bankcoreapi.core.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (id, email, password, role) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, user.getId(), user.getEmail(), user.getPassword(), user.getRole().name());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbc.query(sql, rowMapper, email).stream().findFirst();
    }

    private final RowMapper<User> rowMapper = (rs, rowNum) -> new User(
        UUID.fromString(rs.getString("id")),
        rs.getString("email"),
        rs.getString("password"),
        Role.valueOf(rs.getString("role"))
    );
}