package com.employee_platform.Entity;

import com.employee_platform.Enum.Role;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name="emp_entity")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmpEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String userName;
	@Column(nullable=false,unique=true)
	private String userEmail;
	@Column(nullable=false)
	private String password;
	
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	private String department;

//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}
	
//	public EmpEntity() {}
//	public EmpEntity(Long id,
//					 String userName,
//					 String userEmail,
//					 String password,
//					 String phone,
//					 Role role) {
//		this.id=id;
//		this.userName=userName;
//		this.userEmail=userEmail;
//		this.password=password;
//		this.phone=phone;
//		this.role=role;
//		
//		
//	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	
}
