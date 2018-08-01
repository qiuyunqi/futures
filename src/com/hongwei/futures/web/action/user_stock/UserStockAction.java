package com.hongwei.futures.web.action.user_stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuMessage;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StockStatusRecord;
import com.hongwei.futures.service.FuMessageService;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.StockStatusRecordService;
import com.hongwei.futures.util.Base64;
import com.hongwei.futures.util.MailUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserStockAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298775127459276666L;
	@Resource
	private FuRateService fuRateService;
	@Resource
	private FuStockAccountService fuStockAccountService;
	@Resource
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Resource
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Resource
	private StockStatusRecordService stockStatusRecordService;
	@Resource
	private FuMessageService fuMessageService;
	@Resource
	private FuSmsLogService fuSmsLogService;
	@Resource
	private MailUtil mailUtil;

	private FuAdmin admin;
	private FuUser fuUser;
	private Long userId;
	private Long id;
	private String openUser;
	private String openEquity;
	private String salesDept;
	private Integer accountType;
	private String capitalAccount;
	private String capitalPassword;
	private int operationStatus;
	private String content;
	private Integer guideTeacher;
	private Integer state;
	private Integer isDel;
	private BigDecimal money;

	/**
	 * 解套联盟首页
	 */
	@Action("stockIndex")
	public String stockIndex() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser == null ? 0L : fuUser.getId());
			map.put("isDel", 0);
			// 查询当前用户的所有未删除的股票账户
			List<Object> stockList = new ArrayList<Object>();
			List<FuStockAccount> stockAccounts = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map);
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
					objList.add(stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getManageFee() == null ? BigDecimal.ZERO : stockMoneyDetail.getManageFee());
					objList.add(fuStockAccount.getState());// 当前账户的状态
					stockList.add(objList);
					dayProfits = dayProfits.add((stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit()));
				}
			}
			// 查询当前用户的资金统计结果
			FuStockMoneyInfo fuStockMoneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(map);
			this.getActionContext().put("dayProfits", dayProfits);
			this.getActionContext().put("fuStockMoneyInfo", fuStockMoneyInfo);
			this.getActionContext().put("stockList", stockList);
			this.getActionContext().put("jsptype", "stockIndex");

			map.put("type", 3);
			List<FuMessage> messageList = fuMessageService.findAllMessage(0, Integer.MAX_VALUE, map);
			this.getActionContext().put("messageList", messageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加账号
	 * 
	 * @return
	 */
	@Action("addStock")
	public String addStock() {
		try {
			if (fuUser == null) {
				write("-1");
				return null;
			}
			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());
			// 系统参数还没有设置，请联系客服
			if (param == null) {
				write("-2");
				return null;
			}
			/*
			 * if(fuUser.getIsCheckCard()==null||fuUser.getIsCheckCard()!=2){ write("-3");//请先进行实名认证后，再进行操作 return null; }
			 */
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存股票账户
	 * 
	 * @return
	 */
	@Action("saveStockAccount")
	public String saveStockAccount() {
		try {
			FuStockAccount account = fuStockAccountService.findStockByCapitalAccount(capitalAccount);
			if (account != null) {
				if (account.getFuUser().getId() == fuUser.getId() && account.getIsDel() == 1) {// 账号是当前用户的且状态是已删除
					account.setFuUser(fuUser);
					account.setOpenUser(openUser);
					account.setOpenEquity(openEquity);
					account.setSalesDept(salesDept);
					account.setCapitalAccount(capitalAccount);
					account.setCapitalPassword(new String(Base64.encode(capitalPassword.getBytes())));
					account.setAccountType(accountType);
					// 更新时只设置更新时间
					// account.setCreateTime(new Date());
					account.setUpdateTime(new Date());
					account.setExamineStatus(0); // 未审核状态
					account.setState(0);// 开启委托
					account.setIsDel(0);// 账户已删除,重新把账号设为未删除状态
					fuStockAccountService.save(account);
				} else {// 账户是自己的且未删除 或者账号是别人的
					write("-1");
					return null;
				}
			} else {
				Date nowDate = new Date();
				FuStockAccount fuStockAccount = new FuStockAccount();
				fuStockAccount.setFuUser(fuUser);
				fuStockAccount.setOpenUser(openUser);
				fuStockAccount.setOpenEquity(openEquity);
				fuStockAccount.setSalesDept(salesDept);
				fuStockAccount.setAccountType(accountType);
				fuStockAccount.setCapitalAccount(capitalAccount);
				fuStockAccount.setCapitalPassword(new String(Base64.encode(capitalPassword.getBytes())));
				// 创建时,更新时间与创建时间相同
				fuStockAccount.setCreateTime(nowDate);
				fuStockAccount.setUpdateTime(nowDate);
				fuStockAccount.setState(0);// 开启委托
				fuStockAccount.setIsDel(0);// 未删除
				fuStockAccountService.save(fuStockAccount);
				fuSmsLogService.saveSendServiceEmail("新股票账户申请", "用户" + fuUser.getUserName() + "申请新股票账户");
			}
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 账号详情
	 * 
	 * @return
	 */
	@Action("stockInfo")
	public String stockInfo() {
		try {
			FuStockAccount fuStockAccount = fuStockAccountService.get(id);
			this.getActionContext().put("fuStockAccount", fuStockAccount);
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 未缴费用记录
	 * 
	 * @return
	 */
	@Action("stockManageRecord")
	public String stockManageRecord() {
		try {
			List<Object> objList = new ArrayList<Object>();
			List<Object[]> timeList = fuStockMoneyDetailService.findListByUser(fuUser.getId());
			for (int i = 0; i < timeList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", fuUser.getId());
				map.put("tradeTime", timeList.get(i)[0]);
				// 根据当前用户和交易时间查询到的交易明细
				List<FuStockMoneyDetail> details = fuStockMoneyDetailService.queryStockMoneyDetail(0, Integer.MAX_VALUE, map);
				List<Object> list = new ArrayList<Object>();
				list.add(timeList.get(i)[0]);
				list.add(timeList.get(i)[1]);
				list.add(details);
				objList.add(list);
			}

			// 当前用户昨日总盈利
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("userId", fuUser.getId());
			map1.put("isDel", 0);
			BigDecimal dayProfits = BigDecimal.ZERO;
			List<FuStockAccount> stockAccounts = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map1);
			if (stockAccounts.size() > 0) {
				for (FuStockAccount fuStockAccount : stockAccounts) {
					FuStockMoneyDetail stockMoneyDetail = fuStockMoneyDetailService.findMoneyDetailByAccount(fuStockAccount.getId());
					dayProfits = dayProfits.add((stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit()));
				}
			}
			FuStockMoneyInfo fuStockMoneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(map1);
			this.getActionContext().put("objList", objList);
			this.getActionContext().put("fuStockMoneyInfo", fuStockMoneyInfo);
			this.getActionContext().put("dayProfits", dayProfits);
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存委托状态
	 * 
	 * @return
	 */
	@Action("saveStockState")
	public String saveStockState() {
		try {
			// 创建一条操作记录
			StockStatusRecord stockStatus = new StockStatusRecord();
			stockStatus.setFuUser(fuUser);
			stockStatus.setFuStockAccount(fuStockAccountService.get(id));
			stockStatus.setOperationStatus(operationStatus);
			stockStatus.setAfterStatus((operationStatus == 3 ? 2 : 3));
			stockStatus.setChangeTime(new Date());
			stockStatus.setIsOperation(0);
			stockStatusRecordService.save(stockStatus);
			// 更新账户最新状态
			FuStockAccount stockAccount = fuStockAccountService.get(id);
			stockAccount.setState((operationStatus == 3 ? 2 : operationStatus == 4 ? 3 : 4));// 状态0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中，4申请结算中
			fuStockAccountService.save(stockAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 预约指导
	 * 
	 * @return
	 */
	@Action("addUserInfo")
	public String addUserInfo() {
		try {
			this.getActionContext().put("guideTeacher", guideTeacher);
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存预约指导
	 */
	@Action("saveStockMessage")
	public String saveStockMessage() {
		try {
			FuMessage fuMessage = new FuMessage();
			fuMessage.setFuUser(fuUser);
			fuMessage.setType(3);
			fuMessage.setTime(new Date());
			fuMessage.setContent(content);
			fuMessage.setGuideTeacher(guideTeacher);
			fuMessage.setState(0);
			fuMessageService.save(fuMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("stockOpenAccount")
	public String stockOpenAccount() {
		this.getActionContext().put("jsptype", "stockOpenAccount");
		return SUCCESS;
	}

	/**
	 * 累计盈亏
	 * 
	 * @return
	 */
	@Action("stockProfitRecord")
	public String stockProfitRecord() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser.getId());
			map.put("isDel", 0);
			// 查询当前用户的所有未删除的股票账户
			List<FuStockAccount> stockAccounts = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map);
			// 当前用户昨日总盈利
			BigDecimal dayProfits = BigDecimal.ZERO;
			if (stockAccounts.size() > 0) {
				for (FuStockAccount fuStockAccount : stockAccounts) {
					FuStockMoneyDetail stockMoneyDetail = fuStockMoneyDetailService.findMoneyDetailByAccount(fuStockAccount.getId());
					dayProfits = dayProfits.add((stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit()));
				}
			}

			List<FuStockMoneyDetail> details = fuStockMoneyDetailService.findDetailByUser(fuUser.getId());
			// 查询当前用户的资金统计结果
			FuStockMoneyInfo fuStockMoneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(map);
			this.getActionContext().put("dayProfits", dayProfits);
			this.getActionContext().put("fuStockMoneyInfo", fuStockMoneyInfo);
			this.getActionContext().put("details", details);
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 一键支付
	 * 
	 * @return
	 */
	@Action("oneKeyPay")
	public String oneKeyPay() {
		this.getActionContext().put("money", money);
		return SUCCESS;
	}

	/**
	 * 保存一键支付
	 */
	@Action("saveOneKeyPay")
	public String saveOneKeyPa() {
		try {
			String result = fuStockMoneyInfoService.saveOneKeyPay(fuUser, money);
			write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解套者联盟APP推广
	 * 
	 * @return
	 */
	@Action("stockSolicitation")
	public String stockSolicitation() {
		return SUCCESS;
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

	public String getOpenUser() {
		return openUser;
	}

	public void setOpenUser(String openUser) {
		this.openUser = openUser;
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

	public int getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(int operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGuideTeacher() {
		return guideTeacher;
	}

	public void setGuideTeacher(Integer guideTeacher) {
		this.guideTeacher = guideTeacher;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
