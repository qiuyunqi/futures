package com.hongwei.futures.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 抽奖池
 * 
 * @author han
 * 
 */
@Entity
@Table(name = "live_jackpot")
public class LiveJackpot implements Serializable {

	private static final long serialVersionUID = 3540336023674652739L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "images")
	private String images;

	@Column(name = "changes", nullable = false)
	private Integer changes;

	@Column(name = "min_angle", nullable = false)
	private Integer minAngle;

	@Column(name = "max_angle", nullable = false)
	private Integer maxAngle;

	@Column(name = "grade", nullable = false)
	private Integer grade;

	@Column(name = "type")
	private Integer type;

	@Column(name = "score")
	private BigDecimal score;

	public LiveJackpot() {
	}

	public LiveJackpot(Long id, String name, String images, Integer changes, Integer minAngle, Integer maxAngle, Integer grade, Integer type, BigDecimal score) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.changes = changes;
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
		this.grade = grade;
		this.type = type;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getChanges() {
		return changes;
	}

	public void setChanges(Integer changes) {
		this.changes = changes;
	}

	public Integer getMinAngle() {
		return minAngle;
	}

	public void setMinAngle(Integer minAngle) {
		this.minAngle = minAngle;
	}

	public Integer getMaxAngle() {
		return maxAngle;
	}

	public void setMaxAngle(Integer maxAngle) {
		this.maxAngle = maxAngle;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
}
