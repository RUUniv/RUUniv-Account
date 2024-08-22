package com.ruunivaccountserver.infrastructure.kafka;

import lombok.Getter;

@Getter
public enum KafkaTopic {
    //PRODUCER
    CREATE_API_KEY("CREATE_API_KEY"),
    DELETE_API_KEY("DELETE_API_KEY"),
    //CONSUMER
    ROLLBACK_DELETE_API_KEY("ROLLBACK_DELETE_API_KEY"),
    ROLLBACK_CREATE_API_KEY("ROLLBACK_CREATE_API_KEY"),
    ;

    private final String topic;

    KafkaTopic(String topic) {
        this.topic = topic;
    }
    public static class Topic{
        public static final String CREATE_API_KEY="CREATE_API_KEY";
        public static final String DELETE_API_KEY="DELETE_API_KEY";
        public static final String ROLLBACK_DELETE_API_KEY="ROLLBACK_DELETE_API_KEY";
        public static final String ROLLBACK_CREATE_API_KEY="ROLLBACK_CREATE_API_KEY";
    }
}
