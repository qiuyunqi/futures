package com.hongwei.futures.web.action.admin_list_options;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class adminListOptionsAction extends BaseAction {
	@Autowired
	private WqqOptionsService wqqOptionsService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Long userId;
	private String phone;
	private Date panicTime1;
	private Date panicTime2;
	private Date payTime1;
	private Date payTime2;
	private Integer isPay;
	private Integer guessType;

	@Action("optionsList")
	public String optionsList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (userId != null) {
				map.put("userId", userId);
			}
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (panicTime1 != null) {
				map.put("panicTime1", panicTime1);
			}
			if (panicTime2 != null) {
				map.put("panicTime2", panicTime2);
			}
			if (payTime1 != null) {
				map.put("payTime1", payTime1);
			}
			if (payTime2 != null) {
				map.put("payTime2", payTime2);
			}
			if (isPay != null) {
				map.put("isPay", isPay);
			}
			if (guessType != null) {
				map.put("guessType", guessType);
			}
			List<WqqOptions> optionsList = wqqOptionsService.queryOptionsList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = wqqOptionsService.countOptions(map);
			}
			this.getActionContext().put("optionsList", optionsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getPanicTime1() {
		return panicTime1;
	}

	public void setPanicTime1(Date panicTime1) {
		this.panicTime1 = panicTime1;
	}

	public Date getPanicTime2() {
		return panicTime2;
	}

	public void setPanicTime2(Date panicTime2) {
		this.panicTime2 = panicTime2;
	}

	public Date getPayTime1() {
		return payTime1;
	}

	public void setPayTime1(Date payTime1) {
		this.payTime1 = payTime1;
	}

	public Date getPayTime2() {
		return payTime2;
	}

	public void setPayTime2(Date payTime2) {
		this.payTime2 = payTime2;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getGuessType() {
		return guessType;
	}

	public void setGuessType(Integer guessType) {
		this.guessType = guessType;
	}

}
