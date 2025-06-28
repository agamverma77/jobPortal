package com.jobportal.Job.Portal.service;

import java.util.List;

import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;

import jakarta.validation.Valid;

public interface JobService {

	public JobDTO postJob(@Valid JobDTO jobDTO) throws JobPortalException;

	public List<JobDTO> getAllJobs();

	public JobDTO getJob(Long id) throws JobPortalException;
	
}
