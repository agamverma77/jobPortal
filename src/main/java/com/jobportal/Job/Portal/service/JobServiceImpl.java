package com.jobportal.Job.Portal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.ApplicantDTO;
import com.jobportal.Job.Portal.dto.ApplicationStatus;
import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.entity.Applicant;
import com.jobportal.Job.Portal.entity.Job;
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

	@Override
	public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException {
		Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));//id se job ko search krlo
		List<Applicant> applicants = job.getApplicants();//list of applicants nikaal lenge jisne bhi us job per apply kr rakha h
		if (applicants == null)applicants = new ArrayList<>();//agar applicants null h then assign a new arraylist to it
		if (applicants.stream().filter((x) -> x.getApplicantId() == applicantDTO.getApplicantId()).toList().size() > 0)throw new JobPortalException("JOB_APPLIED_ALREADY");//agar pehle se apply kar rakha h then throw exception, by applying filter , filter on the basis that the id that is coming, does that already exist? size>0 means agar 1 bhi aisa h then user 
		applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);//setting the applicationstatus
		applicants.add(applicantDTO.toEntity());//applicant ko applicant list me add kr denge
		job.setApplicants(applicants);//job me applicant array ko set kr denge
		jobRepository.save(job);//job ko save kr denge in db
	}
	
}
