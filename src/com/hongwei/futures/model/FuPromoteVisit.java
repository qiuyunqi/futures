package com.hongwei.futures.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FuPromoteVisit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_promote_visit")
public class FuPromoteVisit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4699788269961583740L;
	private Long id;
	private Long promoteId;
	private Date visitTime;
	private String visitIp;
	private Integer visitNum;
	

	// Constructors

	/** default constructor */
	public FuPromoteVisit() {
	}

	/** full constructor */
	public FuPromoteVisit(Long promoteId, Date visitTime, String visitIp,
			Integer visitNum) {
		this.promoteId = promoteId;
		this.visitTime = visitTime;
		this.visitIp = visitIp;
		this.visitNum = visitNum;
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
    
	@Column(name = "promote_id")
	public Long getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(Long promoteId) {
		this.promoteId = promoteId;
	}

	@Column(name = "visit_time", length = 19)
	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	@Column(name = "visit_ip")
	public String getVisitIp() {
		return this.visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	@Column(name = "visit_num")
	public Integer getVisitNum() {
		return this.visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

}