package com.autoflow.tenant.service;

import java.util.List;
import java.util.UUID;

import com.autoflow.tenant.dto.TenantRequest;
import com.autoflow.tenant.entity.Tenant;

public interface TenantService {
    
    Tenant create(TenantRequest request);

    List<Tenant> getAll();

    Tenant getById(UUID id);

    void delete(UUID id);
    
}