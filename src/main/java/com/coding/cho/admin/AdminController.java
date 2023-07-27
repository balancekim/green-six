package com.coding.cho.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.coding.cho.common.utils.FileUploadUtil;
import com.coding.cho.goods.GoodsEntity;
import com.coding.cho.goods.GoodsService;
import com.coding.cho.goods.dto.GoodsListDTO;
import com.coding.cho.goods.dto.GoodsSaveDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {

	private final GoodsService service;
	
	//admin index 페이지로 이동
	@GetMapping("/admin")
	public String admin() {
		return"admin/index";
	}
	
	//상품조회
	@ResponseBody
	@GetMapping("/admin/goods")
	public ModelAndView list() {
		ModelAndView mv=new ModelAndView("admin/goods/list");
		mv.addObject("list", service.list());
		return mv;
	}
	
	
	@ResponseBody
	@PostMapping("/admin/goods")
	public void save(GoodsSaveDTO dto) {
		service.save(dto);
	}
	
	//파일업로드
	@ResponseBody
	@PostMapping("/admin/goods/temp-img")
	public Map<String,String> tempUpload(MultipartFile temp){
		return service.tempUpload(temp);
	}
	
	//상품 상세 수정
	@GetMapping("/admin/goods/{no}")
	public String goodsUpdate(@PathVariable long no, Model model) {
		service.detailProcess(no, model); 
		return "admin/goods/details";
	}
	@ResponseBody
	@GetMapping("/franchisee/orderwait")
	public ModelAndView orderWait() {
		return new ModelAndView("franchisee/wait");
	}
	@ResponseBody
	@GetMapping("/franchisee/processing")
	public ModelAndView processing() {
		return new ModelAndView("franchisee/processing");
	}
	@ResponseBody
	@GetMapping("/franchisee/cancel")
	public ModelAndView cancel() {
		return new ModelAndView("franchisee/cancel");
	}
	@ResponseBody
	@GetMapping("/franchisee/end")
	public ModelAndView end() {
		return new ModelAndView("franchisee/end");
	}
	@ResponseBody
	@GetMapping("/index/goods1")
	public ModelAndView goods() {
		return new ModelAndView("index/goods/goods");
	}
}
