package com.annoTest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.formLogin().and()
//			.authorizeRequests()
//				.antMatchers("/login").permitAll()
//				.anyRequest().access("@authChecker.check(request, authentication)");
	}

}
