package com.k8s.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainFormController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/")
	public String main() {
		return "index.html";
	}
	
	@GetMapping("/dbform")
	public String db() {
		return "db/main.html";
	}
}
