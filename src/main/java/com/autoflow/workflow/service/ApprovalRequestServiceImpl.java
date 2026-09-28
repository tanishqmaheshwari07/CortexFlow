package com.autoflow.workflow.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.autoflow.workflow.dto.ApprovalRequestDto;
import com.autoflow.workflow.dto.ApprovalRequestResponse;
import com.autoflow.workflow.entity.ApprovalRequest;
import com.autoflow.workflow.entity.Workflow;
import com.autoflow.workflow.repository.ApprovalRequestRepository;
import com.autoflow.workflow.repository.WorkflowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalRequestServiceImpl implements ApprovalRequestService {

    private final ApprovalRequestRepository approvalRequestRepository;
    private final WorkflowRepository workflowRepository;

    @Override
    public ApprovalRequestResponse create(ApprovalRequestDto requestDto) {

        Workflow workflow = workflowRepository.findById(
                requestDto.getWorkflowId())
                .orElseThrow(() ->
                        new RuntimeException("Workflow not found"));

        ApprovalRequest request = new ApprovalRequest();

        request.setId(UUID.randomUUID());
        request.setWorkflow(workflow);
        request.setRequester(requestDto.getRequester());
        request.setStatus("PENDING");
        request.setCurrentStep(1);
        request.setCreatedAt(LocalDateTime.now());

        ApprovalRequest saved = approvalRequestRepository.save(request);

        ApprovalRequestResponse response =
                new ApprovalRequestResponse();

        response.setId(saved.getId());
        response.setWorkflowId(workflow.getId());
        response.setWorkflowName(workflow.getName());
        response.setRequester(saved.getRequester());
        response.setStatus(saved.getStatus());
        response.setCurrentStep(saved.getCurrentStep());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }
}