package com.dweg0.crud.crudsystem.core.domain;

import lombok.Getter;

@Getter
public class Password {
    private final String hashed;

    public Password(String plainText, PasswordHasher hasher) {
        if (plainText == null || plainText.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        this.hashed = plainText;
    }

    public boolean matches(String plainText, PasswordHasher hasher) {
        return hasher.matches(plainText, this.hashed);
    }
}
