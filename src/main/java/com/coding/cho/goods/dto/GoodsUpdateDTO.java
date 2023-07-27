package com.coding.cho.goods.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsImageEntity;

import lombok.Data;
@Data
public class GoodsUpdateDTO {

	private long gno;
	private String name;
	private int price;

	private String[] url; //s3경로
	private String[] orgName; //s3경로
	private String[] newName; //s3경로
	private String[] bucketKey; //파일명
	private boolean[] def ; //true:def-img
	
	private List<GoodsImageDTO> gie ;
	
	public GoodsUpdateDTO(GoodsEntity entity){
		this.gno=entity.getNo(); 
		this.name=entity.getName();
		this.price=entity.getPrice();
		this.gie=entity.getGie()
				.stream().map(en->new GoodsImageDTO(en))
				.collect(Collectors.toList());
		
	}
}