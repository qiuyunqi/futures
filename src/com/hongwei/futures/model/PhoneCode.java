package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "check_phone_code")
public class PhoneCode implements java.io.Serializable{
	private static final long serialVersionUID = 5939633435751315407L;
	
	private Long id;
	private String phone;
	private String code;
	
	
	public PhoneCode() {
		
	}

	public PhoneCode(Long id, String phone, String code) {
		super();
		this.id = id;
		this.phone = phone;
		this.code = code;
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
