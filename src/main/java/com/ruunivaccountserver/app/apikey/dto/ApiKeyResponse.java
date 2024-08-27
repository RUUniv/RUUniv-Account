package com.ruunivaccountserver.app.apikey.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ApiKeyResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class ApiKeysInfo implements Serializable {
        private List<ApiKeyInfo> apiKeysInfo;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class ApiKeyInfo implements Serializable {
        @Schema(description = "Api Key", example = "test-test-tset-test")
        private String apiKey;

        @Schema(description = "Api Key Id", example = "1")
        private Long apiKeyId;
    }

}
