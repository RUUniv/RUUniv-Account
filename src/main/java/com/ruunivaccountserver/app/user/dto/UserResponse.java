package com.ruunivaccountserver.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

public class UserResponse {

    @AllArgsConstructor
    @Builder
    public static class UserInfo{
        private Long id;
        private String email;
    }
}
