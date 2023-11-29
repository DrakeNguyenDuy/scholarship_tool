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
import lombok.Setter;

@Entity(name="university")
@Getter
@Setter
public class University {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column
	private String name;
	
	@Embedded
	private AuditSection auditSection;
	
	@OneToMany(mappedBy = "university")
	private Set<Personal> personals;
}
