package com.dweg0.crud.crudsystem.core.domain;

public interface PasswordHasher {
    String hash(String plainText);
    boolean matches(String plainText, String hashed);
}
