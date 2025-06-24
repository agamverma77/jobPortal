package com.jobportal.Job.Portal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.Job.Portal.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

}
