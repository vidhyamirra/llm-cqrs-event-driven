package com.example.llm.event;

import java.time.Instant;
import java.util.UUID;

public record PromptSubmittedEvent(
        UUID taskId,
        String userId,
        String prompt,
        String model,
        Instant timestamp
) {}
