package com.socialapp.autheticationservice.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialapp.autheticationservice.entity.User;
import com.socialapp.autheticationservice.repository.UsersRepo;
import com.socialapp.autheticationservice.util.PasswordEncoderUtil;

@Service
public class SignUpService {

	@Autowired
	UsersRepo usersRepo;

	public User signUp(User user) {
		user.setPassword(PasswordEncoderUtil.getPasswordEncoder().encode(user.getPassword()));
		return usersRepo.save(user);

	}
}
