package com.hongwei.futures.web.action.admin_op_options;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.WqqContents;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.WqqContentsService;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpOptionsAction extends BaseAction{
	@Autowired
	private WqqOptionsService wqqOptionsService;
	@Autowired
	private WqqContentsService wqqContentsService;
	
	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private BigDecimal acceptFactor;
	private Integer guessType;
	private Long contentsId;
	
	
	
	public FuAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAcceptFactor() {
		return acceptFactor;
	}

	public void setAcceptFactor(BigDecimal acceptFactor) {
		this.acceptFactor = acceptFactor;
	}

	public Integer getGuessType() {
		return guessType;
	}

	public void setGuessType(Integer guessType) {
		this.guessType = guessType;
	}

	public Long getContentsId() {
		return contentsId;
	}

	public void setContentsId(Long contentsId) {
		this.contentsId = contentsId;
	}
	
}
