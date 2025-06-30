package com.jobportal.Job.Portal.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.service.UserService;
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService; // we have to get user so autowired userservice
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			UserDTO dto=userService.getUserByEmail(email);  //user service se email se user find krenge
			return new CustomUserDetails(dto.getId(), email,dto.getName(), dto.getPassword(),dto.getProfileId(), dto.getAccountType(), new ArrayList<>());
		} catch (JobPortalException e) {
			e.printStackTrace();
		}
		return null;

}
}
