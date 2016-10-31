package com.cls.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cls.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
