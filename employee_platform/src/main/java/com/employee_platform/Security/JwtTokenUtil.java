package com.employee_platform.Security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.employee_platform.Entity.EmpEntity;
import com.employee_platform.Enum.Permissions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	private final Key key;
	private final long tokenValidate= 1000L *60 *60 *12;
	
	
	public JwtTokenUtil() {
		String secret= System.getenv("JWT_SCRECT");
		if(secret==null || secret.isEmpty()) {
			secret="Replace with this placewith a secret code";
		}
		key= Keys.hmacShaKeyFor(secret.getBytes());
		
	}
	
	
	public String generateToken(EmpEntity user) {
		
		Map<String,Object>claim= new HashMap<>();
		claim.put("Role", user.getRole().name());
		
		Set<Permissions>permissions=RoleBasedPermissions.getRoleBasedPermissions().get(user.getRole().name());
		
		Date now= new Date();
		Date expire= new Date(now.getTime()+tokenValidate);
		
		return Jwts.builder()
				.setClaims(claim)
				.setSubject(user.getUserEmail())
				.setIssuedAt(now)
				.setExpiration(expire)
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Claims getClaim(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
	
	public String getUserEmail(String token) {
		return getClaim(token).getSubject();
	}
	
	

}
