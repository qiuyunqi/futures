package com.hongwei.futures.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
public class SysRole implements java.io.Serializable {
	private static final long serialVersionUID = -4943462037451285386L;

	private Long id;
	private String roleCode;
	private String roleName;
	private String roleDesc;
	private Long parentId;
	private FuAdmin createAdmin;
	private Date createTime;
	private FuAdmin updateAdmin;
	private Date updateTime;
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);
	private Set<SysRolePurview> sysRolePurviews = new HashSet<SysRolePurview>(0);
	//private Set<SysPurview> sysPurviews = new HashSet<SysPurview>();

	public SysRole() {
		super();
	}

	public SysRole(Long id, String roleCode, String roleName, Long parentId,
			String roleDesc, FuAdmin createAdmin, Date createTime,
			FuAdmin updateAdmin, Date updateTime,
			Set<SysUserRole> sysUserRoles, Set<SysRolePurview> sysRolePurviews) {
		super();
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.parentId = parentId;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
		this.sysUserRoles = sysUserRoles;
		this.sysRolePurviews = sysRolePurviews;
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

	@Column(name = "rolecode")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "rolename")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roledesc")
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "parentid")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createadmin")
	public FuAdmin getCreateAdmin() {
		return createAdmin;
	}

	public void setCreateAdmin(FuAdmin createAdmin) {
		this.createAdmin = createAdmin;
	}

	@Column(name = "createtime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updateadmin")
	public FuAdmin getUpdateAdmin() {
		return updateAdmin;
	}

	public void setUpdateAdmin(FuAdmin updateAdmin) {
		this.updateAdmin = updateAdmin;
	}

	@Column(name = "updatetime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysUserRole> getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysRolePurview> getSysRolePurviews() {
		return sysRolePurviews;
	}

	public void setSysRolePurviews(Set<SysRolePurview> sysRolePurviews) {
		this.sysRolePurviews = sysRolePurviews;
	}

	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "sys_role_purview", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns = { @JoinColumn(name = "purviewid") })
	public Set<SysPurview> getSysPurviews() {
		return sysPurviews;
	}

	public void setSysPurviews(Set<SysPurview> sysPurviews) {
		this.sysPurviews = sysPurviews;
	}*/

}
