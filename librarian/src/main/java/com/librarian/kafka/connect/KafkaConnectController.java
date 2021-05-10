package com.librarian.kafka.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/kafka")
public class KafkaConnectController {

	
	
	public final RestTemplate restTemplate;
	
	@Autowired
	public KafkaConnectController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Value("${spring.kafka.connect.connect-servers}")
	public String connectServer;
	
	@GetMapping("/connect/status")
	public ResponseEntity<?> getStatus(){
		String result = restTemplate.getForObject(connectServer, String.class);
		return ResponseEntity.ok(result);
	}
	
	
	
	
	@GetMapping("/connect/connector-plugins")
	public ResponseEntity<?> getPlugins(){
		String result = restTemplate.getForObject(connectServer+"/connector-plugins", String.class);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}
	@GetMapping("/connect/connectors")
	public ResponseEntity<?> getConnectors(){
		String result = restTemplate.getForObject(connectServer+"/connectors", String.class);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}
	
}
