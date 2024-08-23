package com.socialapp.autheticationservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
	
	private String jwttoken;
	
	private String username;

}
