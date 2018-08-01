package com.hongwei.futures.web.action.stock_money_info;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class StockMoneyInfoAction extends BaseAction {

	private static final long serialVersionUID = 2998019339710501377L;
	
	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private Long userId;
	private String phone;
	private Date time1;
	private Date time2;
	private BigDecimal money;

	@Autowired
	private FuStockMoneyInfoService fuStockAccountService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;

	@Action("moneyInfo")
	public String moneyInfo() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (userId != null) {
				map.put("userId", userId);
			}
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			List<FuStockMoneyInfo> stockMoneyList = fuStockAccountService
					.queryStockMoneyInfo(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = fuStockAccountService.countStockMoneyInfo(map);
			}
			this.getActionContext().put("stockMoneyList", stockMoneyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 一键支付
	 * @return
	 */
	@Action("oneKeyPay")
	public String oneKeyPay() {
		FuUser fuUser = fuUserService.get(userId);
		this.getActionContext().put("money", money);
		this.getActionContext().put("fuUser", fuUser);
		return SUCCESS;
	}
	
	/**
	 * 保存一键支付
	 * @return
	 */
	@Action("saveOneKeyPay")
	public String saveOneKeyPay() {
		try {
			String result = fuStockMoneyInfoService.saveOneKeyPay(fuUserService.get(userId), money);
			write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
}
