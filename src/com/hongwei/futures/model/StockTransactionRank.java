package com.hongwei.futures.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 首页交易员排行表
 * 
 * @author han
 * 
 */
@Entity
@Table(name = "stock_transaction_rank")
public class StockTransactionRank implements Serializable {

	private static final long serialVersionUID = 920701165803392046L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = true, unique = true)
	private Long id;

	@Column(name = "transaction_name")
	private String transactionName; // 交易员名称

	@Column(name = "month_profit")
	private String monthProfit; // 月收益

	@Column(name = "manager_scale")
	private String managerScale; // 管理规模
	
	@Column(name = "serial_no")
	private Integer serialNo; //序号

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=FuTransaction.class)
	@JoinColumn(name="transaction_id")//指定一个外键，也可以不指定。
	private FuTransaction fuTransaction;

	
	public StockTransactionRank() {
	}

	public StockTransactionRank(Long id, String transactionName,
			String monthProfit, String managerScale,
			FuTransaction fuTransaction, Integer serialNo) {
		super();
		this.id = id;
		this.transactionName = transactionName;
		this.monthProfit = monthProfit;
		this.managerScale = managerScale;
		this.fuTransaction = fuTransaction;
		this.serialNo = serialNo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(String monthProfit) {
		this.monthProfit = monthProfit;
	}

	public String getManagerScale() {
		return managerScale;
	}

	public void setManagerScale(String managerScale) {
		this.managerScale = managerScale;
	}

	public FuTransaction getFuTransaction() {
		return fuTransaction;
	}

	public void setFuTransaction(FuTransaction fuTransaction) {
		this.fuTransaction = fuTransaction;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
}
