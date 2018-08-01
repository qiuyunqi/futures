package com.hongwei.futures.web.action.user_recharge;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class ChinaBackAction extends BaseAction {
	@Autowired
	private FuRechargeService fuRechargeService;
	@Autowired
	private FuAdminService fuAdminService;

	private String merid;
	private String transdate;
	private String orderno;
	private String transtype;
	private String amount;
	private String currencycode;
	private String status;
	private String checkvalue;

	/**
	 * 银联支付反馈的后台地址
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("bgRet")
	public String bgRet() {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink t;

		boolean flag = key.buildKey("999999999999999", 0, this.getServletContext().getRealPath(Property.getProperty("PUBLIC_KEY_PATH")));
		if (flag == false) {
			return SUCCESS;
		}
		t = new chinapay.SecureLink(key);
		boolean flag1 = t.verifyTransResponse(merid, orderno, amount, currencycode, transdate, transtype, status, checkvalue);

		if (flag1 == false) {

		} else if (status.equals("1001")) {
			String id = orderno.substring(9);
			FuRecharge recharge = fuRechargeService.get(Long.parseLong(id));
			try {
				if (recharge != null && recharge.getRechargeStatus() == 0) {// 如果状态还是未审核
					recharge.setPayStatus(1);
					recharge.setOrderNum(orderno);
					recharge.setPayTime(new Date());
					recharge.setRechargeStatus(2);// 通过
					recharge.setFuAdmin(fuAdminService.get(1L));
					recharge.setCheckTime(new Date());
					FuUser user = recharge.getFuUser();
					// 充值总额
					user.setRechargeMoney((user.getRechargeMoney() == null ? new BigDecimal(0.00) : user.getRechargeMoney()).add(recharge.getRechargeMoney()));
					user.setAccountBalance(user.getAccountBalance().add(recharge.getRechargeMoney()));
					fuRechargeService.saveRecharge(recharge, user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (!status.equals("1001")) {
			String id = orderno.substring(9);
			FuRecharge recharge = fuRechargeService.get(Long.parseLong(id));
			try {
				if (recharge != null && recharge.getRechargeStatus() == 0) {// 如果状态还是未审核
					recharge.setPayStatus(0);
					recharge.setPayTime(null);
					recharge.setRechargeStatus(3);// 拒绝
					recharge.setFuAdmin(fuAdminService.get(1L));
					recharge.setCheckTime(new Date());
					fuRechargeService.save(recharge);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 银联支付反馈的前台地址
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "pageRet", results = { @Result(type = "redirect", location = "/user_recharge/rechargeList.htm") })
	public String pageRet() {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink t;
		t = new chinapay.SecureLink(key);

		boolean flag = key.buildKey("999999999999999", 0, this.getServletContext().getRealPath(Property.getProperty("PUBLIC_KEY_PATH")));
		if (flag == false) {
			return SUCCESS;
		}

		boolean flag1 = t.verifyTransResponse(merid, orderno, amount, currencycode, transdate, transtype, status, checkvalue);
		if (flag1 == false) {

		} else if (status.equals("1001")) {
			String id = orderno.substring(9);
			FuRecharge recharge = fuRechargeService.get(Long.parseLong(id));
			try {
				if (recharge != null && recharge.getRechargeStatus() == 0) {// 如果状态还是未审核
					recharge.setPayStatus(1);
					recharge.setOrderNum(orderno);
					recharge.setPayTime(new Date());
					recharge.setRechargeStatus(2);// 通过
					recharge.setFuAdmin(fuAdminService.get(1L));
					recharge.setCheckTime(new Date());
					FuUser user = recharge.getFuUser();
					// 充值总额
					user.setRechargeMoney((user.getRechargeMoney() == null ? new BigDecimal(0.00) : user.getRechargeMoney()).add(recharge.getRechargeMoney()));
					user.setAccountBalance(user.getAccountBalance().add(recharge.getRechargeMoney()));
					fuRechargeService.saveRecharge(recharge, user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (!status.equals("1001")) {
			String id = orderno.substring(9);
			FuRecharge recharge = fuRechargeService.get(Long.parseLong(id));
			try {
				if (recharge != null && recharge.getRechargeStatus() == 0) {// 如果状态还是未审核
					recharge.setPayStatus(0);
					recharge.setPayTime(null);
					recharge.setRechargeStatus(3);// 拒绝
					recharge.setFuAdmin(fuAdminService.get(1L));
					recharge.setCheckTime(new Date());
					fuRechargeService.save(recharge);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/*
	 * @Action(value = "queryOrderIsSucces", results = { @Result(type =
	 * "redirect", location = "/user_recharge/rechargeList.htm") }) public
	 * String queryOrderIsSucces() throws Exception { try { String id =
	 * orderno.substring(9); FuRecharge recharge =
	 * fuRechargeService.get(Long.parseLong(id)); String result =
	 * ChinaPayUtil.querySend(recharge); System.out.println(result); return
	 * SUCCESS; } catch (Exception e) { e.printStackTrace(); } return null; }
	 */

	public static void main(String args[]) {
		Long l = Long.parseLong("0000022");
		System.out.println(l);
	}

	public FuRechargeService getFuRechargeService() {
		return fuRechargeService;
	}

	public void setFuRechargeService(FuRechargeService fuRechargeService) {
		this.fuRechargeService = fuRechargeService;
	}

	public FuAdminService getFuAdminService() {
		return fuAdminService;
	}

	public void setFuAdminService(FuAdminService fuAdminService) {
		this.fuAdminService = fuAdminService;
	}

	public String getMerid() {
		return merid;
	}

	public void setMerid(String merid) {
		this.merid = merid;
	}

	public String getTransdate() {
		return transdate;
	}

	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckvalue() {
		return checkvalue;
	}

	public void setCheckvalue(String checkvalue) {
		this.checkvalue = checkvalue;
	}

}
