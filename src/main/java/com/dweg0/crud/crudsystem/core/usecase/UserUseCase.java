package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.adapter.dto.TokenResponseDTO;
import com.dweg0.crud.crudsystem.core.domain.*;
import com.dweg0.crud.crudsystem.core.exception.UserNotFoundException;

import java.util.Optional;
import java.util.UUID;

public class UserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UserUseCase(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public User createUser(RegisterModel registerModel) {
        Password hashedPassword = Password.fromPlainText(registerModel.password(), passwordHasher);

        User userWithHashedPassword = User.builder()
                .email(new Email(registerModel.email()))
                .password(hashedPassword)
                .name(registerModel.name())
                .username(registerModel.username())
                .build();

        userRepository.save(userWithHashedPassword);
        return userWithHashedPassword;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User modifiedUser) {
        if (modifiedUser == null || modifiedUser.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User currentUser = userRepository.findById(modifiedUser.getId())
                        .orElseThrow(() -> new UserNotFoundException(modifiedUser.getId()));
        currentUser.updateUser(modifiedUser);
        userRepository.save(currentUser);
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(UUID.fromString(id));
    }
}
