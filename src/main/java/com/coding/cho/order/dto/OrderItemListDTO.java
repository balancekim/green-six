package com.coding.cho.order.dto;

import com.coding.cho.order.OrderItemEntity;

import lombok.Data;

@Data
public class OrderItemListDTO {
	
	private String name;
	private int price;
	private int count;
	private int disCount;
	private String url;
	
	public OrderItemListDTO(OrderItemEntity ent) {
		
		this.name = ent.getGoods().getName();
		this.price = ent.getOrderPrice();
		this.count = ent.getCount();
		this.url = ent.getGoods().getGie().get(0).getUrl();
		if(ent.getGoods().isOnSale() ==  true){
		this.disCount = ent.getGoods().getSale().getDiscount();
		}
	}

	
}
