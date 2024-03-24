package com.matohela.scholarshipManage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Entity
@Table(name="token")
@Getter
@Setter
public class Token {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "access_token")
	private String accessToken;
	
	@Column(name = "expire_access_token")
	private long expireAccessToken;
	
	@Column(name = "refresh_token")
	private String refreshToken;
	
	@Column(name = "expire_refresh_token")
	private long expireRefreshToken;
}
