package com.coding.cho.common.service.impl;

import org.springframework.stereotype.Service;

import com.coding.cho.common.domain.dto.FaqBoardSaveDTO;
import com.coding.cho.common.domain.entity.FaqBoardEntityRepository;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.common.service.FaqBoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqBoardServiceProcess implements FaqBoardService{
	
	private final FaqBoardEntityRepository repo;
	
	private final MemberEntityRepository mRepo;
	

	@Override
	public void save(String userId, FaqBoardSaveDTO dto) {
		repo.save(dto.toEntity(mRepo.findByEmail(userId).get()));
		
	}
	
	

}
