package com.coding.cho.goods.dto;

import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsImageEntity;

import lombok.Data;

@Data
public class GoodsListDTO {

	private String name;
	private int price;
	private String url;
	private long no;
	
	public GoodsListDTO(GoodsEntity entity){
		this.name=entity.getName();
		this.price=entity.getPrice();
		this.url=entity.getGie().stream()
				.filter(ie -> ie.isDef())//isDef가 true인 GoodsImageEntity 객체만 추출
                .findFirst() //그중 첫번째 데이터만
                .map(GoodsImageEntity::getUrl) // 객체에서 url필드만 리턴
                .orElseThrow();//예외처리
		this.no=entity.getNo();
		
	}
}