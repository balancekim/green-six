package com.coding.cho.order;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coding.cho.goods.GoodsEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table(name="order_item")
@Getter
@Setter
@Entity
@Builder
public class OrderItemEntity {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gno")
	private GoodsEntity goodsEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ono")
	private OrderEntity orderEntity;
	
	private int orderPrice;
	
	private int count;
	
	private LocalDateTime regTime;
	
	private LocalDateTime updateTime;
}
