package com.drakend.scholarshipManage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScholarHistoryKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 136464191525356308L;

	@Column(name = "personal_id")
	private String personalId;

	@Column(name = "event_id")
	private String eventId;
}
