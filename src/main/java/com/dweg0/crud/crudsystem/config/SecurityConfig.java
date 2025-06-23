package com.dweg0.crud.crudsystem.config;

import com.dweg0.crud.crudsystem.adapter.jwt.JwtFilter;
import com.dweg0.crud.crudsystem.adapter.jwt.JwtServiceImpl;
import com.dweg0.crud.crudsystem.core.adapter.SecurityPasswordHasher;
import com.dweg0.crud.crudsystem.core.domain.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtServiceImpl jwtServiceImpl) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(jwtServiceImpl);

        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/public/**",
                                "/auth/login",
                                "/auth/register",
                                "/auth/forgot-password"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordHasher passwordHasher() {
        return new SecurityPasswordHasher();
    }
}