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

/**
 * FuContinueContract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_continue_contract")
public class FuContinueContract implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4746924694419624207L;
	private Long id;
	private FuUser fuUser;
	private FuProgram fuProgram;
	private FuAdmin fuAdmin;
	private Integer cycle;
	private String introduction;
	private Integer result;
	private Date time;
	private Date checkTime;
	private String comment;

	// Constructors

	/** default constructor */
	public FuContinueContract() {
	}

	/** full constructor */
	public FuContinueContract(FuUser fuUser, FuProgram fuProgram,
			FuAdmin fuAdmin, Integer cycle, String introduction,
			Integer result, Date time, Date checkTime,String comment) {
		this.fuUser = fuUser;
		this.fuProgram = fuProgram;
		this.fuAdmin = fuAdmin;
		this.cycle = cycle;
		this.introduction = introduction;
		this.result = result;
		this.time = time;
		this.checkTime = checkTime;
		this.comment=comment;
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

	@Column(name = "cycle")
	public Integer getCycle() {
		return this.cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	@Column(name = "introduction")
	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "result")
	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
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
	

}