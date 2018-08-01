package com.hongwei.futures.web.action.admin_login;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.AdminLoginLog;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuIpBlacklist;
import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.model.SysRole;
import com.hongwei.futures.service.AdminLoginLogService;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuIpBlacklistService;
import com.hongwei.futures.service.SysRolePurviewService;
import com.hongwei.futures.service.SysRoleService;
import com.hongwei.futures.util.CommonUtils;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.MailEngine;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;

@SuppressWarnings("serial")
public class AdminLoginAction extends BaseAction {
	@Autowired
	private AdminLoginLogService adminLoginLogService;
	@Autowired
	private FuIpBlacklistService fuIpBlacklistService;
	@Autowired
	private FuAdminService fuAdminService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRolePurviewService sysRolePurviewService;
	@Autowired
	private MailEngine mailEngine;
	private String account;
	private String password;
	private Boolean isRemain;// 是否记住账号
	private Boolean isAuto;// 是否自动登录

	private static Logger logger = Logger.getLogger(AdminLoginAction.class);

	/**
	 * 后台登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "adminLogin", results = { @Result(name = "toIndex", location = "/admin_login/indexHome.htm", type = "redirect") })
	public String adminLogin() {
		try {
			if (WebUtil.getCookieByName(getHttpServletRequest(), "remain_token") != null) {
				String account = WebUtil.getCookieByName(getHttpServletRequest(), "remain_token");
				this.getActionContext().put("account", account);
			}
			if (WebUtil.getCookieByName(getHttpServletRequest(), "admin_token") != null) {
				FuAdmin admin = fuAdminService.findLoginByToken(WebUtil.getCookieByName(getHttpServletRequest(), "admin_token"));
				if (admin != null && admin.getIsAuto() != null && admin.getIsAuto()) {// 自动登录
					// 存储用户的基本信息
					getSession().put("admin", admin);
					return "toIndex";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 登陆操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("doAdminLoginAjax")
	public String doAdminLoginAjax() {
		try {
			if (StringUtil.isBlank(account)) {
				write("-2");// 请输入登录账号
				return null;
			}
			if (StringUtil.isBlank(password)) {
				write("-3");// 请输入登录密码
				return null;
			}
			FuAdmin admin = fuAdminService.findAdminByAccount(account);
			if (admin == null) {
				write("-4");// 账号不存在
				return null;
			}
			// 登录ip地址
			String ipStr = IP4.getIP4(ServletActionContext.getRequest());
			// ip表size
			int ipBlackCount = fuIpBlacklistService.countIpBlack(new HashMap<String, Object>());
			// ip表size0,不校验
			if (ipBlackCount != 0 && !admin.getAccount().equals("admin")) {
				// 根据ip地址查询记录
				FuIpBlacklist ipBlacklist = fuIpBlacklistService.findBlackByRegIp(ipStr);
				// 如果查不到ip地址对应的表记录,或者有记录但是黑名单状态,均禁止登录
				if (ipBlacklist == null || (ipBlacklist != null && ipBlacklist.getIsBlack() == 1)) {
					logger.info("您的IP地址: " + ipStr + " 在黑名单中");
					write("-6");
					return null;
				}
			}
			// 禁止登录时间校验
			if (admin.getForbidLoginTime() != null) {
				if (admin.getForbidLoginTime().compareTo(new Date()) >= 0) {
					logger.info("您的IP地址: " + ipStr + " 禁止登录中");
					write("-7");
					return null;
				}
			}
			// 密码错误
			if (!admin.getPassword().equals(CommonUtils.getMd5(password))) {
				// 密码错误次数小于5
				if (admin.getLoginErrorTimes() < 5) {
					// 密码错误次数累加
					admin.setLoginErrorTimes(admin.getLoginErrorTimes() == null ? 1 : admin.getLoginErrorTimes() + 1);
					fuAdminService.save(admin);
				}
				// 密码错误次数达到5时:
				// 1.禁止登录一天
				// 2.邮件通知超级管理员admin
				// 3.将当前ip加入黑名单
				if (admin.getLoginErrorTimes() != null && admin.getLoginErrorTimes() == 5) {
					Calendar c = Calendar.getInstance();
					c.setTime(new Date());
					c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
					admin.setForbidLoginTime(c.getTime());
					fuAdminService.save(admin);

					String[] emailAddresses = new String[1];
					emailAddresses[0] = admin.getEmail();
					mailEngine.sendMessage(emailAddresses, "资产管理平台<service@hhr360.com>", "后台用户:" + admin.getName() + "连续登录错误达到5次,禁止登录1天,解禁时间:" + admin.getForbidLoginTime().toLocaleString(), "后台用户禁止登录通知", null, null);

					FuIpBlacklist ipBlack = fuIpBlacklistService.findBlackByRegIp(ipStr);
					// 未找到ip地址对应的表记录,不创建新记录,避免内部人员误操作封住自身ip
					if (ipBlack != null) {
						ipBlack.setIp(ipStr);
						ipBlack.setIsBlack(1);
						ipBlack.setUpdateAdmin(admin);
						ipBlack.setUpdateTime(new Date());
						fuIpBlacklistService.save(ipBlack);
					}
					logger.info("您的IP地址: " + ipStr + " 密码连续错误5次,禁止登录1天");
					write("-8");
					return null;
				}
				write("-5");
				return null;
			}
			// 正常登录
			String token = UUID.randomUUID().toString();
			WebUtil.addCookie(getHttpServletResponse(), "admin_token", token, 8640000);
			admin.setLoginToken(token);
			admin.setUpdateLoginTime(new Date());
			// 设置登录ip
			admin.setLoginIp(ipStr);
			// 正常登录将登录错误次数清零
			admin.setLoginErrorTimes(0);
			if (isAuto != null && isAuto) {
				admin.setIsAuto(isAuto);
			}
			// 记住账号
			if (isRemain != null && isRemain) {
				WebUtil.addCookie(getHttpServletResponse(), "remain_token", admin.getAccount(), 30 * 24 * 60 * 60);// 记住账号
			} else {
				WebUtil.addCookie(getHttpServletResponse(), "remain_token", "", 30 * 24 * 60 * 60);
			}
			// 存储用户的基本信息
			getSession().put("admin", admin);
			// 存储用户对应的角色权限
			List<SysRole> roleList = sysRoleService.findRoleListByRoleId(admin.getId());
			List<SysPurview> priviList = null;
			for (SysRole role : roleList) {
				priviList = sysRolePurviewService.findPurviewListByRoleId(role.getId());
			}
			getHttpSession().setAttribute("priviList", priviList);
			fuAdminService.save(admin);
			// 创建登录日志
			AdminLoginLog log = new AdminLoginLog();
			log.setFuAdmin(admin);
			log.setLogType(0);
			log.setLogTime(new Date());
			adminLoginLogService.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsRemain() {
		return isRemain;
	}

	public void setIsRemain(Boolean isRemain) {
		this.isRemain = isRemain;
	}

	public Boolean getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Boolean isAuto) {
		this.isAuto = isAuto;
	}
}
