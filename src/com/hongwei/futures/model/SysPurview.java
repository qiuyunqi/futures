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

import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "sys_purview")
public class SysPurview implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6386109543256527737L;
	private Long id;
	private String name;
	private Long parentId;
	private String url;
	private Integer type;
	private String iconicName;
	private Integer sort;
	private Integer category;
	private String remark;
	private String target;
	private FuAdmin createAdmin;
	private Date createTime;
	private FuAdmin updateAdmin;
	private Date updateTime;
	private Set<SysRolePurview> sysRolePurviews = new HashSet<SysRolePurview>(0);
	private Set<SysPurview> children = new HashSet<SysPurview>();
	
	
	public SysPurview() {
		super();
	}

	public SysPurview(Long id, String name, Long parentId, String url,
			Integer type, String iconicName, Integer sort, Integer category,
			String remark, String target, FuAdmin createAdmin, Date createTime,
			FuAdmin updateAdmin, Date updateTime, Set<SysPurview> children,Set<SysRolePurview> sysRolePurviews) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.url = url;
		this.type = type;
		this.iconicName = iconicName;
		this.sort = sort;
		this.category = category;
		this.remark = remark;
		this.target = target;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
		this.sysRolePurviews = sysRolePurviews;
		this.children = children;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "parentid")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "iconicname")
	public String getIconicName() {
		return iconicName;
	}

	public void setIconicName(String iconicName) {
		this.iconicName = iconicName;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "category")
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "target")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
	public Set<SysRolePurview> getSysRolePurviews() {
		return sysRolePurviews;
	}

	public void setSysRolePurviews(Set<SysRolePurview> sysRolePurviews) {
		this.sysRolePurviews = sysRolePurviews;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="parentId")
	@OrderBy(clause = "id ASC")
	public Set<SysPurview> getChildren() {
		return children;
	}

	public void setChildren(Set<SysPurview> children) {
		this.children = children;
	}
	
}
