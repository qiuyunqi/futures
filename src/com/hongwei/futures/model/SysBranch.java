package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysBranch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_branch")
public class SysBranch implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1979673301429883693L;
	private Long id;
	private Long cityId;
	private Long bankId;
	private String branchName;
	private String branchAddress;
	private String branchPhone;

	// Constructors

	/** default constructor */
	public SysBranch() {
	}

	/** full constructor */
	public SysBranch(Long cityId, Long bankId, String branchName) {
		this.cityId = cityId;
		this.bankId = bankId;
		this.branchName = branchName;
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

	@Column(name = "city_id")
	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "bank_id")
	public Long getBankId() {
		return this.bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	@Column(name = "branch_name")
	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "branch_address")
	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Column(name = "branch_phone")
	public String getBranchPhone() {
		return branchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

	
}