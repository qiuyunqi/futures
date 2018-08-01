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
 * FuAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stock_money_info")
public class FuStockMoneyInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5654621766855929870L;
	private Long id;
	private FuUser fuUser;
	private BigDecimal profitInfo;
	private BigDecimal compensateInfo;
	private BigDecimal mustFeeInfo;
	private BigDecimal payFeeInfo;
	private BigDecimal noneFeeInfo;
	private Date createtime;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public FuStockMoneyInfo() {
	}

	public FuStockMoneyInfo(Long id, FuUser fuUser, BigDecimal profitInfo,
			BigDecimal compensateInfo, BigDecimal mustFeeInfo,
			BigDecimal payFeeInfo, BigDecimal noneFeeInfo,Date createtime, Date updatetime) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.profitInfo = profitInfo;
		this.compensateInfo = compensateInfo;
		this.mustFeeInfo = mustFeeInfo;
		this.payFeeInfo = payFeeInfo;
		this.noneFeeInfo = noneFeeInfo;
		this.createtime = createtime;
		this.updatetime = updatetime;
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
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "profit_info")
	public BigDecimal getProfitInfo() {
		return profitInfo;
	}

	public void setProfitInfo(BigDecimal profitInfo) {
		this.profitInfo = profitInfo;
	}

	@Column(name = "compensate_info")
	public BigDecimal getCompensateInfo() {
		return compensateInfo;
	}

	public void setCompensateInfo(BigDecimal compensateInfo) {
		this.compensateInfo = compensateInfo;
	}

	@Column(name = "must_fee_info")
	public BigDecimal getMustFeeInfo() {
		return mustFeeInfo;
	}

	public void setMustFeeInfo(BigDecimal mustFeeInfo) {
		this.mustFeeInfo = mustFeeInfo;
	}

	@Column(name = "pay_fee_info")
	public BigDecimal getPayFeeInfo() {
		return payFeeInfo;
	}

	public void setPayFeeInfo(BigDecimal payFeeInfo) {
		this.payFeeInfo = payFeeInfo;
	}
	
	@Column(name = "none_fee_info")
	public BigDecimal getNoneFeeInfo() {
		return noneFeeInfo;
	}

	public void setNoneFeeInfo(BigDecimal noneFeeInfo) {
		this.noneFeeInfo = noneFeeInfo;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
}