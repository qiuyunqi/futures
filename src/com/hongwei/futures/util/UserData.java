package com.hongwei.futures.util;

public class UserData {
	
	private Integer t_level;//节点级别 1 学校 2学院 3 本硕博 4 年级 5 班级
	private Long t_coId;//学院ID
	private Integer t_type;//类型（本1 硕2 博3）
	private Integer t_grade;//年级
	private Long t_clazId;//班级ID
	public void setT_level(Integer t_level) {
		this.t_level = t_level;
	}
	public Integer getT_level() {
		return t_level;
	}
	public void setT_coId(Long t_coId) {
		this.t_coId = t_coId;
	}
	public Long getT_coId() {
		return t_coId;
	}
	public void setT_type(Integer t_type) {
		this.t_type = t_type;
	}
	public Integer getT_type() {
		return t_type;
	}
	public void setT_grade(Integer t_grade) {
		this.t_grade = t_grade;
	}
	public Integer getT_grade() {
		return t_grade;
	}
	public void setT_clazId(Long t_clazId) {
		this.t_clazId = t_clazId;
	}
	public Long getT_clazId() {
		return t_clazId;
	}
}
