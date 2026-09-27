package com.autoflow.tenant.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.autoflow.tenant.dto.TenantRequest;
import com.autoflow.tenant.entity.Tenant;
import com.autoflow.tenant.repository.TenantRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    @Override
    public Tenant create(TenantRequest request){

        Tenant tenant = Tenant.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.getActive())
                .build();

        return tenantRepository.save(tenant);
    }
    @Override 
    public List<Tenant> getAll(){
        return tenantRepository.findAll();
    }

    @Override 
    public Tenant getById(UUID id){
        return tenantRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        tenantRepository.deleteById(id);
    }
    
}
