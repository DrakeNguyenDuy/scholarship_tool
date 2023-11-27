package com.drakend.scholarshipManage.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "user_group")
@Getter
public class UserGroup {
	
	@EmbeddedId
	private UserGroupKey userGroupKey;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("groupId")
	@JoinColumn(name = "group_id")
	private Group group;
}
