package com.hongwei.futures.web.action.stock_user_account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuStockUserAccount;
import com.hongwei.futures.service.FuStockUserAccountService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class StockUserAccountAction extends BaseAction {

	private static final long serialVersionUID = -2654601774896291861L;
	
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuStockUserAccountService fuStockUserAccountService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private Long userId;
	private String phone;
	private String capitalAccount;

	/**
	 * 解套者资金账户统计页面
	 * 
	 * @return
	 */
	@Action("userAccount")
	public String userAccount() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (userId != null) {
				map.put("userId", userId);
			}
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (!StringUtil.isBlank(capitalAccount)) {
				map.put("capitalAccount", capitalAccount);
			}
			List<FuStockUserAccount> accountList = fuStockUserAccountService
					.queryAccountList(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = fuStockUserAccountService.countUserAccount(map);
			}
			this.getActionContext().put("accountList", accountList);
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

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

}
