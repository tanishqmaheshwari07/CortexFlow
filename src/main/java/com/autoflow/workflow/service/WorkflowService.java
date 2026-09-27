package com.autoflow.workflow.service;

import java.util.List;
import java.util.UUID;

import com.autoflow.workflow.dto.WorkflowRequest;
import com.autoflow.workflow.entity.Workflow;

public interface WorkflowService {

    Workflow create(WorkflowRequest request);

    List<Workflow> getAll();

    List<Workflow> getByTenant(UUID tenantId);

    Workflow getById(UUID id);

    void delete(UUID id);
    
}