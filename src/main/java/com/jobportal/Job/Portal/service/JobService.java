package com.jobportal.Job.Portal.service;

import java.util.List;

import com.jobportal.Job.Portal.dto.ApplicantDTO;
import com.jobportal.Job.Portal.dto.Application;
import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;

import jakarta.validation.Valid;

public interface JobService {

	public JobDTO postJob(@Valid JobDTO jobDTO) throws JobPortalException;

	public List<JobDTO> getAllJobs();

	public JobDTO getJob(Long id) throws JobPortalException;

	public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException;

	public List<JobDTO> getJobsPostedBy(Long id);

	public void changeAppStatus(Application application) throws JobPortalException;
	
}
