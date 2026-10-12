package com.autoflow.workflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoflow.workflow.dto.ApprovalActionRequest;
import com.autoflow.workflow.dto.ApprovalRequestDto;
import com.autoflow.workflow.dto.ApprovalRequestResponse;
import com.autoflow.workflow.entity.ApprovalAction;
import com.autoflow.workflow.entity.ApprovalRequest;
import com.autoflow.workflow.entity.Workflow;
import com.autoflow.workflow.entity.WorkflowStep;
import com.autoflow.workflow.repository.ApprovalActionRepository;
import com.autoflow.workflow.repository.ApprovalRequestRepository;
import com.autoflow.workflow.repository.WorkflowRepository;
import com.autoflow.workflow.repository.WorkflowStepRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalRequestServiceImpl
        implements ApprovalRequestService {

    private final ApprovalRequestRepository approvalRequestRepository;
    private final WorkflowRepository workflowRepository;
    private final WorkflowStepRepository workflowStepRepository;
    private final ApprovalActionRepository approvalActionRepository;

    @Override
    public ApprovalRequestResponse create(ApprovalRequestDto requestDto) {

        Workflow workflow = workflowRepository.findById(
                requestDto.getWorkflowId())
                .orElseThrow(() ->
                        new RuntimeException("Workflow not found"));

        List<WorkflowStep> steps =
                workflowStepRepository.findByWorkflowIdOrderByStepOrderAsc(
                        workflow.getId());

        if (steps.isEmpty()) {
            throw new RuntimeException(
                    "Cannot create request: workflow has no steps");
        }

        ApprovalRequest request = new ApprovalRequest();
        request.setId(UUID.randomUUID());
        request.setWorkflow(workflow);
        request.setRequester(requestDto.getRequester());
        request.setStatus("PENDING");
        request.setCurrentStep(steps.get(0).getStepOrder());
        request.setCreatedAt(LocalDateTime.now());

        ApprovalRequest saved = approvalRequestRepository.save(request);

        return toResponse(saved);
    }

    @Override
    @Transactional
    public ApprovalRequestResponse approve(
            UUID requestId,
            ApprovalActionRequest actionRequest) {

        ApprovalRequest request = getPendingRequest(requestId);

        List<WorkflowStep> steps =
                workflowStepRepository.findByWorkflowIdOrderByStepOrderAsc(
                        request.getWorkflow().getId());

        int currentIndex = findCurrentStepIndex(
                steps, request.getCurrentStep());

        WorkflowStep currentStep = steps.get(currentIndex);

        saveAction(request, currentStep, actionRequest, "APPROVED");

        if (currentIndex + 1 < steps.size()) {
            // More approval steps remain.
            request.setCurrentStep(
                    steps.get(currentIndex + 1).getStepOrder());
        } else {
            // The final step has been approved.
            request.setStatus("APPROVED");
        }

        ApprovalRequest saved = approvalRequestRepository.save(request);

        return toResponse(saved);
    }

    @Override
    @Transactional
    public ApprovalRequestResponse reject(
            UUID requestId,
            ApprovalActionRequest actionRequest) {

        ApprovalRequest request = getPendingRequest(requestId);

        List<WorkflowStep> steps =
                workflowStepRepository.findByWorkflowIdOrderByStepOrderAsc(
                        request.getWorkflow().getId());

        int currentIndex = findCurrentStepIndex(
                steps, request.getCurrentStep());

        WorkflowStep currentStep = steps.get(currentIndex);

        saveAction(request, currentStep, actionRequest, "REJECTED");

        request.setStatus("REJECTED");

        ApprovalRequest saved = approvalRequestRepository.save(request);

        return toResponse(saved);
    }

    private ApprovalRequest getPendingRequest(UUID requestId) {

        ApprovalRequest request = approvalRequestRepository
                .findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("Approval request not found"));

        if (!"PENDING".equals(request.getStatus())) {
            throw new RuntimeException(
                    "Only PENDING requests can be approved or rejected");
        }

        return request;
    }

    private int findCurrentStepIndex(
            List<WorkflowStep> steps, Integer currentStep) {

        for (int i = 0; i < steps.size(); i++) {
            if (steps.get(i).getStepOrder().equals(currentStep)) {
                return i;
            }
        }

        throw new RuntimeException(
                "Current workflow step was not found");
    }

    private void saveAction(
            ApprovalRequest request,
            WorkflowStep step,
            ApprovalActionRequest actionRequest,
            String actionType) {

        ApprovalAction action = new ApprovalAction();

        action.setApprovalRequest(request);
        action.setWorkflowStep(step);
        action.setApprover(actionRequest.getApprover());
        action.setAction(actionType);
        action.setComments(actionRequest.getComments());

        approvalActionRepository.save(action);
    }

    private ApprovalRequestResponse toResponse(
            ApprovalRequest request) {

        ApprovalRequestResponse response =
                new ApprovalRequestResponse();

        response.setId(request.getId());
        response.setWorkflowId(request.getWorkflow().getId());
        response.setWorkflowName(request.getWorkflow().getName());
        response.setRequester(request.getRequester());
        response.setStatus(request.getStatus());
        response.setCurrentStep(request.getCurrentStep());
        response.setCreatedAt(request.getCreatedAt());

        return response;
    }
}
