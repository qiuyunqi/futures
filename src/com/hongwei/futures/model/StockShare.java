package com.hongwei.futures.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock_share")
public class StockShare implements Serializable {

	private static final long serialVersionUID = 1155667948490936163L;

	private Long id;
	private String name;
	private String code;
	private Integer number;
	private FuStockAccount fuStockAccount;

	public StockShare() {
	}

	public StockShare(Long id, String name, String code, Integer number, FuStockAccount fuStockAccount) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.number = number;
		this.fuStockAccount = fuStockAccount;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@ManyToOne
	@JoinColumn(name = "account_id")
	public FuStockAccount getFuStockAccount() {
		return fuStockAccount;
	}

	public void setFuStockAccount(FuStockAccount fuStockAccount) {
		this.fuStockAccount = fuStockAccount;
	}

}
