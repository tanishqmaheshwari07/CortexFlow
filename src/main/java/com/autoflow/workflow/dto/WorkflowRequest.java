package com.autoflow.workflow.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class WorkflowRequest {
    
    @NotNull 
    private UUID tenantId;

    @NotBlank 
    private String name;

    private String description;

    private Boolean active;

}
