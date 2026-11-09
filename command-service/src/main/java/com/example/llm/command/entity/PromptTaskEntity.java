package com.example.llm.command.entity;

import com.example.llm.event.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "prompt_tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromptTaskEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String prompt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false)
    private Instant createdAt;
}
