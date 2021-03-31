package com.websocket.test;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
	
	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
	public MsgVo test(MsgVo msg) {
		
		
		System.out.println(msg.getMsg());
		
		MsgVo retVo = new MsgVo();
		retVo.setMsg("hello world");
		return retVo;
	}
}
