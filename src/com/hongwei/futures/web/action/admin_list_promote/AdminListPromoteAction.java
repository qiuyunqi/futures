package com.hongwei.futures.web.action.admin_list_promote;

import java.math.BigDecimal;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.HhrPromoteParameter;
import com.hongwei.futures.service.HhrPromoteParameterService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListPromoteAction extends BaseAction {
	@Autowired
	private HhrPromoteParameterService promoteParameterService;

	private FuAdmin admin;

	private Long adminId;

	private Long id;
	private BigDecimal totalMoney;
	private String lineMoney;
	private String xz;

	/**
	 * 参数列表
	 * 
	 * @return
	 */
	@Action("promoteInfo")
	public String parameterList() {
		try {
			HhrPromoteParameter promote = promoteParameterService
					.findParameter();
			this.getActionContext().put("promote", promote);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存参数设置
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveParamsAjax")
	public String saveParamsAjax() {
		try {
			HhrPromoteParameter param = promoteParameterService.findParameter();
			param.setIsOpen(Integer.valueOf(xz));
			param.setLineMoney(lineMoney);
			param.setTotalMoney(totalMoney);
			promoteParameterService.save(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getLineMoney() {
		return lineMoney;
	}

	public void setLineMoney(String lineMoney) {
		this.lineMoney = lineMoney;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
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
}
