package com.librarian;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

	@GetMapping("/")
	public String main() {
		return "index.html";
	}

	@GetMapping("/websocket/demo")
	public String websocktDemo() {
		return "/websocket/demo.html";
	}

	@GetMapping("kafka/basic/pdcm")
	public String kafkaBasicPdcm() {
		return "/kafka/basic/pdcm.html";
	}
	
	@GetMapping("/kafka/admin")
	public String kafkaAdmin() {
		return "/kafka/admin/admin.html";
	}
	
}
