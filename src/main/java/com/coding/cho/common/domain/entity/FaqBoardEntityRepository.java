package com.coding.cho.common.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.cho.common.domain.dto.FaqBoardSaveDTO;

public interface FaqBoardEntityRepository extends JpaRepository<FaqBoardEntity, Long>{

	void save(FaqBoardSaveDTO dto);

}
