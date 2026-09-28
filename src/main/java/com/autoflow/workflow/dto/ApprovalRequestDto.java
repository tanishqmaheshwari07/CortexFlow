package com.autoflow.workflow.dto;
import java.util.UUID;

import lombok.Data;

@Data
public class ApprovalRequestDto {
    
    private UUID workflowId;

    private String requester;

}
