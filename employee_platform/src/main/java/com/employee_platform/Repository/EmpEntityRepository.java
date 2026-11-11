package com.employee_platform.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee_platform.Entity.EmpEntity;

@Repository
public interface EmpEntityRepository extends JpaRepository<EmpEntity,Long> {
	
	Optional<EmpEntity>findByUserEmail(String userEmail);
}
