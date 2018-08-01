package com.hongwei.futures.model;

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
@Table(name = "stock_status_record")
public class StockStatusRecord implements java.io.Serializable {
	private static final long serialVersionUID = -1554567246439561227L;
	
	private Long id;
	private Date changeTime;
	private int operationStatus;		// 0:申请开启委托  1:申请暂停委托         3申请开启委托中  4申请暂停委托中 5申请结算中
	private int afterStatus;			//状态 0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中
	private FuUser fuUser; 
	private Date operationTime;			// 任务操作时间
	private int isOperation; 			// 任务是否处理
	private FuStockAccount fuStockAccount; 
	
	
	

	public StockStatusRecord(Long id, Date changeTime, int accountStatus,
			int operationStatus, int afterStatus, FuUser fuUser,
			Date operationTime, int isOperation, FuStockAccount fuStockAccount) {
		super();
		this.id = id;
		this.changeTime = changeTime;
		this.operationStatus = operationStatus;
		this.afterStatus = afterStatus;
		this.fuUser = fuUser;
		this.operationTime = operationTime;
		this.isOperation = isOperation;
		this.fuStockAccount = fuStockAccount;
	}
	
	public StockStatusRecord() {
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
	
	@Column(name = "change_time")
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	
	@Column(name = "operation_status")
	public int getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(int operationStatus) {
		this.operationStatus = operationStatus;
	}

	@Column(name = "after_status")
	public int getAfterStatus() {
		return afterStatus;
	}

	public void setAfterStatus(int afterStatus) {
		this.afterStatus = afterStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "operation_time")
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	@Column(name = "is_operation")
	public int getIsOperation() {
		return isOperation;
	}

	public void setIsOperation(int isOperation) {
		this.isOperation = isOperation;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_account_id")
	public FuStockAccount getFuStockAccount() {
		return fuStockAccount;
	}

	public void setFuStockAccount(FuStockAccount fuStockAccount) {
		this.fuStockAccount = fuStockAccount;
	}
	
}
