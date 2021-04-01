package com.websocket.config.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class StompEventListener {
	 @Autowired
	    private SimpMessageSendingOperations messagingTemplate;
	 
	 
	 @EventListener
	 public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		 System.out.println("connect");
		 System.out.println(event.toString());
		 
	 }
	 
	 @EventListener
	 public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		 StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		 
		 System.out.println("disconnect");
		 System.out.println(headerAccessor.toString());
	 }
	 
	 
}
