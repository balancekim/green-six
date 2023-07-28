package com.coding.cho.event;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsImageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "event")
@Entity
public class EventEntity {
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String content;
	
	
	@OneToMany(mappedBy = "event")
	private List<EventImageEntity> gie;
}