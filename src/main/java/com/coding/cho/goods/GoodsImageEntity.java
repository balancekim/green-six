package com.coding.cho.goods;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goods_image")
@Entity
public class GoodsImageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	@Column(nullable = false)
	private String url; //s3경로
	@Column(nullable = false)
	private String orgName; 
	@Column(nullable = false)
	private String newName; 
	private String bucketKey; //파일명
	private boolean isDef ; //true:def-img
	
	@JoinColumn(name = "goods_no")
	@ManyToOne
	private GoodsEntity goods;
	
}
