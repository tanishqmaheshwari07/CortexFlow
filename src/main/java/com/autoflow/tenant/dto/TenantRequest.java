package com.autoflow.tenant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class TenantRequest {
    
    @NotBlank 
    private String name;

    @NotBlank 
    private String code;

    private Boolean active;
    
}
