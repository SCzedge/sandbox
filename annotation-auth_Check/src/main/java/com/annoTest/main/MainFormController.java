package com.annoTest.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainFormController {

	@GetMapping("/")
	public String main() {
		return "index";
	}
}
