package com.ruunivaccountserver.app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AuthResponse {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class TokenResponse{
        private String accessToken;
        private String refreshToken;
    }
}
