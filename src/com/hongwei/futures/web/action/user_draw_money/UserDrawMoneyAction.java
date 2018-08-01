package com.hongwei.futures.web.action.user_draw_money;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.model.FuDrawMoney;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.service.FuDrawMoneyService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserDrawMoneyAction extends BaseAction {
	private static final long serialVersionUID = -7611576146789807568L;
	
	@Autowired
	private FuBankCardService fuBankCardService;
	@Autowired
	private FuDrawMoneyService fuDrawMoneyService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	private FuUser fuUser;
	private Long userId;
	private Long id;
	private Integer status;
	private Long cardId;
	private String drawPassword;
	private String phoneCode;
	private String money;

	private Integer totalCount;
	private Float accountBalance;

	/**
	 * 我的提款记录
	 * 
	 * @return
	 */
	@Action("drawMoneyList")
	public String drawMoneyList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (userId != null) {
				map.put("userId", userId);
			}
			if (totalCount == null) {
				totalCount = fuDrawMoneyService.getCount(map);
			}
			List<FuDrawMoney> drawList = fuDrawMoneyService.findList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("drawList", drawList);
			this.getActionContext().put("jsptype", "drawMoneyList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 我要提款
	 * 
	 * @return
	 */
	@Action("drawMoney")
	public String drawMoney() {
		try {
			List<FuBankCard> bankCardList = fuBankCardService.findListByUser(userId);
			this.getActionContext().put("bankCardList", bankCardList);
			// 显示余额
			BigDecimal accountBalance = fuUser.getAccountBalance() == null ? new BigDecimal(0.00) : fuUser.getAccountBalance();
			this.getActionContext().put("accountBalance", accountBalance);

			// 提款记录
			Map<String, Object> map = new HashMap<String, Object>();
			if (status != null) {
				map.put("status", status);
			}
			if (userId != null) {
				map.put("userId", userId);
			}
			if (totalCount == null) {
				totalCount = fuDrawMoneyService.getCount(map);
			}
			List<FuDrawMoney> drawList = fuDrawMoneyService.findList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("drawList", drawList);
			this.getActionContext().put("jsptype", "drawMoney");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存提款申请
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveDrawMoneyAjax")
	public String saveDrawMoney() {
		try {
			if (StringUtil.isBlank(money)) {
				write("-9");
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
				write("-10");
				return null;
			}
			if (fuUser.getAccountBalance().compareTo(new BigDecimal(drawM)) == -1) {
				write("-7");
				return null;
			}
			if (cardId == null) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(drawPassword)) {
				write("-6");
				return null;
			} else {
				if (!fuUser.getDrawPassword().equals(CommonUtil.getMd5(drawPassword))) {
					write("-8");
					return null;
				}
			}
			if (StringUtil.isBlank(phoneCode)) {
				write("-4");
				return null;
			}
			if (fuUser.getPhoneCode() != null) {
				if (!phoneCode.equals(fuUser.getPhoneCode())) {
					write("-5");
					return null;
				}
			}
			if (fuUser.getIsCheckCard() == null || fuUser.getIsCheckCard() != 2) {
				write("-11");// 请先进行实名认证后，再进行操作
				return null;
			}
			// 余额减少，预处理
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(new BigDecimal(drawM)));

			// 提现记录
			FuDrawMoney drawMoney = new FuDrawMoney();
			drawMoney.setFuUser(fuUser);
			drawMoney.setFuBankCard(fuBankCardService.get(cardId));
			drawMoney.setDrawMoney(new BigDecimal(drawM));
			drawMoney.setDrawTime(new Date());
			drawMoney.setStatus(0);// 默认是未审核
			drawMoney.setState(1);// 正常
			fuDrawMoneyService.saveDrawMoney(drawMoney, fuUser, 9, false);// 保存用户和提现，写明细
			fuSmsLogService.saveSendServiceEmail("提款申请", "用户" + fuUser.getUserName() + "申请提款" + drawM + "元");
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送手机验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendMsgAjax")
	public String sendMsg() {
		try {
			DecimalFormat format = new DecimalFormat("000000");
			String code = format.format(Math.random() * 999999);
			fuUser.setPhoneCode(code);

			// 验证码过期时间（1个小时）
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR_OF_DAY, 1);
			fuUser.setPhoneCodeTime(cal.getTime());
			FuSmsLog log = new FuSmsLog();
			String msg = "提款验证码：" + code + "，为了保护您的账号安全，验证短信请勿转发给其他人。";
			String message = URLDecoder.decode(msg, "UTF-8");
			log.setContent(message);
			log.setPrio(1);
			log.setReason("提款");
			log.setDestination(fuUser.getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			write("1");
			fuUserService.saveReg(fuUser, log);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getDrawPassword() {
		return drawPassword;
	}

	public void setDrawPassword(String drawPassword) {
		this.drawPassword = drawPassword;
	}

	public Float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
