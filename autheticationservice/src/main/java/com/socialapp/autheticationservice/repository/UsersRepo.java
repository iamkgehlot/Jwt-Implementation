package com.socialapp.autheticationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialapp.autheticationservice.entity.User;

@Repository
public interface UsersRepo extends JpaRepository<User,Integer>{
 
public Optional<User> findByUsername(String username);

}
