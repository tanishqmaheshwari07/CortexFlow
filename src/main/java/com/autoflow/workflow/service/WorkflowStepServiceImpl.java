package com.autoflow.workflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.autoflow.workflow.dto.WorkflowStepRequest;
import com.autoflow.workflow.entity.Workflow;
import com.autoflow.workflow.entity.WorkflowStep;
import com.autoflow.workflow.repository.WorkflowRepository;
import com.autoflow.workflow.repository.WorkflowStepRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class WorkflowStepServiceImpl implements WorkflowStepService {
    
    private final WorkflowStepRepository workflowStepRepository;
    private final WorkflowRepository workflowRepository;

    @Override 
    public WorkflowStep create(WorkflowStepRequest request){
        
        Workflow workflow = workflowRepository.findById(request.getWorkflowId())
                .orElseThrow(() -> new RuntimeException("Workflow not found"));

        WorkflowStep step = WorkflowStep.builder()
                .workflow(workflow)
                .stepOrder(request.getStepOrder())
                .name(request.getName())
                .approverRole(request.getApproverRole())
                .active(request.getActive())
                .createdAt(LocalDateTime.now())
                .build();

        return workflowStepRepository.save(step);
    }

    @Override 
    public List<WorkflowStep> getAll(){
        return workflowStepRepository.findAll();
    }

    @Override
    public List<WorkflowStep> getByWorkflow(UUID workflowId) {
        return workflowStepRepository.findByWorkflowId(workflowId);
    }

    @Override
    public WorkflowStep getById(UUID id) {
        return workflowStepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Step not found"));
    }

    @Override
    public void delete(UUID id) {
        workflowStepRepository.deleteById(id);
    }

}
