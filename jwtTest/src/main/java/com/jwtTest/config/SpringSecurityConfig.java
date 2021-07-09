package com.jwtTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import com.jwtTest.config.filter.CorsFilter;
import com.jwtTest.config.filter.JwtAuthTokenFilter;
import com.jwtTest.config.provider.JwtTokenProvider;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	JwtTokenProvider JwtTokenProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		 TODO Auto-generated method stub
		
	      http
	      .httpBasic().disable()
	      .csrf().disable()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and()
//	      .formLogin().disable()
	      
	      
	      
	      
	      .authorizeRequests()
	      .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	      .antMatchers("/admin/**").hasRole("ADMIN")
	      .antMatchers("/member/**").hasRole("USER")
          .anyRequest().permitAll()
          .and()
          .addFilterBefore(new CorsFilter(),SessionManagementFilter.class)
          .addFilterBefore(new JwtAuthTokenFilter(JwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}