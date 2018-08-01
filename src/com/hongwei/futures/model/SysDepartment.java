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
@Table(name = "sys_department")
public class SysDepartment implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5375949566137453402L;
	private Long id;
	private String dptCode;
	private String dptName;
	private Long dptParentId;
	private String dptAddress;
	private Integer dptSort;
	private String dptRemark;
	private FuAdmin createAdmin;
	private Date createTime;
	private FuAdmin updateAdmin;
	private Date updateTime;
	
	public SysDepartment() {
		super();
	}

	public SysDepartment(Long id, String dptCode, String dptName,
			Long dptParentId, String dptAddress, Integer dptSort,
			String dptRemark, FuAdmin createAdmin, Date createTime,
			FuAdmin updateAdmin, Date updateTime) {
		super();
		this.id = id;
		this.dptCode = dptCode;
		this.dptName = dptName;
		this.dptParentId = dptParentId;
		this.dptAddress = dptAddress;
		this.dptSort = dptSort;
		this.dptRemark = dptRemark;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
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
	
	@Column(name = "dptcode")
	public String getDptCode() {
		return dptCode;
	}
	public void setDptCode(String dptCode) {
		this.dptCode = dptCode;
	}
	
	@Column(name = "dptname")
	public String getDptName() {
		return dptName;
	}
	public void setDptName(String dptName) {
		this.dptName = dptName;
	}
	
	@Column(name = "dptparentid")
	public Long getDptParentId() {
		return dptParentId;
	}
	public void setDptParentId(Long dptParentId) {
		this.dptParentId = dptParentId;
	}
	
	@Column(name = "dptaddress")
	public String getDptAddress() {
		return dptAddress;
	}
	public void setDptAddress(String dptAddress) {
		this.dptAddress = dptAddress;
	}
	
	@Column(name = "dptsort")
	public Integer getDptSort() {
		return dptSort;
	}
	public void setDptSort(Integer dptSort) {
		this.dptSort = dptSort;
	}
	
	@Column(name = "dptremark")
	public String getDptRemark() {
		return dptRemark;
	}
	public void setDptRemark(String dptRemark) {
		this.dptRemark = dptRemark;
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

}
