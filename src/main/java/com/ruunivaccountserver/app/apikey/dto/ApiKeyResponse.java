package com.ruunivaccountserver.app.apikey.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ApiKeyResponse {

    @AllArgsConstructor
    @Builder
    @Getter
    public static class ApiKeysResponse{
        private List<String> apiKeys;
    }

}
