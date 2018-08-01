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
 * FuProgramUp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_program_up")
public class FuProgramUp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6405564642915612535L;
	private Long id;
	private FuUser fuUser;
	private FuProgram fuProgram;
	private BigDecimal matchMoney;
	private BigDecimal safeMoney;
	private BigDecimal manageMoney;
	private BigDecimal totalMatchMoney;
	private BigDecimal warnLine;
	private BigDecimal closeLine;
	private Integer type;
	private Integer dayNum;
	private Date time;
	private Date closeTime;
	private Integer status;
	private BigDecimal commisionPercent;

	// Constructors

	/** default constructor */
	public FuProgramUp() {
	}

	/** full constructor */
	public FuProgramUp(FuUser fuUser, FuProgram fuProgram, BigDecimal matchMoney,
			BigDecimal safeMoney, BigDecimal manageMoney, BigDecimal totalMatchMoney,
			BigDecimal warnLine, BigDecimal closeLine, Integer type, Integer dayNum,
			Date time, Date closeTime, Integer status) {
		this.fuUser = fuUser;
		this.fuProgram = fuProgram;
		this.matchMoney = matchMoney;
		this.safeMoney = safeMoney;
		this.manageMoney = manageMoney;
		this.totalMatchMoney = totalMatchMoney;
		this.warnLine = warnLine;
		this.closeLine = closeLine;
		this.type = type;
		this.dayNum = dayNum;
		this.time = time;
		this.closeTime = closeTime;
		this.status = status;
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

	@Column(name = "match_money")
	public BigDecimal getMatchMoney() {
		return this.matchMoney;
	}

	public void setMatchMoney(BigDecimal matchMoney) {
		this.matchMoney = matchMoney;
	}

	@Column(name = "safe_money")
	public BigDecimal getSafeMoney() {
		return this.safeMoney;
	}

	public void setSafeMoney(BigDecimal safeMoney) {
		this.safeMoney = safeMoney;
	}

	@Column(name = "manage_money")
	public BigDecimal getManageMoney() {
		return this.manageMoney;
	}

	public void setManageMoney(BigDecimal manageMoney) {
		this.manageMoney = manageMoney;
	}

	@Column(name = "total_match_money")
	public BigDecimal getTotalMatchMoney() {
		return this.totalMatchMoney;
	}

	public void setTotalMatchMoney(BigDecimal totalMatchMoney) {
		this.totalMatchMoney = totalMatchMoney;
	}

	@Column(name = "warn_line")
	public BigDecimal getWarnLine() {
		return this.warnLine;
	}

	public void setWarnLine(BigDecimal warnLine) {
		this.warnLine = warnLine;
	}

	@Column(name = "close_line")
	public BigDecimal getCloseLine() {
		return this.closeLine;
	}

	public void setCloseLine(BigDecimal closeLine) {
		this.closeLine = closeLine;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "day_num")
	public Integer getDayNum() {
		return this.dayNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "close_time", length = 19)
	public Date getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="commision_percent")
	public BigDecimal getCommisionPercent() {
		return commisionPercent;
	}

	public void setCommisionPercent(BigDecimal commisionPercent) {
		this.commisionPercent = commisionPercent;
	}
	

}