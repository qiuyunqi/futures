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

@Entity
@Table(name="fu_product")
public class FuProduct implements Serializable{

	private static final long serialVersionUID = 6771873256632740672L;
	
	private Long id;
	private String name;
	private String description;
	private String profit;
	private String profitDesc;
	private String icon;
	private String adTitle; 
	private String riseProfit;
	private String fallProfit;
	private String title;
	private Date endTime; 
	private int isDelete;
	private int orderNum;
	private Long productId; // 产品编号
	private String infoHref; // 产品介绍信息页链接地址
	private WqqContents wqqContents; //产品期数
	
	public FuProduct(Long id, String name, String description, String profit,
			String profitDesc, String icon, String adTitle, String riseProfit,
			String fallProfit, String title, Date endTime, int isDelete,
			int orderNum, Long productId, String infoHref, WqqContents wqqContents) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.profit = profit;
		this.profitDesc = profitDesc;
		this.icon = icon;
		this.adTitle = adTitle;
		this.riseProfit = riseProfit;
		this.fallProfit = fallProfit;
		this.title = title;
		this.endTime = endTime;
		this.isDelete = isDelete;
		this.orderNum = orderNum;
		this.productId = productId;
		this.infoHref = infoHref;
		this.wqqContents = wqqContents;
	}
	public FuProduct(){
		
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
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description", nullable=false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="profit", nullable=false)
	public String getProfit() {
		return profit;
	}
	
	public void setProfit(String profit) {
		this.profit = profit;
	}
	
	@Column(name="profit_desc", nullable=false)
	public String getProfitDesc() {
		return profitDesc;
	}

	public void setProfitDesc(String profitDesc) {
		this.profitDesc = profitDesc;
	}

	@Column(name="icon", nullable=false)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name = "ad_title")
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	
	@Column(name = "rise_profit")
	public String getRiseProfit() {
		return riseProfit;
	}
	public void setRiseProfit(String riseProfit) {
		this.riseProfit = riseProfit;
	}
	
	@Column(name = "fall_profit")
	public String getFallProfit() {
		return fallProfit;
	}
	public void setFallProfit(String fallProfit) {
		this.fallProfit = fallProfit;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name="is_delete")
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name="order_num", nullable=false)
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@Column(name = "product_id")
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@Column(name = "info_href")
	public String getInfoHref() {
		return infoHref;
	}
	public void setInfoHref(String infoHref) {
		this.infoHref = infoHref;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contents_id")
	public WqqContents getWqqContents() {
		return wqqContents;
	}
	public void setWqqContents(WqqContents wqqContents) {
		this.wqqContents = wqqContents;
	}
}
