package com.drakend.scholarshipManage.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "semester")
public class Semester {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column
	private String name;
	
	@Embedded
	private AuditSection auditSection;
	
	@OneToMany(mappedBy = "semester")
	private Set<ScholarHistory> scholarHistories;
}
