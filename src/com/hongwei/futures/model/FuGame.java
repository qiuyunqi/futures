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
@Table(name = "fu_game")
public class FuGame implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4899170852883427946L;
	private Long id;
	private FuUser fuUser;
	private Date gameTime;
	private BigDecimal gameMoney;
	private Integer joinNum;
	private Integer tradeAccount;
	private Integer isTrade;
	private Integer paymentId;
	private Integer competitionNum;

	// Constructors

	/** default constructor */
	public FuGame() {
	}

	/** full constructor */
	public FuGame(Long id, FuUser fuUser, Date gameTime, BigDecimal gameMoney,
			Integer joinNum, Integer tradeAccount, Integer isTrade,Integer paymentId, Integer competitionNum) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.gameTime = gameTime;
		this.gameMoney = gameMoney;
		this.joinNum = joinNum;
		this.tradeAccount = tradeAccount;
		this.isTrade = isTrade;
		this.paymentId = paymentId;
		this.competitionNum = competitionNum;
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

	@Column(name = "game_time", length = 19)
	public Date getGameTime() {
		return this.gameTime;
	}

	public void setGameTime(Date gameTime) {
		this.gameTime = gameTime;
	}

	@Column(name = "game_money")
	public BigDecimal getGameMoney() {
		return this.gameMoney;
	}

	public void setGameMoney(BigDecimal gameMoney) {
		this.gameMoney = gameMoney;
	}

	@Column(name = "join_num")
	public Integer getJoinNum() {
		return this.joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}
	
	@Column(name = "trade_account")
	public Integer getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(Integer tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	@Column(name = "isTrade")
	public Integer getIsTrade() {
		return isTrade;
	}

	public void setIsTrade(Integer isTrade) {
		this.isTrade = isTrade;
	}

	@Column(name = "payment_id")
	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	@Column(name="competition_num")
	public Integer getCompetitionNum() {
		return competitionNum;
	}

	public void setCompetitionNum(Integer competitionNum) {
		this.competitionNum = competitionNum;
	}

}