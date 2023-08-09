package com.coding.cho.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.coding.cho.common.domain.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
@Getter
public class OrderEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long no;
	
	@ManyToOne
	@JoinColumn(name = "uno")
	private MemberEntity member; 
	
	@Column(nullable = false)
	private String uid;
	
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime orderDate;
	
	@CollectionTable(name = "OrderStatus", joinColumns =@JoinColumn(name = "ono") )
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)//1:N
	@Builder.Default
	@Column(name = "orderStatus",nullable = true)
	private Set<OrderStatus> orderStatus=new HashSet<>(); //주문 상태
	
	public OrderEntity addStatus(OrderStatus status) {
		orderStatus.add(status);
		return this;
	}
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL
			, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItemEntity> orderItems;
	
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime updateTime;
	
}
