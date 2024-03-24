package com.matohela.scholarshipManage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.matohela.scholarshipManage.enums.StatusActive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditSection {
	
	  @Column(name = "date_created", nullable = false)
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateCreated;

	  @Column(name = "date_modified")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateModified;

	  @Column(name = "modified_by", length = 60, nullable = false)
	  private String modifiedBy;
	  
	  @Column(name = "create_by", length = 60, nullable = false)
	  private String createBy;
	  
	  @Column(name = "status" )
	  private StatusActive status;
}
