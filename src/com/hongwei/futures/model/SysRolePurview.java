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
@Table(name = "sys_role_purview")
public class SysRolePurview implements java.io.Serializable{
	private static final long serialVersionUID = 3955246328109681208L;
	
	private Long id;
	private SysRole sysRole;
	private SysPurview sysPurview;
	
	public SysRolePurview() {
		super();
	}
	public SysRolePurview(Long id, SysRole sysRole, SysPurview sysPurview) {
		super();
		this.id = id;
		this.sysRole = sysRole;
		this.sysPurview = sysPurview;
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
	@JoinColumn(name = "roleid")
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purviewid")
	public SysPurview getSysPurview() {
		return sysPurview;
	}
	public void setSysPurview(SysPurview sysPurview) {
		this.sysPurview = sysPurview;
	}

}
