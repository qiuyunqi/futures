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
 * FuMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_message")
public class FuMessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2420058970259343085L;
	private Long id;
	private FuUser fuUser;
	private FuAdmin fuAdmin;
	private Integer type;
	private Date time;
	private String content;
	private Integer guideTeacher;
	private String replyContent;
	private String replyMark;
	private Date replyTime;
	private Integer state;

	// Constructors

	/** default constructor */
	public FuMessage() {
	}

	/** full constructor */
	public FuMessage(FuUser fuUser, FuAdmin fuAdmin, Integer type,
			Date time, String content, String replyContent, Integer guideTeacher,
			String replyMark, Date replyTime, Integer state) {
		this.fuUser = fuUser;
		this.fuAdmin = fuAdmin;
		this.type = type;
		this.time = time;
		this.content = content;
		this.guideTeacher = guideTeacher;
		this.replyContent = replyContent;
		this.replyMark = replyMark;
		this.replyTime = replyTime;
		this.state = state;
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
	@JoinColumn(name = "reply_id")
	public FuAdmin getFuAdmin() {
		return this.fuAdmin;
	}

	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "guideTeacher")
	public Integer getGuideTeacher() {
		return guideTeacher;
	}

	public void setGuideTeacher(Integer guideTeacher) {
		this.guideTeacher = guideTeacher;
	}

	@Column(name = "reply_content")
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "reply_mark")
	public String getReplyMark() {
		return this.replyMark;
	}

	public void setReplyMark(String replyMark) {
		this.replyMark = replyMark;
	}

	@Column(name = "reply_time", length = 19)
	public Date getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}