package com.coding.cho.map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService {

	Page<StoreEntity> storeList(Pageable pageable);

	Page<StoreEntity> storeSearchList(String searchKeyword, Pageable pageable);

}
