package com.coding.cho.goods.dto;

import com.coding.cho.goods.SaleEntity;

import lombok.Getter;

@Getter
public class SaleListDTO {
	
	private int discount;
	private String startDate;
	private String endDate;
	
	private GoodsListDTO goods;

	public SaleListDTO(SaleEntity entity) {
		this.discount = entity.getDiscount();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
		this.goods = new GoodsListDTO(entity.getGno());
	}
	
	

}
