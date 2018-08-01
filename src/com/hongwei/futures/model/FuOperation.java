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
@Table(name="fu_operation")
public class FuOperation implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2073744147853904851L;
	private Long id;
	private String modelMain;
	private String modelSub;
	private Integer operation;
	private Long opId;
	private String beforeContent;
	private String afterContent;
	private FuAdmin fuAdmin;
	private Date updateTime;
	
	public FuOperation() {
	}
	public FuOperation(Long id, String modelMain, String modelSub,
			Integer operation, Long opId, String beforeContent,
			String afterContent, FuAdmin fuAdmin, Date updateTime) {
		super();
		this.id = id;
		this.modelMain = modelMain;
		this.modelSub = modelSub;
		this.operation = operation;
		this.opId = opId;
		this.beforeContent = beforeContent;
		this.afterContent = afterContent;
		this.fuAdmin = fuAdmin;
		this.updateTime = updateTime;
	}
	@Id
	@GeneratedValue
	@Column(name="id",unique=true,nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="model_main")
	public String getModelMain() {
		return modelMain;
	}
	public void setModelMain(String modelMain) {
		this.modelMain = modelMain;
	}
	
	@Column(name="model_sub")
	public String getModelSub() {
		return modelSub;
	}
	public void setModelSub(String modelSub) {
		this.modelSub = modelSub;
	}
	
	@Column(name="operation")
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	
	@Column(name="op_id")
	public Long getOpId() {
		return opId;
	}
	public void setOpId(Long opId) {
		this.opId = opId;
	}
	
	@Column(name="before_content")
	public String getBeforeContent() {
		return beforeContent;
	}
	public void setBeforeContent(String beforeContent) {
		this.beforeContent = beforeContent;
	}
	
	@Column(name="after_content")
	public String getAfterContent() {
		return afterContent;
	}
	public void setAfterContent(String afterContent) {
		this.afterContent = afterContent;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	public FuAdmin getFuAdmin() {
		return fuAdmin;
	}
	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}
	
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
}
