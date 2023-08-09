package com.coding.cho.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.cho.common.domain.entity.MemberEntity;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long>{

	OrderEntity findByMember(MemberEntity member);

	List<OrderEntity> findAllByMember(MemberEntity member);

}
