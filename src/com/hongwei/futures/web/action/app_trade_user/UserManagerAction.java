package com.hongwei.futures.web.action.app_trade_user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.modulesforapp.usermanage.AppTradeUserManagerService;

@ParentPackage("api_package")
public class UserManagerAction extends BaseAction {

	private static final long serialVersionUID = -3051291831913394444L;

	@Autowired
	private AppTradeUserManagerService userManagerService;

	/**
	 * APP银联手续费率
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("appUnionPayRecharge")
	public String appUnionPayRecharge() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = userManagerService.appUnionPayRecharge(userId,
					sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 合伙人列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("partnersList")
	public String partnersList() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String curPage = getHttpServletRequest().getParameter("cur_page");
			String pageSize = getHttpServletRequest().getParameter("page_size");
			String objStr = userManagerService.partnersList(userId, curPage, pageSize, sign, version);
//			String objStr = userManagerService.partnersList(userId, sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除银行卡
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("deleteCard")
	public String deleteCard() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String cardNum = getHttpServletRequest().getParameter("card_num");
			String objStr = userManagerService.deleteCard(userId, cardNum,
					sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实名认证的发送验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendRealCode")
	public String sendRealCode() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String phone = getHttpServletRequest().getParameter("phone");
			String objStr = userManagerService.sendRealCode(phone, version,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实名认证第一步
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("realCertification")
	public String realCertification() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String userName = getHttpServletRequest().getParameter("user_name");
			// 身份证
			String indentityCard = getHttpServletRequest().getParameter(
					"indentity_card");
			String phone = getHttpServletRequest().getParameter("phone");
			String phoneCode = getHttpServletRequest().getParameter(
					"phone_code");

			String objStr = userManagerService.realCertification(userId,
					userName, indentityCard, phone, phoneCode, sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实名认证第二步 判断两次输入的交易密码是否是一致
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("confirmDrawPwd")
	public String confirmDrawPwd() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String drawPwd1 = getHttpServletRequest().getParameter("draw_pwd1");
			String drawPwd2 = getHttpServletRequest().getParameter("draw_pwd2");
			String objStr = userManagerService.confirmDrawPwd(drawPwd1,
					drawPwd2, sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实名认证第三步 绑定银行卡
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("bindCard")
	public String bindCard() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String userName = getHttpServletRequest().getParameter("user_name");
			// 身份证
			String indentityCard = getHttpServletRequest().getParameter(
					"indentity_card");
			String phone = getHttpServletRequest().getParameter("phone");
			String phoneCode = getHttpServletRequest().getParameter(
					"phone_code");
			String drawPwd1 = getHttpServletRequest().getParameter("draw_pwd1");
			String drawPwd2 = getHttpServletRequest().getParameter("draw_pwd2");
			// 持卡人
			String userCard = getHttpServletRequest().getParameter("user_card");
			// 银行卡号
			String cardNum = getHttpServletRequest().getParameter("card_num");
			String bankName = getHttpServletRequest().getParameter("bank_name");
			// 卡户银行地址
			String bankProvince = getHttpServletRequest().getParameter(
					"bank_province");
			String bankCity = getHttpServletRequest().getParameter("bank_city");
			String bankBranchName = getHttpServletRequest().getParameter(
					"bank_branch_name");

			String objStr = userManagerService.bindCard(userId, userName,
					indentityCard, phone, phoneCode, drawPwd1, drawPwd2,
					userCard, cardNum, bankName, bankProvince, bankCity,
					bankBranchName, sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 判断用户输入的交易密码是否是正确的
	@Action("checkDrawPwd")
	public String checkDrawPwd() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String drawPwd = getHttpServletRequest().getParameter("draw_pwd");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = userManagerService.checkDrawPwd(userId, drawPwd,
					version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 根据userId获取用户的基本信息
	@Action("userInfo")
	public String userInfo() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = userManagerService.getUserInfo(userId, version,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 资产明细
	@Action("assetsAll")
	public String assetsAll() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String curPage = getHttpServletRequest().getParameter("cur_page");
			String pageSize = getHttpServletRequest().getParameter("page_size");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = userManagerService.assetsAll(userId, curPage, pageSize, version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 收入明细
	@Action("inComeDetail")
	public String inComeDetail() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String curPage = getHttpServletRequest().getParameter("cur_page");
			String pageSize = getHttpServletRequest().getParameter("page_size");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = userManagerService.inComeDetail(userId, curPage, pageSize, version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 支出明细
	@Action("outComeDetail")
	public String outComeDetail() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String curPage = getHttpServletRequest().getParameter("cur_page");
			String pageSize = getHttpServletRequest().getParameter("page_size");
			String objStr = userManagerService.outComeDetail(userId, curPage, pageSize, version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 提取现金
	@Action("appDrawMoney")
	public String appDrawMoney() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			// 银行卡号
			String cardNum = getHttpServletRequest().getParameter("card_num");
			String money = getHttpServletRequest().getParameter("money");
			String drawPwd = getHttpServletRequest().getParameter("draw_pwd");
			String objStr = userManagerService.appDrawMoney(userId, drawPwd,
					cardNum, money, version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 绑定银行卡
	@Action("addBindCard")
	public String addBindCard() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String drawPwd = getHttpServletRequest().getParameter("draw_pwd");
			// 持卡人
			String userCard = getHttpServletRequest().getParameter("user_card");
			// 银行卡号
			String cardNum = getHttpServletRequest().getParameter("card_num");
			String bankName = getHttpServletRequest().getParameter("bank_name");
			// 卡户银行地址
			String bankProvince = getHttpServletRequest().getParameter(
					"bank_province");
			String bankCity = getHttpServletRequest().getParameter("bank_city");
			String bankBranchName = getHttpServletRequest().getParameter(
					"bank_branch_name");
			String objStr = userManagerService.addBindCard(userId, drawPwd,
					userCard, cardNum, bankName, bankProvince, bankCity,
					bankBranchName, version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app充值记录
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appRechargeRecords")
	public String appRechargeRecords() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = userManagerService.appRechargeRecords(phone, sign,
					version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app 预约开户的基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("appointment")
	public String appointment() {
		try {
			String filePath = getServletContext().getRealPath(
					"appointment.json");
			System.out.println(filePath);
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = userManagerService.appointment(sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app提现记录
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appDrawRecords")
	public String appDrawRecords() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String version = getHttpServletRequest().getParameter("version");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = userManagerService.appDrawRecords(phone, version,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询全部的银行名称
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("getAllBankName")
	public String getAllBankName() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = userManagerService.getAllBankName(version, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
