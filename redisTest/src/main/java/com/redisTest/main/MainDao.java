package com.redisTest.main;

import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class MainDao {
	public String getCahceGen() {
		String uuid =UUID.randomUUID().toString(); 
		System.out.println("genUUID"+uuid);
		return uuid;
	}
}
