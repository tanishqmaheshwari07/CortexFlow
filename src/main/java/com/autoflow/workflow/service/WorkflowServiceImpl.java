package com.autoflow.workflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.autoflow.tenant.entity.Tenant;
import com.autoflow.tenant.repository.TenantRepository;
import com.autoflow.workflow.dto.WorkflowRequest;
import com.autoflow.workflow.entity.Workflow;
import com.autoflow.workflow.repository.WorkflowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService {
    
    private final WorkflowRepository workflowRepository;
    private final TenantRepository tenantRepository;

    @Override
    public Workflow create(WorkflowRequest request) {
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Workflow workflow = Workflow.builder()
                .tenant(tenant)
                .name(request.getName())
                .description(request.getDescription())
                .active(request.getActive())
                .createdAt(LocalDateTime.now())
                .build();

        return workflowRepository.save(workflow);
    }

    @Override
    public List<Workflow> getAll() {
        return workflowRepository.findAll();
    }

    @Override
    public List<Workflow> getByTenant(UUID tenantId) {
        return workflowRepository.findByTenantId(tenantId);
    }

    @Override
    public Workflow getById(UUID id) {
        return workflowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
    }

    @Override
    public void delete(UUID id) {
        workflowRepository.deleteById(id);
    }
}

