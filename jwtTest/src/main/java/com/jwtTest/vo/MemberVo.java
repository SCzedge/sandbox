package com.jwtTest.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author yjc
 *
 */
public class MemberVo implements UserDetails{

	private static final long serialVersionUID = 8613716957878288473L;
	
	private String email;
	private String pw;
	private String pw2;
	private String nickName;
	private String memberType;
	private String authToken;

	
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
       ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(memberType));
        return auth;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return pw;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

//	@JsonIgnore
	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getMemberType() {
		return memberType;
	}


	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	public  String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	@JsonIgnore
	public String getPw2() {
		return pw2;
	}

	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}

	@Override
	public String toString() {
		return "MemberVo [email=" + email + ", pw=" + pw + ", pw2=" + pw2 + ", nickName=" + nickName + ", memberType="
				+ memberType + ", authToken=" + authToken + "]";
	}
}
