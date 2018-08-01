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

/**
 * FuGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wqq_contents")
public class WqqContents implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8209796117843523234L;
	// Fields
	private Long id;
	private String name;
	private BigDecimal acceptFactor;
	private Integer acceptResult;
	private Integer state;
	private FuAdmin createAdmin;
	private Date createTime;
	private FuAdmin acceptAdmin;
	private Date acceptTime;
	private FuAdmin upAdmin;
	private Date upTime;
	private FuAdmin downAdmin;
	private Date downTime;
	private String OOI;
	private BigDecimal updownRegion;
	private Date beginTime;
	private Date endTime;
	private BigDecimal beginPrice;
	private BigDecimal endPrice;
	private String upDetail;
	private String downDetail;
	

	// Constructors

	/** default constructor */
	public WqqContents() {
	}
	
	/** full constructor */
	public WqqContents(Long id, String name, BigDecimal acceptFactor,FuAdmin acceptAdmin,Date acceptTime,String upDetail,String downDetail,
			Integer state, FuAdmin createAdmin, Date createTime,Date upTime,Date downTime,FuAdmin upAdmin,FuAdmin downAdmin,
			Integer acceptResult,String OOI,BigDecimal updownRegion,Date beginTime,Date endTime,BigDecimal beginPrice,BigDecimal endPrice) {
		super();
		this.id = id;
		this.name = name;
		this.acceptFactor = acceptFactor;
		this.state = state;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.acceptAdmin = acceptAdmin;
		this.acceptTime = acceptTime;
		this.upAdmin = upAdmin;
		this.upTime = upTime;
		this.downAdmin = downAdmin;
		this.downTime = downTime;
		this.acceptResult = acceptResult;
		this.OOI = OOI;
		this.updownRegion = updownRegion;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.beginPrice = beginPrice;
		this.endPrice = endPrice;
		this.upDetail = upDetail;
		this.downDetail = downDetail;
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
	
	@JoinColumn(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "accept_factor")
	public BigDecimal getAcceptFactor() {
		return acceptFactor;
	}
	public void setAcceptFactor(BigDecimal acceptFactor) {
		this.acceptFactor = acceptFactor;
	}
	
	@Column(name="state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_admin")
	public FuAdmin getCreateAdmin() {
		return createAdmin;
	}
	public void setCreateAdmin(FuAdmin createAdmin) {
		this.createAdmin = createAdmin;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accept_admin")
	public FuAdmin getAcceptAdmin() {
		return acceptAdmin;
	}

	public void setAcceptAdmin(FuAdmin acceptAdmin) {
		this.acceptAdmin = acceptAdmin;
	}

	@Column(name = "accept_time")
	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	@Column(name = "up_time")
	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	@Column(name = "down_time")
	public Date getDownTime() {
		return downTime;
	}

	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "up_admin")
	public FuAdmin getUpAdmin() {
		return upAdmin;
	}
	
	public void setUpAdmin(FuAdmin upAdmin) {
		this.upAdmin = upAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "down_admin")
	public FuAdmin getDownAdmin() {
		return downAdmin;
	}

	public void setDownAdmin(FuAdmin downAdmin) {
		this.downAdmin = downAdmin;
	}

	@Column(name = "accept_result")
	public Integer getAcceptResult() {
		return acceptResult;
	}

	public void setAcceptResult(Integer acceptResult) {
		this.acceptResult = acceptResult;
	}

	@Column(name = "OOI")
	public String getOOI() {
		return OOI;
	}

	public void setOOI(String oOI) {
		OOI = oOI;
	}

	@Column(name = "updown_region")
	public BigDecimal getUpdownRegion() {
		return updownRegion;
	}

	public void setUpdownRegion(BigDecimal updownRegion) {
		this.updownRegion = updownRegion;
	}

	@Column(name = "begin_time")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "begin_price")
	public BigDecimal getBeginPrice() {
		return beginPrice;
	}

	public void setBeginPrice(BigDecimal beginPrice) {
		this.beginPrice = beginPrice;
	}

	@Column(name = "end_price")
	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	@Column(name = "up_detail")
	public String getUpDetail() {
		return upDetail;
	}

	public void setUpDetail(String upDetail) {
		this.upDetail = upDetail;
	}

	@Column(name = "down_detail")
	public String getDownDetail() {
		return downDetail;
	}

	public void setDownDetail(String downDetail) {
		this.downDetail = downDetail;
	}

}