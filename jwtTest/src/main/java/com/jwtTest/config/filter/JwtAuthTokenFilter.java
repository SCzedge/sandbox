package com.jwtTest.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.jwtTest.config.provider.JwtTokenProvider;


public class JwtAuthTokenFilter extends GenericFilterBean{

	private final JwtTokenProvider jwtTokenProvider ;
	
	@Autowired 
	public JwtAuthTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		boolean auth = jwtTokenProvider.validateToken(token);

		
		HttpServletRequest hreq = (HttpServletRequest) request;
		
		if((token != null && auth)) {
			
			 Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
}
