package com.employee_platform.Security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.employee_platform.Entity.EmpEntity;
import com.employee_platform.Enum.Permissions;
import com.employee_platform.Repository.EmpEntityRepository;
@Service
public abstract class CustomEmpDetailService implements UserDetailsService {
	
	@Autowired
	private EmpEntityRepository empRepo;
	
	public UserDetails loadUserByUserEmail(String userEmail) throws Exception {
		
		EmpEntity user=empRepo.findByUserEmail(userEmail)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		Set<Permissions>permissions= RoleBasedPermissions.getRoleBasedPermissions()
				 					.get(user.getRole());
		
		return new org.springframework.security.core.userdetails
					  .User(user.getUserEmail(), user.getPassword(), null);
		
		
	}

}
