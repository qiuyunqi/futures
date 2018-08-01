package com.hongwei.futures.web.action.admin_list_admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListAdminAction extends BaseAction {
	@Autowired
	private FuAdminService adminService;

	private FuAdmin admin;
	private Long adminId;

	private String account;
	private String name;
	private Integer totalCount;

	/**
	 * 后台账号列表
	 * 
	 * @return
	 */
	@Action("adminList")
	public String adminList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(account)) {
				map.put("account", account);
			}
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (totalCount == null)
				totalCount = adminService.getCount(map);
			List<Object[]> adminList = adminService.findList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("adminList", adminList);
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
