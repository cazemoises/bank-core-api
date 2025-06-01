package com.bank.bankcoreapi.core.repository;

import com.bank.bankcoreapi.core.model.User;
import java.util.Optional;

public interface IUserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
}