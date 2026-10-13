
package com.autoflow.workflow.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ApprovalActionResponse {

    private UUID id;
    private String approver;
    private String action;
    private String comments;
    private LocalDateTime actionTime;
    private Integer stepOrder;
    private String stepName;
}
