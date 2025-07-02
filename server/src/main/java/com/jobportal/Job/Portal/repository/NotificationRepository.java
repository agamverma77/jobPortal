package com.jobportal.Job.Portal.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.Job.Portal.dto.NotificationStatus;
import com.jobportal.Job.Portal.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
	public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);//this method gives a list of notifications based on particular status
}
