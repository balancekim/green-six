package com.coding.cho.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.cho.common.domain.entity.MemberEntity;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long>{

	OrderEntity findByMember(MemberEntity member);

}
