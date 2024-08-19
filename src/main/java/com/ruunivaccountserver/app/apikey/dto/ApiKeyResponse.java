package com.ruunivaccountserver.app.apikey.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ApiKeyResponse {

    @AllArgsConstructor
    @Builder
    @Getter
    public static class ApiKeysResponse{
        @Schema(description = "Api Key 목록", example = "[test-test-tset-test, ..]")
        private List<String> apiKeys;
    }

}
