package com.coding.cho.order.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.common.domain.entity.MemberEntity;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsEntityRepository;
import com.coding.cho.map.StoreDTO;
import com.coding.cho.map.StoreEntityRepository;
import com.coding.cho.order.CartEntity;
import com.coding.cho.order.CartEntityRepository;
import com.coding.cho.order.CartItemEntity;
import com.coding.cho.order.CartItemEntityRepository;
import com.coding.cho.order.OrderEntity;
import com.coding.cho.order.OrderEntityRepository;
import com.coding.cho.order.dto.CartDTO;
import com.coding.cho.order.dto.SaveCateDTO;
import com.coding.cho.order.service.OrderService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class OrderServiceProcess implements OrderService {
	
	private final CartItemEntityRepository ciRepo;
	private final CartEntityRepository cartRepo;
	private final MemberEntityRepository memRepo;
	private final OrderEntityRepository orderRepo;
	
	@Transactional
	@Override
	public void orderSaveProcess(String email) {
		
		MemberEntity member=memRepo.findAllByEmail(email);
		orderRepo.save(OrderEntity.builder()
					.member(member)
					.build());
		
		CartEntity cart=cartRepo.findAllByMember_email(email);
		
		
		
		
	}
	
	

}
