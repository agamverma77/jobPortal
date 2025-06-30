package com.jobportal.Job.Portal.jwt;

import lombok.Data;

@Data
public class AuthenticationResponse {
	public AuthenticationResponse(String jwt) {
		this.jwt=jwt;
	}

	private final String jwt;//final so that no one can change it
}
