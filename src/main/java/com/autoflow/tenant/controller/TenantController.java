package com.autoflow.tenant.controller;

import com.autoflow.tenant.dto.TenantRequest;
import com.autoflow.tenant.entity.Tenant;
import com.autoflow.tenant.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping 
    public Tenant create(
            @Valid @RequestBody TenantRequest request){
        return tenantService.create(request);
    }

    @GetMapping 
    public List<Tenant> getAll(){
        return tenantService.getAll();
    }

    @GetMapping("/{id}")
    public Tenant getById(@PathVariable UUID id) {
        return tenantService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        tenantService.delete(id);
    }

}