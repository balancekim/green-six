package com.coding.cho.goods.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsImageEntity;

import lombok.Data;
@Data
public class GoodsDetailDTO {

	private long gno;
	private String name;
	private String content;
	private int price;
	private boolean hotItem ;
	
	private long cNo;
	private String cName;

	private String[] url; //s3경로
	private String[] orgName; //s3경로
	private String[] newName; //s3경로
	private String[] bucketKey; //파일명
	private boolean[] def ; //true:def-img
	
	private List<GoodsImageDTO> gie ;
	
	public GoodsDetailDTO(GoodsEntity entity){
		this.gno=entity.getNo(); 
		this.name=entity.getName();
		this.content=entity.getContent();
		this.hotItem=entity.isHotItem();
		this.cNo=entity.getCategory().getNo();
		this.cName=entity.getCategory().getName();
		this.price=entity.getPrice();
		this.gie=entity.getGie()
				.stream().map(en->new GoodsImageDTO(en))
				.collect(Collectors.toList());
		
	}
	
}
