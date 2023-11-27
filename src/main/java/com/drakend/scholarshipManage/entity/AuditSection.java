package com.drakend.scholarshipManage.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.drakend.scholarshipManage.enums.StatusActive;

@Embeddable
public class AuditSection {
	 
//	  @Column(name = "date_created")
//	  private Date dateCreated;
//
//	  @Column(name = "date_modified")
//	  private Date dateModified;

	  @Column(name = "modified_by", length = 60)
	  private String modifiedBy;
	  
	  @Column(name = "create_by", length = 60)
	  private String createBy;
	  
	  @Column(name = "status")
	  private StatusActive status;
}
