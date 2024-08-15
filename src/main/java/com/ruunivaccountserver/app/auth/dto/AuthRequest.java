package com.ruunivaccountserver.app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthRequest {

    @AllArgsConstructor
    @Getter
    public static class SignUpRequest{
        private String email;
        private String password;
    }

    @AllArgsConstructor
    @Getter
    public static class LoginRequest{
        private String email;
        private String password;
    }
}
