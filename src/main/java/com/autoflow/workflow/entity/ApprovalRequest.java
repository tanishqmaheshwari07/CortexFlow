package com.autoflow.workflow.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data 
@Table(name = "approval_requests")
public class ApprovalRequest {
    
    @Id 
    private UUID id;

    @ManyToOne 
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;

    private String requester;

    private String status;

    private Integer currentStep;

    private LocalDateTime createdAt;

}
