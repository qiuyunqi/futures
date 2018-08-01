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
@Table(name = "stock_money_detail")
public class FuStockMoneyDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1861937075094938284L;
	/**
	 * 
	 */
	private Long id;
	private FuUser fuUser;
	private FuStockAccount fuStockAccount;
	private BigDecimal nowProfit;
	private BigDecimal compensate;
	private BigDecimal manageFee;
	private BigDecimal payFee;
	private BigDecimal quitCompen;
	private FuAdmin createAdmin;
	private Date createTime;
	private Date tradeTime;
	private BigDecimal mustFee;
	private String remark;
	private BigDecimal totalValue;
	private BigDecimal totalCash;
	private BigDecimal beginValue;
	private String transaction;
	private BigDecimal weekProfit;
	private BigDecimal ayerValue;
	private String salesMan;
	private BigDecimal dayKnockdown;

	// Constructors

	/** default constructor */
	public FuStockMoneyDetail() {
	}

	public FuStockMoneyDetail(Long id, FuUser fuUser, FuStockAccount fuStockAccount, BigDecimal nowProfit, BigDecimal compensate, BigDecimal manageFee, BigDecimal payFee, BigDecimal quitCompen, FuAdmin createAdmin, Date createTime, Date tradeTime, BigDecimal mustFee, String remark, BigDecimal totalValue, BigDecimal beginValue, String transaction, BigDecimal weekProfit, BigDecimal ayerValue,
			String salesMan, BigDecimal dayKnockdown, BigDecimal totalCash) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.fuStockAccount = fuStockAccount;
		this.nowProfit = nowProfit;
		this.compensate = compensate;
		this.manageFee = manageFee;
		this.payFee = payFee;
		this.quitCompen = quitCompen;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.tradeTime = tradeTime;
		this.mustFee = mustFee;
		this.remark = remark;
		this.totalValue = totalValue;
		this.totalCash = totalCash;
		this.beginValue = beginValue;
		this.transaction = transaction;
		this.weekProfit = weekProfit;
		this.ayerValue = ayerValue;
		this.salesMan = salesMan;
		this.dayKnockdown = dayKnockdown;
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

	@Column(name = "now_profit")
	public BigDecimal getNowProfit() {
		return nowProfit;
	}

	public void setNowProfit(BigDecimal nowProfit) {
		this.nowProfit = nowProfit;
	}

	@Column(name = "compensate")
	public BigDecimal getCompensate() {
		return compensate;
	}

	public void setCompensate(BigDecimal compensate) {
		this.compensate = compensate;
	}

	@Column(name = "manage_fee")
	public BigDecimal getManageFee() {
		return manageFee;
	}

	public void setManageFee(BigDecimal manageFee) {
		this.manageFee = manageFee;
	}

	@Column(name = "pay_fee")
	public BigDecimal getPayFee() {
		return payFee;
	}

	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	@Column(name = "quit_compen")
	public BigDecimal getQuitCompen() {
		return quitCompen;
	}

	public void setQuitCompen(BigDecimal quitCompen) {
		this.quitCompen = quitCompen;
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

	@Column(name = "tradetime")
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Column(name = "must_fee")
	public BigDecimal getMustFee() {
		return mustFee;
	}

	public void setMustFee(BigDecimal mustFee) {
		this.mustFee = mustFee;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "total_value")
	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	@Column(name = "total_cash")
	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}

	@Column(name = "begin_value")
	public BigDecimal getBeginValue() {
		return beginValue;
	}

	public void setBeginValue(BigDecimal beginValue) {
		this.beginValue = beginValue;
	}

	@Column(name = "transaction")
	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	@Column(name = "week_profit")
	public BigDecimal getWeekProfit() {
		return weekProfit;
	}

	public void setWeekProfit(BigDecimal weekProfit) {
		this.weekProfit = weekProfit;
	}

	@Column(name = "ayer_value")
	public BigDecimal getAyerValue() {
		return ayerValue;
	}

	public void setAyerValue(BigDecimal ayerValue) {
		this.ayerValue = ayerValue;
	}

	@Column(name = "sales_man")
	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	@Column(name = "day_knockdown")
	public BigDecimal getDayKnockdown() {
		return dayKnockdown;
	}

	public void setDayKnockdown(BigDecimal dayKnockdown) {
		this.dayKnockdown = dayKnockdown;
	}
}