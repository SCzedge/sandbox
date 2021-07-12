package com.annoTest.main;

import org.springframework.stereotype.Repository;

@Repository
public class MainDao {

	public UserVo getUser() {
		UserVo user = new UserVo();
		user.setKakao(false);
		user.setNaver(true);
		user.setPassword("{noop}123");
		return user;
	}
}
