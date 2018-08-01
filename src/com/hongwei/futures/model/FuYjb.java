package com.hongwei.futures.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_yjb")
public class FuYjb implements Serializable{

	private static final long serialVersionUID = 4924205270971295385L;
	
	private Long id;
	private String marketValue;
	private String available;
	private String profitImage;
	private String agreement;
	private String rank;
	private String moreDetail;
	private String cumulativeVale;
	
	public FuYjb(Long id, String marketValue, String available, String profitImage,
			String agreement, String rank, String moreDetail,
			String cumulativeVale) {
		super();
		this.id = id;
		this.marketValue = marketValue;
		this.available = available;
		this.profitImage = profitImage;
		this.agreement = agreement;
		this.rank = rank;
		this.moreDetail = moreDetail;
		this.cumulativeVale = cumulativeVale;
	}
	
	public FuYjb(){
		
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = true, unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "market_value")
	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	
	@Column(name = "available")
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	@Column(name = "profit_image")
	public String getProfitImage() {
		return profitImage;
	}

	public void setProfitImage(String profitImage) {
		this.profitImage = profitImage;
	}

	@Column(name = "agreement")
	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	@Column(name = "rank")
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Column(name = "more_detail")
	public String getMoreDetail() {
		return moreDetail;
	}

	public void setMoreDetail(String moreDetail) {
		this.moreDetail = moreDetail;
	}

	@Column(name = "cumulative_value")
	public String getCumulativeVale() {
		return cumulativeVale;
	}

	public void setCumulativeVale(String cumulativeVale) {
		this.cumulativeVale = cumulativeVale;
	}
}
