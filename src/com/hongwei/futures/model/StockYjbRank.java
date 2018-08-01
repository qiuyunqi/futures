package com.hongwei.futures.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 首页余劵宝收益龙虎榜
 * 
 * @author han
 */
@Entity
@Table(name = "stock_yjb_rank")
public class StockYjbRank implements Serializable {

	private static final long serialVersionUID = 920701165803392046L;

	private Long id;

	private String code;

	private String stockName;

	private String stockPy;

	private String monthProfit;

	private String removes;

	private String transactionName; // 交易团队名称

	private Integer serialNo; // 序号

	private Integer heat; // 热度

	public StockYjbRank() {
	}

	public StockYjbRank(Long id, String code, String stockName, String stockPy, String monthProfit, String removes, String transactionName, Integer serialNo, Integer heat) {
		super();
		this.id = id;
		this.code = code;
		this.stockName = stockName;
		this.stockPy = stockPy;
		this.monthProfit = monthProfit;
		this.removes = removes;
		this.transactionName = transactionName;
		this.serialNo = serialNo;
		this.heat = heat;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = true, unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "stock_name")
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Column(name = "stock_py")
	public String getStockPy() {
		return stockPy;
	}

	public void setStockPy(String stockPy) {
		this.stockPy = stockPy;
	}

	@Column(name = "month_profit")
	public String getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(String monthProfit) {
		this.monthProfit = monthProfit;
	}

	@Column(name = "removes")
	public String getRemoves() {
		return removes;
	}

	public void setRemoves(String removes) {
		this.removes = removes;
	}

	@Column(name = "transaction_name")
	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	@Column(name = "serial_no")
	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "heat")
	public Integer getHeat() {
		return heat;
	}

	public void setHeat(Integer heat) {
		this.heat = heat;
	}

}
