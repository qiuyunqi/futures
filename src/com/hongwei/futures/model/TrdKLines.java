package com.hongwei.futures.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trd_klines")
public class TrdKLines implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -342351250860416295L;
	private Long id;
	private String tradingDay;
	private String instrumentId;
	private BigDecimal highestPrice;
	private BigDecimal lowestPrice;
	private BigDecimal closePrice;
	private BigDecimal openPrice;
	
	public TrdKLines(Long id, String instrumentId, String tradingDay, BigDecimal highestPrice,
			BigDecimal lowestPrice, BigDecimal closePrice, BigDecimal openPrice) {
		super();
		this.id = id;
		this.tradingDay= tradingDay;
		this.instrumentId = instrumentId;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.closePrice = closePrice;
		this.openPrice = openPrice;
	}

	public TrdKLines() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="trading_day")
	public String getTradingDay() {
		return tradingDay;
	}

	public void setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
	}

	@Column(name="instrument_id")
	public String getInstrumentId() {
		return instrumentId;
	}
	
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Column(name="highest_price")
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	@Column(name="lowest_price")
	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	@Column(name="close_price")
	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	@Column(name="open_price")
	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}
	
}
