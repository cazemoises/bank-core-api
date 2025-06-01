package com.bank.bankcoreapi.core.controller;

import com.bank.bankcoreapi.core.config.JwtUtil;
import com.bank.bankcoreapi.core.dto.AuthRequest;
import com.bank.bankcoreapi.core.dto.AuthResponse;
import com.bank.bankcoreapi.core.model.User;
import com.bank.bankcoreapi.core.service.UserService;
import com.bank.bankcoreapi.core.service.PasswordService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.bankcoreapi.core.model.Role;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordService passwordService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, PasswordService passwordService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordService = passwordService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        userService.register(request.getEmail(), request.getPassword(), Role.USER);
        return ResponseEntity.ok("Usuário registrado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return userService.findByEmail(request.getEmail())
            .filter(user -> passwordService.matches(request.getPassword(), user.getPassword()))
            .map(user -> {
                String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());
                return ResponseEntity.ok(new AuthResponse(token));
            })
            .orElse(ResponseEntity.status(401).build());
    }
}
