package com.example.llm.command.service;

import com.example.llm.command.config.KafkaProducerConfig;
import com.example.llm.command.entity.PromptTaskEntity;
import com.example.llm.command.repository.PromptTaskRepository;
import com.example.llm.event.PromptSubmittedEvent;
import com.example.llm.event.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromptCommandService {

    private final PromptTaskRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public UUID handle(String userId, String prompt, String model) {
        UUID taskId = UUID.randomUUID();

        PromptTaskEntity task = PromptTaskEntity.builder()
                .id(taskId)
                .userId(userId)
                .prompt(prompt)
                .status(TaskStatus.PENDING)
                .createdAt(Instant.now())
                .build();
        repository.save(task);

        PromptSubmittedEvent event = new PromptSubmittedEvent(
                taskId, userId, prompt, model, Instant.now()
        );
        kafkaTemplate.send(KafkaProducerConfig.PROMPT_REQUESTS_TOPIC, taskId.toString(), event);

        return taskId;
    }
}
