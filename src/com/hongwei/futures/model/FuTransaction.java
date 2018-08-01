package com.hongwei.futures.model;

import java.io.Serializable;
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
 * 交易员表
 * 
 * @author samuel
 * 
 */
@Entity
@Table(name = "stock_transaction")
public class FuTransaction implements Serializable {

	private static final long serialVersionUID = -7798220744926072584L;

	private Long id;
	private FuUser fuUser;
	private String name;
	private Integer isDel;
	private Integer isVerification; // 审核 0: 不通过 1: 通过
	private Integer rating;
	private Date createTime;

	public FuTransaction(Long id, FuUser fuUser, String name, Integer isDel, Integer isVerification, Integer rating, Date createTime) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.name = name;
		this.isDel = isDel;
		this.isVerification = isVerification;
		this.rating = rating;
		this.createTime = createTime;
	}

	public FuTransaction() {

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "is_del")
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Column(name = "is_verification")
	public Integer getIsVerification() {
		return isVerification;
	}

	public void setIsVerification(Integer isVerification) {
		this.isVerification = isVerification;
	}

	@Column(name = "rating")
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
