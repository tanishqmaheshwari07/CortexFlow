package com.autoflow.workflow.service;

import com.autoflow.workflow.dto.ApprovalRequestDto;
import com.autoflow.workflow.dto.ApprovalRequestResponse;

public interface ApprovalRequestService {

    ApprovalRequestResponse create(ApprovalRequestDto request);
}