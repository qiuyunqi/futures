package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysBank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_bank")
public class SysBank implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -936323892048767848L;
	private Long id;
	private String bankName;
	private Integer state;

	// Constructors

	/** default constructor */
	public SysBank() {
	}

	public SysBank(Long id, String bankName, Integer state) {
		super();
		this.id = id;
		this.bankName = bankName;
		this.state = state;
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

	@Column(name = "bank_name")
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
}