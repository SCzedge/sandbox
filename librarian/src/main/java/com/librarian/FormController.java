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

	@GetMapping("kafka/basic")
	public String kafkaBasic() {
		return "/kafka/basic/basic.html";
	}

	@GetMapping("/kafka/admin")
	public String kafkaAdmin() {
		return "/kafka/admin/admin.html";
	}

	@GetMapping("/kafka/connect")
	public String kafkaConnect() {
		return "/kafka/connect/connect.html";
	}

}
