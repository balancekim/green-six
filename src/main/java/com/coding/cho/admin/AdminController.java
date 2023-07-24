package com.coding.cho.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	
	//admin index 페이지로 이동
	@GetMapping("/admin")
	public String admin() {
		return"admin/index";
	}
	
}
