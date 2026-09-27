package com.autoflow.workflow.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoflow.workflow.dto.WorkflowRequest;
import com.autoflow.workflow.entity.Workflow;
import com.autoflow.workflow.service.WorkflowService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/workflows")
@RequiredArgsConstructor 
public class WorkflowController {
    
    private final WorkflowService workflowService;

    @PostMapping 
    public Workflow create(
            @Valid @RequestBody WorkflowRequest request) {
        return workflowService.create(request);
    }

    @GetMapping 
    public List<Workflow> getAll(){
        return workflowService.getAll();
    }

    @GetMapping("/{id}")
    public Workflow getById(@PathVariable UUID id) {
        return workflowService.getById(id);
    }

    @GetMapping("/tenant/{tenantId}")
    public List<Workflow> getByTenant(
            @PathVariable UUID tenantId) {
        return workflowService.getByTenant(tenantId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        workflowService.delete(id);
    }
    
}
