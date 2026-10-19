package com.autoflow.job.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autoflow.job.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

    List<Job> findByStatusOrderByCreatedAtAsc(String status);
}
