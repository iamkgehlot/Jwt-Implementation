package com.socialapp.autheticationservice.config;

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

import com.socialapp.autheticationservice.security.JwtAuthenticationEntryPoint;
import com.socialapp.autheticationservice.security.JwtAuthenticationFilter;
import com.socialapp.autheticationservice.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig  {
	
	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable())
			.cors(cors->cors.disable())
			.authorizeHttpRequests(auth->auth.
					requestMatchers("/login").permitAll()
					.requestMatchers("/signup").permitAll()
					.requestMatchers("/logout").permitAll()
					.requestMatchers("/auth/**").authenticated()
					.anyRequest().authenticated()
	            )
	            .logout(logout -> logout
	                .permitAll()
	            )
					.exceptionHandling(ex->ex.authenticationEntryPoint(point))
					.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			;
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
		
		
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
