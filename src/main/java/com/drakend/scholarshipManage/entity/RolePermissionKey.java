package com.drakend.scholarshipManage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RolePermissionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 171580102561114530L;

	@Column(name="role_id")
	private String roleId;
	
	@Column(name="permission_id")
	private String permissionId;
}
