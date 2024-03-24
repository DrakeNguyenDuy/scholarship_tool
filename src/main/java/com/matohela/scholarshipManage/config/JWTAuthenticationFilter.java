package com.matohela.scholarshipManage.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.matohela.scholarshipManage.common.Constant;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * <p>
	 * This method will help filter request have token
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = extractJwtFromRequest(request);
		if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
			String email = jwtTokenProvider.extractUserEmail(jwt);
			UserDetails userDetail = userDetailsService.loadUserByUsername(email);
			if (userDetail != null) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetail, null, userDetail.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * <p>
	 * This method will help extract JWT from header request
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param request
	 * @return String
	 */
	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(Constant.AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constant.BEARER)) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
