package com.annoTest.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.annoTest.config.Auth;

@RestController
public class MainController {


//	@Auth(role=Role.KAKAO)
	@GetMapping("/kakao")
	@Auth("KAKAO")
	public ResponseEntity<?> user() {
		return null;
	}

	@GetMapping("/naver")
	@Auth("NAVER")
	public ResponseEntity<?> admin() {
		return null;
	}
}
