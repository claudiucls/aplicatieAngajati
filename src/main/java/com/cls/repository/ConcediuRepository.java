package com.cls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cls.entities.Concediu;

@Repository
public interface ConcediuRepository extends JpaRepository<Concediu, Integer>{

}
