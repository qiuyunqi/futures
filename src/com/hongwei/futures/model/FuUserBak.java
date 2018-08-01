package com.hongwei.futures.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 纯粹是作为fuUser的投影, 不参与数据的增删改
 * 	用在FuUserDaoImpl.findUserByHhrParentId 这个方法中
 * @author han
 */
@Entity
@Table(name = "fu_user")
public class FuUserBak  implements Serializable{
	private static final long serialVersionUID = 774320829607763938L;
	
	private Long id;
	private BigInteger userId;
	private BigInteger num;
	private String userName;
	private String nickName;
	private String avatar;
	
	public FuUserBak(){}
	
	public FuUserBak(Long id, BigInteger userId, BigInteger num, String userName,
			String nickName, String avatar) {
		super();
		this.id = id;
		this.userId = userId;
		this.num = num;
		this.userName = userName;
		this.nickName = nickName;
		this.avatar = avatar;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	
	@Transient
	public BigInteger getNum() {
		return num;
	}
	public void setNum(BigInteger num) {
		this.num = num;
	}
	
	@Transient
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Transient
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Transient
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
