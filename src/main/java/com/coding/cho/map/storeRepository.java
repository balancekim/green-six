package com.coding.cho.map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface storeRepository extends JpaRepository<StoreEntity, Long>{

	Page<StoreEntity> findByNameContaining(String searchKeyword, Pageable pageable);

}
