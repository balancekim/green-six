package com.coding.cho.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntityRepository extends JpaRepository<CartEntity, Long> {

	Optional<CartEntity> findByMember_email(String email);

	

}
