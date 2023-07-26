package com.coding.cho.goods.dto;

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
	
	
	
}
