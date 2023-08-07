package com.coding.cho.map;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.cho.common.domain.entity.MemberEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>{

	Page<StoreEntity> findByNameContaining(String searchKeyword, Pageable pageable);

	Optional<StoreEntity> findByMember(MemberEntity entity);

}
