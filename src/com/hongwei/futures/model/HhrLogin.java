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
@Table(name = "hhr_login")
public class HhrLogin implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 501371394147317272L;
	private Long id;
	private FuUser fuUser;
	private String ip;
	private Date loginTime;
	
	public HhrLogin() {
		super();
	}
	public HhrLogin(Long id, FuUser fuUser, String ip, Date loginTime) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.ip = ip;
		this.loginTime = loginTime;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}
	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}
	
	@Column(name = "ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "login_time")
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	

}
