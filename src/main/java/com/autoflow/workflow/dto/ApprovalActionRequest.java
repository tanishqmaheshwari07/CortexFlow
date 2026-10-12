package com.autoflow.workflow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApprovalActionRequest {

    @NotBlank(message = "Approver name is required")
    private String approver;

    private String comments;
}
