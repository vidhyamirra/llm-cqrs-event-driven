package com.example.llm.query.projector;

import com.example.llm.event.LlmInferenceCompletedEvent;
import com.example.llm.event.LlmInferenceFailedEvent;
import com.example.llm.query.entity.PromptReadView;
import com.example.llm.query.repository.PromptReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "llm-completions", groupId = "query-projectors")
public class PromptViewProjector {

    private final PromptReadRepository repository;

    @KafkaHandler
    public void handleCompletion(LlmInferenceCompletedEvent event) {
        log.info("Projecting completion for task: {}", event.taskId());
        PromptReadView view = PromptReadView.builder()
                .taskId(event.taskId())
                .userId(event.userId())
                .response(event.responseText())
                .status("COMPLETED")
                .completedAt(event.timestamp())
                .build();
        repository.save(view);
    }

    @KafkaHandler
    public void handleFailure(LlmInferenceFailedEvent event) {
        log.warn("Projecting failure for task: {}", event.taskId());
        PromptReadView view = PromptReadView.builder()
                .taskId(event.taskId())
                .userId(event.userId())
                .status("FAILED")
                .error(event.errorMessage())
                .completedAt(event.timestamp())
                .build();
        repository.save(view);
    }
}
