package com.coding.cho.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.coding.cho.order.OrderEntity;

import lombok.Data;

@Data
public class OrderDTO {
	private String name;
	private String uid;
	private LocalDateTime orderDate;
	
	List<OrderItemDTO> orderItem;
	
	
	public OrderDTO order(OrderEntity orderEntity){
		this.name=orderEntity.getMember().getName();
		this.uid=orderEntity.getUid();
		this.orderDate=orderEntity.getOrderDate();
		this.orderItem=orderEntity.getOrderItems().stream()
				.map(entity->new OrderItemDTO(entity))
				.collect(Collectors.toList());
		
		return this;
	}


}
