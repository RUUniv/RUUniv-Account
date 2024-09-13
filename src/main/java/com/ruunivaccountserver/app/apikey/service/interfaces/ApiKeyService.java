package com.ruunivaccountserver.app.apikey.service.interfaces;

import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse.ApiKeysInfo;

public interface ApiKeyService {
    void createApiKey(Long userId);

    void deleteApiKey(Long userId, String apiKey);

    ApiKeysInfo getApiKeysInfo(Long userId);
}
