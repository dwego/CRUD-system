package com.dweg0.crud.crudsystem.core.domain;

public class Password {
    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password fromHashed(String hashed) {
        if (hashed == null || hashed.isBlank()) {
            throw new IllegalArgumentException("Hashed password must not be null or blank.");
        }
        return new Password(hashed);
    }

    public static Password fromPlainText(String plainText, PasswordHasher hasher) {
        if (plainText == null || plainText.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        return new Password(hasher.hash(plainText));
    }

    public String value() {
        return value;
    }

    public boolean matches(String plainText, PasswordHasher hasher) {
        return hasher.matches(plainText, value);
    }
}
