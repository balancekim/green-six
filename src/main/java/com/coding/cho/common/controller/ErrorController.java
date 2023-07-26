package com.coding.cho.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ErrorController {

	@GetMapping("/error/{Id}")
	public void error() {}
}
