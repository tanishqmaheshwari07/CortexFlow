package com.autoflow.workflow.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkflowStepRequest {

    @NotNull
    private UUID workflowId;

    @NotNull
    private Integer stepOrder;

    @NotBlank
    private String name;

    @NotBlank
    private String approverRole;

    @NotNull
    private Boolean active;
}
