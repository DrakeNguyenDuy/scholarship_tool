package com.drakend.scholarshipManage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GroupRoleKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7704550355433164047L;

	@Column(name="group_id")
	private String groupId;
	
	@Column(name="role_id")
	private String roleId;
}
