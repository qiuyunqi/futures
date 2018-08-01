package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_role")
public class SysUserRole implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6839635875891286298L;
	private Long id;
	private FuAdmin fuAdmin;
	private SysRole sysRole;
	
	public SysUserRole() {
		super();
	}
	public SysUserRole(Long id, FuAdmin fuAdmin, SysRole sysRole) {
		super();
		this.id = id;
		this.fuAdmin = fuAdmin;
		this.sysRole = sysRole;
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
	@JoinColumn(name = "adminid")
	public FuAdmin getFuAdmin() {
		return fuAdmin;
	}
	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}
