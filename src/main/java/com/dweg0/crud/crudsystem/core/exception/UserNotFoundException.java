package com.dweg0.crud.crudsystem.core.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(UUID userId) {
        super("User not found for ID " + userId);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
