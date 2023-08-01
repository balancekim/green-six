package com.coding.cho.order.service;

import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.order.dto.SaveCateDTO;

public interface OrderService {

	

	void addGoods(SaveCateDTO dto);

	void showCart(SaveCateDTO dto, ModelAndView mv);

}
