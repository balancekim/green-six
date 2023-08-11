package com.coding.cho.order.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coding.cho.common.domain.entity.MemberEntity;
import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.goods.GoodsEntityRepository;
import com.coding.cho.map.StoreEntity;
import com.coding.cho.order.CartEntity;
import com.coding.cho.order.CartEntityRepository;
import com.coding.cho.order.CartItemEntity;
import com.coding.cho.order.CartItemEntityRepository;
import com.coding.cho.order.OrderEntity;
import com.coding.cho.order.OrderEntityRepository;
import com.coding.cho.order.OrderItemEntity;
import com.coding.cho.order.OrderItemEntityRepository;
import com.coding.cho.order.OrderStatus;
import com.coding.cho.order.dto.OrderItemListDTO;
import com.coding.cho.order.dto.OrderListDTO;
import com.coding.cho.order.service.OrderService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class OrderServiceProcess implements OrderService {
	
	private final CartItemEntityRepository ciRepo;
	private final CartEntityRepository cartRepo;
	private final MemberEntityRepository memRepo;
	private final OrderEntityRepository orderRepo;
	private final OrderItemEntityRepository oiRepo;
	private final GoodsEntityRepository gRepo;
	
	@Transactional
	@Override
	public void orderSaveProcess(String email, String uid) {
		
		MemberEntity member=memRepo.findAllByEmail(email);
		CartEntity cart=cartRepo.findByMember(member);
		StoreEntity store=cart.getStore();
		
		OrderEntity order=OrderEntity.builder().member(member).uid(uid).store(store).build().setStatus(OrderStatus.WAIT);
		
		orderRepo.save(order);
		
		List<CartItemEntity> cartItems=ciRepo.findByCart(cart);
		
		  for(int i=0; i<cartItems.size(); i++) { 
			  CartItemEntity cartItem=cartItems.get(i);
			  
			  oiRepo.save(OrderItemEntity.builder()
					  .goods(cartItem.getGoods())
					  .order(order) 
					  .count(cartItem.getCount())
					  .orderPrice(cartItem.getCount()*cartItem.getGoods().getPrice()) 
					  .build()); 
			  }
		 
		
		
		
	}

	@Override
	public void cartDeleteProcess(String email) {
		MemberEntity member=memRepo.findAllByEmail(email);
		CartEntity cart=cartRepo.findByMember(member);
		
		ciRepo.deleteByCart(cart);
		cartRepo.deleteByMember(member);
	}
	//주문 내역
	@Transactional
	@Override
	public void orderHistory(String name, Model model) {
		MemberEntity member=memRepo.findAllByEmail(name);
		Sort sort=Sort.by(Direction.DESC, "orderDate");
		List<OrderEntity> oe = orderRepo.findAllByMember(member, sort);
		
		List<OrderListDTO> list=oe.stream()
				.map(OrderListDTO::new)
				.collect(Collectors.toList());
		
		//Collections.reverse(list);
		
		
		
		model.addAttribute("list",list);
		
		
		
	}

	@Override
	@Transactional
	public void orderHistory(String name, Model model, String uid) {
		MemberEntity member=memRepo.findAllByEmail(name);
		OrderEntity oe = orderRepo.findByMemberAndUid(member,uid);//uid와 멤버번호로 오더 객체 찾음
		List<OrderItemEntity> oieList=oe.getOrderItems();
		System.out.println(oieList);
		
		  List<OrderItemListDTO> list=oieList.stream() .map(OrderItemListDTO::new)
		  .collect(Collectors.toList());
		 int totalPrice=0;
		for(OrderItemListDTO dto:list) {
			totalPrice+=dto.getPrice();
		}
		  
		  model.addAttribute("totalPrice", totalPrice);
		  model.addAttribute("list", list);
		 
	}

	
	

}