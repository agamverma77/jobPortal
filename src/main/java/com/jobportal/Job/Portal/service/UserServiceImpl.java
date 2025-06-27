package com.jobportal.Job.Portal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.entity.User;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.UserRepository;
import com.jobportal.Job.Portal.utility.Utilities;
@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;//cant directly inject using autowire , need to do constructor injection, so create a security config class
	@Override
	public UserDTO registerUser(UserDTO userDTO) throws Exception {
		Optional<User> optional=userRepository.findByEmail(userDTO.getEmail());
		if(optional.isPresent()) throw new JobPortalException("USER_FOUND");
		userDTO.setId(Utilities.getNextSequence("users"));
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user= userDTO.toEntity(); //this converts dto to user as repository works on user not dto
		user=userRepository.save(user);//now save it in userRepository
		return user.toDTO();
	}
	@Override
	public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
		User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));//first we'll check if that email exists then only we'll login right, so if email found then return else give exceptoin
		if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))//check if the password matches
			throw new JobPortalException("INVALID_CREDENTIALS");//if not match, throw an exception
		user.setPassword(null);
		return user.toDTO();
	}
}
