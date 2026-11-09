package com.example.llm.event;

import java.time.Instant;
import java.util.UUID;

public record LlmInferenceCompletedEvent(
        UUID taskId,
        String userId,
        String responseText,
        int promptTokens,
        int completionTokens,
        Instant timestamp
) {}
