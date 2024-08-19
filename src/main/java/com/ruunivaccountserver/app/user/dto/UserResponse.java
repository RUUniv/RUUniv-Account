package com.ruunivaccountserver.app.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;

public class UserResponse {

    @AllArgsConstructor
    @Builder
    public static class UserInfo{
        @Schema(description = "유저 아이디", example = "1")
        private Long id;
        @Schema(description = "Email", example = "account@gmail.com")
        private String email;
    }
}
