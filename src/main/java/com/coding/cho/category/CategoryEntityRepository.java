package com.coding.cho.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{
	
	//최상위카테고리 목록
	List<CategoryEntity> findByParentIsNull();
	
	//하위카테고리 목록
	List<CategoryEntity> findByParent(CategoryEntity parent);

	Optional<CategoryEntity> findByNameAndParent(String categornName, CategoryEntity parent);

}
