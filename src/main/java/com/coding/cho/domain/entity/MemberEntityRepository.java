package com.coding.cho.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>{

	boolean existsByEmail(String email);

	Optional<MemberEntity> findByEmail(String email);

}
