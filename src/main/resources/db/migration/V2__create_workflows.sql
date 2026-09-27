CREATE TABLE workflows
(
    id UUID PRIMARY KEY,

    tenant_id UUID NOT NULL,

    name VARCHAR(255) NOT NULL,

    description TEXT,

    active BOOLEAN NOT NULL,

    created_at TIMESTAMP,

    CONSTRAINT fk_workflow_tenant
        FOREIGN KEY (tenant_id)
        REFERENCES tenants(id)
);