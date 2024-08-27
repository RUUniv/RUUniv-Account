package com.ruunivaccountserver.infrastructure.cache;

import lombok.Getter;

@Getter
public enum CacheType {

    API_KEY("API_KEY", 3600, 10000);

    private final String name;
    private final int expireAfterWrite;
    private final int maximumSize;

    CacheType(String name, int expireAfterWrite, int maximumSize) {
        this.name = name;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = maximumSize;
    }

    public static class CacheValue {
        public static final String API_KEY = "API_KEY";
    }
}