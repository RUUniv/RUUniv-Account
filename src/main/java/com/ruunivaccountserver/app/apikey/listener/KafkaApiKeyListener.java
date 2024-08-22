package com.ruunivaccountserver.app.apikey.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyEvent.ApiKeyCreateRollbackEvent;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyEvent.ApiKeyDeleteRollbackEvent;
import com.ruunivaccountserver.app.user.service.UserService;
import com.ruunivaccountserver.infrastructure.kafka.KafkaTopic.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaApiKeyListener {
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @KafkaListener(topics = Topic.ROLLBACK_CREATE_API_KEY)
    public void rollbackCreateApiKeyHandler(String payload) throws JsonProcessingException {
        log.info("Create Api Key Rollback Event Start");
        ApiKeyCreateRollbackEvent data = objectMapper.readValue(payload,
                ApiKeyCreateRollbackEvent.class);

        userService.deleteApiKey(data.getUserId());
    }

    @KafkaListener(topics = Topic.ROLLBACK_DELETE_API_KEY)
    public void rollbackDeleteApiKeyHandler(String payload) throws JsonProcessingException {
        log.info("Delete Api Key Rollback Event Start");
        ApiKeyDeleteRollbackEvent data = objectMapper.readValue(payload,
                ApiKeyDeleteRollbackEvent.class);

        userService.addApiKey(data.getUserId());
    }
}
