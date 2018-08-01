package com.hongwei.futures.web.action.user_login;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage("fu_common")
public class SingleUserLoginAction extends BaseAction {

	private static final long serialVersionUID = 5930321517173595782L;

	@Resource
	private FuUserService fuUserService;
	public String userName;

	/**
	 * 修改昵称
	 * 
	 * @return
	 */
	@Action("updateUserName")
	public String updateUsreName() {
		try {
			HttpServletRequest request = this.getHttpServletRequest();
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			JSONObject obj = new JSONObject();
			if (null == userName || "".equals(userName)) {
				obj.put("success", 0);
				obj.put("message", "请输入您的昵称");
				writeJson(obj);
				return null;
			}
			String[] keys =  (String[])ActionContext.getContext().getApplication().get("keys");
			for (String key : keys) {
				if (userName.contains(key)) {
					obj.put("success", 0);
					obj.put("message", "您的昵称有非法字符");
					writeJson(obj);
					return null;
				}
			}
			List<FuUser> fuUserList = fuUserService.findSameNickNameUser(userName);
			if (null != fuUserList && fuUserList.size() > 0) {
				obj.put("success", 0);
				obj.put("message", "您输入的昵称已存在, 请重新输入");
				writeJson(obj);
				return null;
			}
			Date nickNameTime = fuUser.getNickNameTime();
			Date now = new Date();
			long day = 0L;
			if (null != nickNameTime) {
				long changeValue = now.getTime() - nickNameTime.getTime();
				day = changeValue / (24 * 60 * 60 * 1000);
				if (day <= 30) {
					obj.put("success", 0);
					obj.put("message", "每30天只能修改一次昵称");
					writeJson(obj);
					return null;
				}
			}
			fuUser.setNickName(userName);
			fuUser.setNickNameTime(now);
			fuUserService.save(fuUser);
			request.getSession().invalidate();
			request.getSession().setAttribute("fuUser", fuUser);
			obj.put("success", 1);
			obj.put("message", "已更新您的昵称");
			writeJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@Action(value = "logout", results = { @Result(name = "toCenter", location = "/user_center/centerIndex.htm") })
	public String logout() {
		try {
			WebUtil.addCookie(this.getHttpServletResponse(), "user_token", "",
					1);
			WebUtil.addCookie(this.getHttpServletResponse(), "token_name", "",
					0);
			this.getHttpServletRequest().getSession().removeAttribute("fuUser");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toCenter";
	}

	@Action(value = "setCookie")
	public String setCookie() {
		try {
			Cookie ticket = new Cookie("token_name", this
					.getHttpServletRequest().getParameter("ticket"));
			ticket.setPath("/");
			ticket.setMaxAge(Integer.parseInt(this.getHttpServletRequest()
					.getParameter("expiry")));
			this.getHttpServletResponse().addCookie(ticket);

			String gotoURL = this.getHttpServletRequest().getParameter(
					"gotoURL");
			if (gotoURL != null) {
				this.getHttpServletResponse().sendRedirect(gotoURL);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
