package com.hongwei.futures.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FuPromote entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_promote")
public class FuPromote implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8128255063313995724L;
	private Long id;
	private FuUser fuUserByPromoteId;
	private FuUser fuUserByPromotedId;
	private BigDecimal totalCommission;
	private Boolean isPeizi;

	// Constructors

	/** default constructor */
	public FuPromote() {
	}

	/** full constructor */
	public FuPromote(FuUser fuUserByPromoteId, FuUser fuUserByPromotedId,
			BigDecimal totalCommission) {
		this.fuUserByPromoteId = fuUserByPromoteId;
		this.fuUserByPromotedId = fuUserByPromotedId;
		this.totalCommission = totalCommission;
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
	@JoinColumn(name = "promote_id")
	public FuUser getFuUserByPromoteId() {
		return this.fuUserByPromoteId;
	}

	public void setFuUserByPromoteId(FuUser fuUserByPromoteId) {
		this.fuUserByPromoteId = fuUserByPromoteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promoted_id")
	public FuUser getFuUserByPromotedId() {
		return this.fuUserByPromotedId;
	}

	public void setFuUserByPromotedId(FuUser fuUserByPromotedId) {
		this.fuUserByPromotedId = fuUserByPromotedId;
	}

	@Column(name = "total_commission")
	public BigDecimal getTotalCommission() {
		return this.totalCommission;
	}

	public void setTotalCommission(BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}

	@Column(name = "is_peizi")
	public Boolean getIsPeizi() {
		return isPeizi;
	}

	public void setIsPeizi(Boolean isPeizi) {
		this.isPeizi = isPeizi;
	}

	
}