package com.coding.cho.goods;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


	List<GoodsEntity> findByOnSaleAndSale_startDateLessThanEqualAndSale_endDateGreaterThanEqual(boolean b,
			LocalDateTime today, LocalDateTime today2);


	List<GoodsEntity> findByOnSaleAndSale_startDateIsNull(boolean b);


	List<GoodsEntity> findByOnSaleAndSale_startDateLessThanEqualAndSale_endDateGreaterThanEqualOrOnSaleAndSale_startDateIsNull(
			boolean b, LocalDateTime today, LocalDateTime today2, boolean c);


	List<GoodsEntity> findByOnSaleAndSale_startDateIsNullOrSale_startDateLessThanEqualAndSale_endDateGreaterThanEqual(
			boolean b, LocalDateTime today, LocalDateTime today2);


	




}
