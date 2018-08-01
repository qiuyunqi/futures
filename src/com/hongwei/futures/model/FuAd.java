package com.hongwei.futures.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="fu_ad")
public class FuAd implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3589917268907418420L;
	private Long id;
	private String imageUrl;
	private String imageAttr;
	private String hrefUrl;
	private int position;
	private String posName;		// 不存数据库 只是用于前台显示
	private Long fuProductId;		// fu_profuct的主键id 
	private int orderNum;
	private int isDelete;
	
	
	public FuAd(Long id, String imageUrl, String imageAttr, String hrefUrl, int position, String posName,
			Long fuProductId, int orderNum, int isDelete) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.imageAttr = imageAttr;
		this.hrefUrl = hrefUrl;
		this.position = position;
		this.posName = posName;
		this.fuProductId = fuProductId;
		this.orderNum = orderNum;
		this.isDelete = isDelete;
	}
	public FuAd(){
		
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

	@Column(name = "image_url")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "image_attr")
	public String getImageAttr() {
		return imageAttr;
	}
	public void setImageAttr(String imageAttr) {
		this.imageAttr = imageAttr;
	}
	
	@Column(name="a_url")
	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	@Column(name="position")
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Column(name="order_num", nullable=false)
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name="is_delete")
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	@Transient
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	
	@Column(name = "fu_product_id")
	public Long getFuProductId() {
		return fuProductId;
	}
	public void setFuProductId(Long fuProductId) {
		this.fuProductId = fuProductId;
	}
	
	
}
