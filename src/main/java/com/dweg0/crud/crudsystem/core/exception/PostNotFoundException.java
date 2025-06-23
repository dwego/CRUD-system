package com.dweg0.crud.crudsystem.core.exception;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {

    private static final String ERROR_POST_ID_NOT_FOUND = "Post with the specified ID not found";

    public PostNotFoundException() {
        super(ERROR_POST_ID_NOT_FOUND);
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(UUID postId) {
        super("Post not found for ID " + postId);
    }
}
