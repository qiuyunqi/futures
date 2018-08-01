package com.hongwei.futures.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_examine")
public class FuExamine implements Serializable{

	private static final long serialVersionUID = 1360608347160394003L;
	
	private Long id;
	private Long stockAccountId;
	private Date firstTime;
	private String firstUser;
	
	public FuExamine(Long id, Long stockAccountId, Date firstTime, String firstUser) {
		super();
		this.id = id;
		this.stockAccountId = stockAccountId;
		this.firstTime = firstTime;
		this.firstUser = firstUser;
	}
	
	public FuExamine() {
		
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
	
	@Column(name = "stock_account_id")
	public Long getStockAccountId() {
		return stockAccountId;
	}

	public void setStockAccountId(Long stockAccountId) {
		this.stockAccountId = stockAccountId;
	}

	@Column(name = "first_time")
	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	@Column(name = "first_user")
	public String getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
	
	
	

}
