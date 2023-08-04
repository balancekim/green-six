package com.coding.cho.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemEntityREpository extends JpaRepository<CartItemEntity, Long> {

	

	

	CartItemEntity findByCartEntityNoAndGoodsNo(long no, long no2);

	List<CartItemEntity> findByCartEntityNo(long no);

	void deleteAllByCartEntity(CartEntity cart);

	

}
