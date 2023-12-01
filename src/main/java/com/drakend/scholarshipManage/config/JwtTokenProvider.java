package com.drakend.scholarshipManage.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.drakend.scholarshipManage.service.impl.UserDetailImpl;
import com.drakend.scholarshipManage.utils.DateHelpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Helper relate token
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Component
@Slf4j
public class JwtTokenProvider {

	private final String JWT_SECRET = "nguyenduylongakadrakend";

	private final long JWT_EXP = 5400000L;

	public String gennerateToken(UserDetailImpl detailImpl) {
		Date now = DateHelpers.getInstance();
		Date expDate = new Date(now.getTime() + JWT_EXP);
		return Jwts.builder().setSubject(detailImpl.getUser().getId()).setIssuedAt(now).setExpiration(expDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	/**
	 * <p>
	 * Extract user id from jwt
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param token
	 * @return String
	 */
	public String extractUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	/**
	 * <p>
	 * Get millisecond expire between expiration in token and now
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param token
	 * @return Long
	 */
	public Long getExpireIn(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		Date dateExpire = claims.getExpiration();
		Date now = DateHelpers.getInstance();
		Long exprieIn = dateExpire.getTime() - now.getTime();
		return exprieIn > 0 ? exprieIn : 0;
	}

	/**
	 * <p>
	 * Validate token
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param authToken
	 * @return boolean
	 */
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
