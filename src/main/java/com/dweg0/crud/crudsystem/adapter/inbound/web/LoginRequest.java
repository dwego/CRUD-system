package com.dweg0.crud.crudsystem.adapter.inbound.web;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String email,
        @NotBlank String password
) {
}
