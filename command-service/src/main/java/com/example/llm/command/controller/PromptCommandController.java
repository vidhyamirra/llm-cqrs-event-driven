package com.example.llm.command.controller;

import com.example.llm.command.dto.CommandAcceptedResponse;
import com.example.llm.command.dto.SubmitPromptRequest;
import com.example.llm.command.service.PromptCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/commands/prompts")
@RequiredArgsConstructor
public class PromptCommandController {

    private final PromptCommandService commandService;

    @PostMapping
    public ResponseEntity<CommandAcceptedResponse> submitPrompt(@RequestBody SubmitPromptRequest request) {
        UUID taskId = commandService.handle(
                request.userId(),
                request.prompt(),
                request.model() != null ? request.model() : "gpt-4o"
        );
        return ResponseEntity.accepted().body(new CommandAcceptedResponse(taskId, "QUEUED"));
    }
}
