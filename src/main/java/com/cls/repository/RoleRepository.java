package com.cls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cls.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Role findByRoleName(String role);

}
