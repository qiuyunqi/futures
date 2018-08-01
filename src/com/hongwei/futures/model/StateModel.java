package com.hongwei.futures.model;

import java.io.Serializable;

public class StateModel implements Serializable {

	private static final long serialVersionUID = 2453112554747875068L;
	
	private Long id;
	private Long idValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdValue() {
		return idValue;
	}
	public void setIdValue(Long idValue) {
		this.idValue = idValue;
	}
	
	

}
