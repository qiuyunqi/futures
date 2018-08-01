package com.hongwei.futures.web.action.user_login;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuIpBlacklist;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuIpBlacklistService;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.util.DesUtil;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.RegexChk;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;

import net.sf.json.JSONObject;

@ParentPackage("fu_common")
public class UserLoginAction extends BaseAction {

	private static final long serialVersionUID = 5930321517173595782L;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private FuIpBlacklistService fuIpBlacklistService;
	@Autowired
	private FuParameterService fuParameterService;
	@Autowired
	private FuAdminService fuAdminService;

	private FuUser fuUser;
	private String accountName;
	private String password;
	private String repassword;
	private String phone;
	private String phoneCode;
	private Integer stepNo;
	private String newPwd;
	private String nickName;
	private String loginBack;// encodeURIComponent(encodeURIComponent(location.href))
	private String invitationCode;
	private Integer rememberAccount;
	private Integer rememberPwd;
	private Integer flag;
	private String verification;
	private String messageType;
	private String sign;
	private String action;
	private String gotoURL;
	private String setCookieURL;

	/**
	 * 单点登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("userLoginSingle")
	public String userLoginSingle() {
		try {
			accountName = WebUtil.getCookieByName(this.getHttpServletRequest(), "user_remember_account");
			password = WebUtil.getCookieByName(this.getHttpServletRequest(), "user_remember_password");
			if (!StringUtil.isBlank(loginBack)) {
				String loginBackUrl = URLDecoder.decode(loginBack, "utf8");
				loginBack = URLEncoder.encode(loginBack, "utf8");
				this.getActionContext().put("loginBackUrl", loginBackUrl);
			}
			this.getActionContext().put("jsptype", "userLogin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("userLogin")
	public String userLogin() {
		try {
			accountName = WebUtil.getCookieByName(this.getHttpServletRequest(), "user_remember_account");
			password = WebUtil.getCookieByName(this.getHttpServletRequest(), "user_remember_password");
			if (!StringUtil.isBlank(loginBack)) {
				String loginBackUrl = URLDecoder.decode(loginBack, "utf8");
				loginBack = URLEncoder.encode(loginBack, "utf8");
				this.getActionContext().put("loginBackUrl", loginBackUrl);
			}
			this.getActionContext().put("jsptype", "userLogin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 登录浮层
	 * 
	 * @return
	 */
	@Action("userLoginAjax")
	public String userLoginAjax() {
		try {
			accountName = WebUtil.getCookieByName(this.getHttpServletRequest(), "user_remember_account");
			password = WebUtil.getCookieByName(this.getHttpServletRequest(), "user_remember_password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 登录操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("doLoginAjax")
	public String doLoginAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			FuIpBlacklist ipBlacklist = fuIpBlacklistService.findBlackByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			if (null != ipBlacklist && ipBlacklist.getIsBlack() == 1) {
				obj.put("code", -1);// ip在黑名单里
				write(obj.toString());
				return null;
			}
			if (StringUtil.isBlank(accountName)) {// 输入账号
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			if (StringUtil.isBlank(password)) {// 输入密码
				obj.put("code", -3);
				write(obj.toString());
				return null;
			}
			FuUser fuUser = fuUserService.findUserByAccount(accountName);
			if (fuUser == null) {// 登陆账号不存在,请注册
				obj.put("code", -4);
				write(obj.toString());
				return null;
			}
			if (!fuUser.getPassword().equals(CommonUtil.getMd5(password))) {// 密码不正确
				obj.put("code", -5);
				write(obj.toString());
				return null;
			}
			String token = UUID.randomUUID().toString();
			WebUtil.addCookie(getHttpServletResponse(), "user_token", token, 10800);// 设置登录账号cookie的存放时间（秒为单位）
			fuUser.setLoginToken(token);
			fuUser.setLoginTime(new Date());
			fuUser.setLastLoginIp(IP4.getIP4(this.getHttpServletRequest()));
			fuUserService.save(fuUser);
			if (rememberAccount != null && rememberAccount.toString().equals("1")) {// 记住账号
				WebUtil.addCookie(getHttpServletResponse(), "user_remember_account", accountName, 8640000);
			} else {
				WebUtil.addCookie(getHttpServletResponse(), "user_remember_account", "", 1);
			}
			if (rememberPwd != null && rememberPwd.toString().equals("1")) {// 记住密码
				WebUtil.addCookie(getHttpServletResponse(), "user_remember_password", password, 8640000);
			} else {
				WebUtil.addCookie(getHttpServletResponse(), "user_remember_password", "", 1);
			}
			this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUser);
			// 未查看的资金进出记录
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser.getId());
			map.put("isCheck", 0);
			Integer counts = fuMoneyDetailService.getCount(map);
			this.getSession().put("counts", counts);

			// 论坛也同时登录
			/*
			 * if (fuUser.getNickName() != null) { String ucsynlogin = DiscuzUtil.login(fuUser.getPhone(), CommonUtil.getMd5(fuUser.getPhone().substring(5, 11))); obj.put("ucsynlogin", ucsynlogin); }
			 */
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 登录时账号校验
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("checkAllAccouontAjax")
	public String checkAllAccouontAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (StringUtil.isBlank(accountName)) {
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			FuUser fuUser = fuUserService.findUserByAccount(accountName);
			if (fuUser == null) {// 登陆账号不存在,请注册
				obj.put("code", -3);
				write(obj.toString());
				return null;
			}
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 注册验证手机号
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("checkRegPhone")
	public String checkRegPhone() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			FuIpBlacklist ipBlacklist = fuIpBlacklistService.findBlackByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			if (null == ipBlacklist || ipBlacklist.getIsBlack() != 0) {// 记录为空或者不是白名单
				String result = DesUtil.webSignVerify(sign);
				if (null == result) {
					obj.put("code", 0);
					obj.put("result", "非法签名");
					write(obj.toString());
					return null;
				}
				if (!"success".equals(result)) {
					obj.put("code", 0);
					obj.put("result", result);
					write(obj.toString());
					return null;
				}
			}
			FuUser u = fuUserService.findUserByRegPhone(phone);
			if (u != null) {// 该手机号已被注册
				obj.put("code", -1);
				write(obj.toString());
				return null;
			}
			obj.put("code", "ok");
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 注册时昵称校验
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("checkNickAjax")
	public String checkNickAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (StringUtil.isBlank(nickName)) {
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			FuUser fuUser = fuUserService.findUserByNickName(nickName);
			if (fuUser == null) {// 登陆账号不存在,请注册
				obj.put("code", -3);
				write(obj.toString());
				return null;
			}
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 单点注册页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("userRegSingle")
	public String userRegSingle() {
		try {
			if (!StringUtil.isBlank(loginBack)) {
				loginBack = URLEncoder.encode(loginBack, "utf8");
			}
			FuParameter fuParameter = fuParameterService.findParameter();
			this.getActionContext().put("messageType", (fuParameter.getMessageType() == 1 ? "语音" : "短信"));
			// 获取sign加密签名
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());
			String data = Property.getProperty("CLIENT_PC") + Property.getProperty("MD5_PC") + sdf.format(curDate) + IP4.ipToLong(IP4.getIP4(ServletActionContext.getRequest()));
			String sign = DesUtil.encryptSign(data, Property.getProperty("DES_STRING"));
			this.getActionContext().put("sign", sign);
			this.getActionContext().put("jsptype", "userReg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("userReg")
	public String userReg() {
		try {
			if (!StringUtil.isBlank(loginBack)) {
				loginBack = URLEncoder.encode(loginBack, "utf8");
			}
			FuParameter fuParameter = fuParameterService.findParameter();
			this.getActionContext().put("messageType", (fuParameter.getMessageType() == 1 ? "语音" : "短信"));
			// 获取sign加密签名
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());
			String data = Property.getProperty("CLIENT_PC") + Property.getProperty("MD5_PC") + sdf.format(curDate) + IP4.ipToLong(IP4.getIP4(ServletActionContext.getRequest()));
			String sign = DesUtil.encryptSign(data, Property.getProperty("DES_STRING"));
			this.getActionContext().put("sign", sign);
			this.getActionContext().put("jsptype", "userReg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 注册操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("doRegAjax")
	public String doRegAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);

			FuIpBlacklist ipBlacklist = fuIpBlacklistService.findBlackByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			if (null != ipBlacklist && ipBlacklist.getIsBlack() == 1) {
				obj.put("code", -1);// ip在黑名单里
				write(obj.toString());
				return null;
			}
			Integer count = fuUserService.findCountByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			FuParameter fuParameter = fuParameterService.findParameter();
			if (count >= fuParameter.getRegNum()) {
				obj.put("code", -13);// ip注册数量超出限制
				obj.put("RegNum", fuParameter.getRegNum());// 注册数目上限告知用户
				write(obj.toString());
				return null;
			}
			FuUser user = fuUserService.findUserByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			if (null != user) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(user.getRegisterTime());
				cal.add(Calendar.MINUTE, fuParameter.getRegInterval());
				if (cal.getTime().getTime() > new Date().getTime()) {
					obj.put("code", -14);// 当前时间超出最后次注册时间加上注册间隔时间
					obj.put("RegInterval", fuParameter.getRegInterval());// 注册间隔时间告知用户
					write(obj.toString());
					return null;
				}
			}
			if (null == ipBlacklist || ipBlacklist.getIsBlack() != 0) {// 记录为空或者不是白名单
				log.error("sign = " + sign);
				if (null == sign || sign == "") {
					obj.put("code", 0);
					obj.put("result", "签名为空");
					write(obj.toString());
					return null;
				}
				// 验签开始
				String result = DesUtil.webSignVerify(sign);
				if (null == result) {
					obj.put("code", 0);
					obj.put("result", "非法签名");
					write(obj.toString());
					return null;
				}
				if (!"success".equals(result) && !"非法数字签名".equals(result)) {
					obj.put("code", 0);
					obj.put("result", result);
					write(obj.toString());
					return null;
				}
				String ipLong = DesUtil.ipToLong(sign);// 从签名里获取的ip地址long字符串
				String nowIp = IP4.ipToLong(IP4.getIP4(ServletActionContext.getRequest())) + "";// 当前访问ip地址long字符串
				log.error("ipLong = " + ipLong);
				log.error("nowIp = " + nowIp);
				log.error("当前IP：" + IP4.getIP4(ServletActionContext.getRequest()));
				if (null == ipLong || null == nowIp) {
					obj.put("code", 0);
					obj.put("result", "网络异常");
					write(obj.toString());
					return null;
				}
				if (!ipLong.equals(nowIp)) {
					obj.put("code", 0);
					obj.put("result", "IP地址:" + IP4.getIP4(ServletActionContext.getRequest()) + "非法");
					write(obj.toString());
					FuIpBlacklist ipBlack = new FuIpBlacklist();
					ipBlack.setCreatAdmin(fuAdminService.get(1L));
					ipBlack.setCreateTime(new Date());
					ipBlack.setIp(IP4.getIP4(ServletActionContext.getRequest()));
					ipBlack.setIsBlack(1);
					fuIpBlacklistService.save(ipBlack);
					return null;
				}
			}

			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("destination", phone);
			if (fuParameter.getMessageType() == 1) {
				map2.put("type", 4);
			} else {
				map2.put("type", 3);
			}
			FuSmsLog sms = fuSmsLogService.findLogByMap(map2);// 根据目标手机号得到最新一条注册短信对象
			if (sms != null) {
				if (!RegexChk.checkUsername(nickName, 3, 15)) {
					obj.put("code", -2);// 3-15位数字，字母，下划线或中文
					write(obj.toString());
					return null;
				}
				if (!RegexChk.checkUserPwd(password)) {
					obj.put("code", -3); // 6位以上数字，字母
					write(obj.toString());
					return null;
				}
				if (!password.equals(repassword)) {
					obj.put("code", -4);
					write(obj.toString());
					return null;
				}
				if (!RegexChk.checkCellPhone(phone)) {
					obj.put("code", -5);// 手机号码不能为空，注意手机格式
					write(obj.toString());
					return null;
				}
				if (StringUtil.isBlank(phoneCode)) {
					obj.put("code", -7);// 请输入手机验证码
					write(obj.toString());
					return null;
				}
				if (!phoneCode.equals(sms.getRegCode())) {
					obj.put("code", -8);// 手机验证码输入错误，或者手机号已经被注册
					write(obj.toString());
					return null;
				}
				// 验证码过期时间为发送短信后一分钟
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((sms.getSendTime() == null ? new Date() : sms.getSendTime()));
				calendar.add(Calendar.MINUTE, 1);
				calendar.getTime();
				if (sms.getRegCode() != null && sms.getRegCode().equals(phoneCode) && calendar.getTime().before(new Date())) {
					obj.put("code", -9);// 验证码已过期
					write(obj.toString());
					return null;
				}
				if (StringUtil.isBlank(invitationCode)) {
					obj.put("code", -10);// 请输入邀请码
					write(obj.toString());
					return null;
				}
				if (invitationCode.length() == 12) {// 填入的邀请码
					FuUser use = fuUserService.findUserByRegInvitationcode(invitationCode);
					if (use == null) {
						obj.put("code", -11);
						write(obj.toString());
						return null;
					}
				}
				if (invitationCode.length() == 11) {// 填入的手机号作为邀请码
					FuUser use = fuUserService.findUserByPhone(invitationCode);
					if (use == null) {
						obj.put("code", -12);
						write(obj.toString());
						return null;
					}
					invitationCode = fuUserService.findUserByPhone(invitationCode).getInvitationCode();
				}
				String invitaCode = fuUserService.saveUser(phone, CommonUtil.getMd5(password), nickName, phoneCode, invitationCode, 1, null);
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				fuUser.setPhoneCodeTime(calendar.getTime());
				fuUserService.save(fuUser);
				sms.setFuUser(fuUser);
				fuSmsLogService.save(sms);

				this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUser);

				obj.put("code", 1);
				obj.put("invitaCode", invitaCode);
				write(obj.toString());
			} else {
				obj.put("code", -6);
				write(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 先确认图形验证码再获取手机验证码
	 */
	@Action("verification")
	public String verification() {
		this.getActionContext().put("messageType", messageType);
		return SUCCESS;
	}

	/**
	 * 验证手机号和昵称是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendMsgCheck")
	public String sendMsgCheck() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			FuIpBlacklist ipBlacklist = fuIpBlacklistService.findBlackByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			if (null != ipBlacklist && ipBlacklist.getIsBlack() == 1) {
				obj.put("code", -1);// ip在黑名单里
				write(obj.toString());
				return null;
			}
			if (null == ipBlacklist || ipBlacklist.getIsBlack() != 0) {// 记录为空或者不是白名单
				String result = DesUtil.webSignVerify(sign);
				if (null == result) {
					obj.put("code", 0);
					obj.put("result", "非法签名");
					write(obj.toString());
					return null;
				}
				if (!"success".equals(result)) {
					obj.put("code", 0);
					obj.put("result", result);
					write(obj.toString());
					return null;
				}
			}
			if (!RegexChk.checkCellPhone(phone)) {// 输入手机
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			FuUser u = fuUserService.findUserByRegPhone(phone);
			if (u != null) {// 该手机号已被注册
				obj.put("code", -3);
				write(obj.toString());
				return null;
			}
			FuUser uu = fuUserService.findUserByNickName(nickName);
			if (uu != null) {
				if (uu.getState() == 1) {
					obj.put("code", -4);
					write(obj.toString());
					return null;
				}
				if (uu.getPhone() != null && !uu.getPhone().equals(phone)) {
					fuUserService.delete(uu.getId());
				}
			}
			if (StringUtil.isBlank(invitationCode)) {
				obj.put("code", -5);// 请输入邀请码
				write(obj.toString());
				return null;
			}
			if (invitationCode.length() == 12) {// 填入的邀请码
				FuUser use = fuUserService.findUserByRegInvitationcode(invitationCode);
				if (use == null) {
					obj.put("code", -6);
					write(obj.toString());
					return null;
				}
			}
			if (invitationCode.length() == 11) {// 填入的手机号作为邀请码
				FuUser use = fuUserService.findUserByPhone(invitationCode);
				if (use == null) {
					obj.put("code", -7);
					write(obj.toString());
					return null;
				}
			}
			obj.put("code", "ok");
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送注册的手机短信验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendRegMsg")
	public String sendRegMsg() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (StringUtil.isBlank(verification)) {
				obj.put("code", -1);
				write(obj.toString());
				return null;
			}
			String sessionVer = (String) this.getHttpServletRequest().getSession().getAttribute("rand");// 得到jsp里的session所存验证码数据
			if (verification.equalsIgnoreCase(sessionVer)) {
				// 忽略大小写比较两个字符串是否一样
				DecimalFormat format = new DecimalFormat("000000");
				String code = format.format(Math.random() * 999999);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("destination", phone);
				FuParameter fuParameter = fuParameterService.findParameter();
				if (fuParameter.getMessageType() == 1) {
					map2.put("type", 4);
				} else {
					map2.put("type", 3);
				}
				/**
				 * 注册短信记录存在多次点击发送短信则只做修改，不存在则新增
				 */
				FuSmsLog sms = fuSmsLogService.findLogByMap(map2);// 根据目标手机号得到最新一条注册短信对象
				FuUser u = fuUserService.findUserByRegPhone(phone);
				if (sms == null || u == null) {
					FuSmsLog log = new FuSmsLog();
					log.setContent("注册手机验证码：" + code + "，为了保护您的账号安全，验证短信请勿转发给其他人。");
					log.setPrio(1);
					log.setReason("用户注册");
					log.setDestination(phone);
					log.setRegCode(code);
					log.setPlanTime(new Date());
					if (fuParameter.getMessageType() == 1) {
						log.setType(4);
					} else {
						log.setType(3);
					}
					log.setFuUser(null);
					log.setState(0);
					fuSmsLogService.save(log);
				} else {
					sms.setContent("注册手机验证码：" + code + "，为了保护您的账号安全，验证短信请勿转发给其他人。");
					sms.setPrio(1);
					sms.setReason("用户注册");
					sms.setDestination(phone);
					sms.setRegCode(code);
					sms.setPlanTime(new Date());
					if (fuParameter.getMessageType() == 1) {
						sms.setType(4);
					} else {
						sms.setType(3);
					}
					sms.setFuUser(null);
					sms.setState(0);
					fuSmsLogService.save(sms);
				}
				obj.put("code", 1);
				write(obj.toString());
			} else {
				obj.put("code", -2);
				write(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@Action("logoutAjax")
	public String logoutAjax() {
		try {
			String token = WebUtil.getCookieByName(getHttpServletRequest(), "user_token");
			if (null != token && !"".equals(token)) {
				FuUser user = fuUserService.findLoginByToken(token);
				if (user != null) {
					user.setLoginToken("");
					fuUserService.save(user);
					write("-1");// 退出成功
					// 论坛退出
					// String ucsynlogout = DiscuzUtil.logout();
					// obj.put("ucsynlogout", ucsynlogout);
					// write(obj.toString());
				} else {
					write("-2");// 请先登录
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送验证码--找回密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendPwdMsgAjax")
	public String sendPwdMsg() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (StringUtil.isBlank(phone)) {// 输入手机
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			FuUser u = fuUserService.findUserByPhone(phone);
			if (u == null) {// 该手机号对应的用户不存在
				obj.put("code", -3);
				write(obj.toString());
				return null;
			}
			DecimalFormat format = new DecimalFormat("000000");
			String code = format.format(Math.random() * 999999);
			u.setPhoneCode(code);
			// 验证码过期时间（1个小时）
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR_OF_DAY, 1);
			u.setPhoneCodeTime(cal.getTime());

			FuSmsLog log = new FuSmsLog();
			log.setPlanTime(new Date());
			log.setContent("找回密码验证码：" + code + "，为了保护您的账号安全，验证短信请勿转发给其他人。");
			log.setPrio(1);
			log.setReason("找回密码");
			log.setDestination(phone);
			log.setType(1);// 短信
			log.setState(0);
			fuUserService.saveReg(u, log);
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 找回密码页面
	 */
	@Action("findPwd")
	public String findPwd() {
		return SUCCESS;
	}

	/**
	 * 找回密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("findPwdAjax")
	public String findPwdAjax() {
		try {
			if (stepNo == null)
				stepNo = 1;
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (stepNo == 1) {// 第一步
				if (!RegexChk.checkCellPhone(phone)) {
					obj.put("code", -2);
					write(obj.toString());
					return null;
				}
				FuUser u = fuUserService.findUserByPhone(phone);
				if (u == null) {// 改手机号对应的用户不存在
					obj.put("code", -3);
					write(obj.toString());
					return null;
				}
				if (u.getPhoneCode() == null || !u.getPhoneCode().equals(phoneCode)) {// 验证码错误，或者还没有获取手机验证码
					obj.put("code", -4);
					write(obj.toString());
					return null;
				}
				if (u.getPhoneCode() != null && u.getPhoneCode().equals(phoneCode) && u.getPhoneCodeTime().before(new Date())) {
					obj.put("code", -7);// 验证码已过期
					write(obj.toString());
					return null;
				}
			}
			if (stepNo == 2) {// 第二步
				FuUser u = fuUserService.findUserByPhone(phone);
				if (!RegexChk.checkUserPwd(newPwd)) {// 6位以上数字，字母
					obj.put("code", -5);
					write(obj.toString());
					return null;
				}
				if (!newPwd.equals(repassword)) {
					obj.put("code", -6);// 两次密码不一致
					write(obj.toString());
					return null;
				}
				u.setPassword(CommonUtil.getMd5(newPwd));
				fuUserService.save(u);
			}
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 点击资金进出消息
	 * 
	 * @return
	 */
	@Action("toMoneyDetail")
	public String toMoneyDetail() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser == null ? 0L : fuUser.getId());
			map.put("isCheck", 0);
			List<FuMoneyDetail> moneyDetails = fuMoneyDetailService.findFuMoneyDetailByParams(map);
			for (FuMoneyDetail fuMoneyDetail : moneyDetails) {
				fuMoneyDetail.setIsCheck(1);
				fuMoneyDetailService.save(fuMoneyDetail);
			}
			Integer counts = fuMoneyDetailService.getCount(map);
			this.getSession().put("counts", counts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 刷新资金进出消息数目
	 * 
	 * @return
	 */
	@Action("fushMoneyDetail")
	public String fushMoneyDetail() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser == null ? 0L : fuUser.getId());
			map.put("isCheck", 0);
			Integer counts = fuMoneyDetailService.getCount(map);
			this.getSession().put("counts", counts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getLoginBack() {
		return loginBack;
	}

	public void setLoginBack(String loginBack) {
		this.loginBack = loginBack;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Integer getStepNo() {
		return stepNo;
	}

	public void setStepNo(Integer stepNo) {
		this.stepNo = stepNo;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public Integer getRememberAccount() {
		return rememberAccount;
	}

	public void setRememberAccount(Integer rememberAccount) {
		this.rememberAccount = rememberAccount;
	}

	public Integer getRememberPwd() {
		return rememberPwd;
	}

	public void setRememberPwd(Integer rememberPwd) {
		this.rememberPwd = rememberPwd;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getGotoURL() {
		return gotoURL;
	}

	public void setGotoURL(String gotoURL) {
		this.gotoURL = gotoURL;
	}

	public String getSetCookieURL() {
		return setCookieURL;
	}

	public void setSetCookieURL(String setCookieURL) {
		this.setCookieURL = setCookieURL;
	}
}
