package com.websocket.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
//	@GetMapping("/")
//	public String main() {
//		System.out.println("인덱스는 탑니다요.");
//		return "/index.html";
//	}
//	@GetMapping("/stomp/test")
//	public String test() {
//		System.out.println("왜요또");
//		return "/websocket/test.html";
//	}
	@GetMapping("/")
	public String main() {
		System.out.println("인덱스는 탑니다요.");
		return "index";
	}
	@GetMapping("/stomp/test")
	public String test() {
		System.out.println("왜요또");
		return "/websocket/test";
	}
}
