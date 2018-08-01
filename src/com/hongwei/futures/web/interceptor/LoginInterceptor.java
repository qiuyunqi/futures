package com.hongwei.futures.web.interceptor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.WebUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * pc前端登录拦截器
	 */
	private static final long serialVersionUID = -2207435662130379836L;
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	public static String COOKIE_NAME = "token_name";
	@Autowired
	public FuUserService fuUserService;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ai.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ai.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		String requestUrl = request.getRequestURI();
		String path = request.getContextPath();

		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (fuUser != null) {
			Long userId = fuUser.getId();
			ai.getInvocationContext().getValueStack().setValue("fuUser", fuUser);
			ai.getInvocationContext().getValueStack().setValue("userId", userId);
		}

		String gotoURL = request.getParameter("gotoURL");
		if (requestUrl.equals(path + "/user_login/logout.htm")) {
			gotoURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/user_center/centerIndex.htm";
		}
		if (gotoURL == null) {
			gotoURL = request.getRequestURL().toString();
		}

		String URL = Property.getProperty("SSO_SERVICE") + "?action=preLogin&setCookieURL=" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/user_login/setCookie.htm&gotoURL=" + gotoURL;

		Cookie ticket = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_NAME)) {
					ticket = cookie;
					break;
				}
			}

		if (requestUrl.equals(path + "/user_login/logout.htm")) {
			request.getSession().removeAttribute("fuUser");
			WebUtil.addCookie(response, COOKIE_NAME, "", 0);
			WebUtil.addCookie(response, "user_token", "", 0);
			doLogout(request, response, ticket, URL);
			return null;

		}

		if (requestUrl.equals(path + "/user_login/setCookie.htm")) {
			ai.invoke();
		} else if (ticket != null) {
			boolean flag = authCookie(request, response, ticket, URL);
			if (flag) {
				ai.invoke();
			} else {
				response.sendRedirect(URL);
			}
		} else {
			response.sendRedirect(URL);
		}

		return null;
	}

	private boolean doLogout(HttpServletRequest request, HttpServletResponse response, Cookie ticket, String url) throws JSONException, IOException {
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair("action", "logout");
		params[1] = new NameValuePair(COOKIE_NAME, ticket.getValue());
		try {
			post(request, response, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.sendRedirect(url);
		}
		return true;
	}

	private boolean authCookie(HttpServletRequest request, HttpServletResponse response, Cookie ticket, String url) throws IOException {
		NameValuePair[] params = new NameValuePair[2];
		params[0] = new NameValuePair("action", "authTicket");
		params[1] = new NameValuePair(COOKIE_NAME, ticket.getValue());
		try {
			JSONObject result = post(request, response, params);
			if (result.getBoolean("error")) {
				return false;
			} else {
				logger.info(result);
				FuUser fuUser = fuUserService.get((long) result.getInt("userId"));
				logger.info("authCookie session -->" + new Date() + fuUser.getId());
				request.getSession().setAttribute("fuUser", fuUser);
				return true;
			}
		} catch (JSONException e) {
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(url);
			throw new RuntimeException(e);
		}

	}

	private JSONObject post(HttpServletRequest request, HttpServletResponse response, NameValuePair[] params) throws HttpException, IOException, JSONException {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(Property.getProperty("SSO_SERVICE"));
		postMethod.addParameters(params);
		switch (httpClient.executeMethod(postMethod)) {
		case HttpStatus.SC_OK:
			return new JSONObject(postMethod.getResponseBodyAsString());
		default:
			// 其它处理
			return null;
		}
	}
}