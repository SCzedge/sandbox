package com.k8s.main;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final MainDao mainDao;
	
	public MainController(MainDao mainDao) {
		this.mainDao = mainDao;
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<?> fetch(){
		List<Map<String,Object>> result = mainDao.fetch();
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/submit")
	public ResponseEntity<?> submit(@RequestBody Map<String,Object> data){
		int result = mainDao.submit(data);
		return ResponseEntity.ok(result);
	}
	
	
}
