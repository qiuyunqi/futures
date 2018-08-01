package com.hongwei.futures.web.action.admin_op_commission;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuCommission;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuCommissionService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class CommissionAction extends BaseAction {
	@Autowired
	private FuCommissionService fuCommissionService;

	private FuAdmin admin;
	private Long adminId;

	private Long id;
	private String remark;
	private Integer status;

	/**
	 * 审核浮层
	 * 
	 * @return
	 */
	@Action("checkAjax")
	public String checkAjax() {
		return SUCCESS;
	}

	/**
	 * 审核佣金
	 * 
	 * @return
	 */
	@Action("checkCommissionAjax")
	public String checkCommission() {
		try {
			if (id != null) {
				FuCommission commission = fuCommissionService.get(id);
				commission.setStatus(status);// 0未处理/1已兑换/2已拒绝
				commission.setFuAdmin(admin);
				commission.setCheckTime(new Date());
				// 佣金通过审核后，生产佣金兑换明细,用户更新已兑佣金
				if (status != null && status == 1) {// 佣金兑换成功
					// 已兑佣金
					FuUser user = commission.getFuUserByUserId();
					user.setExchangeMoney(user.getExchangeMoney().add(
							commission.getCommissionMoney()));
					user.setAccountBalance(user.getAccountBalance().add(
							commission.getCommissionMoney()));// 余额
					fuCommissionService
							.saveCommissionExchange(commission, user);
				} else {
					fuCommissionService.save(commission);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除佣金记录
	 * 
	 * @return
	 */
	@Action("delCommissionAjax")
	public String delCommission() {
		try {
			if (id != null) {
				FuCommission commission = fuCommissionService.get(id);
				commission.setState(0);
				fuCommissionService.save(commission);
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
