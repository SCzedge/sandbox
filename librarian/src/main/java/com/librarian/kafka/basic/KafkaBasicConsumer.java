package com.librarian.kafka.basic;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaBasicConsumer {
	
	private final KafkaBasicController controller;
	
	@Autowired
	public KafkaBasicConsumer(KafkaBasicController controller) {
		this.controller = controller;
	}
	
	
    @KafkaListener(topics = "basic", groupId = "foo")
    public void consume(String msg) throws IOException {
        System.out.println(String.format("Basic Consumed: %s", msg));
        controller.consume(msg);
        
    }
	
	
}
