package com.annoTest.main;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class MainServiceImpl implements UserDetailsService {

	private final MainDao mainDao;
	
	public MainServiceImpl(MainDao mainDao) {
		this.mainDao = mainDao;
	}
	
	@Override
	public UserVo loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		return (UserVo) mainDao.getUser();
	}
}
