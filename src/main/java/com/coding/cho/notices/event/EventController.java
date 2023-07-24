package com.coding.cho.notices.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {
	
	@GetMapping("/event")
	public String event() {
		return "notice/event/event";
	}
	
}
