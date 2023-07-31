package com.coding.cho.goods;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.cho.category.CategoryEntity;
import com.coding.cho.goods.dto.GoodsSaveDTO;
import com.coding.cho.goods.dto.SaleSaveDTO;

public interface SaleEntityRepository extends JpaRepository<SaleEntity, Long>{

	void save(SaleSaveDTO saledto);

	Optional<SaleEntity> findByGnoNo(long no);

	void deleteByGnoNo(long no);
	

}
