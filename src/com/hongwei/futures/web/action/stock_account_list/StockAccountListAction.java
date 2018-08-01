package com.hongwei.futures.web.action.stock_account_list;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuExamine;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StockShare;
import com.hongwei.futures.service.FuExamineService;
import com.hongwei.futures.service.FuOperationService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.StockShareService;
import com.hongwei.futures.util.Base64;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class StockAccountListAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private StockShareService stockShareService;
	@Autowired
	private FuOperationService fuOperationService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuExamineService fuExamineService;
	@Autowired
	private FuTransactionService fuTransactionService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private Integer totalCount;
	private Long accountUserId;
	private String capitalAccount;
	private Integer state;
	private String phone;
	private String userName;
	private Integer isDel;
	private Date time1;
	private Date time2;
	private String openUser;
	private String openEquity;
	private String salesDept;
	private Integer accountType;
	private String capitalPassword;
	private Integer examineStatus;
	private Long transactionId;
	private Integer profitModel;
	private BigDecimal available;
	private BigDecimal ableMoney;
	private String availableSplit;
	private String ableMoneySplit;
	private String commission;
	private Long shareId;
	private String shareName;
	private String shareCode;
	private Integer shareNumber;
	private Integer isPublish;
	private String createTimeUp;
	private String createTimeDown;
	private String updateTimeUp;
	private String updateTimeDown;
	private Integer isPayMargin;
	private String message;

	@Action("accountList")
	public String accountList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (accountUserId != null) {
				map.put("accountUserId", accountUserId);
			}
			if (!StringUtil.isBlank(capitalAccount)) {
				map.put("capitalAccount", capitalAccount);
			}
			if (state != null) {
				map.put("state", state);
			}
			if (isDel != null) {
				map.put("isDel", isDel);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			if (isPublish != null) {
				map.put("isPublish", isPublish);
			}
			if (!StringUtil.isBlank(createTimeUp)) {
				map.put("createTimeUp", createTimeUp);
			}
			if (!StringUtil.isBlank(createTimeDown)) {
				map.put("createTimeDown", createTimeDown);
			}
			if (!StringUtil.isBlank(updateTimeUp)) {
				map.put("updateTimeUp", updateTimeUp);
			}
			if (!StringUtil.isBlank(updateTimeDown)) {
				map.put("updateTimeDown", updateTimeDown);
			}
			if (totalCount == null) {
				totalCount = fuStockAccountService.countAccounts(map);
			}
			List<Object> stockList = new ArrayList<Object>();
			List<FuStockAccount> accountList = fuStockAccountService.queryAccountList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			for (FuStockAccount fuStockAccount : accountList) {
				List<Object> objList = new ArrayList<Object>();
				objList.add(fuStockAccount.getFuUser() == null ? "" : fuStockAccount.getFuUser().getId());
				objList.add(fuStockAccount.getOpenUser());
				objList.add(fuStockAccount.getOpenEquity());
				objList.add(fuStockAccount.getSalesDept());
				objList.add(fuStockAccount.getCapitalAccount());
				objList.add(fuStockAccount.getAccountType());
				objList.add(fuStockAccount.getCapitalPassword() == null ? "" : new String(Base64.decode(fuStockAccount.getCapitalPassword())));
				objList.add(fuStockAccount.getCreateTime());
				objList.add(fuStockAccount.getIsDel());
				objList.add(fuStockAccount.getFuUser() == null ? "" : fuStockAccount.getFuUser().getPhone());
				objList.add(fuStockAccount.getFuUser() == null ? "" : fuStockAccount.getFuUser().getUserName());
				objList.add(fuStockAccount.getState());
				objList.add(fuStockAccount.getId());
				objList.add(fuStockAccount.getExamineStatus() == null ? 0 : fuStockAccount.getExamineStatus());
				objList.add(fuStockAccount.getUpdateTime());
				objList.add(fuStockAccount.getIsPublish() == null ? 0 : fuStockAccount.getIsPublish());
				objList.add(fuStockAccount.getIsPayMargin() == null ? 0 : fuStockAccount.getIsPayMargin());
				stockList.add(objList);
			}
			this.getActionContext().put("stockList", stockList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("newAccountAjax")
	public String newAccountAjax() {
		try {
			FuStockAccount stock = fuStockAccountService.get(id);
			List<FuTransaction> baseList = fuTransactionService.findBaseTransactions();
			this.getActionContext().put("baseList", baseList);
			this.getActionContext().put("stock", stock);
			this.getActionContext().put("capitalPassword", stock.getCapitalPassword() == null ? "" : new String(Base64.decode(stock.getCapitalPassword())));
			this.getActionContext().put("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 进入审核页面
	 * 
	 * @return
	 */
	@Action("newAccountInfoAjax")
	public String newAccountInfoAjax() {
		try {
			FuStockAccount stock = fuStockAccountService.get(id);
			List<FuTransaction> baseList = fuTransactionService.findBaseTransactions();
			this.getActionContext().put("baseList", baseList);
			this.getActionContext().put("stock", stock);
			this.getActionContext().put("capitalPassword", stock.getCapitalPassword() == null ? "" : new String(Base64.decode(stock.getCapitalPassword())));
			this.getActionContext().put("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 新增股票信息
	@Action("saveStockShare")
	public String saveStockShare() {
		try {
			StockShare share = new StockShare();
			share.setName(shareName);
			share.setCode(shareCode);
			share.setNumber(shareNumber);
			share.setFuStockAccount(fuStockAccountService.get(id));
			stockShareService.save(share);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 编辑股票信息
	@Action("editStockShare")
	public String editStockShare() {
		try {
			StockShare share = stockShareService.get(shareId);
			share.setName(shareName);
			share.setCode(shareCode);
			share.setNumber(shareNumber);
			stockShareService.save(share);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 删除股票记录
	@Action("deleteStockShare")
	public String deleteStockShare() {
		try {
			stockShareService.delete(shareId);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查询股票记录
	@Action("queryStockShare")
	public String queryStockShare() {
		try {
			StockShare share = stockShareService.get(shareId);
			write(share.getName() + "," + share.getCode() + "," + share.getNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存修改账户
	 * 
	 * @return
	 */
	@Action("saveUpdateStock")
	public String saveUpdateStock() {
		try {
			FuStockAccount fuStockAccount = fuStockAccountService.get(id);
			FuUser user = fuStockAccount.getFuUser();
			JSONObject obj = new JSONObject();
			obj.put("openUser", fuStockAccount.getOpenUser());
			obj.put("openEquity", fuStockAccount.getOpenEquity());
			obj.put("salesDept", fuStockAccount.getSalesDept());
			obj.put("capitalAccount", fuStockAccount.getCapitalAccount());
			obj.put("capitalPassword", fuStockAccount.getCapitalPassword());
			obj.put("accountType", fuStockAccount.getAccountType());
			obj.put("state", fuStockAccount.getState());
			obj.put("isDel", fuStockAccount.getIsDel());
			String beforeContent = obj.toString();

			fuStockAccount.setOpenUser(openUser);
			fuStockAccount.setOpenEquity(openEquity);
			if (salesDept != null && !"".equals(salesDept)) {
				fuStockAccount.setSalesDept(salesDept);
			}
			fuStockAccount.setMessage(message);
			fuStockAccount.setUpdateTime(new Date());
			fuStockAccount.setIsPayMargin(isPayMargin);
			if (examineStatus != null) {
				fuStockAccount.setExamineStatus(examineStatus);
			}
			// 接单中修改后变为接单成功
			/*
			 * if (examineStatus == null && fuStockAccount.getExamineStatus() ==
			 * 3) { fuStockAccount.setExamineStatus(4); }
			 */
			if (accountType != null) {
				fuStockAccount.setAccountType(accountType);
			}
			if (!StringUtil.isBlank(capitalAccount)) {
				fuStockAccount.setCapitalAccount(capitalAccount);
			}
			if (!StringUtil.isBlank(capitalPassword)) {
				fuStockAccount.setCapitalPassword(new String(Base64.encode(capitalPassword.getBytes())));
			}
			if (state != null) {
				fuStockAccount.setState(state);
			}
			if (isDel != null) {
				fuStockAccount.setIsDel(isDel);
			}
			if (available != null) {
				fuStockAccount.setAvailable(available);
			}
			if (ableMoney != null) {
				fuStockAccount.setAbleMoney(ableMoney);
			}
			if (!StringUtil.isBlank(availableSplit)) {
				fuStockAccount.setAvailableSplit(availableSplit);
			}
			if (!StringUtil.isBlank(ableMoneySplit)) {
				fuStockAccount.setAbleMoneySplit(ableMoneySplit);
			}
			if (transactionId != null && !"".equals(transactionId)) {
				FuTransaction fuTransaction = fuTransactionService.get(transactionId);
				fuStockAccount.setTransactionId(transactionId);
				fuStockAccount.setExamineStatus(4);
				fuStockAccount.setTransactionStatus(0);
				fuStockAccountService.saveAccountAndRecord(fuStockAccount, user, 0, fuTransaction);
				isPublish = 1;
			}
			if (profitModel != null) {
				fuStockAccount.setProfitModel(profitModel);
			}
			if (!StringUtil.isBlank(commission)) {
				fuStockAccount.setCommission(commission);
			}
			if (null != isPublish) {
				fuStockAccount.setIsPublish(isPublish);
			}
			if (null != openUser && !"".equals(openUser)) {
				fuStockAccount.setOpenUser(openUser);
			}
			fuStockAccountService.save(fuStockAccount);

			// 审核流水
			FuExamine examine = new FuExamine();
			examine.setStockAccountId(id);
			examine.setFirstTime(new Date());
			examine.setFirstUser(admin.getName());
			fuExamineService.save(examine);

			// 仅当初审成功或初审失败时发送短信至用户
			if (examineStatus != null && (examineStatus == 1 || examineStatus == 2)) {
				FuSmsLog log = new FuSmsLog();
				log.setFuUser(user);
				log.setDestination(user.getPhone());
				log.setRegCode(null);
				log.setReason("股票账户审核");
				log.setPrio(1);
				log.setContent(examineStatus == 1 ? "您好，您上传到余券宝的股票账户已经通过初步审核，请打开APP完善账户信息。祝您生活愉快！" : "您好，您上传到余券宝的股票账户未通过初步审核，请打开APP重新上传股票信息。祝您生活愉快！");
				log.setState(0);
				log.setPlanTime(new Date());
				log.setType(1);
				fuSmsLogService.save(log);
			}
			JSONObject obj2 = new JSONObject();
			obj2.put("openUser", fuStockAccount.getOpenUser());
			obj2.put("openEquity", fuStockAccount.getOpenEquity());
			obj2.put("salesDept", fuStockAccount.getSalesDept());
			obj2.put("capitalAccount", fuStockAccount.getCapitalAccount());
			obj2.put("capitalPassword", fuStockAccount.getCapitalPassword());
			obj2.put("accountType", fuStockAccount.getAccountType());
			obj2.put("state", fuStockAccount.getState());
			obj2.put("isDel", fuStockAccount.getIsDel());
			String afterContent = obj2.toString();
			fuOperationService.saveOperationRecord("股票", "解套者账户统计", 2, fuStockAccount.getId(), beforeContent, afterContent, admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 股票列表
	 * 
	 * @return
	 */
	@Action("stockShareList")
	public String stockShareList() {
		try {
			JSONArray jsonArr = new JSONArray();
			List<StockShare> shareList = stockShareService.findStockShareByAccount(id);
			if (shareList != null && shareList.size() != 0) {
				for (int i = 0; i < shareList.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("SHARE_ID", shareList.get(i).getId() == null ? "" : shareList.get(i).getId());
					obj.put("SHARE_NAME", shareList.get(i).getName() == null ? "" : shareList.get(i).getName());
					obj.put("SHARE_CODE", shareList.get(i).getCode() == null ? "" : shareList.get(i).getCode());
					obj.put("SHARE_NUMBER", shareList.get(i).getNumber() == null ? "" : shareList.get(i).getNumber());
					jsonArr.add(obj);
				}
			}
			write(jsonArr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getAccountUserId() {
		return accountUserId;
	}

	public void setAccountUserId(Long accountUserId) {
		this.accountUserId = accountUserId;
	}

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
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

	public String getCapitalPassword() {
		return capitalPassword;
	}

	public void setCapitalPassword(String capitalPassword) {
		this.capitalPassword = capitalPassword;
	}

	public Integer getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(Integer examineStatus) {
		this.examineStatus = examineStatus;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getProfitModel() {
		return profitModel;
	}

	public void setProfitModel(Integer profitModel) {
		this.profitModel = profitModel;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public BigDecimal getAbleMoney() {
		return ableMoney;
	}

	public void setAbleMoney(BigDecimal ableMoney) {
		this.ableMoney = ableMoney;
	}

	public String getAvailableSplit() {
		return availableSplit;
	}

	public void setAvailableSplit(String availableSplit) {
		this.availableSplit = availableSplit;
	}

	public String getAbleMoneySplit() {
		return ableMoneySplit;
	}

	public void setAbleMoneySplit(String ableMoneySplit) {
		this.ableMoneySplit = ableMoneySplit;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public Integer getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(Integer shareNumber) {
		this.shareNumber = shareNumber;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public String getCreateTimeUp() {
		return createTimeUp;
	}

	public void setCreateTimeUp(String createTimeUp) {
		this.createTimeUp = createTimeUp;
	}

	public String getCreateTimeDown() {
		return createTimeDown;
	}

	public void setCreateTimeDown(String createTimeDown) {
		this.createTimeDown = createTimeDown;
	}

	public String getUpdateTimeUp() {
		return updateTimeUp;
	}

	public void setUpdateTimeUp(String updateTimeUp) {
		this.updateTimeUp = updateTimeUp;
	}

	public String getUpdateTimeDown() {
		return updateTimeDown;
	}

	public void setUpdateTimeDown(String updateTimeDown) {
		this.updateTimeDown = updateTimeDown;
	}

	public Integer getIsPayMargin() {
		return isPayMargin;
	}

	public void setIsPayMargin(Integer isPayMargin) {
		this.isPayMargin = isPayMargin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
