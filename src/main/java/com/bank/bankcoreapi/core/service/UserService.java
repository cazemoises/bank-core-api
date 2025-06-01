package com.bank.bankcoreapi.core.service;

import com.bank.bankcoreapi.core.model.Role;
import com.bank.bankcoreapi.core.model.User;
import com.bank.bankcoreapi.core.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final IUserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(IUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        return repository.findByEmail(email)
            .filter(u -> encoder.matches(password, u.getPassword()));
    }

    @Override
    public void register(User user) {
        user.setId(UUID.randomUUID());
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public void register(String email, String password, Role role) {
        User user = new User(UUID.randomUUID(), email, encoder.encode(password), role);
        repository.save(user);
    }
    
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
    
}
