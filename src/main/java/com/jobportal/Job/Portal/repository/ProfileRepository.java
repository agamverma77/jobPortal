package com.jobportal.Job.Portal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.Job.Portal.entity.Profile;

public interface ProfileRepository extends MongoRepository<Profile, Long> {
	
}
 