package com.jobportal.Job.Portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jobportal.Job.Portal.jwt.JwtAuthenticationEntryPoint;
import com.jobportal.Job.Portal.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	 @Autowired
	    private JwtAuthenticationEntryPoint point;
	    @Autowired
	    private JwtAuthenticationFilter filter;
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(session -> 
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            )
	            .exceptionHandling(exception -> 
	                exception.authenticationEntryPoint(point)
	            )
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/auth/login","/users/register", "/users/verifyOtp/**","/users/sendOtp/**","/users/changePass").permitAll()
	                .anyRequest().authenticated()
	            )
	            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	    }

	    }


