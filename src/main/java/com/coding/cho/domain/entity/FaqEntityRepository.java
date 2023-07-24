package com.coding.cho.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FaqEntityRepository extends JpaRepository<FaqEntity, Long>{

	List<FaqEntity> findByDivision(FaqDivision divsion);
	
	Page<FaqEntity> findByDivision(FaqDivision divsion, Pageable pageable);

}
