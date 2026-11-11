package com.employee_platform.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled=true)
public class SecutityConfig {
	
	
	private JwtTokenUtil jwtUtil;
	private CustomEmpDetailService customUserDetail;
	
	public SecutityConfig(JwtTokenUtil jwtUtil,CustomEmpDetailService customUserDetail) {
		this.jwtUtil=jwtUtil;
		this.customUserDetail=customUserDetail;
		
	}
	
	
	@Bean
	public AuthenticationManager authenicationManager(AuthenticationConfiguration config)throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		
		http.csrf(crsf-> crsf.disable())
		    .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authorizeHttpRequests(auth->auth.requestMatchers("").permitAll().anyRequest().authenticated());
		
//		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
		
	}
	
//	@Bean
//	public jwtAuthenticationFilter jwtAuthenticationFilter() {
//		
//		return new jwtAuthenticationFilter(jwtUtil,customUserDetail);
//	}

}
