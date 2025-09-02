package com.dweg0.crud.crudsystem.config;

import com.dweg0.crud.crudsystem.core.domain.PasswordHasher;
import com.dweg0.crud.crudsystem.core.usecase.AuthenticateUseCase;
import com.dweg0.crud.crudsystem.core.usecase.JwtService;
import com.dweg0.crud.crudsystem.core.usecase.UserRepository;
import com.dweg0.crud.crudsystem.core.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UserUseCase userUseCase(UserRepository userRepository, PasswordHasher passwordHasher) {
        return new UserUseCase(userRepository, passwordHasher);
    }

    @Bean
    public AuthenticateUseCase authenticateUseCase(UserRepository userRepository, PasswordHasher passwordHasher, JwtService jwtService) {
        return new AuthenticateUseCase(userRepository, passwordHasher, jwtService);
    }
}
