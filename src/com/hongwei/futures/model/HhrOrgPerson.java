package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hhr_org_person")
public class HhrOrgPerson implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3071813395789684104L;
	private int id;
	private String userName;    // 真实姓名
    private String cerNum;      // 从业资格号
    private String zxNum;       // 投资咨询从业证书号
    private String orgType;     // 区分期货证券  1:期货  2:证券
    
	public HhrOrgPerson() {
        super();
    }
    public HhrOrgPerson(int id, String userName, String cerNum, String zxNum, String orgType) {
        super();
        this.id = id;
        this.userName = userName;
        this.cerNum = cerNum;
        this.zxNum = zxNum;
        this.orgType = orgType;
    }
    
    @Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "cer_num")
	public String getCerNum() {
		return cerNum;
	}
	public void setCerNum(String cerNum) {
		this.cerNum = cerNum;
	}
	@Column(name = "zx_num")
	public String getZxNum() {
		return zxNum;
	}
	public void setZxNum(String zxNum) {
		this.zxNum = zxNum;
	}
	@Column(name = "org_type")
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
}
