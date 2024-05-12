package com.desafio.tecnico.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.desafio.tecnico.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	@Value("${validation.jwtKey}")
	private String JWT_KEY;


	public String generateAccessToken(User user) {
		return Jwts.builder()
				.setSubject(String.format("%s", user.getName()))
				.setIssuedAt(new Date())				
				.signWith(SignatureAlgorithm.HS512, JWT_KEY)
				.compact();

	}

}
