package com.hongwei.futures.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.hongwei.futures.util.DesUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SignInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -5615631164011209571L;

	public String intercept(ActionInvocation ai) throws Exception {
		// 验证API来的接口是对的
//		ActionContext.getContext().get
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String method = request.getServletPath();
		if(method.contains("appUpgradeQuery")) {
			return ai.invoke();
		}else if(method.contains("notify_url")){
			return ai.invoke();
		}else if(method.contains("return_url")){
			return ai.invoke();
		}else if(method.contains("index_guide")){
			return ai.invoke();
		}else if(method.contains("appUpgradeQuery")) {
			return ai.invoke();
		}
		
		String sign = request.getParameter("sign");
		if(null == sign || "".equals(sign)) {
			JSONObject obj = new JSONObject();
			obj.put("is_success", 0);
			obj.put("message", "缺少数字签名");
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(obj.toString());
			response.getWriter().flush();
			return null; // 错误代码
		}
		String result = DesUtil.webserviceSignVerify(sign);
//		String result = "success";
		if("success".equals(result)) {
			return ai.invoke();
		}else {
			JSONObject obj = new JSONObject();
			obj.put("is_success", 0);
			obj.put("message", "数字签名解析错误");
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(obj.toString());
			response.getWriter().flush();
			return null; // 错误代码

		}
	}

}
