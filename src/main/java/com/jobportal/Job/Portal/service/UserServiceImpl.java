package com.jobportal.Job.Portal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.ResponseDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.entity.OTP;
import com.jobportal.Job.Portal.entity.User;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.OTPRepository;
import com.jobportal.Job.Portal.repository.UserRepository;
import com.jobportal.Job.Portal.utility.Data;
import com.jobportal.Job.Portal.utility.Utilities;

import jakarta.mail.internet.MimeMessage;
@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;//cant directly inject using autowire , need to do constructor injection, so create a security config class
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private OTPRepository otpRepository;

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
	@Override
	public Boolean sendOTP(String email) throws Exception {
		User user=userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));//check if that email exists in db
		MimeMessage mm = mailSender.createMimeMessage();//to give sendotp message to user
		MimeMessageHelper message = new MimeMessageHelper(mm, true); //helper og mimemessage
		message.setTo(email);
		message.setSubject("Your OTP Code");
		String generatedOtp = Utilities.generateOTP();//to generate otp using this func in utilities
		OTP otp = new OTP(email, generatedOtp, LocalDateTime.now());//save the generated otp in db
		otpRepository.save(otp);//save to db, difference b/w save & insert
		message.setText(Data.getMessageBody(generatedOtp, user.getName()), true);
		mailSender.send(mm);
		return true;
	}
	@Override
	public Boolean verifyOtp(String email, String otp) throws JobPortalException {
		OTP otpEntity = otpRepository.findById(email).orElseThrow(() -> new JobPortalException("OTP_NOT_FOUND"));//otp exists or not, check from email
		if(!otpEntity.getOtpCode().equals(otp))throw new JobPortalException("OTP_INCORRECT"); //if the otp got and the otp saved dont match, return error
		return true;//if otp equal then return true
	}
	@Override
	public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
		User user = userRepository.findByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));//we'll take email & if user not found give exception
		user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
		userRepository.save(user);//set new password and save
		return new ResponseDTO("Password changed successfully.");
	}
	@Scheduled(fixedRate = 60000)//kitni kitni der baad run hoga ye func
	public void removeExpiredOTPs() {
		LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(5); //set expiry time of 5 min from localdatetime do minus 5 mins, jinka time current time se 5 min kam hai , jo 5 min pehle bane h unko hata do, isiliye otp class me creationtime banae the
		List<OTP> expiredOTPs = otpRepository.findByCreationTimeBefore(expiryTime);//list of otp's in 5 min
		if (!expiredOTPs.isEmpty()) {
			otpRepository.deleteAll(expiredOTPs);//if list not empty then delete those otp's
			System.out.println("Removed "+ expiredOTPs.size()+" expired OTPs");
		}
	}
}
