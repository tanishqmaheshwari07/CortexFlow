package com.autoflow.workflow.repository;

import com.autoflow.workflow.entity.WorkflowStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkflowStepRepository
        extends JpaRepository<WorkflowStep, UUID> {

    List<WorkflowStep> findByWorkflowId(UUID workflowId);
}