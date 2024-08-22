package com.ruunivaccountserver.app.apikey.listener;

import static com.ruunivaccountserver.infrastructure.kafka.KafkaTopic.ROLLBACK_DELETE_API_KEY;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaApiKeyListener {
    @KafkaListener(topics = "ROLLBACK_CREATE_API_KEY")
    public void rollbackCreateApiKeyHandler(){

    }

    @KafkaListener(topics = "ROLLBACK_DELETE_API_KEY")
    public void rollbackDeleteApiKeyHandler(){

    }
}
