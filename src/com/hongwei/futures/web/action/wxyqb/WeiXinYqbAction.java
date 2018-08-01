package com.hongwei.futures.web.action.wxyqb;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.FuYjb;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.FuYjbService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.util.HttpClientUtil;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.WeiXinUtil;
import com.hongwei.futures.web.action.BaseAction;

/**
 * 微信版本的余券宝
 * 
 * @author han
 * @date 2016-04-08
 */
@ParentPackage("fu_common")
public class WeiXinYqbAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(WeiXinYqbAction.class);

	@Autowired
	private FuYjbService fuYjbService;
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuUserService fuUserService;

	private String from;
	private String isappinstalled;
	private String phone; // 手机号
	private String phoneCode; // 手机验证码
	private String serverId; // 微信上传图片返回的media_id
	private String srcId; // 微信上传图片本地的img地址
	private String registerCode; // 注册码

	/**
	 * 余券宝首页
	 */
	@Action(value = "index", results = { @Result(name = "backIndex", location = "/wxyqb/backIndex.htm", type = "redirect") })
	public String index() {
		try {
			List<FuYjb> yjbList = fuYjbService.findAll();
			FuYjb yqb = yjbList.get(0);
			this.getActionContext().put("yqb", yqb);
			String code = this.getHttpServletRequest().getParameter("code");
			logger.info("code===========>" + code);
			if (null == code || "".equals(code)) {
				return SUCCESS;
			}
			FuUser fuUser = null;
			fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			if (null == fuUser) {
				// 获取token和openid
				String appid = Property.getProperty("WEIXIN_APPID");
				String secret = Property.getProperty("WEIXIN_APP_SECRET");

				String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";

				logger.info("getTokenUrl===========>" + url);

				JSONObject result1 = HttpClientUtil.httpRequest(url, "GET", null);
				// 解析json数据
				String openId = (String) result1.get("openid");
				String token = (String) result1.get("access_token");
				logger.info("openId===========>" + openId);
				// 获取用户信息
				String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openId + "&lang=zh_CN";
				JSONObject result2 = HttpClientUtil.httpRequest(userUrl, "GET", null);
				;
				String nickName = (String) result2.get("nickname");
				String avatar = (String) result2.get("headimgurl");
				logger.info("nickName===========>" + nickName);
				// nickName openid
				fuUser = fuUserService.findUserByWeixinCode(openId);
				logger.info("fuUsers1===>" + fuUser);
				if (null == fuUser && null != openId) {
					this.getHttpServletRequest().getSession().setAttribute("nickName", nickName);
					this.getHttpServletRequest().getSession().setAttribute("avatar", avatar);
				}
				this.getHttpServletRequest().getSession().setAttribute("openId", openId);
				this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUser);
				String openIds = (String) this.getHttpServletRequest().getSession().getAttribute("openId");
				FuUser fuUsers = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
				logger.info("openIds===>" + openIds);
				logger.info("fuUsers2===>" + fuUsers);
				return SUCCESS;
			} else {
				List<FuStockAccount> accountList = fuStockAccountService.findByUserIdAndIsDel(fuUser.getId(), 0);
				if (null != accountList && accountList.size() > 0) {
					return "backIndex";
				} else {
					return SUCCESS;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return SUCCESS;
		}
	}

	@Action("apply")
	public String newApplit() throws Exception {
		WeiXinUtil.getToken(this.getHttpSession(), this.getHttpServletRequest(), this.getActionContext(), from, isappinstalled);
		return SUCCESS;
	}

	/**
	 * 提交账户 等待匹配
	 * 
	 * @return
	 */
	@Action(value = "saveYqb", results = { @Result(name = "toapply", location = "/wxyqb/apply.htm", type = "redirect") })
	public String saveYqb() throws Exception {
		FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
		logger.info("fuUser.getPhone======>" + fuUser);
		if (null == fuUser || null == fuUser.getPhone()) {
			write("1"); // 进入绑定手机页面
			return null;
		}
		if (null == serverId || "".equals(serverId)) {
			write("2"); // 上传图片失败
			return null;
		}
		// 保存账号信息
		FuStockAccount fsa = new FuStockAccount();
		fsa.setFuUser(fuUser);
		fsa.setOpenEquity("小合科技");
		fsa.setSalesDept("北京市海淀区高粱斜街28号");
		fsa.setSourceType(2); // 0: 网站 1:APP 2: 微信
		fsa.setCreateTime(new Date());
		// 创建时,更新时间与创建时间相同
		fsa.setUpdateTime(new Date());
		fsa.setIsDel(0); // 可用账号
		fsa.setExamineStatus(0);// 审核中状态
		fsa.setIsPublish(0); // 未发布状态
		fsa.setState(0);
		fuStockAccountService.saveFsa(fsa, this.getHttpSession(), serverId);
		this.getHttpServletRequest().getSession().setAttribute("srcId", null);
		write("3");
		return null;
	}

	@Action("saveAccount")
	public String saveAccount() {
		int rint = (int) (Math.random() * 10) + 5;
		this.getActionContext().put("time", rint);
		return SUCCESS;
	}

	@Action("matchSuccess")
	public String matchSuccess() {
		return SUCCESS;
	}

	/**
	 * 返回首页
	 */
	@Action("backIndex")
	public String backIndex() {
		FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
		// FuUser fuUser = fuUserService.get(289L);
		if (null == fuUser) { // 没有绑定
			return "addUser";
		} else {
			Long userId = fuUser.getId();
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put("userId", userId);
			map2.put("isDel", 0);
			List<FuStockAccount> fsaList = fuStockAccountService.findAccountByMap(0, 100, map2);
			if (null != fsaList && fsaList.size() > 0) {
				BigDecimal totalProfit = new BigDecimal(0); // 昨日所有账号盈亏和
				List<Object> list = new ArrayList<Object>();
				for (FuStockAccount fuStockAccount : fsaList) {
					Map<String, Object> map = new HashMap<String, Object>();
					FuStockMoneyDetail stockDetail = fuStockMoneyDetailService.findDetailByStockId(fuStockAccount.getId());
					if (stockDetail != null) {
						totalProfit = totalProfit.add(stockDetail.getNowProfit());
					}
					if (null == fuStockAccount.getOpenEquity() && null == fuStockAccount.getCapitalAccount()) {
						map.put("message", "正在补充您的账户信息, 请耐心等候");
					} else {
						map.put("openEquity", fuStockAccount.getOpenEquity() == null ? "******" : fuStockAccount.getOpenEquity());
						map.put("capitalAccount", null == fuStockAccount.getCapitalAccount() ? "******" : fuStockAccount.getCapitalAccount());
						map.put("nowProfit", null == stockDetail ? "0.00" : null == stockDetail.getNowProfit() ? "0.00" : BigDecimal.ZERO.compareTo(stockDetail.getNowProfit()) == 1 ? "+" + stockDetail.getNowProfit() : stockDetail.getNowProfit());
					}
					list.add(map);
				}
				this.getActionContext().put("fsaList", list);
				this.getActionContext().put("dayProfit", BigDecimal.ZERO.compareTo(totalProfit) == 0 ? "0.00" : totalProfit);

				Map<String, Object> infoParames = new HashMap<String, Object>();
				infoParames.put("userId", fuUser.getId());
				FuStockMoneyInfo moneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(infoParames);
				// 所有账户 所有的累积盈亏
				this.getActionContext().put("totalProfit", null == moneyInfo ? "0.00" : moneyInfo.getProfitInfo() == null ? 0.00 : moneyInfo.getProfitInfo());
				// 该用户所有的未缴费用
				this.getActionContext().put("noneTotalFee", null == moneyInfo ? "0.00" : moneyInfo.getNoneFeeInfo() == null ? 0.00 : moneyInfo.getNoneFeeInfo());
				return "yqbProfit";
			} else {
				return "addUser";
			}
		}

	}

	/**
	 * 发送验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendCode")
	public String sendCode() throws Exception {
		if (null == phone || "".equals(phone)) {
			write("0"); // 没有获取到手机号码
			return null;
		}
		String ip = IP4.getIP4(ServletActionContext.getRequest());
		int flag = fuSmsLogService.examin(phone, ip);
		if (flag == 0) { // false
			write("1"); // 请联系管理员解冻你的封印
			return null;
		} else if (flag == 2) {
			write("2"); // 您点击过于频繁, 请稍后再试
			return null;
		}
		DecimalFormat format = new DecimalFormat("0000");
		String code = format.format(Math.random() * 9999);
		String message = URLDecoder.decode("您的手机验证码是:" + code + "，请填入界面完成绑定手机号码。", "UTF-8");
		FuSmsLog log = new FuSmsLog();
		log.setContent(message);
		log.setPrio(1);
		log.setReason("发送验证码");
		log.setDestination(phone);
		log.setPlanTime(new Date());
		log.setType(1);
		// log.setType(4);// 语音短信
		log.setRegCode(code);
		log.setState(0);
		fuSmsLogService.save(log);
		write("3");
		return null;
	}

	/**
	 * 绑定手机
	 * 
	 * @return
	 */
	@Action("bindPhone")
	public String bindPhone() {
		try {
			if (null == phone || "".equals(phone)) {
				write("0"); // 没有获取到手机号码
				return null;
			}
			if (null == phoneCode || "".equals(phoneCode)) {
				write("1"); // 没有获取到验证码
				return null;
			}
			FuSmsLog fs = fuSmsLogService.getNewCode(phone);
			if (null == fs) {
				write("2"); // 请重新发送验证码
				return null;
			}
			if (null == fs.getRegCode() || !phoneCode.equals(fs.getRegCode())) {
				write("3"); // 验证码输入错误
				return null;
			}

			String openId = (String) this.getHttpServletRequest().getSession().getAttribute("openId");
			if (null == openId) {
				write("5"); // 非法路径
				return null;
			}
			FuUser fuUser = fuUserService.findUserByAccount(phone);
			String nickName = (String) this.getHttpServletRequest().getSession().getAttribute("nickName");
			String avatar = (String) this.getHttpServletRequest().getSession().getAttribute("avatar");
			String code = (String) this.getHttpServletRequest().getSession().getAttribute("registerCode");
			if (null != code && !"".equals(code)) {
				registerCode = code;
			}
			if (null == fuUser) {
				DecimalFormat format = new DecimalFormat("000000");
				String password = format.format(Math.random() * 9999);
				password = CommonUtil.getMd5(password);
				fuUserService.saveWeiXinUser(phone, password, nickName, null, null == registerCode ? "364122458502" : registerCode, 4, avatar, openId);
				fuUser = fuUserService.findUserByWeixinCode(openId);
			} else {
				fuUser.setWeixinCode(openId);
				fuUserService.save(fuUser);
			}
			// 销毁Session
			this.getHttpServletRequest().getSession().invalidate();
			// 保存用户Session
			this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUser);
			write("4");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("bindPhone exception" + e);
		}
		return null;
	}

	@Action("saveImage")
	public String saveImage() {
		if (null != srcId && !"".equals(srcId)) {
			this.getHttpServletRequest().getSession().setAttribute("srcId", srcId);
		}
		return null;
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

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

}
