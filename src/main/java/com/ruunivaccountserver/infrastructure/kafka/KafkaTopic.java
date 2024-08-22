package com.ruunivaccountserver.infrastructure.kafka;

public enum KafkaTopic {
    //PRODUCER
    CREATE_API_KEY,
    DELETE_API_KEY,
    //CONSUMER
    ROLLBACK_DELETE_API_KEY,
    ROLLBACK_CREATE_API_KEY,
}
