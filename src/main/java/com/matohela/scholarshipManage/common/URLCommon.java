package com.matohela.scholarshipManage.common;

/**
 * <p>
 * This class contain all URL of application
 * </p>
 * 
 * @author NguyenDuyLong2810
 * 
 */
public class URLCommon {
	// common
	public static final String ID = "/{id}";

	// authentication
	public static final String AUTH = "/auth";
	public static final String REGISTER = "/register";
	public static final String ADMIN = "/admin";
	public static final String CREATE_SUBADMIN = "/create-subadmin";
	public static final String LOGIN = "/login";

	// permission-role-group
	public static final String GROUP = "/groups";
	public static final String PERMISSION = "/permissions";
	public static final String ROLE = "/roles";
	public static final String INDIVIDUAL_ROLE = ROLE + ID;
	public static final String INDIVIDUAL_GROUP = GROUP + ID;
	public static final String INDIVIDUAL_PERMISSION = PERMISSION + ID;
	
}
