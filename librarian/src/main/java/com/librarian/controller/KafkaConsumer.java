package com.librarian.controller;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener(topics="exam",groupId="foo")
	public void consume(String message)throws IOException{
		System.out.println(String.format("Consumed message : %s",message));
	}
}
