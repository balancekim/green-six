package com.coding.cho.orderBarket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OrderBarketController {
	
	private final OrderBarketService service;
	
	@GetMapping("/order/barket/{email}")
	public String list(Model model,@PathVariable String email) {
		service.list(model, email);
		
		return "order/orderBarket";
	}

}
