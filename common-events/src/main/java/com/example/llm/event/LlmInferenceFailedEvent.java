package com.example.llm.event;

import java.time.Instant;
import java.util.UUID;

public record LlmInferenceFailedEvent(
        UUID taskId,
        String userId,
        String errorMessage,
        Instant timestamp
) {}
