package com.hongwei.futures.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "match_xh")
public class MatchXhCaiFu implements Serializable {
	private static final long serialVersionUID = -2928477664986097856L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "phone")
	private String phone; // 手机号码

	@Column(name = "profit")
	private int profit; // 收益率

	@Column(name = "recommend_num")
	private int recommendNum; // 推荐人数

	@Column(name = "parent_id")
	private Long parentId; // 推荐人的userId

	@Column(name = "is_lucky")
	private int isLucky; // 0: 没有中幸运奖 1: 中了幸运奖
	
	@Column(name = "match_time")
	private Date matchTime; // 报名比赛时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public int getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public int getIsLucky() {
		return isLucky;
	}

	public void setIsLucky(int isLucky) {
		this.isLucky = isLucky;
	}

	public Date getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}

}
