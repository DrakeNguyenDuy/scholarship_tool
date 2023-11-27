package com.drakend.scholarshipManage.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.drakend.scholarshipManage.service.impl.UserDetailImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

	private final String JWT_SECRET = "lodaaaaaa";

	private final long JWT_EXP = 604800000L;

	public String gennerateToken(UserDetailImpl detailImpl) {
		Date now = new Date();
		Date expDate = new Date(now.getTime() + JWT_EXP);
		return Jwts.builder().setSubject(detailImpl.getUser().getId()).setIssuedAt(expDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	public String extractUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parsePlaintextJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
}
