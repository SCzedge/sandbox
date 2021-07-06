package com.redisTest.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainFormController {

	@GetMapping("/")
	public String index() {
		return "index.html";
	}

}
