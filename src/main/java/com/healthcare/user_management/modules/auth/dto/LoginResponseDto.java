package com.healthcare.user_management.modules.auth.dto;


public record LoginResponseDto(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresIn
) {}
