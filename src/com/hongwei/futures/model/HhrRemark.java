package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hhr_remark")
public class HhrRemark implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3739642737684918991L;
	private Long id;
	private FuUser fuUser;
	private FuUser relevanceUser;
	private String remarkName;
	
	public HhrRemark() {
		super();
	}
	
	
	public HhrRemark(Long id, FuUser fuUser, FuUser relevanceUser,
			String remarkName) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.relevanceUser = relevanceUser;
		this.remarkName = remarkName;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relevance_id")
	public FuUser getRelevanceUser() {
		return relevanceUser;
	}
	public void setRelevanceUser(FuUser relevanceUser) {
		this.relevanceUser = relevanceUser;
	}

	@Column(name = "remark_name")
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

}
