package com.websocket.test;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public TestVo test() {
		TestVo retVo = new TestVo();
		retVo.setMsg("hello world");
		return retVo;
	}
}
