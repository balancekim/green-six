package com.coding.cho.order.service;

import org.springframework.ui.Model;

public interface CartService {

	void cartList(String email, Model model);

	void saveProcess(String name, long gno);

}
