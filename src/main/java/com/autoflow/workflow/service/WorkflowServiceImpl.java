package com.autoflow.workflow.service;

import com.autoflow.tenant.repository.TenantRepository;
import com.autoflow.workflow.dto.WorkflowRequest;
import com.autoflow.workflow.repository.WorkflowRepository;

public class WorkflowServiceImpl implements WorkflowService{
    
    private final WorkflowRepository workflowRepository;
    private final TenantRepository tenantRepository;

    public Workflow create(WorkflowRequest request){

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
    }

}
