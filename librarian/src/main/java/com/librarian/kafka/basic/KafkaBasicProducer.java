package com.librarian.kafka.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaBasicProducer {
   
	private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public KafkaBasicProducer(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void pdBasic(String msg) {
    	kafkaTemplate.send("basic",msg);
    }
    
}
