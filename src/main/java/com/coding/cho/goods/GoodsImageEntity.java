package com.coding.cho.goods;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Table(name = "goods_image")
@Entity
public class GoodsImageEntity {
	
	@Id
	@GeneratedValue(generator = "gen_item_img", strategy = GenerationType.SEQUENCE)
	private long no;
	@Column(nullable = false)
	private String url; //s3경로
	@Column(nullable = false)
	private String orgName; //s3경로
	@Column(nullable = false)
	private String newName; //s3경로
	private String bucketKey; //파일명
	private boolean isList ; //false:content-img
	private boolean isDef ; //true:def-img
	
	@JoinColumn(name = "goods_no")
	@ManyToOne
	private GoodsEntity goods;
	
}
