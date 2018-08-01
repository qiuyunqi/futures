package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysProvince entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_province")
public class SysProvince implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2186028107054561512L;
	private Long id;
	private String provinceName;

	// Constructors

	/** default constructor */
	public SysProvince() {
	}

	/** full constructor */
	public SysProvince(String provinceName) {
		this.provinceName = provinceName;
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

	@Column(name = "province_name", length = 50)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}