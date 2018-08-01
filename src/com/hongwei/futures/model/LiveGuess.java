package com.hongwei.futures.model;

import java.io.Serializable;
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

@Entity
@Table(name="live_guess")
public class LiveGuess implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -657048593687747629L;
	private Long id;
	private FuUser fuUser;
	private BigDecimal guessAnswer;
	private Date guessTime;
	private LiveDraw liveDraw;
	private Integer isWinning;
	private BigDecimal awardsMoney;
	private Date awardsTime;
	
	public LiveGuess() {
		super();
	}
	public LiveGuess(Long id, FuUser fuUser, BigDecimal guessAnswer,Integer isWinning,
			Date guessTime, LiveDraw liveDraw,BigDecimal awardsMoney,Date awardsTime) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.guessAnswer = guessAnswer;
		this.guessTime = guessTime;
		this.liveDraw = liveDraw;
		this.isWinning = isWinning;
		this.awardsMoney = awardsMoney;
		this.awardsTime = awardsTime;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}
	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}
	
	@Column(name = "guess_answer")
	public BigDecimal getGuessAnswer() {
		return guessAnswer;
	}
	public void setGuessAnswer(BigDecimal guessAnswer) {
		this.guessAnswer = guessAnswer;
	}
	
	@Column(name = "guess_time")
	public Date getGuessTime() {
		return guessTime;
	}
	public void setGuessTime(Date guessTime) {
		this.guessTime = guessTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "draw_id")
	public LiveDraw getLiveDraw() {
		return liveDraw;
	}
	public void setLiveDraw(LiveDraw liveDraw) {
		this.liveDraw = liveDraw;
	}
	
	@Column(name = "isWinning")
	public Integer getIsWinning() {
		return isWinning;
	}
	public void setIsWinning(Integer isWinning) {
		this.isWinning = isWinning;
	}
	
	@Column(name = "awards_money")
	public BigDecimal getAwardsMoney() {
		return awardsMoney;
	}
	public void setAwardsMoney(BigDecimal awardsMoney) {
		this.awardsMoney = awardsMoney;
	}
	
	@Column(name = "awards_time")
	public Date getAwardsTime() {
		return awardsTime;
	}
	public void setAwardsTime(Date awardsTime) {
		this.awardsTime = awardsTime;
	}

}
