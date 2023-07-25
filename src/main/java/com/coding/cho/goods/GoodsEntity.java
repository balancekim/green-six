package com.coding.cho.goods;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coding.cho.common.domain.entity.CategoryEntity;

import lombok.Builder;

@Builder
@Table(name = "goods")
@Entity
public class GoodsEntity {

	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private long price;
	
	@OneToMany(mappedBy = "goods")
	private List<GoodsImageEntity> gie;
	
	
}

