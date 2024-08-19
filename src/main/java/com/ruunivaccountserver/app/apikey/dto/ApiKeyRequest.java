package com.ruunivaccountserver.app.apikey.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

public class ApiKeyRequest {

    @AllArgsConstructor
    @Getter
    @Jacksonized
    @Builder
    public static class ApiKeyCreateRequest{
        @Schema(description = "유저 아이디", example = "1")
        private Long userId;
    }

    @AllArgsConstructor
    @Getter
    public static class ApiKeyDeleteRequest{
        @Schema(description = "유저 아이디", example = "1")
        private Long userId;
        @Schema(description = "Api Key", example = "test-test-tset-test")
        private String apiKey;
    }
}
