package com.redisTest.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl {

	private final MainDao mainDao;

	@Autowired
	public MainServiceImpl(MainDao mainDao) {
		this.mainDao = mainDao;
	}
	
	@Cacheable(value="test")
	public String getCahceGen() {
		return mainDao.getCahceGen();
	}

	@CacheEvict(value="test")
	public String getCahceDel() {
		return null;
	}
}
