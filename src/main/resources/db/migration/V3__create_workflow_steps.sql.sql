CREATE TABLE workflow_steps
(
    id UUID PRIMARY KEY,

    workflow_id UUID NOT NULL,

    step_order INTEGER NOT NULL,

    name VARCHAR(255) NOT NULL,

    approver_role VARCHAR(100),

    active BOOLEAN NOT NULL,

    created_at TIMESTAMP,

    CONSTRAINT fk_step_workflow
        FOREIGN KEY (workflow_id)
        REFERENCES workflows(id)
);