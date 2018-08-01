package com.hongwei.futures.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FuBadCredit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_bad_credit")
public class FuBadCredit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876225654137882631L;
	private Long id;
	private FuUser fuUser;
	private FuProgram fuProgram;
	private Integer type;
	private BigDecimal money;
	private String detail;
	private Date time;
	private FuAdmin fuAdmin;
	private Date addTime;
	private Integer state;

	// Constructors

	/** default constructor */
	public FuBadCredit() {
	}

	/** full constructor */
	public FuBadCredit(FuUser fuUser, FuProgram fuProgram, Integer type,
			BigDecimal money, String detail, Date time, FuAdmin fuAdmin,
			Date addTime, Integer state) {
		this.fuUser = fuUser;
		this.fuProgram = fuProgram;
		this.type = type;
		this.money = money;
		this.detail = detail;
		this.time = time;
		this.fuAdmin = fuAdmin;
		this.addTime = addTime;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return this.fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id")
	public FuProgram getFuProgram() {
		return this.fuProgram;
	}

	public void setFuProgram(FuProgram fuProgram) {
		this.fuProgram = fuProgram;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "money")
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "detail")
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "add_id")
	public FuAdmin getFuAdmin() {
		return fuAdmin;
	}

	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}

	@Column(name = "add_time", length = 19)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}