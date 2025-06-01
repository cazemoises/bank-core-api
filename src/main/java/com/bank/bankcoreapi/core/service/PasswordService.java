package com.bank.bankcoreapi.core.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final PasswordEncoder encoder;

    public PasswordService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encode(String raw) {
        return encoder.encode(raw);
    }

    public boolean matches(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }
}
