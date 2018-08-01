package com.hongwei.futures.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.WebUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class NoLoginInterceptor extends AbstractInterceptor {
	/**
	 * pc前端登录拦截器
	 */
	private static final long serialVersionUID = -2207435662130379836L;

	@Autowired
	public FuUserService fuUserService;
	@Autowired
	public FuAdminService fuAdminService;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = (HttpServletRequest) arg0.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		String referer = request.getHeader("Referer");
		String url = request.getRequestURL().toString();
		if (url.startsWith("http://")) {
			url = url.replace("http://", "https://");
			arg0.getInvocationContext().put("httpsLink", url);
		}
		arg0.getInvocationContext().put("referer", referer);
		FuUser fuUser = null;
		Long userId = null;
		if (WebUtil.getCookieByName(request, "user_token") != null) {// 自动登录
			String token = WebUtil.getCookieByName(request, "user_token");
			fuUser = fuUserService.findLoginByToken(token);
			if (null != fuUser){
				userId = fuUser.getId();
				arg0.getInvocationContext().getValueStack().setValue("fuUser", fuUser);
				arg0.getInvocationContext().getValueStack().setValue("userId", userId);
			}
		}
		/*FuIpBlacklist ipBlacklist = fuIpBlacklistService.findBlackByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
		if (null != ipBlacklist && ipBlacklist.getIsBlack() == 1) {
			log.info(IP4.getIP4(request) + "在黑名单中");
			return WebUtil.returnCode(arg0, "nopermission");
		}*/
		return arg0.invoke();
	}
}
