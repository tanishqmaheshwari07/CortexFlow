package com.autoflow.workflow.controller;

import com.autoflow.workflow.dto.ApprovalActionRequest;
import com.autoflow.workflow.dto.ApprovalRequestDto;
import com.autoflow.workflow.dto.ApprovalRequestResponse;
import com.autoflow.workflow.service.ApprovalRequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
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

    
    @PostMapping("/{id}/approve")
    public ResponseEntity<ApprovalRequestResponse> approve(
            @PathVariable UUID id,
            @Valid @RequestBody ApprovalActionRequest actionRequest) {

        return ResponseEntity.ok(
                approvalRequestService.approve(id, actionRequest));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<ApprovalRequestResponse> reject(
            @PathVariable UUID id,
            @Valid @RequestBody ApprovalActionRequest actionRequest) {

        return ResponseEntity.ok(
                approvalRequestService.reject(id, actionRequest));
    }
}