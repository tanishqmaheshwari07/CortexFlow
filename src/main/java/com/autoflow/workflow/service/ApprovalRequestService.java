
package com.autoflow.workflow.service;

import java.util.UUID;

import com.autoflow.workflow.dto.ApprovalRequestDto;
import com.autoflow.workflow.dto.ApprovalRequestResponse;
import com.autoflow.workflow.dto.ApprovalActionRequest;

public interface ApprovalRequestService {

    ApprovalRequestResponse create(ApprovalRequestDto request);

    ApprovalRequestResponse approve(
            UUID requestId,
            ApprovalActionRequest actionRequest);

    ApprovalRequestResponse reject(
            UUID requestId,
            ApprovalActionRequest actionRequest);
}
