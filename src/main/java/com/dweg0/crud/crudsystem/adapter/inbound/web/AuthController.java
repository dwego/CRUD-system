package com.dweg0.crud.crudsystem.adapter.inbound.web;

import com.dweg0.crud.crudsystem.core.domain.PasswordHasher;
import com.dweg0.crud.crudsystem.core.domain.model.Email;
import com.dweg0.crud.crudsystem.core.domain.model.Password;
import com.dweg0.crud.crudsystem.core.domain.model.User;
import com.dweg0.crud.crudsystem.core.usecase.UserRepository;
import com.dweg0.crud.crudsystem.core.usecase.UserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/public")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase, PasswordHasher passwordHasher) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .email(new Email(request.email()))
                .password(new Password(request.password()))
                .name(request.name())
                .username(request.username())
                .build();

        User createdUser = userUseCase.createUser(user);

        return ResponseEntity.ok(UserResponse.fromDomain(createdUser));
    }



    @GetMapping("/login")
    public void login() {

    }
}
