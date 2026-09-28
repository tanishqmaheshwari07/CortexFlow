package com.autoflow.workflow.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoflow.workflow.dto.WorkflowStepRequest;
import com.autoflow.workflow.entity.WorkflowStep;
import com.autoflow.workflow.service.WorkflowStepService;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/workflow-steps")
@RequiredArgsConstructor
public class WorkflowStepController {
    
    private final WorkflowStepService workflowStepService;

    @PostMapping
    public WorkflowStep create(@Valid @RequestBody WorkflowStepRequest request){
        return workflowStepService.create(request);
    }

    @GetMapping
    public List<WorkflowStep> getAll() {
        return workflowStepService.getAll();
    }

    @GetMapping("/{id}")
    public WorkflowStep getById(@PathVariable UUID id) {
        return workflowStepService.getById(id);
    }

    @GetMapping("/workflow/{workflowId}")
    public List<WorkflowStep> getByWorkflow(@PathVariable UUID workflowId) {
        return workflowStepService.getByWorkflow(workflowId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        workflowStepService.delete(id);
    }

}
