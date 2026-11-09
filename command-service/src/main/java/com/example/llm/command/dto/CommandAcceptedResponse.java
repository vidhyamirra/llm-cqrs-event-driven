package com.example.llm.command.dto;

import java.util.UUID;

public record CommandAcceptedResponse(
        UUID taskId,
        String status
) {}
