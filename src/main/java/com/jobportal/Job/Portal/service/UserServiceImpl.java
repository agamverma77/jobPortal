package com.jobportal.Job.Portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.entity.User;
import com.jobportal.Job.Portal.repository.UserRepository;
@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user= userDTO.toEntity(); //this converts dto to user as repository works on user not dto
		user=userRepository.save(user);//now save it in userRepository
		return user.toDTO();
	}


}
