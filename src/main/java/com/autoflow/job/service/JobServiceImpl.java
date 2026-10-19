package com.autoflow.job.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.autoflow.job.dto.JobRequest;
import com.autoflow.job.entity.Job;
import com.autoflow.job.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public Job create(JobRequest request) {
        Job job = Job.builder()
                .type(request.getType())
                .payload(request.getPayload())
                .scheduledAt(request.getScheduledAt())
                .status("QUEUED")
                .attempts(0)
                .createdAt(LocalDateTime.now())
                .build();

        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getById(UUID id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found with id: " + id));
    }

    @Override
    public List<Job> getQueuedJobs() {
        return jobRepository.findByStatusOrderByCreatedAtAsc("QUEUED");
    }

    @Override
    public List<Job> getQueued() {
        return getQueuedJobs();
    }
}
