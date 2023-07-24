package com.coding.cho.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.domain.dto.cscenter.FaqListDTO;
import com.coding.cho.domain.entity.FaqDivision;
import com.coding.cho.domain.entity.FaqEntity;
import com.coding.cho.domain.entity.FaqEntityRepository;
import com.coding.cho.service.CscenterService;
import com.coding.cho.utils.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CscenterServiceProcess implements CscenterService {

	private final FaqEntityRepository faq;
	

	@Override
	public void faqListProcess(int divNo, int page, Model model) {
		FaqDivision division= FaqDivision.values()[divNo-1];
		int size=3;//한페이지에 표현할 데이터 개수
		Pageable pageable=PageRequest.of(page-1, size, Direction.DESC, "no");
		Page<FaqEntity> result= faq.findByDivision(division, pageable);
		model.addAttribute("list", result.getContent().stream()
									.map(FaqListDTO::new)
									.collect(Collectors.toList()));
		
		//페이지처리
		//model.addAttribute("pd", PageData.create(page, size, (int)result.getTotalElements() ));
		model.addAttribute("pd", PageData.create(page, size, (int)result.getTotalElements(), 3));
		
	}

}
