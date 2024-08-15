package com.ruunivaccountserver.app.apikey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ApiKeyEvent {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ApiKeyDeleteEvent{
        private String userId;
        private String apiKey;
    }
}
