package com.coding.cho.order.service;

public interface OrderService {

	void orderSaveProcess(String email, String uid);

	void cartDeleteProcess(String email);

}
