package com.coding.cho.orderBarket.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.order.CartEntity;
import com.coding.cho.order.CartEntityRepository;
import com.coding.cho.order.CartItemEntity;
import com.coding.cho.order.CartItemEntityREpository;
import com.coding.cho.orderBarket.OrderBarketService;
import com.coding.cho.orderBarket.dto.OrderBarketDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderBarketServiceProcess implements OrderBarketService {

	private final MemberEntityRepository mRepo;
	private final CartItemEntityREpository ciRepo;
	private final CartEntityRepository cRepo;
	
	@Override
	public void list(Model model, String email) {
		//email로 member_No 찾기
		long uno=mRepo.findByEmail(email).orElseThrow().getNo();
		System.out.println("========MemberNO========"+uno);
		//member_No로 cart_no 찾기
		CartEntity cartEntity=cRepo.findByMemberEntityNo(uno).orElseThrow();
		long cno = cartEntity.getNo();
		System.out.println("========CartNO========"+cno);
		//cart_no로 저장된 cartItem 확인
		List<CartItemEntity> cartItemList=ciRepo.findByCartEntityNo(cno);
		System.out.println("========CartItemList========"+cartItemList);
		List<OrderBarketDTO> dtoList= new ArrayList<>();
		
		for(CartItemEntity cartItem:cartItemList) {
			OrderBarketDTO dto = new OrderBarketDTO(cartItem, uno);
			dtoList.add(dto);
			System.out.println("============dto=============="+dto);
		}
		
		System.out.println("============dto리스트=============="+dtoList);
		
		model.addAttribute("list", dtoList);
		
		
	}

}
