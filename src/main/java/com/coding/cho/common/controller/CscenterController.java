package com.coding.cho.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.cho.common.domain.dto.FaqBoardSaveDTO;
import com.coding.cho.common.service.CscenterService;
import com.coding.cho.common.service.FaqBoardService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class CscenterController {
	
	private final CscenterService service;
	
	private final FaqBoardService fBService;
	
	@GetMapping("/common/cscenter")
	public String cscenter() {
		return "user/cscenter/faq/list";
	}
	
	//faq list
	//faq divNo==8 자유게시판일 경우는 다른 다른 페이지로 이동
	@GetMapping("/common/faq/{divNo}") ///common/faq/{divNo}?page=1
	public String faqList(
			@PathVariable int divNo,
			@RequestParam(defaultValue = "1") int page,
			Model model) {
		
		if(divNo==8) {
			service.faqBoardListProcess(page, model);
			return "user/cscenter/faq/list-board-data";}
		else {
			service.faqListProcess(divNo, page, model);
			return "user/cscenter/faq/list-data";}
	}

	@GetMapping("/faq/board/write")
	public String write() {
		return "user/cscenter/faq/write";
	}
	
	@PostMapping("/faq/board/write")
	public String saveProcess(Authentication authentication, FaqBoardSaveDTO dto) {
		
		fBService.save(authentication.getName(), dto);
		return "redirect:/common/cscenter";
	}
}
