package com.socialapp.autheticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.socialapp.autheticationservice.service.SignUpService;
import com.socialapp.autheticationservice.util.PasswordEncoderUtil;

import com.socialapp.autheticationservice.entity.JwtResponse;
import com.socialapp.autheticationservice.entity.User;
import com.socialapp.autheticationservice.security.JwtHelper;

@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	SignUpService signUpService;

	@Autowired
	JwtHelper helper;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user) {

		return new ResponseEntity<>(signUpService.signUp(user), HttpStatus.OK);
	}

	

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody User request) {
		
		this.doAuthentication(request.getUsername(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwttoken(token).username(userDetails.getUsername()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void doAuthentication(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);

		try {

			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {

			throw new RuntimeException("Invalid Username or Password");
		} catch (Exception e) {
			throw new RuntimeException("ex" + e);
		}

	}

}
