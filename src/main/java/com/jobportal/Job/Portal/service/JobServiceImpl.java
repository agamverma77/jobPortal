package com.jobportal.Job.Portal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.JobRepository;
import com.jobportal.Job.Portal.utility.Utilities;

import jakarta.validation.Valid;

@Service("jobService")
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;

	@Override
	public JobDTO postJob(@Valid JobDTO jobDTO) throws JobPortalException {
		jobDTO.setId(Utilities.getNextSequence("jobs")); //first set the id of job
		jobDTO.setPostTime(LocalDateTime.now());//set the posting time of job
		return jobRepository.save(jobDTO.toEntity()).toDTO();//save the job in entity and return as dto
	}

	@Override
	public List<JobDTO> getAllJobs() {
		return jobRepository.findAll().stream().map((x)->x.toDTO()).toList(); //can return all jobs using for loop also but I'm using stream in a list
	}

	@Override
	public JobDTO getJob(Long id) throws JobPortalException {
		return jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT-FOUND")).toDTO();
	}
	
}
