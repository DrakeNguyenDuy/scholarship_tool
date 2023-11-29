package com.drakend.scholarshipManage.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="scholar_awarding_event")
public class ScholarAwardingEvent {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private String address;
	
	@Column
	private double amount;
	
	@OneToMany(mappedBy = "scholarAwardingEvent")
	private Set<ScholarHistory> histories;

}
