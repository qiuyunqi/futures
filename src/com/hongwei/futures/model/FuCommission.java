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
 * FuCommission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_commission")
public class FuCommission implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940336719334920844L;
	private Long id;
	private FuUser fuUserByUserId;
	private FuProgram fuProgram;
	private FuAdmin fuAdmin;
	private FuUser fuUserByExtendUserId;
	private BigDecimal commissionMoney;
	private BigDecimal commissionPercent;
	private Integer status;
	private Date time;
	private Date checkTime;
	private Integer state;
	private String remark;

	// Constructors

	/** default constructor */
	public FuCommission() {
	}

	/** full constructor */
	public FuCommission(FuUser fuUserByUserId, FuProgram fuProgram,
			FuAdmin fuAdmin, FuUser fuUserByExtendUserId,
			BigDecimal commissionMoney, BigDecimal commissionPercent, Integer status,
			Date time, Date checkTime, Integer state) {
		this.fuUserByUserId = fuUserByUserId;
		this.fuProgram = fuProgram;
		this.fuAdmin = fuAdmin;
		this.fuUserByExtendUserId = fuUserByExtendUserId;
		this.commissionMoney = commissionMoney;
		this.commissionPercent = commissionPercent;
		this.status = status;
		this.time = time;
		this.checkTime = checkTime;
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
	public FuUser getFuUserByUserId() {
		return this.fuUserByUserId;
	}

	public void setFuUserByUserId(FuUser fuUserByUserId) {
		this.fuUserByUserId = fuUserByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id")
	public FuProgram getFuProgram() {
		return this.fuProgram;
	}

	public void setFuProgram(FuProgram fuProgram) {
		this.fuProgram = fuProgram;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	public FuAdmin getFuAdmin() {
		return this.fuAdmin;
	}

	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "extend_user_id")
	public FuUser getFuUserByExtendUserId() {
		return this.fuUserByExtendUserId;
	}

	public void setFuUserByExtendUserId(FuUser fuUserByExtendUserId) {
		this.fuUserByExtendUserId = fuUserByExtendUserId;
	}

	@Column(name = "commission_money")
	public BigDecimal getCommissionMoney() {
		return this.commissionMoney;
	}

	public void setCommissionMoney(BigDecimal commissionMoney) {
		this.commissionMoney = commissionMoney;
	}

	@Column(name = "commission_percent")
	public BigDecimal getCommissionPercent() {
		return this.commissionPercent;
	}

	public void setCommissionPercent(BigDecimal commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "check_time", length = 19)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}