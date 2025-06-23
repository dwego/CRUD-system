package com.dweg0.crud.crudsystem.core.domain;

public class Email {

    private static final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private final String value;

    public Email(String value) {
        if (!isValidEmail(value)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.matches(emailRegex);
    }
}
