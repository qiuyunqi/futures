package com.hongwei.futures.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "stock_option_record")
public class StockOptionRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Long stockId;
	private Integer isOption;		// 0: 接券 1:退券
	private Date createTime;
	
	public StockOptionRecord(Long id, Long userId, Long stockId,
			Integer isOption, Date createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.stockId = stockId;
		this.isOption = isOption;
		this.createTime = createTime;
	}

	public StockOptionRecord() {}
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "stock_id")
	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	@Column(name = "is_option")
	public Integer getIsOption() {
		return isOption;
	}

	public void setIsOption(Integer isOption) {
		this.isOption = isOption;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
