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
@Table(name = "hhr_quali_register")
public class HhrQualiRegister implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4392846659944057594L;
	private Long id;
	private FuUser fuUser;
	private String userName;
	private String qualiNum;
	private String qualiPic;
	private Date createDate;
	private Integer isChecked;
	private Integer type;
	
	public HhrQualiRegister() {
		super();
	}

	public HhrQualiRegister(Long id, FuUser fuUser, String userName, String qualiNum, String qualiPic, Date createDate, Integer isChecked, Integer type) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.userName = userName;
		this.qualiNum = qualiNum;
		this.qualiPic = qualiPic;
		this.createDate = createDate;
		this.isChecked = isChecked;
		this.type = type;
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
	
	

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "quali_num")
	public String getQualiNum() {
		return qualiNum;
	}
  
	public void setQualiNum(String qualiNum) {
		this.qualiNum = qualiNum;
	}

	@Column(name = "quali_pic")
	public String getQualiPic() {
		return qualiPic;
	}

	public void setQualiPic(String qualiPic) {
		this.qualiPic = qualiPic;
	}

	@Column(name = "is_checked")
	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}
	
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
