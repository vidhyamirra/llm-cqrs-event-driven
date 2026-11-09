package com.example.llm.command.dto;

public record SubmitPromptRequest(
        String userId,
        String prompt,
        String model
) {}
