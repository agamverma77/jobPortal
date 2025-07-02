package com.jobportal.Job.Portal.entity;


import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.jobportal.Job.Portal.dto.NotificationDTO;
import com.jobportal.Job.Portal.dto.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification")
public class Notification {
	private Long id;
	private Long userId;
	private String message;  //like you are shortliseted etc
	private String action; //like what action is there, interview, OA
	private String route;
	private NotificationStatus status;
	private LocalDateTime timestamp;
	public NotificationDTO toDTO() {
		return new NotificationDTO (this.id, this.userId, this.message, this.action, this.route, this.status, this.timestamp);
	}
}
