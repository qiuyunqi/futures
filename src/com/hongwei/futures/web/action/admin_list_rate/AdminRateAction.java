package com.hongwei.futures.web.action.admin_list_rate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminRateAction extends BaseAction {
	@Autowired
	private FuRateService fuRateService;

	private FuAdmin admin;
	private Long adminId;
	private Integer id;
	private Integer totalCount;
	private Long userId;
	private String accountName;
	private String userName;
	private String phone;
	private String cardNumber;
	private String email;

	private Integer searchType;// 模糊查询null和0，精确查询1

	@Action("rateList")
	public String rateList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (userId != null) {
				map.put("userId", userId);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (totalCount == null) {
				totalCount = fuRateService.countRate(map);
			}
			List<FuRate> rateList = fuRateService.findRateList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("rateList", rateList);
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
}
