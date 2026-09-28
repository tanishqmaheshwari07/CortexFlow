CREATE TABLE approval_requests
(
    id UUID PRIMARY KEY,

    workflow_id UUID NOT NULL,

    requester VARCHAR(255),

    status VARCHAR(50),

    current_step INTEGER,

    created_at TIMESTAMP,

    CONSTRAINT fk_request_workflow
    FOREIGN KEY (workflow_id)
    REFERENCES workflows(id)
);