package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_city")
public class SysCity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6874782636757254612L;
	// Fields

	private Long id;
	private String cityName;
	private String zipCode;
	private Long provinceId;

	// Constructors

	/** default constructor */
	public SysCity() {
	}

	/** full constructor */
	public SysCity(String cityName, String zipCode, Long provinceId) {
		this.cityName = cityName;
		this.zipCode = zipCode;
		this.provinceId = provinceId;
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

	@Column(name = "city_name", length = 50)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "zip_code")
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "province_id")
	public Long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

}