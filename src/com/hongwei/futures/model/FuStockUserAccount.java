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
@Table(name = "stock_user_account")
public class FuStockUserAccount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -442366676645328059L;
	private Long id;
	private FuUser fuUser;
	private FuStockAccount fuStockAccount;
	private BigDecimal profitInfo;
	private BigDecimal compensateInfo;
	private BigDecimal mustFeeInfo;
	private Date createtime;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public FuStockUserAccount() {
	}

	public FuStockUserAccount(Long id, FuUser fuUser, FuStockAccount fuStockAccount, BigDecimal profitInfo,
			BigDecimal compensateInfo, BigDecimal mustFeeInfo, Date createtime, Date updatetime) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.fuStockAccount = fuStockAccount;
		this.profitInfo = profitInfo;
		this.compensateInfo = compensateInfo;
		this.mustFeeInfo = mustFeeInfo;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	public FuStockAccount getFuStockAccount() {
		return fuStockAccount;
	}

	public void setFuStockAccount(FuStockAccount fuStockAccount) {
		this.fuStockAccount = fuStockAccount;
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