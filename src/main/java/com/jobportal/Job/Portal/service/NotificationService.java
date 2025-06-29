package com.jobportal.Job.Portal.service;

import java.util.List;

import com.jobportal.Job.Portal.dto.NotificationDTO;
import com.jobportal.Job.Portal.entity.Notification;
import com.jobportal.Job.Portal.exception.JobPortalException;

public interface NotificationService {
	public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException;//method to send notification
	public List<Notification> getUnreadNotifications(Long userId);//jo notifications ek baar read krlenge unko front end me nhi dikhaenge, bs unread wali hi dikhaenge 
	public void readNotification(Long id) throws JobPortalException;
}
