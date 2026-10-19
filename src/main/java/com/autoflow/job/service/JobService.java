package com.autoflow.job.service;

import java.util.List;
import java.util.UUID;

import com.autoflow.job.dto.JobRequest;
import com.autoflow.job.entity.Job;

public interface JobService {

    Job create(JobRequest request);

    List<Job> getAll();

    Job getById(UUID id);

    List<Job> getQueuedJobs();

    default List<Job> getQueued() {
        return getQueuedJobs();
    }
}
