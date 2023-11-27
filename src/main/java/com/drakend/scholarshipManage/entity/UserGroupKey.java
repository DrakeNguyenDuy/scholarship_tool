package com.drakend.scholarshipManage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserGroupKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3642341275722993261L;

	@Column(name="user_id")
	private String userId;
	
	@Column(name="group_id")
	private String groupId;
}
