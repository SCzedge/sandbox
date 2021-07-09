package com.redisTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	private final LoginSuccessHandler loginHandler;

	@Autowired
	public SpringSecurityConfig(LoginSuccessHandler loginHandler) {
		this.loginHandler = loginHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin().successHandler(loginHandler)
			.and()
			.authorizeRequests()
				.antMatchers("/").authenticated()
		;
	}
}
