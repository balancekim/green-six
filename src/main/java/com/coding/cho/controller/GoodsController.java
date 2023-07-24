package com.coding.cho.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coding.cho.service.GoodsService;
import com.coding.cho.service.impl.GoodsServicePorcess;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GoodsController {
	
	private final GoodsService service;
	
	@GetMapping("/admin/goods/new")
	public String write() {
		return "admin/goods/write";
	}
	
	@ResponseBody
	@PostMapping("/common/goods/uploadSummernoteImg")
	public Map<String, String> uploadSummernoteImg(MultipartFile file) {
		
		return service.uploadSummernoteImgProcess(file);
	}

}
