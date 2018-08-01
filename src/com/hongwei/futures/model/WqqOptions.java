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
 * FuGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wqq_options")
public class WqqOptions implements java.io.Serializable {
	private static final long serialVersionUID = -8436878911130578983L;
	// Fields
	private Long id;
	private FuUser fuUser;
	private String tradeNo;
	private BigDecimal money;
	private Date panicTime;
	private Date payTime;
	private Integer isPay;
	private Integer guessType;
	private WqqContents wqqContents;
	private BigDecimal orderIncome;
	

	// Constructors

	/** default constructor */
	public WqqOptions() {
	}
	

	/** full constructor */
	public WqqOptions(Long id, FuUser fuUser, String tradeNo, BigDecimal money, Date panicTime,
			Date payTime, Integer isPay, Integer guessType, WqqContents wqqContents, BigDecimal orderIncome) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.tradeNo = tradeNo;
		this.money = money;
		this.panicTime = panicTime;
		this.payTime = payTime;
		this.isPay = isPay;
		this.guessType = guessType;
		this.wqqContents = wqqContents;
		this.orderIncome = orderIncome;
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

	@Column(name = "trade_no")
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	@Column(name = "money")
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "panic_time")
	public Date getPanicTime() {
		return panicTime;
	}

	public void setPanicTime(Date panicTime) {
		this.panicTime = panicTime;
	}

	@Column(name = "pay_time")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "isPay")
	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	@Column(name = "guessType")
	public Integer getGuessType() {
		return guessType;
	}
	public void setGuessType(Integer guessType) {
		this.guessType = guessType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contents_id")
	public WqqContents getWqqContents() {
		return wqqContents;
	}
	public void setWqqContents(WqqContents wqqContents) {
		this.wqqContents = wqqContents;
	}

	@Column(name = "order_income")
	public BigDecimal getOrderIncome() {
		return orderIncome;
	}
	public void setOrderIncome(BigDecimal orderIncome) {
		this.orderIncome = orderIncome;
	}

}