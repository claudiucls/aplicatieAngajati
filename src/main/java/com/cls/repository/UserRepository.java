package com.cls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cls.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
