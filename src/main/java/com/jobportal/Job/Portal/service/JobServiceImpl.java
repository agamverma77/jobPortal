package com.jobportal.Job.Portal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.ApplicantDTO;
import com.jobportal.Job.Portal.dto.Application;
import com.jobportal.Job.Portal.dto.ApplicationStatus;
import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.dto.JobStatus;
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
		if(jobDTO.getId()==0)//if job id =0 then we assign it a new id and set post time
		{
		jobDTO.setId(Utilities.getNextSequence("jobs")); //first set the id of job
		jobDTO.setPostTime(LocalDateTime.now());//set the posting time of job
		}
		else//if job already exists , we first find the job
		{
			Job job=jobRepository.findById(jobDTO.getId()).orElseThrow(()->new JobPortalException("JOB_NOT-FOUND"));//we found the job
			//if job status is draft , eg. I wrote it 4 days back & I'm posting it today then I have to change(update) its posting time
			if(job.getJobStatus().equals(JobStatus.DRAFT)||job.getJobStatus().equals(JobStatus.CLOSED))jobDTO.setPostTime(LocalDateTime.now());
		}
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

	@Override
	public List<JobDTO> getJobsPostedBy(Long id) {
		return jobRepository.findByPostedBy(id).stream().map((x)->x.toDTO()).toList(); 
	}

	@Override
	public void changeAppStatus(Application application) throws JobPortalException {
		Job job = jobRepository.findById(application.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));//we get the job
		List<Applicant> apps = job.getApplicants().stream().map((x) -> {
			if (application.getApplicantId() == x.getApplicantId()) {//if id is same then set its application status
				x.setApplicationStatus(application.getApplicationStatus()); 
				if(application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
					x.setInterviewTime(application.getInterviewTime());//if status is interviewing set interview time
				}
			}
			return x;//if status is not interviewing and something else then it simply changes the status
		}).toList(); //we get the applicants in list
		job.setApplicants(apps);//we save that list in job
		jobRepository.save(job);//and save the job
		
	}

	
}
