package com.employee_platform.DTC;

import com.employee_platform.Enum.Role;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
	
	public String userName;
	public String userEmail;
	public String phone;
	public String password;
	public Role role;
	
	

}
