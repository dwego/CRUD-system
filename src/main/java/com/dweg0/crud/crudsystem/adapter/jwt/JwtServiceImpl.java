package com.dweg0.crud.crudsystem.adapter.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.dweg0.crud.crudsystem.core.domain.User;
import com.dweg0.crud.crudsystem.core.usecase.JwtService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${auth.jwt.token.secret}")
    private String secret;

    @Value("${auth.jwt.token.expiration}")
    private Integer TokenExpirationHours;

    @Value("${auth.jwt.refresh-token.expiration}")
    private Integer refreshTokenExpirationHours;

    public String createJwtToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(String.valueOf(user.getId()))
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao criar o token JWT", e);
        }
    }

    public String getIdFromJwtToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Estamos no GMT -3:00
    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(TokenExpirationHours)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}