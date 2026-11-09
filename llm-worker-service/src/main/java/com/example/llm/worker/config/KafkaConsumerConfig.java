package com.example.llm.worker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConsumerConfig {

    public static final String LLM_COMPLETIONS_TOPIC = "llm-completions";

    @Bean
    public NewTopic llmCompletionsTopic() {
        return TopicBuilder.name(LLM_COMPLETIONS_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
