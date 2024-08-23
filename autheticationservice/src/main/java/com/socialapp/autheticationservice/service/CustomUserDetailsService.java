package com.socialapp.autheticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialapp.autheticationservice.entity.User;
import com.socialapp.autheticationservice.repository.UsersRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepo usersRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return  new CustomUserDetails(user);
	}
}
