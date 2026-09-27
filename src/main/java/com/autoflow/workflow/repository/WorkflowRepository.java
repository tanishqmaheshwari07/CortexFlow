package com.autoflow.workflow.repository;

import com.autoflow.workflow.entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkflowRepository
        extends JpaRepository<Workflow, UUID> {
                
   List<Workflow> findByTenantId(UUID tenantId);
}