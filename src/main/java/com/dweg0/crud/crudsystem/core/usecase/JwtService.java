package com.dweg0.crud.crudsystem.core.usecase;

import com.dweg0.crud.crudsystem.core.domain.User;

public interface JwtService {
    String createJwtToken(User user, Integer expirationInHours);
    String getIdFromJwtToken(String token);
    boolean isTokenValid(String token);
}