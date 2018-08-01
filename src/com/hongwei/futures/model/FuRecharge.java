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
 * FuRecharge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_recharge")
public class FuRecharge implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8290125818417002615L;
	private Long id;
	private FuUser fuUser;
	private FuAdmin fuAdmin;
	private String orderNum;
	private Integer type;
	private String rechargeBank;
	private String rechargeAccount;
	private BigDecimal rechargeMoney;
	private Integer payType;
	private Integer payStatus;
	private Date payTime;
	private Integer rechargeStatus;
	private Date rechargeTime;
	private Date checkTime;
	private Integer state;
	private String rechargePic;
	private String rechargeId;
	private Integer proceedsType;
	private String checkRemark;

	// Constructors

	

	/** default constructor */
	public FuRecharge() {
	}

	/** full constructor */
	public FuRecharge(FuUser fuUser, FuAdmin fuAdmin, Integer type,
			String rechargeAccount, BigDecimal rechargeMoney, Integer payType, Integer payStatus,
			Date payTime, Integer rechargeStatus, Date rechargeTime,String checkRemark,
			Integer state, String rechargePic, String rechargeId,Integer proceedsType) {
		this.fuUser = fuUser;
		this.fuAdmin = fuAdmin;
		this.type = type;
		this.rechargeAccount = rechargeAccount;
		this.rechargeMoney = rechargeMoney;
		this.payType = payType;
		this.payStatus = payStatus;
		this.payTime = payTime;
		this.rechargeStatus = rechargeStatus;
		this.rechargeTime = rechargeTime;
		this.state = state;
		this.rechargePic = rechargePic;
		this.rechargeId = rechargeId;
		this.proceedsType=proceedsType;
		this.checkRemark=checkRemark;
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

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "recharge_bank")
	public String getRechargeBank() {
		return rechargeBank;
	}

	public void setRechargeBank(String rechargeBank) {
		this.rechargeBank = rechargeBank;
	}

	@Column(name = "recharge_account")
	public String getRechargeAccount() {
		return this.rechargeAccount;
	}

	public void setRechargeAccount(String rechargeAccount) {
		this.rechargeAccount = rechargeAccount;
	}

	@Column(name = "recharge_money")
	public BigDecimal getRechargeMoney() {
		return this.rechargeMoney;
	}

	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	@Column(name = "pay_type")
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Column(name = "pay_status")
	public Integer getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	@Column(name = "pay_time", length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "recharge_status")
	public Integer getRechargeStatus() {
		return this.rechargeStatus;
	}

	public void setRechargeStatus(Integer rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}

	@Column(name = "recharge_time", length = 19)
	public Date getRechargeTime() {
		return this.rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "order_num")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "check_time")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	@Column(name = "recharge_pic")
	public String getRechargePic() {
		return rechargePic;
	}

	public void setRechargePic(String rechargePic) {
		this.rechargePic = rechargePic;
	}
	
	@Column(name = "recharge_id")
	public String getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}

	@Column(name = "proceedsType")
	public Integer getProceedsType() {
		return proceedsType;
	}

	public void setProceedsType(Integer proceedsType) {
		this.proceedsType = proceedsType;
	}

	@Column(name = "check_remark")
	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	
}