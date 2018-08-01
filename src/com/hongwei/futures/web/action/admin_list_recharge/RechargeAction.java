package com.hongwei.futures.web.action.admin_list_recharge;

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
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class RechargeAction extends BaseAction {
	@Autowired
	private FuRechargeService fuRechargeService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private String userName;
	private Integer type;
	private Integer rechargeStatus;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date time1;
	private Date time2;
	private String rechargeAccount;
	private Integer state;
	private String phone;
	private Integer totalCount;

	/**
	 * 充值申请列表
	 * 
	 * @return
	 */
	@Action("rechargeList")
	public String rechargeList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (state == null) {
				state = 1;
			}
			map.put("state", state);
			if (id != null) {
				map.put("id", id);
			}
			if (type != null) {
				map.put("type", type);
			}
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (rechargeStatus != null) {
				map.put("rechargeStatus", rechargeStatus);
			}
			if (!StringUtil.isBlank(rechargeAccount)) {
				map.put("rechargeAccount", rechargeAccount);
			}
			if (money1 != null) {
				map.put("money1", money1);
			}
			if (money2 != null) {
				map.put("money2", money2);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			if (totalCount == null)
				totalCount = fuRechargeService.getCount(map);
			List<FuRecharge> rechargeList = fuRechargeService.findBy(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("rechargeList", rechargeList);
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRechargeStatus() {
		return rechargeStatus;
	}

	public void setRechargeStatus(Integer rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getRechargeAccount() {
		return rechargeAccount;
	}

	public void setRechargeAccount(String rechargeAccount) {
		this.rechargeAccount = rechargeAccount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
