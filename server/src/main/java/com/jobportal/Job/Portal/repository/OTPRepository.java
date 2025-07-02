package com.jobportal.Job.Portal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.Job.Portal.entity.OTP;


public interface OTPRepository extends MongoRepository<OTP, String> {
	    List<OTP> findByCreationTimeBefore(LocalDateTime expiryTime);
}