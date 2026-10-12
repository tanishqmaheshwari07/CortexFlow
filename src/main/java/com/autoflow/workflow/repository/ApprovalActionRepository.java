
package com.autoflow.workflow.repository;

import com.autoflow.workflow.entity.ApprovalAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApprovalActionRepository
        extends JpaRepository<ApprovalAction, UUID> {

    List<ApprovalAction>
        findByApprovalRequest_IdOrderByActionTimeAsc(UUID requestId);
}
