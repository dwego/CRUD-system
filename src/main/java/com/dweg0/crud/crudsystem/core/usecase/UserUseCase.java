package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.core.domain.*;
import com.dweg0.crud.crudsystem.core.exception.UserNotFoundException;

import java.util.Optional;
import java.util.UUID;

public class UserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;

    public UserUseCase(UserRepository userRepository, PasswordHasher passwordHasher, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.jwtService = jwtService;
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

    public String authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        User authenticatedUser = user.get();
        if (!authenticatedUser.getPassword().matches(password, passwordHasher)) {
            return null;
        }

        return jwtService.createJwtToken(authenticatedUser);
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(UUID.fromString(id));
    }
}
