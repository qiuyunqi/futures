package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 预约开户人的基本信息
 * @author  han
 *
 */
@Entity
@Table(name = "fu_appointment")
public class FuAppointmentInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4850181709292334403L;
	/**
	 * 
	 */
	private Long id;				// 标准 id
	private String tag;				// 从业员的标签
	private String avatar;			// 从业员的头像
	private String userName;		// 从业员的姓名
	private String qualifyId;		// 从业员资格编号
	private String phone;			// 从业员的电话号码
	private String securitiesName;  // 从业员从事的证券名称
	private int orderNum;			// 排序编码  0 最大
	private int isDel;
	
	public FuAppointmentInfo(){
		
	}
	
	public FuAppointmentInfo(Long id, String tag, String avatar,
			String userName, String qualifyId, String phone,
			String securitiesName, int orderNum, int isDel) {
		super();
		this.id = id;
		this.tag = tag;
		this.avatar = avatar;
		this.userName = userName;
		this.qualifyId = qualifyId;
		this.phone = phone;
		this.securitiesName = securitiesName;
		this.orderNum = orderNum;
		this.isDel = isDel;
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
	
	@Column(name = "tag")
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Column(name = "avatar")
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "qualify_id")
	public String getQualifyId() {
		return qualifyId;
	}
	public void setQualifyId(String qualifyId) {
		this.qualifyId = qualifyId;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "securities_name")
	public String getSecuritiesName() {
		return securitiesName;
	}
	public void setSecuritiesName(String securitiesName) {
		this.securitiesName = securitiesName;
	}
	
	@Column(name = "order_num")
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@Column(name = "is_del")
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
	
}
