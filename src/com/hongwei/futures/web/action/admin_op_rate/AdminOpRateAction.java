package com.hongwei.futures.web.action.admin_op_rate;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpRateAction extends BaseAction {
	private static final long serialVersionUID = 2463415008613397675L;
	@Autowired
	private FuRateService rateService;
	@Autowired
	private FuUserService userService;

	private FuAdmin admin;
	private Long adminId;

	private Long id;
	// 用户名
	private String accountName;
	// 亏损警报线百分比
	private BigDecimal warnlinePercent;
	// 亏损平仓线百分比
	private BigDecimal flatlinePercent;
	// 获取佣金比例
	private BigDecimal commissionPercent;
	// 每日利息百分比
	private BigDecimal feeDay;
	// 每月利息百分比
	private BigDecimal feePercent;
	// 3个月利息百分比
	private BigDecimal interestPercent;
	// 日配最多方案数目
	private Integer shortNum;
	// 月配最多方案数目
	private Integer longNum;

	/**
	 * 新增用户费率
	 * 
	 * @return
	 */
	@Action("newRatePop")
	public String newRate() {
		try {
			if (id != null) {
				FuRate fuRate = rateService.get(id);
				this.getActionContext().put("fuRate", fuRate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存用户费率
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveRateAjax")
	public String saveRate() {
		try {
			FuRate fuRate = new FuRate();
			if (id != null) {
				fuRate = rateService.get(id);
				fuRate.setUpdatetime(new Date());
				fuRate.setUpdateuser(adminId);
			}
			if (StringUtil.isBlank(accountName)) {
				write("-2");
				return null;
			}
			FuUser user = userService.findUserByAccount(accountName);
			if (user == null) {
				write("-3");
				return null;
			}
			if (id == null) {
				fuRate.setFuUser(user);
				fuRate.setCreatetime(new Date());
				// FuAdmin fuAdmin = new FuAdmin();
				// fuAdmin.setId(adminId);
				fuRate.setFuAdmin(admin);
				fuRate.setUpdatetime(new Date());
				fuRate.setUpdateuser(adminId);
			}
			/*
			 * if (StringUtil.isBlank(warnlinePercent)) { write("-4"); return
			 * null; }
			 */
			if (warnlinePercent == null) {
				write("-4");
				return null;
			}
			fuRate.setWarnlinePercent(warnlinePercent);
			fuRate.setFlatlinePercent(flatlinePercent);
			fuRate.setCommissionPercent(commissionPercent);
			fuRate.setFeeDay(feeDay);
			fuRate.setFeePercent(feePercent);
			fuRate.setInterestPercent(interestPercent);
			fuRate.setShortNum(shortNum);
			fuRate.setLongNum(longNum);
			rateService.save(fuRate);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除用户费率
	 * 
	 * @return
	 */
	@Action("delRateAjax")
	public String delRate() {
		try {
			if (id != null) {
				rateService.delete(id);
			}
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getWarnlinePercent() {
		return warnlinePercent;
	}

	public void setWarnlinePercent(BigDecimal warnlinePercent) {
		this.warnlinePercent = warnlinePercent;
	}

	public BigDecimal getFlatlinePercent() {
		return flatlinePercent;
	}

	public void setFlatlinePercent(BigDecimal flatlinePercent) {
		this.flatlinePercent = flatlinePercent;
	}

	public BigDecimal getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(BigDecimal commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	public BigDecimal getFeeDay() {
		return feeDay;
	}

	public void setFeeDay(BigDecimal feeDay) {
		this.feeDay = feeDay;
	}

	public BigDecimal getFeePercent() {
		return feePercent;
	}

	public void setFeePercent(BigDecimal feePercent) {
		this.feePercent = feePercent;
	}

	public BigDecimal getInterestPercent() {
		return interestPercent;
	}

	public void setInterestPercent(BigDecimal interestPercent) {
		this.interestPercent = interestPercent;
	}

	public Integer getShortNum() {
		return shortNum;
	}

	public void setShortNum(Integer shortNum) {
		this.shortNum = shortNum;
	}

	public Integer getLongNum() {
		return longNum;
	}

	public void setLongNum(Integer longNum) {
		this.longNum = longNum;
	}

}
