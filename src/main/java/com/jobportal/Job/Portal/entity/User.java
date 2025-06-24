package com.jobportal.Job.Portal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jobportal.Job.Portal.dto.AccountType;

import lombok.Data;

@Data
@Document(collection="users") // users mapped to db table
public class User {
	@Id // id of user
	private String id;
	private String name;
	@Indexed(unique = true)//this is used to insert only unique entries in db here for db
	private String email;
	private String password;
	private AccountType accountType;
}
