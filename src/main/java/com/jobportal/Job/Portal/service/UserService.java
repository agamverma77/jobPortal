package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.ResponseDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public interface UserService {
	public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

	public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

	public Boolean sendOTP(String email) throws Exception;

	public Boolean verifyOtp(String email, String otp) throws JobPortalException;

	public ResponseDTO changePassword( LoginDTO loginDTO) throws JobPortalException;

	public UserDTO getUserByEmail(String email) throws JobPortalException;
}
