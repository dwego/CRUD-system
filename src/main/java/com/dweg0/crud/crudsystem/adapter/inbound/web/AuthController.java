package com.dweg0.crud.crudsystem.adapter.inbound.web;

import com.dweg0.crud.crudsystem.adapter.jwt.JwtServiceImpl;
import com.dweg0.crud.crudsystem.core.domain.PasswordHasher;
import com.dweg0.crud.crudsystem.core.domain.RegisterModel;
import com.dweg0.crud.crudsystem.core.domain.User;
import com.dweg0.crud.crudsystem.core.usecase.UserUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtServiceImpl jwtServiceImpl;
    private final UserUseCase userService;

    public AuthController(JwtServiceImpl jwtServiceImpl, UserUseCase userService, PasswordHasher passwordHasher) {
        this.jwtServiceImpl = jwtServiceImpl;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        String jwtToken = userService.authenticate(loginRequest.email(), loginRequest.password());
        if (jwtToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterModel registerRequest) {
        try {
            User newUser = userService.createUser(registerRequest);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authentication at start of request: " + auth);
            return ResponseEntity.status(HttpStatus.CREATED).body(RegisterResponse.fromDomain(newUser));

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email ou username já cadastrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String userId = jwtServiceImpl.getIdFromJwtToken(token);
            Optional<User> user = userService.getUser(userId);
            return ResponseEntity.ok(user.map(RegisterResponse::fromDomain).orElse(null));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error, nao forneceu o token");
    }

}