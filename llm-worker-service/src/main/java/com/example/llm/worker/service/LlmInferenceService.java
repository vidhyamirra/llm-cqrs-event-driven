package com.example.llm.worker.service;

import com.example.llm.event.LlmInferenceCompletedEvent;
import com.example.llm.event.LlmInferenceFailedEvent;
import com.example.llm.event.PromptSubmittedEvent;
import com.example.llm.worker.config.KafkaConsumerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class LlmInferenceService {

    private final ChatClient chatClient;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void processPrompt(PromptSubmittedEvent event) {
        log.info("Processing LLM task: {}", event.taskId());

        try {
            String response = chatClient.prompt()
                    .user(event.prompt())
                    .call()
                    .content();

            LlmInferenceCompletedEvent completion = new LlmInferenceCompletedEvent(
                    event.taskId(),
                    event.userId(),
                    response,
                    0,
                    0,
                    Instant.now()
            );

            kafkaTemplate.send(KafkaConsumerConfig.LLM_COMPLETIONS_TOPIC, event.taskId().toString(), completion);
            log.info("Successfully completed LLM task: {}", event.taskId());

        } catch (Exception ex) {
            log.error("Failed to generate LLM response for task: {}", event.taskId(), ex);

            LlmInferenceFailedEvent failure = new LlmInferenceFailedEvent(
                    event.taskId(),
                    event.userId(),
                    ex.getMessage(),
                    Instant.now()
            );
            kafkaTemplate.send(KafkaConsumerConfig.LLM_COMPLETIONS_TOPIC, event.taskId().toString(), failure);
        }
    }
}
