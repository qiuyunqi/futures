package com.hongwei.futures.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FuIndexData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_index_data")
public class FuIndexData implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7759062660985966743L;
	private Long id;
	private String userName;
	private Integer transactionType;
	private String transactionObject;
	private Integer transactionNum;
	private BigDecimal applyMoney;
	private Integer getGain;
	private BigDecimal getMoney;
	private Integer isDel;

	// Constructors

	/** default constructor */
	public FuIndexData() {
	}

	/** full constructor */
	public FuIndexData(String userName, Integer transactionType,
			String transactionObject, Integer transactionNum,
			BigDecimal applyMoney, Integer getGain, BigDecimal getMoney, Integer isDel) {
		this.userName = userName;
		this.transactionType = transactionType;
		this.transactionObject = transactionObject;
		this.transactionNum = transactionNum;
		this.applyMoney = applyMoney;
		this.getGain = getGain;
		this.getMoney = getMoney;
		this.isDel = isDel;
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

	@Column(name = "user_name", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "transaction_type")
	public Integer getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	@Column(name = "transaction_Object", length = 11)
	public String getTransactionObject() {
		return this.transactionObject;
	}

	public void setTransactionObject(String transactionObject) {
		this.transactionObject = transactionObject;
	}

	@Column(name = "transaction_num")
	public Integer getTransactionNum() {
		return this.transactionNum;
	}

	public void setTransactionNum(Integer transactionNum) {
		this.transactionNum = transactionNum;
	}

	@Column(name = "apply_money")
	public BigDecimal getApplyMoney() {
		return this.applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	@Column(name = "get_gain")
	public Integer getGetGain() {
		return this.getGain;
	}

	public void setGetGain(Integer getGain) {
		this.getGain = getGain;
	}

	@Column(name = "get_money")
	public BigDecimal getGetMoney() {
		return this.getMoney;
	}

	public void setGetMoney(BigDecimal getMoney) {
		this.getMoney = getMoney;
	}

	@Column(name = "is_del")
	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}