package com.coding.cho.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MapController {
	
	@Autowired
	private StoreService storeService;
	
	/*
	 * @GetMapping("/map") public String map() { return "map/map"; }
	 */
	//업체 리스트를 페이징하여 보여주기
		@GetMapping("/map")
		public String storeList(Model model, 
								@PageableDefault(page=0, size=10, sort="no", direction = Sort.Direction.DESC) Pageable pageable,//페이징
								String searchKeyword) {
			
			Page<StoreEntity> list= null;
			
			if(searchKeyword == null) {
				list = storeService.storeList(pageable);
			}else {
				list = storeService.storeSearchList(searchKeyword, pageable);
			}
			
			int nowPage = list.getPageable().getPageNumber()+1;
			int startPage = Math.max(nowPage-4, 1);
			int endPage = Math.min(nowPage +5, Math.max(1, list.getTotalPages()));
			System.out.println(list.getContent().size());
			
			model.addAttribute("list", list.getContent());
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			
			return "map/map";
		}
	
}
