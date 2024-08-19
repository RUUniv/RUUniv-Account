package com.ruunivaccountserver.app.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthRequest {

    @AllArgsConstructor
    @Getter
    public static class SignUpRequest{
        @Schema(description = "Email", example = "account@gmail.com")
        @Email
        private String email;
        @Schema(description = "Password", example = "account112")
        private String password;
    }

    @AllArgsConstructor
    @Getter
    public static class LoginRequest{
        @Schema(description = "Email", example = "account@gmail.com")
        @Email
        private String email;
        @Schema(description = "Password", example = "account112")
        private String password;
    }
}
