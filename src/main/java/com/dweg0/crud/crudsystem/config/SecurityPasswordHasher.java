package com.dweg0.crud.crudsystem.core.adapter;

import com.dweg0.crud.crudsystem.core.domain.PasswordHasher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityPasswordHasher implements PasswordHasher {

    private final PasswordEncoder passwordEncoder;

    public SecurityPasswordHasher() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hash(String plainText) {
        return passwordEncoder.encode(plainText);
    }

    @Override
    public boolean matches(String plainText, String hashed) {
        return passwordEncoder.matches(plainText, hashed);
    }
}
