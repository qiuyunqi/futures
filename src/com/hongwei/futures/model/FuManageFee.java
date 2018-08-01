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
 * FuManageFee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_manage_fee")
public class FuManageFee implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5090880920346789307L;
	private Long id;
	private FuUser fuUser;
	private FuProgram fuProgram;
	private FuAdmin fuAdmin;
	private Integer payCycle;
	private BigDecimal money;
	private Date payTime;
	private Integer type;
	private Integer state;
	private Date checkTime;
	private String comment;
	private BigDecimal accountBalance;
	private Integer flag;
	private String feeType;
	private Date beginTime;
	private Date endTime;

	// Constructors

	/** default constructor */
	public FuManageFee() {
	}

	/** full constructor */
	public FuManageFee(FuUser fuUser, FuProgram fuProgram, FuAdmin fuAdmin,
			Integer payCycle, BigDecimal money, Date payTime, Integer type,
			Integer state, Date checkTime,String comment,BigDecimal accountBalance,
			Date beginTime,Date endTime) {
		this.fuUser = fuUser;
		this.fuProgram = fuProgram;
		this.fuAdmin = fuAdmin;
		this.payCycle = payCycle;
		this.money = money;
		this.payTime = payTime;
		this.type = type;
		this.state = state;
		this.checkTime = checkTime;
		this.comment=comment;
		this.accountBalance=accountBalance;
		this.beginTime=beginTime;
		this.endTime=endTime;
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

	@Column(name = "pay_cycle")
	public Integer getPayCycle() {
		return this.payCycle;
	}

	public void setPayCycle(Integer payCycle) {
		this.payCycle = payCycle;
	}

	@Column(name = "money")
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "pay_time", length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	@Column(name="account_balance")
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	@Column(name="flag")
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Column(name="fee_type")
	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	@Column(name="begin_time")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}