package com.annoTest.config;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Auth{
//	public enum Role{
//		NAVER,
//		KAKAO,
//		GOOGLE
//	}
//	public Role role() default Role.NAVER;
	
	String value() default "";
}
