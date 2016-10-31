package com.cls.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cls.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Role findByRoleName(String role);

}
