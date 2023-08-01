package com.coding.cho.goods;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coding.cho.category.CategoryEntity;
import com.coding.cho.goods.dto.GoodsSaveDTO;


public interface GoodsEntityRepository extends JpaRepository<GoodsEntity, Long>{

	List<GoodsEntity> findByCategory(CategoryEntity categoryEntity);


	List<GoodsEntity> findByOnSale(boolean onSale);

	List<GoodsEntity> findByHotItem(boolean hotItem);
	
	


}
