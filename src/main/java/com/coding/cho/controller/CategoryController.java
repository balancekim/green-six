package com.coding.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coding.cho.service.CategoryService;
import com.coding.cho.service.impl.CategoryServiceProcess;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {
	
	private  final CategoryService service;//Constructor DI
	
	@GetMapping("/admin/category/new")
	public String write() {
		return "admin/category/write";
	}
	
	@PostMapping("/admin/category")
	public String save(String[] name) {
		service.saveProcess(name);
		return "redirect:/admin/category/new";
	}

}
