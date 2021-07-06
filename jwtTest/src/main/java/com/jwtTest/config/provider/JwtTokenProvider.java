package com.jwtTest.config.provider;


import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtTest.vo.MemberVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtTokenProvider {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	private String secretKey;
	private long validityInMilliseconds;

    
    public JwtTokenProvider(
            @Value("${secretKey.jwt.token.JWTSecretKey}") String secretKey,
            @Value("${secretKey.jwt.token.expire-length}") long validityInMilliseconds) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }
    
    
//login
    @SuppressWarnings("unchecked")
    public String generateLoginToken(MemberVo vo) throws UnsupportedEncodingException {
    	ObjectMapper objMapper = new ObjectMapper();
    	vo.setPw(null);
    	Map<String,Object> map = objMapper.convertValue(vo,Map.class); 
    	
    	Claims claims = Jwts.claims(map);
    	
    	Date now = new Date();
    	Date validity = new Date(now.getTime() + validityInMilliseconds);
    	
    	return Jwts.builder()
    			.setClaims(claims)
    			.setIssuedAt(now)
    			.setExpiration(validity)
    			.signWith(SignatureAlgorithm.HS512, secretKey)
    			.compact();
    }
    
    // Request의 Header에서 token 값추출. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    
    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
    	Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        
    	MemberVo vo= new MemberVo();
    	vo.setEmail((String) claims.getBody().get("email"));
    	vo.setMemberType((String)claims.getBody().get("memberType"));
    	return new UsernamePasswordAuthenticationToken(vo, "", vo.getAuthorities());
    }
}