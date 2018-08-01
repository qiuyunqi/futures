package com.hongwei.futures.web.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.model.SysRole;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.SysRolePurviewService;
import com.hongwei.futures.service.SysRoleService;
import com.hongwei.futures.util.WebUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PurviewInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -5615631164011209571L;

	@Autowired
	public FuAdminService fuAdminService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRolePurviewService sysRolePurviewService;

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation ai) throws Exception {
		String namespace = ai.getProxy().getNamespace();
		String actionName = ai.getProxy().getActionName();
		String privUrl = namespace + "/" + actionName;

		FuAdmin fuAdmin = null;
		// FuAdmin fuAdmin = (FuAdmin)
		// ActionContext.getContext().getSession().get("admin");
		if (WebUtil.getCookieByName(ServletActionContext.getRequest(), "admin_token") != null) {// 自动登录
			String token = WebUtil.getCookieByName(ServletActionContext.getRequest(), "admin_token");
			fuAdmin = fuAdminService.findLoginByToken(token);
			if (null == fuAdmin) {// 跳转登录页面
				if (privUrl.startsWith("/admin_login/adminLogin")) { // 登录的URL
					return ai.invoke();
				} else if (privUrl.startsWith("/admin_login/adminForward")) {
					return ai.invoke();
				} else {
					return WebUtil.returnCode(ai, "reAdminLogin");
				}
			} else {
				ai.getInvocationContext().getValueStack().setValue("admin", fuAdmin);
				ai.getInvocationContext().getValueStack().setValue("adminId", fuAdmin.getId());
				if (privUrl.startsWith("/admin_login/adminForward")) {
					return ai.invoke();
				}
				if (privUrl.startsWith("/admin_login/indexHome")) {
					return ai.invoke();
				}
				if (privUrl.startsWith("/admin_login/logoutAjax")) {
					return ai.invoke();
				}
				privUrl = privUrl + ".htm";
				boolean result = false;
				List<SysPurview> priviList = (List<SysPurview>) ActionContext.getContext().getSession().get("priviList");
				if (null == priviList) { // sesson过期
					// 重新存储用户对应的角色权限 和用户对象
					List<SysRole> roleList = sysRoleService.findRoleListByRoleId(fuAdmin.getId());
					for (SysRole role : roleList) {
						priviList = sysRolePurviewService.findPurviewListByRoleId(role.getId());
					}
					ActionContext.getContext().getSession().put("priviList", priviList);
					ActionContext.getContext().getSession().put("admin", fuAdmin);
					// return WebUtil.returnCode(ai, "nopermission");// 没有权限的页面
				}
				if (fuAdmin.getType() == 1) { // 超管
					result = true;
				} else {
					int pos = privUrl.indexOf("?");
					if (pos > -1) {
						privUrl = privUrl.substring(0, pos);
					}
					Collection<String> privilegeUrls = new ArrayList<String>();

					for (SysPurview p : priviList) {
						privilegeUrls.add(p.getUrl());
					}
					if (!privilegeUrls.contains(privUrl)) {

					} else {
						for (String pUrl : privilegeUrls) {
							if (privUrl.equals(pUrl)) {
								result = true;
							}
						}
					}
				}
				if (result == true) {
					return ai.invoke();
				} else {
					return WebUtil.returnCode(ai, "nopermission");// 没有权限的页面
				}
			}
		} else {
			return WebUtil.returnCode(ai, "reAdminLogin");
		}
	}

}
