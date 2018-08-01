package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysDistrict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_district")
public class SysDistrict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2796320188886434279L;
	private Long id;
	private String districtName;
	private Long cityId;

	// Constructors

	/** default constructor */
	public SysDistrict() {
	}

	/** full constructor */
	public SysDistrict(String districtName, Long cityId) {
		this.districtName = districtName;
		this.cityId = cityId;
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

	@Column(name = "district_name", length = 50)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Column(name = "city_id")
	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

}