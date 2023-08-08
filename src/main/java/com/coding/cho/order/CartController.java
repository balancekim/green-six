package com.coding.cho.order;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coding.cho.order.service.CartService;
import com.coding.cho.order.service.impl.CartServiceProcess;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CartController {
	
	private final CartService service;

	@GetMapping("/cart")
	public String cart(Authentication auth, Model model) {
		
		service.cartList(auth.getName(), model);
		return "cart/list";
	}
	
	//비동기 카드담기
	@ResponseBody
	@PostMapping("/cart")
	public ResponseEntity<Boolean> save(Authentication auth, long gno) {
		service.saveProcess(auth.getName(), gno);
		return ResponseEntity.ok().body(true);
	}
}
