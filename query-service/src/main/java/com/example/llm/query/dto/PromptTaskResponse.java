package com.example.llm.query.dto;

import java.time.Instant;
import java.util.UUID;

public record PromptTaskResponse(
        UUID taskId,
        String userId,
        String response,
        String status,
        String error,
        Instant completedAt
) {}
