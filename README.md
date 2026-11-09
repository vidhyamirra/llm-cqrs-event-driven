# LLM CQRS Event-Driven Microservices

This repository implements an asynchronous CQRS (Command Query Responsibility Segregation) architecture for LLM inference using Spring Boot 3, Spring AI, Apache Kafka, and PostgreSQL.

## Architecture

- **command-service**: Accepts prompt requests, stores initial status (`PENDING`), and publishes `PromptSubmittedEvent`.
- **llm-worker-service**: Consumes `PromptSubmittedEvent`, processes prompts asynchronously via Spring AI / LLM provider, and publishes `LlmInferenceCompletedEvent` or `LlmInferenceFailedEvent`.
- **query-service**: Listens to LLM completion events, updates materialized read models, and provides low-latency REST endpoints for task query and streaming.
- **common-events**: Shared domain events and DTOs.

## Quickstart

1. Start infrastructure:
   ```bash
   docker-compose up -d
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run services:
   ```bash
   # Terminal 1
   mvn -pl command-service spring-boot:run
   # Terminal 2
   mvn -pl llm-worker-service spring-boot:run
   # Terminal 3
   mvn -pl query-service spring-boot:run
   ```
