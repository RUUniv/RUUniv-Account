package com.ruunivaccountserver.app.apikey.service;

import com.ruunivaccountserver.app.apikey.dto.ApiKeyEvent.ApiKeyDeleteEvent;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse.ApiKeyInfo;
import com.ruunivaccountserver.app.user.entity.User;
import com.ruunivaccountserver.app.user.service.UserService;
import com.ruunivaccountserver.infrastructure.feign.VerificationServerApi.VerificationServerClient;
import com.ruunivaccountserver.infrastructure.feign.VerificationServerApiKeysResponse;
import com.ruunivaccountserver.infrastructure.kafka.KafkaTopic;
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

    public void createApiKey(Long userId){
        userService.checkApiKeyCountMax(userId);

        kafkaTemplate.send(KafkaTopic.CREATE_API_KEY.toString(),userId.toString());
    }

    public void deleteApiKey(Long userId , String apiKey){
        ApiKeyDeleteEvent event = ApiKeyDeleteEvent.builder()
                    .userId(userId.toString())
                    .apiKey(apiKey)
                    .build();

        userService.deleteApiKey(userId);
        kafkaTemplate.send(KafkaTopic.DELETE_API_KEY.toString(),event);
    }

    public List<ApiKeyInfo> getApiKeysInfo(Long userId){
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
