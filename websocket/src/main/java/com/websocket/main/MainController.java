package com.websocket.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String main() {
		return "index";
	}
	@GetMapping("/stomp/test")
	public String test() {
		return "/websocket/test";
	}
}
