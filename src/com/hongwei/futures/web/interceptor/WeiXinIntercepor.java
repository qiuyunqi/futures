package com.hongwei.futures.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.WeiXinUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class WeiXinIntercepor extends AbstractInterceptor {
	private static final long serialVersionUID = -5615631164011209571L;

	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String method = request.getServletPath();
		
		FuUser fuUser = (FuUser)request.getSession().getAttribute("fuUser");
		String appid = Property.getProperty("WEIXIN_APPID");
		String url = Property.getProperty("REDIRECT_URL");
		request.setAttribute("url", url);
		request.setAttribute("appid", appid);
		
		// 放过登录 绑定手机号 发送短信验证码
		if (method.contains("wxLogin") || method.contains("addPhone") || method.contains("bindPhone") || method.contains("sendCode")) {
			return ai.invoke();
		}
		if (null != fuUser) {
			String from = request.getParameter("from");
			String isappinstalled = request.getParameter("isappinstalled");
			ActionContext actionContext = ActionContext.getContext();
			WeiXinUtil.getToken(request.getSession(), request, actionContext, from, isappinstalled);
			request.setAttribute("fuUser", fuUser);
			return ai.invoke();
		} else {
			// 解决Ajax跨域
			if (method.contains("ihavestock.htm")) {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("1");
				response.getWriter().flush();
				return null;
			} else if (method.contains("order.htm")) {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("1");
				response.getWriter().flush();
				return null;
			}
			String state = "";
			if (method.contains("meIndex.htm")) {
				state = "toMeIndex";
			} else if (method.contains("haveTicket.htm")) {
				state = "toHaveTicket";
			} else if (method.contains("findTicket.htm")) {
				state = "findTicket";
			} else if (method.contains("newIndex.htm")) {
				state = "toNewIndex";
			} else if (method.contains("forTicketInfo.htm")) {
				String id = request.getParameter("id");
				state = "toTicketInfo_" + id;
			} else {
				state = "toNewIndex";
			}
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+url+"&response_type=code&scope=snsapi_userinfo&state="+ state +"#wechat_redirect");
			return null;
		}
	}

}
