package com.ruunivaccountserver.app.apikey.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyEvent.ApiKeyDeleteEvent;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse.ApiKeysResponse;
import com.ruunivaccountserver.infrastructure.feign.VerificationServerClient.StudentVerifyApiClient;
import com.ruunivaccountserver.infrastructure.kafka.KafkaTopic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiKeyService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final StudentVerifyApiClient studentVerifyApiClient;

    public void createApiKey(Long userId){
        kafkaTemplate.send(KafkaTopic.CREATE_API_KEY.toString(),userId.toString());
    }

    public void deleteApiKey(Long userId , String apiKey){

        ApiKeyDeleteEvent event = ApiKeyDeleteEvent.builder()
                    .userId(userId.toString())
                    .apiKey(apiKey)
                    .build();

        kafkaTemplate.send(KafkaTopic.DELETE_API_KEY.toString(),event);
    }

    public ApiKeysResponse getApiKeys(Long userId){
        return ApiKeysResponse.builder()
                .apiKeys(studentVerifyApiClient.getApiKeys(userId))
                .build();
    }
}
