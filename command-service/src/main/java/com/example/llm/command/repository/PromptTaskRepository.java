package com.example.llm.command.repository;

import com.example.llm.command.entity.PromptTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromptTaskRepository extends JpaRepository<PromptTaskEntity, UUID> {}
