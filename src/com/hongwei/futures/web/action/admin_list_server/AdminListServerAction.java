package com.hongwei.futures.web.action.admin_list_server;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.service.FuServerService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListServerAction extends BaseAction {

	private FuAdmin admin;
	private Long adminId;
	@Autowired
	private FuServerService fuServerService;

	/**
	 * 显示服务器列表
	 * 
	 * @return
	 */

	@Action("serverList")
	public String serverList() {
		try {
			List<FuServer> list = fuServerService.fundList();
			this.getActionContext().put("serverList", list);
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
