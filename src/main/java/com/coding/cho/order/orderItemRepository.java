package com.coding.cho.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface orderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
