package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
}
