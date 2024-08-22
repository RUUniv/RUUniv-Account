package com.ruunivaccountserver.app.apikey.service;

import com.ruunivaccountserver.app.apikey.dto.ApiKeyEvent.ApiKeyCreateEvent;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyEvent.ApiKeyDeleteEvent;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse.ApiKeyInfo;
import com.ruunivaccountserver.app.user.service.UserService;
import com.ruunivaccountserver.infrastructure.feign.VerificationServerApi.VerificationServerClient;
import com.ruunivaccountserver.infrastructure.feign.VerificationServerApiKeysResponse;
import com.ruunivaccountserver.infrastructure.kafka.KafkaTopic.Topic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiKeyService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final VerificationServerClient verificationServerClient;
    private final UserService userService;

    public void createApiKey(Long userId) {
        userService.checkApiKeyCountMax(userId);
        ApiKeyCreateEvent event = ApiKeyCreateEvent.builder()
                .userId(userId)
                .build();

        kafkaTemplate.send(Topic.CREATE_API_KEY, event);
    }

    public void deleteApiKey(Long userId, String apiKey) {
        userService.deleteApiKey(userId);
        ApiKeyDeleteEvent event = ApiKeyDeleteEvent.builder()
                .userId(userId)
                .apiKey(apiKey)
                .build();

        kafkaTemplate.send(Topic.DELETE_API_KEY, event);
    }

    public List<ApiKeyInfo> getApiKeysInfo(Long userId) {
        List<VerificationServerApiKeysResponse> apiKeys =
                verificationServerClient.getApiKeys(userId);

        return apiKeys.stream()
                .map(apiKey -> ApiKeyInfo.builder()
                        .apiKeyId(apiKey.getApiKeyId())
                        .apiKey(apiKey.getApiKey())
                        .build())
                .toList();
    }
}
