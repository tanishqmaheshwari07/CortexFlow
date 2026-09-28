package com.autoflow.workflow.service;

import java.util.List;
import java.util.UUID;

import com.autoflow.workflow.dto.WorkflowStepRequest;
import com.autoflow.workflow.entity.WorkflowStep;

public interface WorkflowStepService {

    WorkflowStep create(WorkflowStepRequest request);

    List<WorkflowStep> getAll();

    List<WorkflowStep> getByWorkflow(UUID workflowId);

    WorkflowStep getById(UUID id);

    void delete(UUID id);

}