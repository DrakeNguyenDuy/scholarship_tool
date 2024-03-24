package com.matohela.scholarshipManage.helper;

import org.springframework.security.core.Authentication;

import com.matohela.scholarshipManage.service.impl.UserDetailImpl;

/**
 * <p>This class contain methods authentication helper</p>
 * @author NguyenDuyLong2810
 * 
 */
public class AuthHelper {
	private AuthHelper() {
	}

	/**
	 * @author NguyenDuyLong2810
	 * @param authentication
	 * @return String
	 */
	public static String getUserId(Authentication authentication) {
		UserDetailImpl userDetailImpl = (UserDetailImpl) authentication.getPrincipal();
		return userDetailImpl.getUser().getId();
	}
}
