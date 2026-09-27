package com.autoflow.tenant.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.autoflow.workflow.entity.Workflow;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tenants")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Tenant {

    @JsonManagedReference
    @OneToMany(mappedBy = "tenant")
    private List<Workflow> workflows;
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Boolean active;

    private LocalDateTime createdAt;

}
