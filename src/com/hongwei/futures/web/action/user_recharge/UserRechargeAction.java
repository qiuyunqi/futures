package com.hongwei.futures.web.action.user_recharge;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.util.MoneyUtil;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserRechargeAction extends BaseAction {
	@Autowired
	private FuRechargeService fuRechargeService;
	@Autowired
	private FuParameterService fuParameterService;
	@Autowired
	private FuBankCardService fuBankCardService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	private FuUser fuUser;
	private Long userId;
	private String bank;
	private String money;
	private Integer type;
	private String account;
	private Integer totalCount;
	private Long id;
	private Long bankId;
	private String poundageMoney;
	private int proceedsType;
	private Integer rechargeStatus;

	/**
	 * 账户充值
	 * 
	 * @return
	 */
	@Action(value = "recharge", results = { @Result(name = "quickPay", location = "/WEB-INF/content/user_recharge/quickPay.jsp"),
			@Result(name = "netBank", location = "/WEB-INF/content/user_recharge/netBank.jsp"), @Result(name = "zfb", location = "/WEB-INF/content/user_recharge/zfb.jsp"),
			@Result(name = "bankTransfer", location = "/WEB-INF/content/user_recharge/bankTransfer.jsp"),
			@Result(name = "toList", location = "${ctx}/user_recharge/rechargeList.htm", type = "redirect") })
	public String recharge() {
		try {
			FuParameter params = fuParameterService.findParameter();
			this.getActionContext().put("params", params);
			JSONObject obj = new JSONObject();
			if (params.getRechargeConfig() != null) {
				obj = JSONObject.fromObject(params.getRechargeConfig());
			}
			this.getActionContext().put("rechargeConfig", obj);

			// 显示余额
			BigDecimal accountBalance = fuUser.getAccountBalance() == null ? BigDecimal.ZERO : fuUser.getAccountBalance();
			this.getActionContext().put("accountBalance", accountBalance);

			// 充值记录
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("rechargeStatus", rechargeStatus);
			if (totalCount == null) {
				totalCount = fuRechargeService.getCount(map);
			}
			List<FuRecharge> rechargeList = fuRechargeService.findBy((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("rechargeList", rechargeList);

			this.getActionContext().put("jsptype", "recharge");
			if (type == null) {
				if (obj.containsKey("zf3")) {
					type = 3;
				} else if (obj.containsKey("zf1")) {
					type = 1;
				} else if (obj.containsKey("zf4")) {
					type = 4;
				} else if (obj.containsKey("zf2")) {
					type = 2;
				} else {
					type = null;
				}
			}
			this.getActionContext().put("type", type);
			if (type != null) {
				if (type == 1) {
					return "quickPay";
				} else if (type == 2) {
					return "netBank";
				} else if (type == 3) {
					return "zfb";
				} else {
					List<FuBankCard> bankCardList = fuBankCardService.findListByUser(userId);
					this.getActionContext().put("bankCardList", bankCardList);
					return "bankTransfer";
				}
			} else {
				return "toList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 跳转在线充值页面
	 * 
	 * @return
	 */
	@Action("onlinePay")
	public String onlinePay() {
		try {
			JSONObject obj = new JSONObject();
			FuParameter params = fuParameterService.findParameter();
			this.getActionContext().put("params", params);

			if (params.getRechargeConfig() != null) {
				obj = JSONObject.fromObject(params.getRechargeConfig());
			}
			this.getActionContext().put("rechargeConfig", obj);

			this.getActionContext().put("jsptype", "onlinePay");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 银联充值申请成功
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("quickSucced")
	public String quickSucced() {
		try {
			this.getConfig();
			FuRecharge recharge = fuRechargeService.get(id);
			if (recharge != null) {
				this.getActionContext().put("recharge", recharge);
			}
			if (recharge != null) {
				chinapay.PrivateKey key1 = new chinapay.PrivateKey();
				chinapay.SecureLink t;
				key1.buildKey(Property.getProperty("CHINAPAY_MERCHANT_NO"), 0, this.getServletContext().getRealPath(Property.getProperty("MERCHANT_KEY_PATH"))); // 注意要用哪一个商户号签名就要用对应的key文件。
				t = new chinapay.SecureLink(key1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				NumberFormat ndf1 = new DecimalFormat("0000000");
				NumberFormat ndf2 = new DecimalFormat("000000000000");
				// 支付订单数据准备
				String MerId = Property.getProperty("CHINAPAY_MERCHANT_NO");
				String OrdId = Property.getProperty("CHINAPAY_OrderHead") + ndf1.format(recharge.getId());// 000002146
																											// +
																											// 0000074
				System.out.println(OrdId);
				String TransDate = sdf.format(new Date());
				String Version = Property.getProperty("CHINAPAY_Version");
				String TransAmt = ndf2.format(recharge.getRechargeMoney().multiply(new BigDecimal(100)));
				String CuryId = Property.getProperty("CHINAPAY_CuryId");
				String TransType = Property.getProperty("CHINAPAY_TransType");
				String BgRetUrl = Property.getProperty("CHINAPAY_BgRetUrl");
				String PageRetUrl = Property.getProperty("CHINAPAY_PageRetUrl");
				String GateId = Property.getProperty("CHINAPAY_GateId");
				String Priv1 = Property.getProperty("CHINAPAY_Priv1");
				String ChkValue = t.Sign(MerId + OrdId + TransAmt + CuryId + TransDate + TransType + Version + BgRetUrl + PageRetUrl + GateId + Priv1);
				// System.out.println(MerId + OrdId + TransAmt + CuryId +
				// TransDate + TransType + Version + BgRetUrl + PageRetUrl +
				// GateId + Priv1);
				JSONObject obj = new JSONObject();
				obj.put("MerId", MerId);
				obj.put("OrdId", OrdId);
				obj.put("TransAmt", TransAmt);
				obj.put("CuryId", CuryId);
				obj.put("TransDate", TransDate);
				obj.put("TransType", TransType);
				obj.put("Version", Version);
				obj.put("BgRetUrl", BgRetUrl);
				obj.put("PageRetUrl", PageRetUrl);
				obj.put("GateId", GateId);
				obj.put("Priv1", Priv1);
				obj.put("ChkValue", ChkValue);
				this.getActionContext().put("obj", obj);
			}
			this.getActionContext().put("poundageMoney", poundageMoney);
			this.getActionContext().put("jsptype", "quickSucced");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存充值记录（前台）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveUserRecharge")
	public String saveUserRecharge() {
		try {
			// 暂时不需要这个逻辑
			// if(type!=3&&StringUtil.isBlank(bank)){
			// write("-2");
			// return null;
			// }
			if (type == 4 && bankId == null) {
				write("-6");
				return null;
			}
			/*
			 * if(type==3&&StringUtil.isBlank(account)){//支付宝 write("-3"); return null; }
			 */
			if (StringUtil.isBlank(money)) {
				write("-4");
				return null;
			}
			String drawM = "";
			if (money.contains(",")) {
				money = money.replace(",", "");
			}
			if (money.contains("，")) {
				money = money.replace("，", "");
			}
			drawM = money;
			if (money.contains(".")) {
				money = money.replace(".", "");
			}
			if (!StringUtil.isNumeric(money)) {
				write("-5");
				return null;
			}
			FuRecharge recharge = new FuRecharge();
			recharge.setFuUser(fuUser);

			recharge.setType(type);
			if (type == 1) {// 银联支付
				recharge.setRechargeBank("银联支付");
			} else if (type == 2) {// 网银支付
				recharge.setRechargeBank("网银充值");
			} else if (type == 3) {// 支付宝
				recharge.setRechargeBank("支付宝支付");
				recharge.setRechargeAccount(account);
			} else if (type == 4) {// 银行转账
				FuBankCard bankCard = fuBankCardService.get(bankId);
				recharge.setRechargeBank(bankCard.getBankName());
				recharge.setRechargeAccount(bankCard.getCardNumber());
				recharge.setOrderNum(String.valueOf((int) ((Math.random() * 1000000 + 100000))));// 随机产生订单号
			}
			recharge.setRechargeMoney(new BigDecimal(drawM));
			recharge.setRechargeTime(new Date());
			recharge.setRechargeStatus(0);// 待审核
			recharge.setState(1);
			recharge.setProceedsType(proceedsType);
			fuRechargeService.save(recharge);
			fuSmsLogService.saveSendServiceEmail(type == 1 ? "银联充值" : type == 4 ? "银行转账充值" : "", "用户" + fuUser.getUserName() + "申请" + (type == 1 ? "银联充值" : type == 4 ? "银行转账充值" : "") + drawM + "元");
			write(recharge.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 支付宝下一步
	 * 
	 * @return
	 */
	@Action("zfbInfo")
	public String zfbInfo() {
		try {
			this.getConfig();
			FuRecharge recharge = fuRechargeService.get(id);
			if (recharge != null) {
				this.getActionContext().put("recharge", recharge);
			}
			this.getActionContext().put("poundageMoney", poundageMoney);
			this.getActionContext().put("jsptype", "zfbInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 银行转账下一步
	 * 
	 * @return
	 */
	@Action("bankTransferInfo")
	public String bankTransferInfo() {
		try {
			this.getConfig();
			FuRecharge recharge = fuRechargeService.get(id);
			if (recharge != null) {
				this.getActionContext().put("recharge", recharge);
			}
			this.getActionContext().put("jsptype", "bankTransferInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void getConfig() {
		try {
			FuParameter params = fuParameterService.findParameter();
			this.getActionContext().put("params", params);
			JSONObject obj = new JSONObject();
			if (params.getRechargeConfig() != null) {
				obj = JSONObject.fromObject(params.getRechargeConfig());
			}
			this.getActionContext().put("rechargeConfig", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 充值记录
	 * 
	 * @return
	 */
	@Action("rechargeList")
	public String rechargeList() {
		try {
			FuParameter params = fuParameterService.findParameter();
			this.getActionContext().put("params", params);
			JSONObject obj = new JSONObject();
			if (params.getRechargeConfig() != null) {
				obj = JSONObject.fromObject(params.getRechargeConfig());
			}
			this.getActionContext().put("rechargeConfig", obj);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			if (totalCount == null) {
				totalCount = fuRechargeService.getCount(map);
			}
			List<FuRecharge> rechargeList = fuRechargeService.findBy((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("rechargeList", rechargeList);
			this.getActionContext().put("jsptype", "rechargeList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 充值记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("toChineseAjax")
	public String toChinese() {
		try {
			String drawM = "";
			if (money.contains(",")) {
				money = money.replace(",", "");
			}
			if (money.contains("，")) {
				money = money.replace("，", "");
			}
			drawM = money;
			if (money.contains(".")) {
				money = money.replace(".", "");
			}
			if (!StringUtil.isNumeric(money)) {
				write("-2");
				return null;
			}
			JSONObject obj = new JSONObject();
			String chineseMoney = MoneyUtil.amountToChinese(Double.valueOf(drawM));
			obj.put("chineseMoney", chineseMoney);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getPoundageMoney() {
		return poundageMoney;
	}

	public void setPoundageMoney(String poundageMoney) {
		this.poundageMoney = poundageMoney;
	}

	public int getProceedsType() {
		return proceedsType;
	}

	public void setProceedsType(int proceedsType) {
		this.proceedsType = proceedsType;
	}

	public Integer getRechargeStatus() {
		return rechargeStatus;
	}

	public void setRechargeStatus(Integer rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}

}
