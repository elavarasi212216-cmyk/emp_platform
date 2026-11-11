package com.employee_platform.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public abstract class jwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtTokenUtil jwtutil;
	private final CustomEmpDetailService customEmpDetail;
	
	public jwtAuthenticationFilter(JwtTokenUtil jwtutil,CustomEmpDetailService customEmpDetail) {
		this.jwtutil=jwtutil;
		this.customEmpDetail=customEmpDetail;
		
	}
	
	public void doFilterInternal(HttpServletRequest request,
			          			 HttpServletResponse response,
			          			 FilterChain filterchain) throws RuntimeException, IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		String token = null;
		
		if(StringUtils.hasText(token) && header.startsWith("Bearer")) {
			token = header.substring(7);
			
		}
		
		if(token!=null && jwtutil.validateToken(token)) {
			String userEmail= jwtutil.getUserEmail(token);
			
//			UserDetails userDetails;
			try {
				UserDetails	userDetails = customEmpDetail.loadUserByUserEmail(userEmail);
				
				UsernamePasswordAuthenticationToken authentication= 
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				SecurityContextHolder.clearContext();
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "user not found");
				return ;
			}
		
		}
		
		filterchain.doFilter(request, response);
		
	}

}
