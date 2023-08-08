package com.coding.cho.order;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coding.cho.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OrderController {

	private final OrderService service;
	
	@ResponseBody
	@PostMapping("/cart/orderSave")
	public ResponseEntity<Boolean> orderSave(Authentication auth){
		service.orderSaveProcess(auth.getName());
		return ResponseEntity.ok().body(true);
	}
}
