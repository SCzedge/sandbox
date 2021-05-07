package com.librarian.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class websocketController {

	@MessageMapping("/hello")
	@SendTo("/topic/hello")
	public Map<String,String> hello(@Payload String msg) {
		Map<String,String> rtn = new HashMap<>();
		System.out.println("msg : "+msg);
		rtn.put("msg", "YES");
		return rtn;
	}

}
