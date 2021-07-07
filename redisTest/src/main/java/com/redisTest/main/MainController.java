package com.redisTest.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	private final StringRedisTemplate stringRedisTemplate;
	private final MainServiceImpl mainService;

//	private final String KEY = "banana";

	@Autowired
	public MainController(StringRedisTemplate stringRedisTemplate, MainServiceImpl mainService) {
		this.stringRedisTemplate = stringRedisTemplate;
		this.mainService = mainService;
	}

	@GetMapping("/sessionid")
	public ResponseEntity<?> getSessionId(HttpSession session) {
		try {
			String ssid = session.getId().toString();
			return ResponseEntity.ok(ssid);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/redis/test/get/{key}")
	public ResponseEntity<?> getRedisTest(@PathVariable String key) {
		try {
			Map<String, String> redis = new HashMap<>();

			ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();

//			stringValueOperations.set("apple", "red");

			redis.put(key, stringValueOperations.get(key));

			return ResponseEntity.ok(redis);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/redis/test/cache/gen")
	public ResponseEntity<?> getCahceGen() {
		try {
			String result = mainService.getCahceGen();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();

		}
	}

	@GetMapping("/redis/test/cache/del")
	public ResponseEntity<?> getCahceDel() {
		try {
			String result = mainService.getCahceDel();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
