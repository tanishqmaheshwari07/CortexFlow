package com.autoflow.workflow.controller;

import com.autoflow.workflow.dto.ApprovalRequestDto;
import com.autoflow.workflow.dto.ApprovalRequestResponse;
import com.autoflow.workflow.service.ApprovalRequestService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ApprovalRequestController {

    private final ApprovalRequestService approvalRequestService;

    @PostMapping
    public ApprovalRequestResponse create(
            @RequestBody ApprovalRequestDto request) {

        return approvalRequestService.create(request);
    }
}