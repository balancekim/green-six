package com.coding.cho.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.order.dto.SaveCateDTO;
import com.coding.cho.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller 
public class OrderController {

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
	//하나삭제
	@ResponseBody
	@PostMapping("/order/item-delete")
	public ModelAndView itemDelete(SaveCateDTO dto) {
		System.out.println("자 컨트롤러 연결되냐?"+dto.getEmail()+"<하하>"+dto.getGno());
		
		os.itemDelete(dto);
		ModelAndView mv=new ModelAndView("order/cartInfo");
		os.showCart(dto,mv);
		return mv;
	}
	//장바구니비우기
	@ResponseBody
	@PostMapping("/order/delete-All")
	public ModelAndView deleteAll(SaveCateDTO dto) {
		System.out.println("자 컨트롤러 연결되냐?"+dto.getEmail()+"<하하>"+dto.getGno());
		
		os.deleteAll(dto);
		ModelAndView mv=new ModelAndView("order/cartInfo");
		os.showCart(dto,mv);
		return mv;
	}
	
}
