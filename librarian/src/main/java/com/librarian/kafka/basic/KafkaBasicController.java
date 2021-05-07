package com.librarian.kafka.basic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
@RestController
public class KafkaBasicController {
	private final KafkaBasicProducer producer;
	
	@Autowired
	public KafkaBasicController(KafkaBasicProducer producer) {
		this.producer=producer;
	}
	
	
	@MessageMapping("/kafka/basic/producing")
	public void producing(@Payload String msg) {
		producer.pdBasic(msg);
	}
	
	@SendTo("/kafka/basic/consume")
	public Map<String,String> consume(String msg) {
		
		Map<String,String> rtn = new HashMap<>();
		rtn.put("msg", msg);
		
		return rtn;
	}
	
	public void test() {
		SimpleBrokerMessageHandler.sendMessageToSubscribers("te","ESAF");
	}
}
