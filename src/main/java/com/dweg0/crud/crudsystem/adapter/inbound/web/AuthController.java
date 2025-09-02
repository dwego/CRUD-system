package com.dweg0.crud.crudsystem.adapter.inbound.web;

import com.dweg0.crud.crudsystem.adapter.dto.*;
import com.dweg0.crud.crudsystem.adapter.jwt.JwtServiceImpl;
import com.dweg0.crud.crudsystem.core.domain.PasswordHasher;
import com.dweg0.crud.crudsystem.core.domain.RegisterModel;
import com.dweg0.crud.crudsystem.core.domain.User;
import com.dweg0.crud.crudsystem.core.usecase.AuthenticateUseCase;
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
    private final AuthenticateUseCase authService;

    public AuthController(JwtServiceImpl jwtServiceImpl, UserUseCase userService, PasswordHasher passwordHasher, AuthenticateUseCase authService) {
        this.jwtServiceImpl = jwtServiceImpl;
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequest loginRequest) {
        TokenResponseDTO jwtToken = authService.execute(
                loginRequest.email(),
                loginRequest.password(),
                jwtServiceImpl.getTokenExpirationHours(),
                jwtServiceImpl.getRefreshTokenExpirationHours()
        );
        if (jwtToken.token() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(jwtToken);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterModel registerRequest) {
        try {
            User newUser = userService.createUser(registerRequest);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authentication at start of request: " + auth);
            return ResponseEntity.status(HttpStatus.CREATED).body(RegisterResponse.fromDomain(newUser));

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<RegisterResponse> getUser(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String userId = jwtServiceImpl.getIdFromJwtToken(token);
            Optional<User> user = userService.getUser(userId);
            return ResponseEntity.ok(user.map(RegisterResponse::fromDomain).orElse(null));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<TokenResponseDTO> authRefreshToken(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring(7);
            TokenResponseDTO response = authService.getRefreshToken(refreshToken, jwtServiceImpl.getTokenExpirationHours());
            if (response != null) {
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}