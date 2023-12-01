package com.drakend.scholarshipManage.common;

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
	public static final String CREATE = "/create";
	public static final String EDIT = "/edit";
	public static final String DELETE = "/delete";
	public static final String ID = "/{id}";

	// authentication
	public static final String AUTH = "/auth";
	public static final String REGISTER = "/register";
	public static final String ADMIN = "/admin";
	public static final String CREATE_SUBADMIN = "/create-subadmin";
	public static final String LOGIN = "/login";

	// permission-role-group
	private static final String GROUP = "/groups";
	private static final String PERMISSION = "/permissions";
	private static final String ROLE = "/roles";
	public static final String EDIT_ROLE = ROLE + EDIT + ID;
	public static final String CREATE_ROLE = ROLE + CREATE;
	public static final String DELETE_ROLE = ROLE + DELETE + ID;
	public static final String EDIT_GROUP = GROUP + EDIT + ID;
	public static final String EDIT_PERMISSION = PERMISSION + EDIT + ID;
	public static final String ADD_USER_TO_GROUP = GROUP + "/add-user" + ID;
}
