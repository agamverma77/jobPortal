package com.jobportal.Job.Portal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.NotificationDTO;
import com.jobportal.Job.Portal.dto.NotificationStatus;
import com.jobportal.Job.Portal.entity.Notification;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.NotificationRepository;
import com.jobportal.Job.Portal.utility.Utilities;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException {
		notificationDTO.setId(Utilities.getNextSequence("notification"));//set the notification id
		notificationDTO.setStatus(NotificationStatus.UNREAD);
		notificationDTO.setTimestamp(LocalDateTime.now());//set the timestamp
		notificationRepository.save(notificationDTO.toEntity());//save the notification
	}

	@Override
	public List<Notification> getUnreadNotifications(Long userId) {
		//by calling the method made in notificationrepository
		return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);

	}

	@Override
	public void readNotification(Long id) throws JobPortalException {
		Notification noti=notificationRepository.findById(id).orElseThrow(()->new JobPortalException("No Notitication found"));//first get the notification
		noti.setStatus(NotificationStatus.READ);//set the status to READ
		notificationRepository.save(noti);
		
	}
	
}
