package com.coding.cho.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coding.cho.common.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter@Setter
public class OrderEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long no;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uno")
	private MemberEntity member; 
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus; //주문 상태
	
	@OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL
			, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItemEntity> orderItems = new ArrayList<>();
	
	private LocalDateTime regTime;
	
	private LocalDateTime updateTime;
}
