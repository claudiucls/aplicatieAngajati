package com.cls.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cls.entities.Angajat;
import java.lang.String;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat, Long>{
	
	@Query("select a from Angajat a where a.nume like %?1% or a.prenume like %?1% order by a.dataTerminarii desc NULLS FIRST, a.dataAngajarii desc")
	Page<Angajat> findByNumeOrPrenume(String cauta,Pageable page);
	
}
