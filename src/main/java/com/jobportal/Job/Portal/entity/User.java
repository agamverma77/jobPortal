package com.jobportal.Job.Portal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jobportal.Job.Portal.dto.AccountType;
import com.jobportal.Job.Portal.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="users") // users mapped to db table
public class User {
	@Id // id of user
	private String id;
	private String name;
	@Indexed(unique = true)//this is used to insert only unique entries in db here for db
	private String email;
	private String password;
	private AccountType accountType;
	
	public UserDTO toDTO()
	{
		return new UserDTO(this.id, this.name,this.email, this.password, this.accountType);
	}
}
