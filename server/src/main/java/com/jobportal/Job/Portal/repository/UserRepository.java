package com.jobportal.Job.Portal.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.Job.Portal.entity.User;

public interface UserRepository extends MongoRepository<User, Long> {
	public Optional<User> findByEmail(String email);
}
