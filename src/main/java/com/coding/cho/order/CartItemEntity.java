package com.coding.cho.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coding.cho.goods.GoodsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "cart_item")
@Entity
public class CartItemEntity {

	
	@Id 
	@Column(name="cart_item_id")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cno")
	private CartEntity cartEntity;
	
	@JoinColumn(name = "gno", nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private GoodsEntity goods;
	
	
	@Column(nullable = false)
	private int count;
	
	
	public CartItemEntity addCount() {
		count++;
		return this;
	}
	
}
