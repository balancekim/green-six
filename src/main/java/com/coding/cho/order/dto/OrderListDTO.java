package com.coding.cho.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.coding.cho.order.OrderEntity;
import com.coding.cho.order.OrderItemEntity;

import lombok.Data;

@Data
public class OrderListDTO {
	private long no;
	private String name;
	private String uid;
	private LocalDateTime orderDate;

	
	private int totalPrice;
	private int totalCount;
	private String mainGood;
	

	
	
	
	public  OrderListDTO(OrderEntity orderEntity){
		this.no=orderEntity.getNo();
		this.name=orderEntity.getMember().getName();
		this.uid=orderEntity.getUid();
		this.orderDate=orderEntity.getOrderDate();
		
		
		List<OrderItemEntity> oieList=orderEntity.getOrderItems();
		for(OrderItemEntity oie:oieList ) {
			this.totalPrice+=oie.getOrderPrice();
			this.totalCount+=oie.getCount();
			if(this.mainGood == null) {
			this.mainGood=oie.getGoods().getName();	
			}
		}
		
	}


}
