package com.hongwei.futures.web.action.wxyqb;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StateModel;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.HttpClientUtil;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.web.action.BaseAction;

/**
 * 供劵
 * 
 * @author han
 * @date 2016-06-22
 */
@ParentPackage("wx_yqb")
public class ForSecurityAction extends BaseAction {

	private static final long serialVersionUID = 8328775253179262102L;
	private static final Log logger = LogFactory.getLog(ForSecurityAction.class);

	private FuUser fuUser;
	private Long id;
	private Integer start;
	private String state;
	private StateModel stateModel;
	private String redirectUrl;

	private String openEquity; // 开户劵商
	private String salesDept; // 营业
	private Integer accountType; // 账户类型
	private Integer profitModel; // 收益模式 0:稳定收益 1:保本分成
	private BigDecimal profitConfig; // 收益分配 存储的值==> 0.4672
	private String availableSplit; // 收益分配
	private String capitalAccount; // 资金账号
	private String capitalPassword; // 资金密码
	private String message; // 留言
	private String serverId; // 微信上传图片
	private String query; // 搜索的条件

	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private FuUserService fuUserService;

	
	/**
	 * 开始抢劵
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("order")
	public synchronized String order() throws Exception {
		FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
		logger.info("order====>" + fuUser);
		if (null == fuUser) {
			// 进入微信登录
			write("1"); // 用户没有登录
			return null;
		} else if (null != fuUser && (null == fuUser.getPhone() || "".equals(fuUser.getPhone()))) {
			write("2"); // 用户登录了 但是没有绑定手机号码
			return null;
		} else {
			FuTransaction fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
			if (null == fuTransaction || fuTransaction.getIsVerification() == 0) {
				write("3"); // 当前用户不是交易团队
				return null;
			}
			FuStockAccount fuStockAccount = fuStockAccountService.get(id);
			if (null == fuStockAccount) {
				write("4"); // 股票账户不存在
				return null;
			}
			if (fuStockAccount.getExamineStatus() == 4) {
				write("5"); // 股票已经被抢
				return null;
			}

			if (fuStockAccount.getFuUser().getId() == fuTransaction.getFuUser().getId()) {
				write("7"); // 您不能抢自己发的劵
				return null;
			}
			// 抢券成功
			fuStockAccount.setTransactionId(fuTransaction.getId());
			fuStockAccount.setOrderTime(new Date());
			fuStockAccount.setExamineStatus(4);
			fuStockAccount.setTransactionStatus(0);
			fuStockAccountService.saveAccountAndRecord(fuStockAccount, fuUser, 0, fuTransaction);
			write("6");
		}
		return null;
	}

	@Action(value = "wxLogin", results = { 
			@Result(type = "redirect", name = "toOrder", location = "/wxyqb/redirectOrder.htm", params = { "id", "${stateModel.id}" }), 
			@Result(type = "redirect", name = "addPhone", location = "/wxyqb/addPhone.htm", params = { "redirectUrl", "${redirectUrl}" }), 
			@Result(type = "redirect", name = "toMeIndex", location = "/wxyqb/meIndex.htm"),
			@Result(type = "redirect", name = "toHaveTicket", location = "/wxyqb/haveTicket.htm"), 
			@Result(type = "redirect", name = "findTicket", location = "/wxyqb/findTicket.htm"), 
			@Result(type = "redirect", name = "toNewIndex", location = "/wxyqb/newIndex.htm"),
			@Result(type = "redirect", name = "toTicketInfo", location = "/wxyqb/forTicketInfo.htm"),
			@Result(type = "redirect", name = "toihavestock", location = "/wxyqb/redirectIhavestock.htm")
			})
	public String wxLogin() throws Exception {
		String wxCode = this.getHttpServletRequest().getParameter("code");
		String[] split = state.split("_");
		if ("toOrder".equals(split[0])) {
			stateModel = new StateModel();
			stateModel.setId(Long.parseLong(split[1]));
		} else if ("toNewIndex".equals(split[0]) && split.length == 2) {
			this.getHttpServletRequest().getSession().setAttribute("registerCode", split[1]);
		} else if ("toTicketInfo".equals(split[0])) {
			stateModel = new StateModel();
			stateModel.setId(Long.parseLong(split[1]));
		}
		FuUser fuUser = null;
		fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
		if (null == fuUser) {
			// 获取token和openid
			String appid = Property.getProperty("WEIXIN_APPID");
			String secret = Property.getProperty("WEIXIN_APP_SECRET");

			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + wxCode + "&grant_type=authorization_code";

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
			logger.info("wxLogin_fuUser=====>" + fuUser);
			this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUser);
			this.getHttpServletRequest().getSession().setAttribute("openId", openId);
			if (null == fuUser && null != openId) {
				// 新用户
				this.getHttpServletRequest().getSession().setAttribute("nickName", nickName);
				this.getHttpServletRequest().getSession().setAttribute("avatar", avatar);
				if ("toOrder".equals(split[0])) {
					redirectUrl = "/wxyqb/redirectOrder.htm?id=" + stateModel.getId();
				} else if ("toHaveTicket".equals(split[0])) {
					redirectUrl = "/wxyqb/haveTicket.htm";
				} else if ("toMeIndex".equals(split[0])) {
					redirectUrl = "/wxyqb/meIndex.htm";
				} else if ("findTicket".equals(split[0])) {
					redirectUrl = "/wxyqb/findTicket.htm";
				} else if ("toNewIndex".equals(split[0])) {
					redirectUrl = "/wxyqb/newIndex.htm";
				} else if ("toTicketInfo".equals(split[0])) {
					redirectUrl = "/wxyqb/forTicketInfo.htm?id=" + stateModel.getId();
				}
				return "addPhone";
			} else if (null == fuUser.getPhone()) {
				if ("toOrder".equals(split[0])) {
					redirectUrl = "/wxyqb/redirectOrder.htm?id=" + stateModel.getId();
				} else if ("toHaveTicket".equals(split[0])) {
					redirectUrl = "/wxyqb/haveTicket.htm";
				} else if ("toMeIndex".equals(split[0])) {
					redirectUrl = "/wxyqb/meIndex.htm";
				} else if ("findTicket".equals(split[0])) {
					redirectUrl = "/wxyqb/findTicket.htm";
				} else if ("toNewIndex".equals(split[0])) {
					redirectUrl = "/wxyqb/newIndex.htm";
				} else if ("toTicketInfo".equals(split[0])) {
					redirectUrl = "/wxyqb/forTicketInfo.htm?id=" + stateModel.getId();
				}
				return "addPhone";
			}
		}
		return split[0];
	}

	/**
	 * 开始抢劵
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("redirectOrder")
	public synchronized String redirectOrder() throws Exception {
		FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
		logger.info("order redirectOrder====>" + fuUser);
		if (null == fuUser) {
			// 进入微信登录
			this.getHttpServletRequest().setAttribute("isSuccess", 0);
		} else if (null != fuUser && (null == fuUser.getPhone() || "".equals(fuUser.getPhone()))) {
			// 用户登录了 但是没有绑定手机号码
			this.getHttpServletRequest().setAttribute("isSuccess", 1);
		} else {
			FuTransaction fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
			if (null == fuTransaction || fuTransaction.getIsVerification() == 0) {
				this.getHttpServletRequest().setAttribute("isSuccess", 2);// 当前用户不是交易团队
				return SUCCESS;
			}
			FuStockAccount fuStockAccount = fuStockAccountService.get(id);
			if (null == fuStockAccount) {
				this.getHttpServletRequest().setAttribute("isSuccess", 3); // 股票账户不存在
				return SUCCESS;
			}
			if (fuStockAccount.getExamineStatus() != 3) {
				this.getHttpServletRequest().setAttribute("isSuccess", 4); // 股票已经被抢
				return SUCCESS;
			}
			// 抢券成功
			fuStockAccount.setTransactionId(fuTransaction.getId());
			fuStockAccount.setOrderTime(new Date());
			fuStockAccount.setExamineStatus(4);
			fuStockAccount.setTransactionStatus(0);
			fuStockAccountService.saveAccountAndRecord(fuStockAccount, fuUser, 0, fuTransaction);
			this.getHttpServletRequest().setAttribute("isSuccess", 5);
		}
		return SUCCESS;
	}

	@Action("haveTicket")
	public String ticket() {
		try {
			FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			if (null == fuUser) {
				this.getHttpServletRequest().setAttribute("data", 0);
			} else {
				this.getHttpServletRequest().setAttribute("data", 1);
			}
			String appid = Property.getProperty("WEIXIN_APPID");
			String url = Property.getProperty("REDIRECT_URL");
			this.getHttpServletRequest().setAttribute("url", url);
			this.getHttpServletRequest().setAttribute("appid", appid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 保存商劵
	 * 
	 * @return
	 */
	@Action("saveTicket")
	public String haveTicket() {
		try {
			if (null == openEquity || "".equals(openEquity)) {
				write("1"); // 开户劵商不能为空
				return null;
			}
			if (null == salesDept || "".equals(salesDept)) {
				write("2"); // 营业部不能为空
				return null;
			}
			if (null == accountType || (1 != accountType && 2 != accountType && 3 != accountType)) {
				write("3"); // 请选择类型
				return null;
			}

			if (null == profitModel) {
				write("4"); // 收益模式不能为空
				return null;
			}

			if (null == availableSplit) {
				write("5"); // 收益分配不能为空
				return null;
			}

			if (null != message && !"".equals(message) && message.length() > 25) {
				write("6"); // 最多25个字
				return null;
			}
			FuUser fuUser = null;
			fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			if (null == fuUser) {
				write("7");
				return null;
			}
			// 保存账号信息
			FuStockAccount fsa = new FuStockAccount();
			fsa.setFuUser(fuUser);
			fsa.setOpenEquity(openEquity);
			fsa.setSalesDept(salesDept);
			fsa.setAccountType(accountType);
			fsa.setProfitModel(profitModel);
			fsa.setAvailableSplit(availableSplit);
			fsa.setCapitalAccount(capitalAccount);
			fsa.setMessage(message);
			fsa.setSourceType(2); // 0: 网站 1:APP 2: 微信
			fsa.setCreateTime(new Date());
			// 创建时,更新时间与创建时间相同
			fsa.setUpdateTime(new Date());
			fsa.setIsDel(0); // 可用账号
			fsa.setExamineStatus(0);// 审核中状态
			fsa.setIsPublish(0); // 未发布状态
			fsa.setState(0);
			fuStockAccountService.saveFsa(fsa, this.getHttpSession(), serverId);
			this.getHttpServletRequest().getSession().removeAttribute("srcId");
			write("8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 绑定手机
	 * 
	 * @return
	 */
	@Action("addPhone")
	public String addPhone() {
		this.getHttpServletRequest().setAttribute("redirectUrl", redirectUrl);
		return SUCCESS;
	}

	/**
	 * 首页 我要劵
	 * 
	 * @return
	 */
	@Action("ihavestock")
	public String ihavestock() {
		try {
			FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			if (null == fuUser) {
				write("1");
				return null;
			}

			/*
			 * Integer isTransaction = fuUser.getIsTransaction(); if (null ==
			 * isTransaction || isTransaction != 1) { write("2"); return null; }
			 * else { write("3"); return null; }
			 */
			FuTransaction fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
			if (fuTransaction == null) { // 不是交易员
				write("2");
			} else if (null == fuTransaction.getIsVerification() || fuTransaction.getIsVerification() != 1) { // 正在审核
				write("3");
			} else { // 交易员
				write("4");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return null;
	}

	/**
	 * 首页我要劵登录后的回调函数
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@Action("redirectIhavestock")
	public String redirectIhavestock() throws ServletException, IOException {
		FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
//		FuUser fuUser = this.fuUserService.findFuUserById(32651L);
		logger.info("ihaveStock redirectIhavestock====>" + fuUser);
		if (null == fuUser) {
			// 进入微信登录
			this.getHttpServletRequest().setAttribute("isSuccess", 0);
		} else if (null != fuUser && (null == fuUser.getPhone() || "".equals(fuUser.getPhone()))) {
			// 用户登录了 但是没有绑定手机号码
			this.getHttpServletRequest().setAttribute("isSuccess", 1);
		} else {
			FuTransaction fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
			if (fuTransaction == null) { // 不是交易员
				this.getHttpServletRequest().getRequestDispatcher("/wxyqb/business.htm").forward(this.getHttpServletRequest(), this.getHttpServletResponse());
				return SUCCESS;
			} else if (null == fuTransaction.getIsVerification() || fuTransaction.getIsVerification() != 1) {
				this.getHttpServletRequest().setAttribute("isSuccess", 3);// 正在审核
				return SUCCESS;
			} else { // 交易员
				this.getHttpServletRequest().getRequestDispatcher("/wxyqb/userFindTicket.htm?userId="+fuUser.getId())
				.forward(this.getHttpServletRequest(), this.getHttpServletResponse());
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
//-----------------------------------get  set function-------------------------------------------------------------	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public StateModel getStateModel() {
		return stateModel;
	}

	public void setStateModel(StateModel stateModel) {
		this.stateModel = stateModel;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getOpenEquity() {
		return openEquity;
	}

	public void setOpenEquity(String openEquity) {
		this.openEquity = openEquity;
	}

	public String getSalesDept() {
		return salesDept;
	}

	public void setSalesDept(String salesDept) {
		this.salesDept = salesDept;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getProfitConfig() {
		return profitConfig;
	}

	public void setProfitConfig(BigDecimal profitConfig) {
		this.profitConfig = profitConfig;
	}

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	public String getCapitalPassword() {
		return capitalPassword;
	}

	public void setCapitalPassword(String capitalPassword) {
		this.capitalPassword = capitalPassword;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getProfitModel() {
		return profitModel;
	}

	public void setProfitModel(Integer profitModel) {
		this.profitModel = profitModel;
	}

	public String getAvailableSplit() {
		return availableSplit;
	}

	public void setAvailableSplit(String availableSplit) {
		this.availableSplit = availableSplit;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

}
