package com.coding.cho.admin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coding.cho.common.service.GoodsService;
import com.coding.cho.common.utils.FileUploadUtil;
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
	
	
}
