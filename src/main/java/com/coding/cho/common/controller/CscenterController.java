package com.coding.cho.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.cho.common.domain.dto.FaqBoardSaveDTO;
import com.coding.cho.common.domain.dto.cscenter.FaqBoardUpdateDTO;
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
	//faq 카테고리별 이동
	@GetMapping("/common/faq/{divNo}") ///common/faq/{divNo}?page=1
	public String faqList(
			@PathVariable int divNo,
			@RequestParam(defaultValue = "1") int page,
			Model model) {
		
			service.faqListProcess(divNo, page, model);
			return "user/cscenter/faq/list-data";
	}
	
	//문의하기 페이지로 이동
		@GetMapping("/common/faq/faqBoard") ///common/faq/{divNo}?page=1
		public String faqBoard(
				@RequestParam(defaultValue = "1") int page,
				Model model) {
				service.faqBoardListProcess(page, model);
				return "user/cscenter/faq/list-board-data2";
		}
	
	
	//faq 자유게시판으로 이동
	@GetMapping("/faq/board/write")
	public String write() {
		return "user/cscenter/faq/write";
	}
	//faq 자유게시판 작성
	@PostMapping("/faq/board/write")
	public String saveProcess(Authentication authentication, FaqBoardSaveDTO dto) {
		
		fBService.save(authentication.getName(), dto);
		return "redirect:/common/faq/faqBoard";
	}
	
	//faq 자유게시판 상세 페이지이동
	@GetMapping("/faq/board/detail/{no}")
	public String boardDetail(Model model, @PathVariable("no") long no) {
		fBService.faqBoardDetail(no, model);
		
		return "user/cscenter/faq/boardDetail";
	}
	
	//faq 자유게시판 게시글 수정페이지로 이동
	@GetMapping("/faq/board/modify/{no}")
	public String boardModify(Model model, @PathVariable("no") long no) {
		fBService.faqBoardModify(no, model);
		
		return "user/cscenter/faq/boardModify";
	}
	
	//faq 자유게시판 게시글 삭제
	@GetMapping("/faq/board/delete/{no}")
	public String boardDelete(Authentication authentication,@PathVariable("no") long no) {
		boolean isOwner = fBService.isOwner(authentication.getName(), no);
		if(isOwner) {
			fBService.faqBoardDelete(no);
			return "redirect:/common/faq/faqBoard";
		} else {
			return "redirect:/common/faq/faqBoard";
		}
	}
	
	@PostMapping("/faq/board/update/{no}")
	public String boardUpdate(FaqBoardUpdateDTO dto, @PathVariable("no") long no) {
		fBService.faqBoardupdate(dto, no);
		
		return "redirect:/faq/board/detail/{no}";
	}
	
}
