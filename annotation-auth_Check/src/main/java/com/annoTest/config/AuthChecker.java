package com.annoTest.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.annoTest.main.UserVo;

@Component
public class AuthChecker {

	public boolean check(HttpServletRequest request, Authentication authentication) {
		try {

			UserVo user = (UserVo) authentication.getPrincipal();

			System.out.println(user.toString());
			System.out.println(request.getMethod().toString());
			System.out.println(request.getClass().getName());
			System.out.println(request.getClass().getSimpleName());
			System.out.println(request.getRequestURI().getClass().toString());
			
			System.out.println("tes");

			
			
			
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
