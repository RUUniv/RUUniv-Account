package com.ruunivaccountserver.app.apikey.dto;

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
        private Long userId;
    }

    @AllArgsConstructor
    @Getter
    public static class ApiKeyDeleteRequest{
        private Long userId;
        private String apiKey;
    }
}
