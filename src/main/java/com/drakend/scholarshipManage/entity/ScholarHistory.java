package com.drakend.scholarshipManage.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "scholar_history")
public class ScholarHistory {

	@EmbeddedId
	private ScholarHistoryKey scholarHistoryKey;

	@ManyToOne
	@MapsId("personalId")
	@JoinColumn(name = "personal_id")
	private Personal personal;

	@ManyToOne
	@MapsId("eventId")
	@JoinColumn(name = "event_id")
	private ScholarAwardingEvent scholarAwardingEvent;

	@Column
	private double grade;

	@ManyToOne
	@JoinColumn(name = "semester_id")
	private Semester semester;

}
