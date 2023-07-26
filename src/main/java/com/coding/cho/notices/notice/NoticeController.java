package com.coding.cho.notices.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeController {
	
	private final NoticeService service;
	
	@GetMapping("/notice")
	public String notice(Model model) {
		service.findAll(model);
		return "notice/notice/notice";		
	}
	
	@GetMapping("/notice-write")
	public String noticeWrite(){
		return "notice/notice/notice-write";
	}
	
	@PostMapping("/notice-write")
	public String noticeWrite2(NoticeDTO dto) {
		service.saveNotice(dto);
		
		return "notice/notice/notice";
	}
}
