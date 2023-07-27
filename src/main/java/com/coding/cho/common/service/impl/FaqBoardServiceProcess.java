package com.coding.cho.common.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.common.domain.dto.FaqBoardSaveDTO;
import com.coding.cho.common.domain.dto.cscenter.FaqBoardUpdateDTO;
import com.coding.cho.common.domain.entity.FaqBoardEntity;
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

	@Override
	public void faqBoardDetail(long no, Model model) {
		model.addAttribute("board", repo.findById(no).get());
	}

	@Override
	public void faqBoardModify(long no, Model model) {
		model.addAttribute("board", repo.findById(no).get());
	}

	@Override
	public void faqBoardDelete(long no) {
		repo.deleteById(no);
	}

	@Override
	public boolean isOwner(String name, long no) {
		FaqBoardEntity faqBoardEntity = repo.findById(no).get();
		if( faqBoardEntity != null && faqBoardEntity.getCreator().getEmail().equals(name)) {
			return true;
		} return false;
	}

	@Override
	@Transactional
	public void faqBoardupdate(FaqBoardUpdateDTO dto, long no) {
		repo.findById(no).map(e->e.updateTitleOrContent(dto));
		
	}
	
	

}
