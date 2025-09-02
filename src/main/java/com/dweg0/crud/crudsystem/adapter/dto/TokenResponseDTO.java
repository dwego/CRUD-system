package com.dweg0.crud.crudsystem.adapter.dto;

import lombok.Builder;

@Builder
public record TokenResponseDTO(
        String token,
        String refreshToken
) {
}
