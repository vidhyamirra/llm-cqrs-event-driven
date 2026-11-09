package com.example.llm.query.controller;

import com.example.llm.query.dto.PromptTaskResponse;
import com.example.llm.query.entity.PromptReadView;
import com.example.llm.query.repository.PromptReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/queries/prompts")
@RequiredArgsConstructor
public class PromptQueryController {

    private final PromptReadRepository repository;

    @GetMapping("/{taskId}")
    public ResponseEntity<PromptTaskResponse> getPromptResult(@PathVariable UUID taskId) {
        return repository.findById(taskId)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private PromptTaskResponse toResponse(PromptReadView view) {
        return new PromptTaskResponse(
                view.getTaskId(),
                view.getUserId(),
                view.getResponse(),
                view.getStatus(),
                view.getError(),
                view.getCompletedAt()
        );
    }
}
