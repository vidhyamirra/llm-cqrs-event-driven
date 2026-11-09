CREATE TABLE IF NOT EXISTS prompt_read_view (
    task_id UUID PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    response TEXT,
    status VARCHAR(50) NOT NULL,
    error TEXT,
    completed_at TIMESTAMP WITH TIME ZONE
);
