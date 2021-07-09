package com.redisTest.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

@Component
public class SessionConfig implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(se.getSession().getAttribute("stest"));
		System.out.println("LOG_OUT : "+se.getSession().getId());
	}

}
