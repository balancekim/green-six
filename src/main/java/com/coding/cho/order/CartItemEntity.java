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
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cart_item")
@Entity
public class CartItemEntity {

	
	@Id 
	@Column(name="cart_item_id")
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cno")
	private CartEntity cartEntity;
	
	@JoinColumn(name = "gno", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private GoodsEntity goods;
	
	
	@Column(nullable = false)
	private int count;             //상품 개수
	
	
	public static CartItemEntity createCartItem(CartEntity cart, GoodsEntity goods, int amount) {
	        CartItemEntity cartItem = new CartItemEntity();
	        cartItem.setCartEntity(cart);
	        cartItem.setGoods(goods);
	        cartItem.setCount(amount);
	        return cartItem;
	    }
	
	public void addCount(int count) {
		this.count+=count;
		
	}
	public void deleteCount(int count) {
		this.count-=count;
		
	}
	
}
