package com.bank.bankcoreapi.core.service;

import com.bank.bankcoreapi.core.model.User;
import java.util.Optional;

public interface IUserService {
    void register(User user);
    Optional<User> authenticate(String email, String password);
}