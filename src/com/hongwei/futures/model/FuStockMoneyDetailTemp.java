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
@Table(name = "stock_money_detail_temp")
public class FuStockMoneyDetailTemp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1079672750223033355L;
	/**
	 * 
	 */
	private Long id;
	private Date tradetime;
	private String phone;
	private String capitalAccount;
	private BigDecimal nowProfit;
	private BigDecimal manageFee;
	private String remark;
	private Integer batchNum;
	private FuUser fuUser;
	private FuStockAccount fuStockAccount;
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
	public FuStockMoneyDetailTemp() {
	}

	public FuStockMoneyDetailTemp(Long id, Date tradetime, String phone, String capitalAccount, BigDecimal nowProfit, BigDecimal manageFee, String remark, Integer batchNum, FuUser fuUser, FuStockAccount fuStockAccount, BigDecimal totalValue, BigDecimal totalCash, BigDecimal beginValue, String transaction, BigDecimal weekProfit, BigDecimal ayerValue, String salesMan, BigDecimal dayKnockdown) {
		super();
		this.id = id;
		this.tradetime = tradetime;
		this.phone = phone;
		this.capitalAccount = capitalAccount;
		this.nowProfit = nowProfit;
		this.manageFee = manageFee;
		this.remark = remark;
		this.batchNum = batchNum;
		this.fuUser = fuUser;
		this.fuStockAccount = fuStockAccount;
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

	@Column(name = "tradetime")
	public Date getTradetime() {
		return tradetime;
	}

	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "capital_account")
	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	@Column(name = "now_profit")
	public BigDecimal getNowProfit() {
		return nowProfit;
	}

	public void setNowProfit(BigDecimal nowProfit) {
		this.nowProfit = nowProfit;
	}

	@Column(name = "manage_fee")
	public BigDecimal getManageFee() {
		return manageFee;
	}

	public void setManageFee(BigDecimal manageFee) {
		this.manageFee = manageFee;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "batch_num")
	public Integer getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
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