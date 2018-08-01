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

@Entity
@Table(name = "fu_article")
public class FuArticle implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 9191394270883843062L;

	private Long id;
	private FuAdmin fuAdmin;
	private String title;
	private String pic;
	private String content;
	private String hrefUrl;
	private Date time;
	private Integer state;
	private FuDictionary fuDictionary;

	// Constructors

	/** default constructor */
	public FuArticle() {
	}

	/** full constructor */
	public FuArticle(FuAdmin fuAdmin, String title, String pic, String content, String hrefUrl, Date time, Integer state, FuDictionary fuDictionary) {
		this.fuAdmin = fuAdmin;
		this.title = title;
		this.pic = pic;
		this.content = content;
		this.hrefUrl = hrefUrl;
		this.time = time;
		this.state = state;
		this.fuDictionary = fuDictionary;
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
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "href_url")
	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictionary_id")
	public FuDictionary getFuDictionary() {
		return fuDictionary;
	}

	public void setFuDictionary(FuDictionary fuDictionary) {
		this.fuDictionary = fuDictionary;
	}

}