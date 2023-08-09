package com.coding.cho.order.dto;

import java.util.List;

import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.order.CartItemEntity;
import com.coding.cho.order.OrderEntity;

import lombok.Data;

@Data
public class OrderItemSaveDTO {

	private int count; //
	private int orderPrice;
	private GoodsEntity goods;
	private OrderEntity order;
	
	public List<OrderItemSaveDTO> dto(List<CartItemEntity> cartItem) {
		return null;
		
	}
}
