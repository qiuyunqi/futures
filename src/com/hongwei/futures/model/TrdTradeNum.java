package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trd_trade_num")
public class TrdTradeNum implements java.io.Serializable{

	private static final long serialVersionUID = 3657711009547365337L;
	private Long id;
	private Long userId;
	private String instrumentId;
	private Integer num;
	private String dateTime;
	
	
	
	public TrdTradeNum(Long id, Long userId, String instrumentId, Integer num,
			String dateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.instrumentId = instrumentId;
		this.num = num;
		this.dateTime = dateTime;
	}
	
	public TrdTradeNum() {
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
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "instrument_id")
	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Column(name = "num")
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Column(name = "date_time")
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
	
}
