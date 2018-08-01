package com.hongwei.futures.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "wx_draw_info")
public class DrawInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1785995549657827395L;
	private Long id;
	private Long userId;
	private Integer drawNum;
	private Date firstDrawTime;
	private Date secondDrawTime;
	private int isFirstLogin;
	private String drawName;
	private String userName;
	private String phone;
	private String address;
	private Integer oldDrawNum; // 已经抽奖的次数
	private Integer prizeId;
	private Integer isNewUser; //时候是新用户 0: 是新用户 1:不是新用户
	
	
	public DrawInfo(Long id, Long userId, Integer drawNum, Date firstDrawTime,
			Date secondDrawTime, int isFirstLogin, String userName,
			String phone, String address, String drawName, Integer oldDrawNum, 
			Integer prizeId, Integer isNewUser) {
		super();
		this.id = id;
		this.userId = userId;
		this.drawNum = drawNum;
		this.firstDrawTime = firstDrawTime;
		this.secondDrawTime = secondDrawTime;
		this.isFirstLogin = isFirstLogin;
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.drawName = drawName;
		this.oldDrawNum = oldDrawNum;
		this.prizeId = prizeId;
		this.isNewUser = isNewUser;
	}

	public DrawInfo() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="draw_num")
	public Integer getDrawNum() {
		return drawNum;
	}
	public void setDrawNum(Integer drawNum) {
		this.drawNum = drawNum;
	}
	
	@Column(name="first_draw_time")
	public Date getFirstDrawTime() {
		return firstDrawTime;
	}
	public void setFirstDrawTime(Date firstDrawTime) {
		this.firstDrawTime = firstDrawTime;
	}
	
	@Column(name = "second_draw_time")
	public Date getSecondDrawTime() {
		return secondDrawTime;
	}
	public void setSecondDrawTime(Date secondDrawTime) {
		this.secondDrawTime = secondDrawTime;
	}

	@Column(name="is_first_login")
	public int getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(int isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "draw_name")
	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	@Column(name = "old_draw_num")
	public Integer getOldDrawNum() {
		return oldDrawNum;
	}

	public void setOldDrawNum(Integer oldDrawNum) {
		this.oldDrawNum = oldDrawNum;
	}

	@Column(name = "prize_id")
	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	@Column(name="is_new_user")
	public int getIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(Integer isNewUser) {
		this.isNewUser = isNewUser;
	}
	
	
	
}
