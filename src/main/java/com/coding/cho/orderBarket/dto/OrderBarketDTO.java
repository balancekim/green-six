package com.coding.cho.orderBarket.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.dto.GoodsImageDTO;
import com.coding.cho.order.CartItemEntity;

import lombok.Data;

@Data
public class OrderBarketDTO {
	
	private long cno; //카트번호
	private long uno; //member번호
	private long gno; //상품 번호
	private List<GoodsImageDTO> gie ; //상품 이미지
	private String name; //상품명
	private boolean onSale; //세일 확인
	private int price; //일반 가격
	private int count; //구매 수량
	private int eaPrice; //세일 포함 개당 가격
	
	
	
	public OrderBarketDTO(CartItemEntity entity, long uno) {
		this.cno=entity.getCartEntity().getNo();
		this.uno=uno;
		this.gno=entity.getGoods().getNo();
		this.gie=entity.getGoods().getGie().stream()
				.distinct()
				.map(GoodsImageDTO::new)
				.collect(Collectors.toList());
		this.name=entity.getGoods().getName();
		this.onSale=entity.getGoods().isOnSale();
		this.price=entity.getGoods().getPrice();
		this.count=entity.getCount();
		//세일이 true일때 기간이 있으면 (entity.getGoods().getPrice() - entity.getGoods().getSale().getDiscount())
		//기간이 없으면 entity.getGoods().getPrice(), 
		//세일이 false이며 entity.getGoods().getPrice()
		this.eaPrice= saleCheck(entity.getGoods());
		
	}
	
	private int saleCheck(GoodsEntity goods) {
		int checkPrice;
		if(goods.isOnSale()==true) {
			boolean sd = goods.getSale().getStartDate().isBefore(LocalDateTime.now());
			boolean ed = goods.getSale().getEndDate().isAfter(LocalDateTime.now());
			if (sd == true && ed == true) {
				checkPrice=(goods.getPrice() - goods.getSale().getDiscount());
			} else { //할인시작일, 할인종료일이 없으면 tf 에 false 값 추가
				checkPrice=goods.getPrice();
			}
		} else {
			checkPrice=goods.getPrice();
		}
		return checkPrice;
		}
	
}
