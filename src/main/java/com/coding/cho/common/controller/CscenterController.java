package com.coding.cho.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.cho.common.service.CscenterService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class CscenterController {
	
	private final CscenterService service;
	
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
		service.faqListProcess(divNo, page, model);
		
		if(divNo==8) {return "user/cscenter/faq/list-board-data";}
		else {return "user/cscenter/faq/list-data";}
	}

	@GetMapping("/faq/board/write")
	public String write() {
		return "user/cscenter/faq/write";
	}
	
}
