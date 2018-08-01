package com.hongwei.futures.web.action.admin_list_bad_record;

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
import com.hongwei.futures.model.FuBadCredit;
import com.hongwei.futures.service.FuBadCreditService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListBadRecordAction extends BaseAction {
	@Autowired
	private FuBadCreditService fuBadCreditService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Integer state;
	private String accountName;
	private String userName;
	private Long programId;
	private Integer type;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date time1;
	private Date time2;

	/**
	 * 不良记录列表
	 * 
	 * @return
	 */

	@Action("badList")
	public String badList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (state != null)
				map.put("state", state);
			if (!StringUtil.isBlank(accountName))
				map.put("accountName", accountName);
			if (!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (programId != null)
				map.put("programId", programId);
			if (type != null)
				map.put("type", type);
			if (money1 != null)
				map.put("money1", money1);
			if (money2 != null)
				map.put("money2", money2);
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);

			if (totalCount == null)
				totalCount = fuBadCreditService.countBadCredit(map);
			List<FuBadCredit> badList = fuBadCreditService.findBadList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("badList", badList);
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
