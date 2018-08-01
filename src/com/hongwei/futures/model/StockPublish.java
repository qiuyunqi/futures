package com.hongwei.futures.model;

import java.io.Serializable;
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
 * 我要找劵
 * 
 * @author samuel
 * 
 */
@Entity
@Table(name = "stock_publish")
public class StockPublish implements Serializable {

	private static final long serialVersionUID = 1762262815503472367L;

	private Long id;

	private String title;

	private String description;

	private FuUser fuUser;

	private Date createTime;

	private Integer isDel;

	public StockPublish() {
	}

	public StockPublish(Long id, String title, String description, FuUser fuUser, Date createTime, Integer isDel) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.fuUser = fuUser;
		this.createTime = createTime;
		this.isDel = isDel;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = true, unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "is_del")
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
