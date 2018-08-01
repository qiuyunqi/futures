package com.hongwei.futures.web.action.user_login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuIpBlacklist;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.Ticket;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuIpBlacklistService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.util.DESUtils;
import com.hongwei.futures.util.DesUtil;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.RegexChk;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage("fu_common")
public class SSOAuthAction extends BaseAction {

	private static final long serialVersionUID = 4157510225463773221L;
	private static Logger logger = Logger.getLogger(SSOAuthAction.class);
	/** 单点登录标记 */
	private static Map<String, Ticket> tickets = new ConcurrentHashMap<String, Ticket>();
	
	/** ticket有效时间 */
	private int ticketTimeout;
	
	/** 回收ticket线程池 */
	
	private static final String COOKIE_NAME ="token_name"; 
	private static final boolean SECURE = false; 
	private static String  SECRETKEY = "111111112222222233333333";
	
	
	private String action;
	private String gotoURL;
	private String setCookieURL;
	private String encodedticketKey;
	private int expiry;
	
	
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
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
	
	
	@Action(value = "ssoauth", 
			results = { 
			@Result(name = "toLogin", location = "/user_login/userLoginSingle.htm", 
					params={"setCookieURL", "%{setCookieURL}", "gotoURL", "%{gotoURL}"}, type="redirect"),
			@Result(name = "toRegister", location = "/user_login/userRegSingle.htm", 
				params={"setCookieURL", "%{setCookieURL}", "gotoURL", "%{gotoURL}"}, type="redirect")
	})
	public String ssoauth() throws ServletException, IOException {
		if("preLogin".equals(action)) {
			return preLogin();
		} else if("login".equals(action)) {
			return doLogin();
		} else if("logout".equals(action)) {
			doLogout();
		} else if("authTicket".equals(action)) {
			authTicket();
		} else if ("register".equals(action)){
			doRegister();
		} else if ("toRegister".equals(action)) {
			return "toRegister";
		}
		return null;
	}

	
	
