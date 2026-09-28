package com.autoflow.workflow.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ApprovalRequestResponse {

    private UUID id;

    private UUID workflowId;

    private String workflowName;

    private String requester;

    private String status;

    private Integer currentStep;

    private LocalDateTime createdAt;
}