package com.dweg0.crud.crudsystem.core.domain;

import lombok.Getter;

@Getter
public class Email {
    private static final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String email;

    public Email(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public static boolean isValidEmail(String email) {
        return email.isBlank() && email.matches(emailRegex);
    }

}