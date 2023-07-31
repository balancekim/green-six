package com.coding.cho.order.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.cho.common.domain.entity.MemberEntityRepository;
import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsEntityRepository;
import com.coding.cho.order.CartEntityRepository;
import com.coding.cho.order.CartItemEntity;
import com.coding.cho.order.CartItemEntityREpository;
import com.coding.cho.order.dto.SaveCateDTO;
import com.coding.cho.order.service.OrderService;

@Service
public class OrderServiceProcess implements OrderService {
	
	CartItemEntityREpository cir;
	CartEntityRepository cr;
	GoodsEntityRepository gr;
	MemberEntityRepository mr;

	@Override
	public void addGoods(SaveCateDTO dto) {
		
			
				
	}

	

}
