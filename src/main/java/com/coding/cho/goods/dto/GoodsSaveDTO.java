package com.coding.cho.goods.dto;

import java.util.List;

import javax.transaction.Transactional;

import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsImageEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsSaveDTO {

	private long gno;
	private String name;
	private int price;

	private String[] url; //s3경로
	private String[] orgName; //s3경로
	private String[] newName; //s3경로
	private String[] bucketKey; //파일명
	private boolean[] def ; //true:def-img
	
	private List<GoodsImageEntity> gie ;
	
	
	public GoodsSaveDTO(GoodsEntity entity){
		this.gno=entity.getNo(); 
		this.name=entity.getName();
		this.price=entity.getPrice();
		this.gie=entity.getGie();
		
	}
	
}

