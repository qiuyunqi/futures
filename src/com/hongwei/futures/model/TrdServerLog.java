package com.hongwei.futures.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "trd_server_log")
public class TrdServerLog implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5976455922148104107L;
	private Long id;
	private String errorCode; 
	private String errorMsg; 
	private Date log_time;
	

	public TrdServerLog() {
		super();
	}

	public TrdServerLog(Long id, String errorCode, String errorMsg, Date log_time) {
		super();
		this.id = id;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.log_time = log_time;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "error_code")
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Column(name = "error_msg")
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Column(name = "log_time")
	public Date getLog_time() {
		return log_time;
	}

	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
}
