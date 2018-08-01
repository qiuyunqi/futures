package com.hongwei.futures.web.action.app_im;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.rongyun.RongCloudService;

@ParentPackage("api_package")
public class AppImAction extends BaseAction {
	private static final long serialVersionUID = 6920674665178371011L;

	@Autowired
	private RongCloudService rongCloudService;

	@Action("getToken")
	public String getToken() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String userName = getHttpServletRequest().getParameter("user_name");
			String portraitUri = getHttpServletRequest().getParameter(
					"portraitUri");
			String objStr = rongCloudService.getToken(user_id, userName,
					portraitUri, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("refresh")
	public String refresh() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String userName = getHttpServletRequest().getParameter("user_name");
			String portraitUri = getHttpServletRequest().getParameter(
					"portraitUri");
			String objStr = rongCloudService.refresh(user_id, userName,
					portraitUri, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
