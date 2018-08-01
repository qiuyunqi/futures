package com.hongwei.futures.web.action.admin_list_money_flow;

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
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class MoneyFlowAction extends BaseAction {
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private String userName;
	private Long dictionaryId;
	private Integer status;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date date1;
	private Date date2;
	private String rechargeType;
	private Integer totalCount;

	/**
	 * 资金进出记录—查询余额与银行卡之间的记录
	 * 
	 * @return
	 */
	@Action("moneyFlowList")
	public String moneyFlowList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bigType", 1);// 充值提款
			if (id != null) {
				map.put("id", id);
			}
			if (dictionaryId != null) {
				map.put("dictionaryId", dictionaryId);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (status != null) {
				map.put("status", status);
			}
			if (!StringUtil.isBlank(rechargeType)) {
				map.put("rechargeType", rechargeType);
			}
			if (money1 != null) {
				map.put("money1", money1);
			}
			if (money2 != null) {
				map.put("money2", money2);
			}
			if (date1 != null) {
				map.put("date1", date1);
			}
			if (date2 != null) {
				map.put("date2", date2);
			}
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListBy(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("detailList", detailList);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getMoney1() {
		return money1;
	}

	public void setMoney1(BigDecimal money1) {
		this.money1 = money1;
	}

	public BigDecimal getMoney2() {
		return money2;
	}

	public void setMoney2(BigDecimal money2) {
		this.money2 = money2;
	}

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

}
