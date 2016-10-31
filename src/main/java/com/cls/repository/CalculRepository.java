package com.cls.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cls.entities.Calcul;

public interface CalculRepository extends JpaRepository<Calcul, Long>{
	
	@Query("select c from Calcul c where c.lunaCurenta =?1")
	Page<Calcul> listAllByDate(String luna,Pageable page); 
	
	@Query("select c from Calcul c group by c.lunaCurenta order by c.lunaCurenta desc")
	List<Calcul> listAllUnique();
	
	List<Calcul> findByLunaCurenta(String lunaCurenta);
	

}
