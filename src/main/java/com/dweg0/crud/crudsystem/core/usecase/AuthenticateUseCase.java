package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.adapter.dto.RequestRefreshDTO;
import com.dweg0.crud.crudsystem.adapter.dto.TokenResponseDTO;
import com.dweg0.crud.crudsystem.core.domain.PasswordHasher;
import com.dweg0.crud.crudsystem.core.domain.User;
import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuthenticateUseCase {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;
    
    public AuthenticateUseCase(
            UserRepository userRepository, 
            PasswordHasher passwordHasher, 
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.jwtService = jwtService;
    }

    public TokenResponseDTO execute(String email, String password, Integer tokenExpirationHours, Integer refreshTokenExpirationHours) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().matches(password, passwordHasher))
                .map(user -> TokenResponseDTO.builder()
                        .token(jwtService.createJwtToken(user, tokenExpirationHours))
                        .refreshToken(jwtService.createJwtToken(user, refreshTokenExpirationHours))
                        .build())
                .orElse(null);
    }

    public TokenResponseDTO getRefreshToken(String requestRefresh, Integer tokenExpirationHours) {
        String login = jwtService.getIdFromJwtToken(requestRefresh);
        Optional<User> user = userRepository.findById(UUID.fromString(login));

        if (user == null) {
            return null;
        }

        User authUser = user.get();

        var authentication= new UsernamePasswordAuthenticationToken(
                authUser.getId(),
                null,
                List.of()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return TokenResponseDTO.builder()
                .token(jwtService.createJwtToken(authUser, tokenExpirationHours))
                .refreshToken(requestRefresh)
                .build();
    }
}
