package com.websocket.test;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
	
	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
	public MsgVo test(@Payload MsgVo msg) {
		
		
		System.out.println(msg.getMsg());
		
		MsgVo retVo = new MsgVo();
		retVo.setMsg("hello world");
		return msg;
	}
}
