package com.drakend.scholarshipManage.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "personal")
public class Personal {

	@Id
	private String id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "phone_number")
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "university_id")
	private University university;
	
	@Column(columnDefinition = "TEXT")
	private String situation;
	
	@OneToMany(mappedBy = "personal")
	private Set<ScholarHistory> histories;
}
