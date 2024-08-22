package com.ruunivaccountserver.app.apikey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ApiKeyEvent {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ApiKeyCreateEvent{
        private Long userId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ApiKeyDeleteEvent{
        private Long userId;
        private String apiKey;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ApiKeyCreateRollbackEvent{
        private Long userId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ApiKeyDeleteRollbackEvent{
        private Long userId;
    }
}
