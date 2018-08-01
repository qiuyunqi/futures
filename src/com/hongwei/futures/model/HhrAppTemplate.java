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
@Table(name = "hhr_app_template")
public class HhrAppTemplate implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -938040711618470060L;
	private Long id;
	private String template;
	private String row1;
	private String row2;
	private String row3;
	private String row4;
	private FuAdmin updateAdmin;
	private Date updateTime;
	
	public HhrAppTemplate() {
		super();
	}

	public HhrAppTemplate(Long id, String template, String row1, String row2,
			String row3, String row4, FuAdmin updateAdmin, Date updateTime) {
		super();
		this.id = id;
		this.template = template;
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		this.row4 = row4;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
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

	@Column(name = "template")
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Column(name = "row1")
	public String getRow1() {
		return row1;
	}

	public void setRow1(String row1) {
		this.row1 = row1;
	}

	@Column(name = "row2")
	public String getRow2() {
		return row2;
	}

	public void setRow2(String row2) {
		this.row2 = row2;
	}

	@Column(name = "row3")
	public String getRow3() {
		return row3;
	}

	public void setRow3(String row3) {
		this.row3 = row3;
	}

	@Column(name = "row4")
	public String getRow4() {
		return row4;
	}

	public void setRow4(String row4) {
		this.row4 = row4;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updateadmin")
	public FuAdmin getUpdateAdmin() {
		return updateAdmin;
	}

	public void setUpdateAdmin(FuAdmin updateAdmin) {
		this.updateAdmin = updateAdmin;
	}

	@Column(name = "updatetime", length = 19)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
