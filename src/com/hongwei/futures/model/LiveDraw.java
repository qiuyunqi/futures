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
@Table(name = "live_draw")
public class LiveDraw implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6538360948422772461L;
	private Long id;
	private String question;
	private BigDecimal money;
	private BigDecimal answer;
	private FuAdmin createAdmin;
	private Date createTime;
	private Date beginTime;
	private Date endTime;
	private Integer isPublish;
	private Date publishTime;

	public LiveDraw() {
		super();
	}

	public LiveDraw(Long id, String question, BigDecimal answer, Integer isPublish, Date publishTime, FuAdmin createAdmin, Date createTime, Date beginTime, Date endTime, BigDecimal money) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.isPublish = isPublish;
		this.publishTime = publishTime;
		this.money = money;
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

	@Column(name = "question")
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "answer")
	public BigDecimal getAnswer() {
		return answer;
	}

	public void setAnswer(BigDecimal answer) {
		this.answer = answer;
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

	@Column(name = "isPublish")
	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	@Column(name = "publish_time")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "money")
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
