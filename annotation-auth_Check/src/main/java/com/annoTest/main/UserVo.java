package com.annoTest.main;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserVo implements UserDetails {
	private static final long serialVersionUID = 1299161221958260018L;
	public String username;
	public String password;
	public boolean naver;
	public boolean kakao;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isNaver() {
		return naver;
	}

	public void setNaver(boolean naver) {
		this.naver = naver;
	}

	public boolean isKakao() {
		return kakao;
	}

	public void setKakao(boolean kakao) {
		this.kakao = kakao;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("null"));
		return auth;
	}

	@Override
	public String toString() {
		return "UserVo [username=" + username + ", password=" + password + ", naver=" + naver + ", kakao=" + kakao
				+ "]";
	}

}
