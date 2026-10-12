package com.autoflow.workflow.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "approval_actions")
@Getter
@Setter 
@NoArgsConstructor 
public class ApprovalAction {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_request_id", nullable = false)
    private ApprovalRequest approvalRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_step_id", nullable = false)
    private WorkflowStep workflowStep;

    @Column(nullable = false)
    private String approver;

    @Column(nullable = false, length = 20)
    private String action;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(name = "action_time", nullable = false)
    private LocalDateTime actionTime;

    @PrePersist
    protected void onCreate(){
        if(actionTime == null){
            actionTime = LocalDateTime.now();
        }
    }

}
