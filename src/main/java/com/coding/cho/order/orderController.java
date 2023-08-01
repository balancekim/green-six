package com.coding.cho.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.order.dto.SaveCateDTO;
import com.coding.cho.order.service.OrderService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller 
public class orderController {

	private final OrderService os;
	
	@ResponseBody
	@PostMapping("/order/addCart")
	public ModelAndView addCart(SaveCateDTO dto) {
		System.out.println("담기 누르면 gno 값이 오냐?"+dto);
		ModelAndView mv=new ModelAndView("order/cartInfo");	
		 os.addGoods(dto); 
		 
		 os.showCart(dto,mv);
			/* mv.addObject("cart",dto); */ 
		return mv;
	}
	
}
