package com.hongwei.futures.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="admin_login_log")
public class AdminLoginLog implements java.io.Serializable{
	
	private static final long serialVersionUID = -2073744147853904851L;
	private Long id;
	private FuAdmin fuAdmin;
	private Integer logType;
	private Date logTime;
	
	public AdminLoginLog() {
		
	}
	
	public AdminLoginLog(Long id, FuAdmin fuAdmin, Integer logType, Date logTime) {
		this.id = id;
		this.fuAdmin = fuAdmin;
		this.logType = logType;
		this.logTime = logTime;
	}
	
	@Id
	@GeneratedValue
	@Column(name="id",unique=true,nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	public FuAdmin getFuAdmin() {
		return fuAdmin;
	}
	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}

	@Column(name = "log_type")
	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	@Column(name = "log_time")
	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
}