	private String doRegister() {
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

			if(null == ipBlacklist || ipBlacklist.getIsBlack() != 0){//记录为空或者不是白名单
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
				String[] keys =  (String[])ActionContext.getContext().getApplication().get("keys");
				for (String key : keys) {
					if (nickName.contains(key)) {
						obj.put("success", -13);
						obj.put("message", "您的昵称有非法字符");
						write(obj.toString());
						return null;
					}
				}
				// 保存注册用户
				fuUserService.saveUser(phone, CommonUtil.getMd5(password), nickName, phoneCode, invitationCode, 1, null);
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				fuUser.setPhoneCodeTime(calendar.getTime());
				fuUserService.save(fuUser);
				sms.setFuUser(fuUser);
				fuSmsLogService.save(sms);

				String ticketKey = UUID.randomUUID().toString().replace("-", "");
				encodedticketKey = DESUtils.encrypt(ticketKey, SECRETKEY);
				
				Timestamp createTime = new Timestamp(System.currentTimeMillis());
				Calendar cal = Calendar.getInstance();
				cal.setTime(createTime);
				cal.add(Calendar.MINUTE, ticketTimeout);
				Timestamp recoverTime = new Timestamp(cal.getTimeInMillis());
				Ticket ticket = new Ticket(fuUser.getId(), fuUser.getUserName(), createTime, recoverTime);
				tickets.put(ticketKey, ticket);
				
				
				HttpServletRequest request = this.getHttpServletRequest();
				HttpServletResponse response = this.getHttpServletResponse();
				
				String[] checks = request.getParameterValues("autoAuth");
				expiry = -1;
				if(checks != null && "1".equals(checks[0]))
					expiry = 7 * 24 * 3600;
				Cookie cookie = new Cookie(COOKIE_NAME, encodedticketKey);
				cookie.setSecure(SECURE);// 为true时用于https
				cookie.setMaxAge(expiry);
				cookie.setPath("/");
				response.addCookie(cookie);
				
				request.getSession().setAttribute("fuUser", fuUser);
				logger.info("doRegister session -->" + new Date() + fuUser.getId());
				obj.put("code", 1);
				obj.put("setCookieURL", setCookieURL+"?gotoURL="+gotoURL+"&ticket="+encodedticketKey+"&expiry="+expiry);
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

	private void authTicket() throws IOException {
		HttpServletRequest request = this.getHttpServletRequest();
		HttpServletResponse response = this.getHttpServletResponse();
		StringBuilder result = new StringBuilder("{");
		PrintWriter out = response.getWriter();
		String encodedTicket = request.getParameter(COOKIE_NAME);
		if(encodedTicket == null || "".equals(encodedTicket)) {
			result.append("\"error\":true,\"errorInfo\":\"Ticket can not be empty!\"");
		} else {
			String decodedTicket = DESUtils.decrypt(encodedTicket, SECRETKEY);
			if(tickets.containsKey(decodedTicket))
				result.append("\"error\":false,\"userId\":").append(tickets.get(decodedTicket).getUserId());
			else
				result.append("\"error\":true,\"errorInfo\":\"Ticket is not found!\"");
		}
		result.append("}");
		out.print(result);
		
	}

	private void doLogout() throws IOException {
		HttpServletRequest request = this.getHttpServletRequest();
		HttpServletResponse response = this.getHttpServletResponse();
		StringBuilder result = new StringBuilder("{");
		PrintWriter out = response.getWriter();
		String encodedTicket = request.getParameter(COOKIE_NAME);
		if(encodedTicket == null) {
			result.append("\"error\":true,\"errorInfo\":\"Ticket can not be empty!\"");
		} else {
			request.getSession().invalidate();
			String decodedTicket = DESUtils.decrypt(encodedTicket, SECRETKEY);
			tickets.remove(decodedTicket);
			result.append("\"error\":false");
		}
		result.append("}");
		out.print(result);
		
	}

	private String doLogin() {
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
			
			String ticketKey = UUID.randomUUID().toString().replace("-", "");
			encodedticketKey = DESUtils.encrypt(ticketKey, SECRETKEY);
			
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			cal.setTime(createTime);
			cal.add(Calendar.MINUTE, ticketTimeout);
			Timestamp recoverTime = new Timestamp(cal.getTimeInMillis());
			Ticket ticket = new Ticket(fuUser.getId(), fuUser.getUserName(), createTime, recoverTime);
			tickets.put(ticketKey, ticket);
			
			
			HttpServletRequest request = this.getHttpServletRequest();
			HttpServletResponse response = this.getHttpServletResponse();
			
			request.getSession().setAttribute("fuUser", fuUser);
			logger.info("doLogin session -->" + new Date() + fuUser.getId());
			
			String[] checks = request.getParameterValues("autoAuth");
			expiry = -1;
			if(checks != null && "1".equals(checks[0]))
				expiry = 7 * 24 * 3600;
			Cookie cookie = new Cookie(COOKIE_NAME, encodedticketKey);
			cookie.setSecure(SECURE);// 为true时用于https
			cookie.setMaxAge(expiry);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			obj.put("code", 1);
			obj.put("setCookieURL", setCookieURL+"?gotoURL="+gotoURL+"&ticket="+encodedticketKey+"&expiry="+expiry);
			write(obj.toString());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String preLogin() throws IOException {
		HttpServletRequest request = this.getHttpServletRequest();
		HttpServletResponse response = this.getHttpServletResponse();
		
		Cookie ticket = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(COOKIE_NAME)) {
					ticket = cookie;
					break;
				}
			}
		if(ticket == null) {
			return "toLogin";
		} else {
			String encodedTicket = ticket.getValue();
			String decodedTicket = DESUtils.decrypt(encodedTicket, SECRETKEY);
			if(tickets.containsKey(decodedTicket)) {
				if(setCookieURL != null){
	                response.sendRedirect(setCookieURL + "?ticket=" + encodedTicket + "&expiry=" + ticket.getMaxAge() + "&gotoURL=" + gotoURL);
	                return null;
				}
			} else {
				return "toLogin";
			}
		}
		return "toLogin";
		
	}

	// --------------------------get set function---------------------
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

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginBack() {
		return loginBack;
	}

	public void setLoginBack(String loginBack) {
		this.loginBack = loginBack;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
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
}
