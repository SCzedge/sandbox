package com.annoTest.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.annoTest.main.UserVo;

@Configuration
public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===== interceptor =====");
		try {

			if (!(handler instanceof HandlerMethod)) {
				return true;
			} 
			
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			
			
			
			Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
			System.out.println(auth.value().toString());
			
			
			UserVo user = (UserVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
