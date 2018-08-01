package com.hongwei.futures.web.action.admin_list_parameter;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminParameterInfoAction extends BaseAction {
	@Autowired
	private FuParameterService fuParameterService;

	private FuAdmin admin;
	private Long adminId;

	/**
	 * 参数列表
	 * 
	 * @return
	 */
	@Action("parameterInfo")
	public String parameterList() {
		try {
			FuParameter params = fuParameterService.findParameter();
			this.getActionContext().put("params", params);
			if (params.getRechargeConfig() != null) {
				this.getActionContext().put("rechargeConfig",
						JSONObject.fromObject(params.getRechargeConfig()));
			}
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

}
