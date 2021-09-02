package com.librarian.kafka.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaBasicController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final SimpMessagingTemplate stompTemplate;

	@Autowired
	public KafkaBasicController(KafkaTemplate<String, String> kafkaTemplate, SimpMessagingTemplate stompTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.stompTemplate = stompTemplate;
	}

	@MessageMapping("/kafka/basic/producing")
	public void producing(@Payload String msg) {
		System.out.println(msg);
		kafkaTemplate.send("basic", msg);
	}

	@KafkaListener(topics = "basic", groupId = "foo")
	public void consume(String msg) {
		System.out.println(msg);
		stompTemplate.convertAndSend("/topic/kafka/basic/consume", msg);
	}

}
