package com.hongwei.futures.web.action.user_options;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.WqqContents;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.WqqContentsService;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.util.CommonUtils;
import com.hongwei.futures.util.HttpClientUtil;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.Sign;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserOptionsAction extends BaseAction {
	private static final long serialVersionUID = -9082235736198389445L;

	@Autowired
	private WqqOptionsService wqqOptionsService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private WqqContentsService wqqContentsService;

	private FuUser fuUser;
	private Long id;
	private String phone;
	private String code;
	private String invitationCode;
	private BigDecimal money;
	private Integer guessType;
	private String from;
	private String isappinstalled;
	private Long contentsId;

	/**
	 * 协议确认页面
	 * 
	 * @return
	 */
	@Action("agreeInfoAjax")
	public String agreeInfoAjax() {
		return SUCCESS;
	}

	/**
	 * 涨跌百分比
	 * 
	 * @return
	 */
	@Action("upDownPercent")
	public String upDownPercent() {
		try {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("contentsId", contentsId);
			map1.put("guessType", 1);
			map1.put("isPay", 1);
			int upCount = wqqOptionsService.countOptions(map1);// 买涨的人数
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("contentsId", contentsId);
			map2.put("guessType", 0);
			map2.put("isPay", 1);
			int dropCount = wqqOptionsService.countOptions(map2);// 买跌的人数
			int upPercent = 0;
			int dropPecent = 0;
			if (upCount > 0) {
				double up = (double) upCount / (upCount + dropCount);
				upPercent = (int) (up * 100);// 得到一个两位数的整数
				dropPecent = 100 - upPercent;
			}
			if (dropCount > 0 && upCount == 0) {
				double drop = (double) dropCount / (upCount + dropCount);
				dropPecent = (int) (drop * 100);// 得到一个两位数的整数
				upPercent = 100 - dropPecent;
			}
			if (upCount == 0) {
				upPercent = 0;
			}
			if (dropCount == 0) {
				dropPecent = 0;
			}
			JSONObject obj = new JSONObject();
			obj.put("upPercent", upPercent);
			obj.put("dropPecent", dropPecent);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微期权主页
	 * 
	 * @return
	 */
	@Action("wqqIndex")
	public String wqqIndex() {
		try {
			String access_token = null;
			if (null == this.getHttpSession().getAttribute("access_token")) {
				// 第一步获取token存入全局缓存，
				String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Property.getProperty("WEIXIN_APPID") + "&secret=" + Property.getProperty("WEIXIN_APP_SECRET"));
				org.json.JSONObject obj1 = new JSONObject(result1);
				access_token = obj1.get("access_token").toString();
				this.getHttpSession().setAttribute("access_token", access_token);
			} else {
				access_token = this.getHttpSession().getAttribute("access_token").toString();
			}

			String jsapi_ticket = null;
			if (null == this.getHttpSession().getAttribute("jsapi_ticket")) {
				// 第二步根据token得到jsapi_ticket存入全局缓存
				String result2 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi");
				JSONObject obj2 = new JSONObject(result2);
				jsapi_ticket = obj2.get("ticket").toString();
				this.getHttpSession().setAttribute("jsapi_ticket", jsapi_ticket);
			} else {
				jsapi_ticket = this.getHttpSession().getAttribute("jsapi_ticket").toString();
			}

			// 获取请求的地址
			HttpServletRequest request = this.getHttpServletRequest();
			StringBuffer url = request.getRequestURL();
			String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
			String httpUrl = contextUrl + request.getRequestURI();
			if (from != null && isappinstalled != null) {
				httpUrl = httpUrl + "?from=" + from + "&isappinstalled=" + isappinstalled;
			}
			// 签名算法
			Map<String, String> map = Sign.sign(jsapi_ticket, httpUrl);
			this.getActionContext().put("appId", Property.getProperty("WEIXIN_APPID"));
			this.getActionContext().put("timestamp", map.get("timestamp"));
			this.getActionContext().put("nonceStr", map.get("nonceStr"));
			this.getActionContext().put("signature", map.get("signature"));

			this.getActionContext().put("newDate", new Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 微期权微店主页
	 * 
	 * @return
	 */
	@Action("wqqWeiDianIndex")
	public String wqqWeiDianIndex() {
		try {
			String access_token = null;
			if (null == this.getHttpSession().getAttribute("access_token")) {
				// 第一步获取token存入全局缓存，
				String result1 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Property.getProperty("WEIXIN_APPID") + "&secret=" + Property.getProperty("WEIXIN_APP_SECRET"));
				JSONObject obj1 = new JSONObject(result1);
				access_token = obj1.get("access_token").toString();
				this.getHttpSession().setAttribute("access_token", access_token);
			} else {
				access_token = this.getHttpSession().getAttribute("access_token").toString();
			}

			String jsapi_ticket = null;
			if (null == this.getHttpSession().getAttribute("jsapi_ticket")) {
				// 第二步根据token得到jsapi_ticket存入全局缓存
				String result2 = HttpClientUtil.getHTTP("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi");
				JSONObject obj2 = new JSONObject(result2);
				jsapi_ticket = obj2.get("ticket").toString();
				this.getHttpSession().setAttribute("jsapi_ticket", jsapi_ticket);
			} else {
				jsapi_ticket = this.getHttpSession().getAttribute("jsapi_ticket").toString();
			}

			// 获取请求的地址
			HttpServletRequest request = this.getHttpServletRequest();
			StringBuffer url = request.getRequestURL();
			String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
			String httpUrl = contextUrl + request.getRequestURI();
			if (from != null && isappinstalled != null) {
				httpUrl = httpUrl + "?from=" + from + "&isappinstalled=" + isappinstalled;
			}
			// 签名算法
			Map<String, String> map = Sign.sign(jsapi_ticket, httpUrl);
			this.getActionContext().put("appId", Property.getProperty("WEIXIN_APPID"));
			this.getActionContext().put("timestamp", map.get("timestamp"));
			this.getActionContext().put("nonceStr", map.get("nonceStr"));
			this.getActionContext().put("signature", map.get("signature"));

			this.getActionContext().put("newDate", new Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * app微期权详情
	 * 
	 * @return
	 */
	@Action("wqqInfo")
	public String wqqInfo() {
		try {
			WqqContents contents = null;
			if (null != id && !"".equals(id)) {
				// 根据id 查询具体每一期的微期权的详情
				contents = wqqContentsService.get(id);
			}
			this.getActionContext().put("contents", contents);

			net.sf.json.JSONObject upDetail = new net.sf.json.JSONObject();
			if (null != contents && null != contents.getUpDetail()) {
				upDetail = net.sf.json.JSONObject.fromObject(contents.getUpDetail());
				this.getActionContext().put("upDetail", upDetail);
			}

			net.sf.json.JSONObject downDetail = new net.sf.json.JSONObject();
			if (null != contents && contents.getDownDetail() != null) {
				downDetail = net.sf.json.JSONObject.fromObject(contents.getDownDetail());
				this.getActionContext().put("downDetail", downDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 微期权涨详情页面
	 * 
	 * @return
	 */
	@Action("wqqInfoUp")
	public String wqqInfoUp() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", 1);
			WqqContents contents = wqqContentsService.findContentsByMap(0, Integer.MAX_VALUE, map).get(0);// 得到正在上线的微期权
			this.getActionContext().put("contents", contents);
			net.sf.json.JSONObject upDetail = new net.sf.json.JSONObject();
			if (null != contents && null != contents.getUpDetail()) {
				upDetail = net.sf.json.JSONObject.fromObject(contents.getUpDetail());
				this.getActionContext().put("upDetail", upDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 微期权跌详情页面
	 * 
	 * @return
	 */
	@Action("wqqInfoDown")
	public String wqqInfoDown() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", 1);
			WqqContents contents = wqqContentsService.findContentsByMap(0, Integer.MAX_VALUE, map).get(0);// 得到正在上线的微期权
			this.getActionContext().put("contents", contents);
			net.sf.json.JSONObject downDetail = new net.sf.json.JSONObject();
			if (null != contents && contents.getDownDetail() != null) {
				downDetail = net.sf.json.JSONObject.fromObject(contents.getDownDetail());
				this.getActionContext().put("downDetail", downDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 微期权支付页面
	 * 
	 * @return
	 */
	@Action("wqqPay")
	public String wqqPay() {
		try {
			this.getActionContext().put("guessType", guessType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 发送验证码
	 * 
	 * @return
	 */
	@Action("saveSendCode")
	public String saveSendCode() {
		try {
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (null != fuUser && fuUser.getState() == 0) {
				write("-1");
				return null;
			}
			wqqOptionsService.saveSendCode(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检查余额是否充足
	 * 
	 * @return
	 */
	@Action("checkBalance")
	public String checkBalance() {
		try {
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (null == fuUser) {
				double rand = new Random().nextDouble();
				String newPass = new String(rand + "").substring(2, 8);
				wqqOptionsService.saveWqqRegister(phone, CommonUtils.getMd5(newPass), invitationCode);
				wqqOptionsService.sendPwdWqq(phone, newPass);
			}
			if (null != fuUser && fuUser.getState() == 0) {
				write("-4");
				return null;
			}
			int result = wqqOptionsService.checkCode(code, phone);
			if (result == -1 || result == -2) {
				write(result + "");
				return null;
			}
			FuUser user = fuUserService.findUserByPhone(phone);
			if (user.getAccountBalance().compareTo(money) == -1) {
				write("-3");
				return null;
			}
			String token = UUID.randomUUID().toString();
			WebUtil.addCookie(getHttpServletResponse(), "user_token", token, 10800);// 设置登录账号cookie的存放时间（秒为单位）
			fuUser.setLoginToken(token);
			fuUser.setLoginTime(new Date());
			fuUser.setLastLoginIp(IP4.getIP4(this.getHttpServletRequest()));
			fuUserService.save(fuUser);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存期权支付（支付宝）
	 * 
	 * @return
	 */
	@Action("saveOptionPay")
	public String saveOptionPay() {
		try {
			FuUser user = fuUserService.findUserByPhone(phone);
			WqqOptions options = new WqqOptions();
			options.setFuUser(user);
			options.setMoney(money);
			options.setPanicTime(new Date());
			options.setIsPay(0);
			options.setGuessType(guessType);
			options.setWqqContents(wqqContentsService.get(contentsId));
			wqqOptionsService.save(options);
			String optionsId = options.getId().toString();
			write(optionsId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存期权支付（网站余额）
	 * 
	 * @return
	 */
	@Action("saveOptionPayWeb")
	public String saveOptionPayWeb() {
		try {
			FuUser user = fuUserService.findUserByPhone(phone);
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
			String tradeno = sdf.format(new Date()) + String.valueOf((int) (Math.random() * 90 + 10));
			wqqOptionsService.saveOptionPayWeb(user, tradeno, money, 1, guessType, contentsId);

			// 发送短信

			String message = URLDecoder.decode("微期权购买成功，购买时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "，订单号：" + tradeno + "，金额：" + money + "元。", "UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setContent(message);
			log.setPrio(1);
			log.setRegCode(null);
			log.setReason("微期权支付成功");
			log.setDestination(user.getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogService.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * APP获取每一期的详情
	 * 
	 * @return
	 */
	@Action("getWqqInfo")
	public String getWqqInfo() {
		try {
			String id = getHttpServletRequest().getParameter("id");
			WqqContents contents = wqqContentsService.get(Long.parseLong(id));
			this.getActionContext().put("contents", contents);
			net.sf.json.JSONObject upDetail = new net.sf.json.JSONObject();
			if (null != contents && null != contents.getUpDetail()) {
				upDetail = net.sf.json.JSONObject.fromObject(contents.getUpDetail());
				this.getActionContext().put("upDetail", upDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getGuessType() {
		return guessType;
	}

	public void setGuessType(Integer guessType) {
		this.guessType = guessType;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getIsappinstalled() {
		return isappinstalled;
	}

	public void setIsappinstalled(String isappinstalled) {
		this.isappinstalled = isappinstalled;
	}

	public Long getContentsId() {
		return contentsId;
	}

	public void setContentsId(Long contentsId) {
		this.contentsId = contentsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
