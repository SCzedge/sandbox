package com.annoTest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	private final Interceptor interceptor;
	
	public MvcConfig(Interceptor interceptor) {
		this.interceptor= interceptor;
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/login");
	}

}
