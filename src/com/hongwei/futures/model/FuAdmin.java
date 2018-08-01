package com.hongwei.futures.model;

import java.io.Serializable;
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

/**
 * FuAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_admin")
public class FuAdmin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7178606222379684908L;
	private Long id;
	private String account;
	private String password;
	private Integer type;
	private String name;
	private SysDepartment sysDepartment;
	private String jobDesc;
	private String rageConfig;
	private String loginToken;
	private Integer state;
	private Boolean isAuto;
	private Date createTime;
	private Date updateLoginTime;
	private Integer loginErrorTimes;
	private String loginIp;
	private Date forbidLoginTime;
	private String email;
	private String phone;
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);
	//private Set<SysRole> roles = new HashSet<SysRole>();

	// Constructors

	/** default constructor */
	public FuAdmin() {
	}

	/** full constructor */
	public FuAdmin(Long id, String account, String password, Integer type,
			String name, SysDepartment sysDepartment, String jobDesc,
			String rageConfig, String loginToken, Integer state,
			Boolean isAuto, Date createTime, Date updateLoginTime,
			Integer loginErrorTimes, String loginIp, Date forbidLoginTime,
			String email, String phone, Set<SysUserRole> sysUserRoles) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.type = type;
		this.name = name;
		this.sysDepartment = sysDepartment;
		this.jobDesc = jobDesc;
		this.rageConfig = rageConfig;
		this.loginToken = loginToken;
		this.state = state;
		this.isAuto = isAuto;
		this.createTime = createTime;
		this.updateLoginTime = updateLoginTime;
		this.loginErrorTimes = loginErrorTimes;
		this.loginIp = loginIp;
		this.forbidLoginTime = forbidLoginTime;
		this.email = email;
		this.phone = phone;
		this.sysUserRoles = sysUserRoles;
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

	@Column(name = "account")
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	public SysDepartment getSysDepartment() {
		return sysDepartment;
	}

	public void setSysDepartment(SysDepartment sysDepartment) {
		this.sysDepartment = sysDepartment;
	}

	@Column(name = "job_desc")
	public String getJobDesc() {
		return this.jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	@Column(name = "rage_config", length = 65535)
	public String getRageConfig() {
		return this.rageConfig;
	}

	public void setRageConfig(String rageConfig) {
		this.rageConfig = rageConfig;
	}

	@Column(name = "login_token")
	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_login_time")
	public Date getUpdateLoginTime() {
		return updateLoginTime;
	}

	public void setUpdateLoginTime(Date updateLoginTime) {
		this.updateLoginTime = updateLoginTime;
	}

	@Column(name = "is_auto")
	public Boolean getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Boolean isAuto) {
		this.isAuto = isAuto;
	}

	@Column(name = "login_error_times")
	public Integer getLoginErrorTimes() {
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(Integer loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}

	@Column(name = "login_ip")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "forbid_login_time")
	public Date getForbidLoginTime() {
		return forbidLoginTime;
	}

	public void setForbidLoginTime(Date forbidLoginTime) {
		this.forbidLoginTime = forbidLoginTime;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysUserRole> getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}
	
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "adminid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}*/
	

}