package com.coding.cho.notices.event;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.event.EventEntity;
import com.coding.cho.event.EventListDTO;
import com.coding.cho.event.EventService;
import com.coding.cho.map.StoreEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EventController {
	
	private final EventService es;
	
	
	  @GetMapping("/event") public String event(Model model) {
	  model.addAttribute("list", es.list());
	  System.out.println("es.list"+es.list()); return "notice/event/event"; }
	 
	
	 
	 
	
	/*
	 * @GetMapping("/event") public String event(Model model,
	 * 
	 * @PageableDefault(page=0, size=4, sort="no", direction = Sort.Direction.DESC)
	 * Pageable pageable) { Page<EventEntity> list= null; List<EventEntity>
	 * allList=es.eventAllList(); list = es.eventList(pageable);
	 * 
	 * int nowPage = list.getPageable().getPageNumber()+1; int startPage =
	 * Math.max(nowPage-4, 1); int endPage = Math.min(nowPage +5, Math.max(1,
	 * list.getTotalPages())); System.out.println(list.getContent().size());
	 * 
	 * model.addAttribute("allList",allList);
	 * model.addAttribute("list2",list.getContent()); model.addAttribute("nowPage",
	 * nowPage); model.addAttribute("startPage", startPage);
	 * model.addAttribute("endPage",endPage); model.addAttribute("list", es.list());
	 * 
	 * return "notice/event/event"; }
	 */
	
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
