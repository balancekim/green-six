package com.coding.cho.notices.event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.event.EventService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EventController {
	
	private final EventService es;
	
	
	@GetMapping("/event")
	public String event(Model model) {
		model.addAttribute("list", es.list());
			System.out.println("es.list"+es.list());
		return "notice/event/event";
	}
	@ResponseBody
	@GetMapping("/index/event")
	public ModelAndView notice() {
		
		return es.eventList();
		
	}
	@GetMapping("/event/{no}")
	public String noticeDetail(@PathVariable("no") long no, Model model) {
		es.detail(no, model);
		return "/notice/event/eventDetail";
	}
	 
	 
}
