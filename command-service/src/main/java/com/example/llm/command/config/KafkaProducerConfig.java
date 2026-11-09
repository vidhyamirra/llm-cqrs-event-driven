package com.example.llm.command.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfig {

    public static final String PROMPT_REQUESTS_TOPIC = "llm-prompt-requests";

    @Bean
    public NewTopic promptRequestsTopic() {
        return TopicBuilder.name(PROMPT_REQUESTS_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
