package com.example.llm.query.repository;

import com.example.llm.query.entity.PromptReadView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromptReadRepository extends JpaRepository<PromptReadView, UUID> {}
