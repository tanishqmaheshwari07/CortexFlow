
CREATE TABLE approval_actions (
    id UUID PRIMARY KEY,
    approval_request_id UUID NOT NULL
        REFERENCES approval_requests(id),
    workflow_step_id UUID NOT NULL
        REFERENCES workflow_steps(id),
    approver VARCHAR(255) NOT NULL,
    action VARCHAR(20) NOT NULL
        CHECK (action IN ('APPROVED', 'REJECTED')),
    comments TEXT,
    action_time TIMESTAMP NOT NULL
);

CREATE INDEX idx_approval_actions_request
    ON approval_actions(approval_request_id);
