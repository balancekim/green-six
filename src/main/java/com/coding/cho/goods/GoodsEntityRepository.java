package com.coding.cho.goods;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.cho.goods.dto.GoodsSaveDTO;

public interface GoodsEntityRepository extends JpaRepository<GoodsEntity, Long>{

	

}
