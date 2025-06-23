package com.dweg0.crud.crudsystem.adapter.inbound.web;

import com.dweg0.crud.crudsystem.core.domain.model.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String name,
        String email,
        String avatarUrl,
        String bio
) {
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail().value(),
                user.getAvatarUrl(),
                user.getBio()
        );
    }
}
