package com.hongwei.futures.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FuNotice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_notice")
public class FuNotice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2430413482773727012L;
	private Long id;
	private FuAdmin fuAdmin;
	private String title;
	private String pic;
	private String content;
	private Timestamp time;
	private Integer state;

	// Constructors

	/** default constructor */
	public FuNotice() {
	}

	/** full constructor */
	public FuNotice(FuAdmin fuAdmin, String title, String pic, String content,
			Timestamp time, Integer state) {
		this.fuAdmin = fuAdmin;
		this.title = title;
		this.pic = pic;
		this.content = content;
		this.time = time;
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
	@JoinColumn(name = "create_id")
	public FuAdmin getFuAdmin() {
		return this.fuAdmin;
	}

	public void setFuAdmin(FuAdmin fuAdmin) {
		this.fuAdmin = fuAdmin;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "pic")
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}