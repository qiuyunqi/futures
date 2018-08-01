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
 * FuAddMargin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_add_margin")
public class FuAddMargin implements java.io.Serializable {
	private static final long serialVersionUID = 3369987809110479324L;
	// Fields

	/**
	 * 
	 */
	private Long id;
	private FuUser fuUser;
	private FuProgram fuProgram;
	private FuAdmin fuAdmin;
	private BigDecimal money;
	private Date time;
	private Integer type;
	private String remark;
	private Integer state;
	private Date checkTime;
	private String comment;
	private Integer paymentId;

	// Constructors

	/** default constructor */
	public FuAddMargin() {
	}

	/** full constructor */
	public FuAddMargin(FuUser fuUser, FuProgram fuProgram, FuAdmin fuAdmin,
			BigDecimal money, Date time, Integer type, String remark,
			Integer state, Date checkTime,String comment,Integer paymentId) {
		this.fuUser = fuUser;
		this.fuProgram = fuProgram;
		this.fuAdmin = fuAdmin;
		this.money = money;
		this.time = time;
		this.type = type;
		this.remark = remark;
		this.state = state;
		this.checkTime = checkTime;
		this.comment=comment;
		this.paymentId=paymentId;
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

	@Column(name = "money")
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "check_time", length = 19)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	@Column(name="comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name="payment_id")
	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

}