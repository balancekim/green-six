package com.coding.cho.category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping("/category/{no}")
	public String caetegoryList(@PathVariable long no,Model model) {
		service.listProcess(no,model);
		return "admin/category/list";
	}
}
