package com.hongwei.futures.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userId;
	
	private String username;
	
	private Timestamp createTime;
	
	private Timestamp recoverTime;
	
	public Ticket() {
		super();
	}

	public Ticket(Long userId, String username, Timestamp createTime, Timestamp recoverTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.createTime = createTime;
		this.recoverTime = recoverTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getRecoverTime() {
		return recoverTime;
	}

	public void setRecoverTime(Timestamp recoverTime) {
		this.recoverTime = recoverTime;
	}
	
}
