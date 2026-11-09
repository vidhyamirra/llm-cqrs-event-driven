package com.example.llm.query.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "prompt_read_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromptReadView {
    @Id
    private UUID taskId;

    @Column(nullable = false)
    private String userId;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String error;

    private Instant completedAt;
}
