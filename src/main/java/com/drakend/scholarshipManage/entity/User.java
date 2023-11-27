package com.drakend.scholarshipManage.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

@Entity(name = "user")
@Getter
public class User {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "citizend_id")
	private String citizenId;

	@Embedded
	private AuditSection auditSection;

	@OneToMany(mappedBy = "user")
	private Set<UserGroup> userGroups;

}
