package com.example.llm.worker.listener;

import com.example.llm.event.PromptSubmittedEvent;
import com.example.llm.worker.service.LlmInferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PromptRequestListener {

    private final LlmInferenceService inferenceService;

    @KafkaListener(
            topics = "llm-prompt-requests",
            groupId = "llm-inference-workers",
            concurrency = "3"
    )
    public void onPromptSubmitted(PromptSubmittedEvent event) {
        log.info("Received prompt task: {}", event.taskId());
        inferenceService.processPrompt(event);
    }
}
