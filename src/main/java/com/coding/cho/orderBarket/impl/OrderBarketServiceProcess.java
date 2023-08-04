package com.coding.cho.orderBarket.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.order.CartItemEntityREpository;
import com.coding.cho.orderBarket.OrderBarketService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderBarketServiceProcess implements OrderBarketService {

	private final MemberEntityRepository mRepo;
	private final CartItemEntityREpository ciRepo;
	
	@Override
	public void list(Model model, String email) {
		
		
		
	}

}
