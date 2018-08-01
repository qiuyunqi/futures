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


@Entity
@Table(name = "hhr_stat_temp")
public class HhrStatTemp implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7574864258494584425L;
	private Long id;
	private FuUser fuUser;
	private BigDecimal money;   
	private Date createDate;
	private Integer batchNum;
	
	public HhrStatTemp() {
		super();
	}

	public HhrStatTemp(Long id, FuUser fuUser, BigDecimal money, Date createDate, Integer batchNum) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.money = money;
		this.createDate = createDate;
		this.batchNum = batchNum;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}
	
	@Column(name = "money")
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "batch_num")
	public Integer getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
	}
	
}
