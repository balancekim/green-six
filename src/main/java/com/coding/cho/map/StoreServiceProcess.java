package com.coding.cho.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class StoreServiceProcess implements StoreService {
	
	@Autowired
	private storeRepository repo;
	
	//업체 리스트
	@Override
	public Page<StoreEntity> storeList(Pageable pageable) {
		return repo.findAll(pageable);
	}
	//키워드 검색
	@Override
	public Page<StoreEntity> storeSearchList(String searchKeyword, Pageable pageable) {
		return null;
	}
	
}
