package com.autoflow.tenant.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.autoflow.tenant.entity.Tenant;

public  interface TenantRepository 
         extends JpaRepository<Tenant,UUID>{    
}
