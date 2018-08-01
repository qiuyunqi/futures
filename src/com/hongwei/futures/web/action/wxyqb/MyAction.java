package com.hongwei.futures.web.action.wxyqb;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StockPublish;
import com.hongwei.futures.model.StockShare;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.StockPublishService;
import com.hongwei.futures.service.StockShareService;
import com.hongwei.futures.util.Base64;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.WeiXinUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("wx_yqb")
public class MyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(MyAction.class);

	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private StockShareService stockShareService;
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private StockPublishService stockPublishService;

	private FuUser fuUser;
	private String from;
	private String isappinstalled;
	private String phone; // 手机号
	private String phoneCode; // 手机验证码
	private String serverId; // 微信上传图片返回的media_id
	private String srcId; // 微信上传图片本地的img地址
	private Long userId;
	private String nickName;
	private Long id;

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Action("meIndex")
	public String meIndex() {
		try {
			FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			this.getHttpServletRequest().setAttribute("appid", Property.getProperty("WEIXIN_APPID"));
			this.getHttpServletRequest().setAttribute("url", Property.getProperty("REDIRECT_URL"));
			if (fuUser == null) {
				this.getActionContext().put("flag", "error");
			} else {
				InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
				Properties properties = new Properties();
				properties.load(in);

				this.getActionContext().put("fuUser", fuUser);
				// 手机号部分隐藏
				this.getActionContext().put("phone", fuUser.getPhone().substring(0, 3) + "****" + fuUser.getPhone().substring(7, fuUser.getPhone().length()));

				FuTransaction transaction = fuTransactionService.findByUserId(fuUser.getId());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("transactionId", transaction == null ? 0L : transaction.getId());
				List<FuStockAccount> accounts = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map);

				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("userId", fuUser.getId());
				map2.put("isDel", 0);
				List<FuStockAccount> toTicketNum = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map2);

				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("userId", fuUser.getId());
				List<StockPublish> ticketList = stockPublishService.findPublishByMap(0, Integer.MAX_VALUE, map3);

				this.getActionContext().put("toTicketNum", toTicketNum.size());// 我的供劵数
				this.getActionContext().put("findTicketNum", accounts.size());// 我找的劵数
				this.getActionContext().put("ticketManagerNum", ticketList.size());// 找劵管理数目
				// 下级合伙人数量
				List<FuUser> nextLevel = fuUserService.findListByParentId(fuUser.getId());
				if (nextLevel != null) {
					this.getActionContext().put("nextcount", nextLevel.size());
				} else {
					this.getActionContext().put("nextcount", 0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 个人信息
	@Action("userInfo")
	public String userInfo() {
		FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
		this.getActionContext().put("fuUser", fuUser);
		try {
			WeiXinUtil.getToken(this.getHttpSession(), this.getHttpServletRequest(), this.getActionContext(), from, isappinstalled);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 设置昵称
	@Action("setName")
	public String setName() {
		FuUser fuUser = fuUserService.get(userId);
		this.getActionContext().put("fuUser", fuUser);
		return SUCCESS;
	}

	// 保存昵称
	@Action("saveNickName")
	public String saveNickName() {
		try {
			FuUser fuUser = fuUserService.get(userId);
			FuUser sameNick = fuUserService.findUserByNickName(nickName);
			if (null != sameNick) {
				write("-1");
				return null;
			} else {
				fuUser.setNickName(nickName);
				fuUserService.save(fuUser);
				this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUser);
				write("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("setImageSrcId")
	public String setImageSrcId() {
		if (srcId != null && !"".equals(srcId)) {
			this.getHttpServletRequest().getSession().setAttribute("srcId", srcId);
		}
		return null;
	}

	// 保存用户头像
	@Action("saveUserAvatar")
	public String saveUserAvatar() {
		try {
			if (serverId == null || "".equals(serverId)) {
				write("1"); // 上传图片失败
				return null;
			}
			FuUser fuUser = fuUserService.get(userId);
			String userAvatar = WeiXinUtil.downloadPicAndUploadOSS(this.getHttpSession(), serverId);
			fuUser.setUserAvatar(userAvatar);
			fuUserService.save(fuUser);
			this.getHttpServletRequest().getSession().setAttribute("srcId", null);
			this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUser);
			write("2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 我的邀请
	@Action("myInvitation")
	public String myInvitation() {
		List<FuUser> nextList = fuUserService.findListByParentIdCount(userId, 9);
		this.getActionContext().put("nextList", nextList);
		this.getActionContext().put("fuUser", fuUserService.get(userId));
		return SUCCESS;
	}

	// 邀请人列表
	@Action("invitationList")
	public String invitationList() {
		try {
			List<FuUser> userList = fuUserService.findListByParentId(userId);
			JSONObject obj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			for (FuUser user : userList) {
				JSONObject json = new JSONObject();
				json.put("userAvatar", user.getUserAvatar());
				json.put("nickName", user.getNickName());
				jsonArr.add(json);
			}
			obj.put("lists", jsonArr);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	// 我的财富
	@Action("moneyDetail")
	public String moneyDetail() {
		List<FuMoneyDetail> detailList = fuMoneyDetailService.findListByUserIdCount(userId, 6);
		this.getActionContext().put("detailList", detailList);
		this.getActionContext().put("fuUser", fuUserService.get(userId));
		return SUCCESS;
	}

	// 资金明细列表
	@Action("userMoneyDetailList")
	public String userMoneyDetailList() {
		try {
			List<FuMoneyDetail> list = fuMoneyDetailService.findListByUserId(userId);
			JSONObject obj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			for (FuMoneyDetail detail : list) {
				JSONObject json = new JSONObject();
				json.put("name", detail.getFuDictionary().getName());
				json.put("accountBalanceAfter", detail.getAccountBalanceAfter());
				json.put("time", new SimpleDateFormat("yyyy-MM-dd").format(detail.getTime()));
				json.put("money", detail.getMoney());
				jsonArr.add(json);
			}
			obj.put("lists", jsonArr);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 我找的劵
	 * 
	 * @return
	 */
	@Action("IFindTicket")
	public String IFindTicket() {
		try {
			FuTransaction transaction = fuTransactionService.findByUserId(userId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("transactionId", transaction == null ? 0L : transaction.getId());
			List<FuStockAccount> accounts = fuStockAccountService.findAccountByMap(0, 5, map);
			this.getActionContext().put("accounts", accounts);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 我找的劵数据
	 * 
	 * @return
	 */
	@Action("findTicketData")
	public String findTicketData() {
		try {
			FuTransaction transaction = fuTransactionService.findByUserId(userId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("transactionId", transaction == null ? 0L : transaction.getId());
			List<FuStockAccount> accounts = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			if (accounts.size() > 0) {
				for (FuStockAccount account : accounts) {
					JSONObject obj = new JSONObject();
					obj.put("id", account.getId());
					obj.put("openEquity", account.getOpenEquity());
					obj.put("capitalAccount", account.getCapitalAccount());
					obj.put("transactionStatus", account.getTransactionStatus());
					obj.put("status", account.getTransactionStatus() == 0 ? "正在操作" : "已退劵");
					obj.put("available", new DecimalFormat("#,###,##0.00").format(account.getAvailable() == null ? 0 : account.getAvailable()));
					obj.put("ableMoney", new DecimalFormat("#,###,##0.00").format(account.getAbleMoney() == null ? 0 : account.getAbleMoney()));
					obj.put("createTime", new SimpleDateFormat("yyyy.MM.dd").format(account.getCreateTime()));
					array.add(obj);
				}
			}
			json.put("array", array);
			write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 我找的劵账户信息
	 */
	@Action("accountInfo")
	public String accountInfo() {
		try {
			FuStockAccount stockAccount = fuStockAccountService.get(id);
			List<StockShare> shares = stockShareService.findStockShareByAccount(id);

			this.getActionContext().put("capitalPassword", stockAccount.getCapitalPassword() == null ? "" : new String(Base64.decode(stockAccount.getCapitalPassword())));
			this.getActionContext().put("stockAccount", stockAccount);
			this.getActionContext().put("shares", shares);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 保存退劵
	 * 
	 * @return
	 */
	@Action("saveFadeTicket")
	public String saveFadeTicket() {
		try {
			FuStockAccount stockAccount = fuStockAccountService.get(id);
			stockAccount.setTransactionStatus(1);// 已退劵
			fuStockAccountService.save(stockAccount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 我的供劵
	 * 
	 * @return
	 */
	@Action("myForTicket")
	public String myForTicket() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("isDel", 0);
			// 查询当前用户的所有未删除的股票账户
			List<FuStockAccount> stockAccounts = fuStockAccountService.findAccountByMap(0, 5, map);
			List<Object> stockList = new ArrayList<Object>();
			// 当前用户昨日总盈利
			BigDecimal dayProfits = BigDecimal.ZERO;
			if (stockAccounts.size() > 0) {
				for (FuStockAccount fuStockAccount : stockAccounts) {
					FuStockMoneyDetail stockMoneyDetail = fuStockMoneyDetailService.findMoneyDetailByAccount(fuStockAccount.getId());
					List<Object> objList = new ArrayList<Object>();
					objList.add(fuStockAccount.getId());
					objList.add(fuStockAccount.getOpenEquity());
					objList.add(fuStockAccount.getCapitalAccount());
					objList.add(stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit());
					stockList.add(objList);
					dayProfits = dayProfits.add((stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit()));
				}
			}
			// 查询当前用户的资金统计结果
			FuStockMoneyInfo fuStockMoneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(map);
			this.getActionContext().put("dayProfits", dayProfits);
			this.getActionContext().put("fuStockMoneyInfo", fuStockMoneyInfo);
			this.getActionContext().put("stockList", stockList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 我的供券数据
	 * 
	 * @return
	 */
	@Action("forTicketData")
	public String forTicketData() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("isDel", 0);
			// 查询当前用户的所有未删除的股票账户
			List<FuStockAccount> stockAccounts = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			if (stockAccounts.size() > 0) {
				for (FuStockAccount fuStockAccount : stockAccounts) {
					FuStockMoneyDetail stockMoneyDetail = fuStockMoneyDetailService.findMoneyDetailByAccount(fuStockAccount.getId());
					JSONObject obj = new JSONObject();
					obj.put("id", fuStockAccount.getId());
					obj.put("openEquity", fuStockAccount.getOpenEquity());
					obj.put("capitalAccount", fuStockAccount.getCapitalAccount());
					obj.put("nowProfit", new DecimalFormat("#,###,##0.00").format(stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit()));
					array.add(obj);
				}
			}
			json.put("array", array);
			write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 绑定新手机
	 * 
	 * @return
	 */
	@Action("unwrapPhone")
	public String unwrapPhone() {
		return SUCCESS;
	}

	/**
	 * 发送验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendUnwrapCode")
	public String sendUnwrapCode() {
		try {
			if (null == phone || "".equals(phone)) {
				write("0"); // 没有获取到手机号码
				return null;
			}
			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (null == fuUser) {
				write("-1"); // 该手机号的用户不存在
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
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 微信绑定到新手机号
	 * 
	 * @return
	 */
	@Action("bindAccount")
	public String bindAccount() {
		try {
			if (null == phoneCode || "".equals(phoneCode)) {
				write("1"); // 没有获取到验证码
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("destination", phone);
			map.put("type", 1);
			FuSmsLog fs = fuSmsLogService.findLogByMap(map);// 根据目标手机号得到最新一条短信对象
			if (null == fs) {
				write("2"); // 请重新发送验证码
				return null;
			}
			if (null == fs.getRegCode() || !phoneCode.equals(fs.getRegCode())) {
				write("3"); // 验证码输入错误
				return null;
			}

			String openId = (String) this.getHttpServletRequest().getSession().getAttribute("openId");
			logger.info("openId=======>" + openId);
			if (null == openId) {
				write("5"); // 非法路径
				return null;
			}
			FuUser fromUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");// 当前的用户绑定的微信code清空

			FuUser toUser = fuUserService.findUserByPhone(phone);// 微信code绑定到指定的手机号

			logger.info("fromUser=======>" + fromUser);
			logger.info("toUser=======>" + toUser);
			fromUser.setWeixinCode(null);
			fuUserService.save(fromUser);// 当前的用户绑定的微信code清空

			toUser.setWeixinCode(openId);
			fuUserService.save(toUser);// 微信code绑定到指定的手机号

			// 销毁Session
			// this.getHttpServletRequest().getSession().invalidate();
			// 保存用户Session
			this.getHttpServletRequest().getSession().setAttribute("fuUser", toUser);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("bindPhone exception" + e);
		}
		return null;
	}
}
