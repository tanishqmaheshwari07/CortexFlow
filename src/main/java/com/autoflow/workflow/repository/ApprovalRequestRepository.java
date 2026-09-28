package com.autoflow.workflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoflow.workflow.entity.ApprovalRequest;

public interface ApprovalRequestRepository
        extends JpaRepository<ApprovalRequest, UUID> {
}