package com.jobportal.Job.Portal.jwt;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import com.jobportal.Job.Portal.dto.AccountType;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String name;
	private String password;
	private Long profileId;
	private AccountType accountType; //front end me using jwt token hi hum pata krenge ki ye ek applicant h ya job dega
	private Collection<?extends GrantedAuthority>authorities;

}

