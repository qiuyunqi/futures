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
 * FuDrawMoney entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_draw_money")
public class FuDrawMoney implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -769054303063402682L;
	private Long id;
	private FuUser fuUser;
	private FuAdmin fuAdmin;
	private FuBankCard fuBankCard;
	private Date drawTime;
	private Integer status;
	private String drawReason;
	private BigDecimal drawMoney;
	private String comment;
	private Date checkTime;
	private Integer state;

	// Constructors

	/** default constructor */
	public FuDrawMoney() {
	}

	/** full constructor */
	public FuDrawMoney(FuUser fuUser, FuAdmin fuAdmin, FuBankCard fuBankCard,
			Date drawTime, Integer status, String drawReason,
			BigDecimal drawMoney, String comment, Integer state) {
		this.fuUser = fuUser;
		this.fuAdmin = fuAdmin;
		this.fuBankCard = fuBankCard;
		this.drawTime = drawTime;
		this.status = status;
		this.drawReason = drawReason;
		this.drawMoney = drawMoney;
		this.comment = comment;
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
	@JoinColumn(name = "admin_id")
	public FuAdmin getFuAdmin() {
		return this.fuAdmin;
	}

	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_id")
	public FuBankCard getFuBankCard() {
		return this.fuBankCard;
	}

	public void setFuBankCard(FuBankCard fuBankCard) {
		this.fuBankCard = fuBankCard;
	}

	@Column(name = "draw_time", length = 19)
	public Date getDrawTime() {
		return this.drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "draw_reason", length = 65535)
	public String getDrawReason() {
		return this.drawReason;
	}

	public void setDrawReason(String drawReason) {
		this.drawReason = drawReason;
	}

	@Column(name = "draw_money")
	public BigDecimal getDrawMoney() {
		return this.drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	@Column(name = "comment", length = 65535)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "check_time")
	public Date getCheckTime() {
		return checkTime;
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

}