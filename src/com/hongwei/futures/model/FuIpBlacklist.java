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

/**
 * FuGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_ip_blacklist")
public class FuIpBlacklist implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1231802883293141137L;
	private Long id;
	private String ip;
	private Integer isBlack;
	private FuAdmin creatAdmin;
	private Date createTime;
	private FuAdmin updateAdmin;
	private Date updateTime;
	private Integer type; // 0:ip地址 1: 手机号码 2:邮箱

	// Constructors

	/** default constructor */
	public FuIpBlacklist() {
	}

	/** full constructor */
	public FuIpBlacklist(Long id, String ip, Integer isBlack, FuAdmin creatAdmin, Date createTime,
			FuAdmin updateAdmin, Date updateTime, Integer type) {
		super();
		this.id = id;
		this.ip = ip;
		this.isBlack = isBlack;
		this.creatAdmin = creatAdmin;
		this.createTime = createTime;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ip")
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "isBlack")
	public Integer getIsBlack() {
		return this.isBlack;
	}

	public void setIsBlack(Integer isBlack) {
		this.isBlack = isBlack;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_admin")
	public FuAdmin getCreatAdmin() {
		return creatAdmin;
	}

	public void setCreatAdmin(FuAdmin creatAdmin) {
		this.creatAdmin = creatAdmin;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_admin")
	public FuAdmin getUpdateAdmin() {
		return updateAdmin;
	}

	public void setUpdateAdmin(FuAdmin updateAdmin) {
		this.updateAdmin = updateAdmin;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}