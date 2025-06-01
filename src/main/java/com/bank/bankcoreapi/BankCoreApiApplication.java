package com.bank.bankcoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class BankCoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankCoreApiApplication.class, args);
	}

}
