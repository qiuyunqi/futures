package com.hongwei.futures.web.action.app_trade_unrelieve;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.modulesforapp.unrelieve.AppTradeUnrelieveService;

/**
 * 解套者联盟
 * 
 * @author han
 * 
 */
@ParentPackage("api_package")
public class AppTradeUnrelieveAction extends BaseAction {

	private static final long serialVersionUID = 5765812613735925802L;
	@Autowired
	private AppTradeUnrelieveService appTradeUnrelieveService;

	// 昨日盈亏 自己的账户基本信息 累积盈利 未缴管理费
	@Action("accountList")
	public String accountList() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeUnrelieveService.accountList(userId,
					version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 一键缴费
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("keyPayment")
	public String keyPayment() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeUnrelieveService.keyPayment(userId,
					version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加解套者的账号
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("addAccountInfo")
	public String addAccountInfo() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String openUser = getHttpServletRequest().getParameter("open_user");
			String openEquity = getHttpServletRequest().getParameter(
					"open_equity");
			String salesDept = getHttpServletRequest().getParameter(
					"sales_dept");
			String capitalAccount = getHttpServletRequest().getParameter(
					"capital_account");
			String capitalPassword = getHttpServletRequest().getParameter(
					"capital_password");
			String partnerAccount = getHttpServletRequest().getParameter(
					"partner_account");
			// 股票类型参数
			String accountType = getHttpServletRequest().getParameter(
					"account_type");
			String objStr = appTradeUnrelieveService
					.addAccountInfo(userId, openUser, openEquity, salesDept,
							capitalAccount, capitalPassword, partnerAccount,
							accountType, version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 每日盈亏流水
	@Action("profitOrLossDetail")
	public String prifitOrLossDetail() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeUnrelieveService.profitOrLossDetail(userId,
					version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查询费用
	@Action("cost")
	public String cost() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeUnrelieveService
					.cost(userId, sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 已缴费用
	@Action("feesPaid")
	public String feesPaid() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeUnrelieveService.feesPaid(userId, sign,
					version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 应缴费用
	@Action("noFeesPaid")
	public String noFeesPaid() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeUnrelieveService.noFeesPaid(userId, sign,
					version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 根据 id 查询账号具体信息
	@Action("accountDetail")
	public String accountDetail() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String stockId = getHttpServletRequest().getParameter("id");
			String objStr = appTradeUnrelieveService.accountDetail(stockId,
					version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
