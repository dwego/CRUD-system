package com.dweg0.crud.crudsystem.core.domain;

public record RegisterModel(
        String username,
        String name,
        String email,
        String password
) {

    public RegisterModel {
        if (username == null || username.isEmpty() || name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username, name, email and password must not be null or empty.");
        }
    }
}
