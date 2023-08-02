package com.coding.cho.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.order.dto.DeleteCountDTO;
import com.coding.cho.order.dto.SaveCateDTO;
import com.coding.cho.order.service.OrderService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller 
public class orderController {

	private final OrderService os;
	//저장 후 뿌리기
	@ResponseBody
	@PostMapping("/order/addCart")
	public ModelAndView addCart(SaveCateDTO dto) {
		ModelAndView mv=new ModelAndView("order/cartInfo");	
		 os.addGoods(dto);//받은 dto 값으 
		 
		 os.showCart(dto,mv);
			/* mv.addObject("cart",dto); */ 
		return mv;
	}
	//삭제 후 뿌리기
	@ResponseBody
	@PostMapping("/order/delete-count")
	public ModelAndView deleteCount(SaveCateDTO dto) {
		System.out.println("자 컨트롤러 연결되냐?"+dto.getEmail()+"<하하>"+dto.getGno());
		
		os.deleteCount(dto);
		ModelAndView mv=new ModelAndView("order/cartInfo");
		os.showCart(dto,mv);
		return mv;
	}
	
}
