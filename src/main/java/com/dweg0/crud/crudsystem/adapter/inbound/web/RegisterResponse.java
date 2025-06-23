package com.dweg0.crud.crudsystem.adapter.inbound.web;

import com.dweg0.crud.crudsystem.core.domain.User;

import java.util.UUID;

public record RegisterResponse(
        UUID id,
        String username,
        String name,
        String email,
        String avatarUrl,
        String bio
) {
    public static RegisterResponse fromDomain(User user) {
        return new RegisterResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail().value(),
                user.getAvatarUrl(),
                user.getBio()
        );
    }
}
