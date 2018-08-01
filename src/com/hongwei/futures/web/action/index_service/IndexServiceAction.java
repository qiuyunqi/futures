package com.hongwei.futures.web.action.index_service;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.ws.AppWebService;

@ParentPackage("api_package")
public class IndexServiceAction extends BaseAction {
	private static final long serialVersionUID = 6096399069630623887L;
	@Autowired
	private AppWebService appWebService;

	/**
	 * app登陆
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("login")
	public String login() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String password = getHttpServletRequest().getParameter("password");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.login(phone, password, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app注册
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("register")
	public String register() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String password = getHttpServletRequest().getParameter("password");
			String nickName = getHttpServletRequest().getParameter("nickName");
			String invitation_code = getHttpServletRequest().getParameter(
					"invitation_code");
			String reg_type = getHttpServletRequest().getParameter("reg_type");
			String code = getHttpServletRequest().getParameter("phone_code");
			String avatar = getHttpServletRequest().getParameter("avatar"); // 图片名称
			String avatarStr = getHttpServletRequest().getParameter(
					"avatar_str");// 图片的字节转化的字符串
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.register(phone, password, nickName,
					invitation_code, reg_type, code, avatar, avatarStr, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app主界面
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("index")
	public String index() {
		try {
			Long user_id = getHttpServletRequest().getParameter("user_id") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"user_id"));
			String weixin_code = getHttpServletRequest().getParameter(
					"weixin_code");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.index(user_id, weixin_code, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app修改密码
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("updatePwd")
	public String updatePwd() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String newpwd = getHttpServletRequest().getParameter("newpwd");
			String phone_code = getHttpServletRequest().getParameter(
					"phone_code");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.updatePwd(phone, newpwd, phone_code,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app获取用户合伙人列表
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("partners")
	public String partners() {
		try {
			Long user_id = getHttpServletRequest().getParameter("user_id") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"user_id"));
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.partners(user_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app修改密码发送手机验证码
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("updatePwdPhoneCode")
	public String updatePwdPhoneCode() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.updatePwdPhoneCode(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app注册发送短信验证码
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("regPhoneCode")
	public String regPhoneCode() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.regPhoneCode(phone, sign);
			/*
			 * JSONObject obj = new JSONObject(); JSONObject fromObject =
			 * obj.fromObject(objStr); String code =
			 * (String)fromObject.get("phone_code"); String phoneCode =
			 * phone+"#"+code;
			 * getHttpServletRequest().getSession().setAttribute("phoneCode",
			 * phoneCode);
			 * System.out.println("asdas-->"+getHttpServletRequest().getSession
			 * ().getAttribute("phoneCode"));
			 */

			/*
			 * String message = (String) fromObject.get("message"); int
			 * is_success = (Integer) fromObject.get("is_success"); JSONObject
			 * obj2 = new JSONObject(); obj2.put("is_success", is_success);
			 * obj2.put("message", message); obj2.put("phone_code", code);
			 */write(objStr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app修改提款密码
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("updateDrawPwd")
	public String updateDrawPwd() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String new_drawpwd = getHttpServletRequest().getParameter(
					"new_drawpwd");
			String phone_code = getHttpServletRequest().getParameter(
					"phone_code");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.updateDrawPwd(phone, new_drawpwd,
					phone_code, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改提款密码发送短信验证码
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("updateDrawPwdPhoneCode")
	public String updateDrawPwdPhoneCode() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.updateDrawPwdPhoneCode(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app设置个人资料
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("updateUserInfo")
	public String updateUserInfo() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String nick_name = getHttpServletRequest()
					.getParameter("nick_name");
			Integer gender = getHttpServletRequest().getParameter("gender") == null ? null
					: Integer.valueOf(getHttpServletRequest().getParameter(
							"gender"));
			Integer is_marriage = getHttpServletRequest().getParameter(
					"is_marriage") == null ? null : Integer
					.valueOf(getHttpServletRequest()
							.getParameter("is_marriage"));
			Long province_id = getHttpServletRequest().getParameter(
					"province_id") == null ? null : Long
					.valueOf(getHttpServletRequest()
							.getParameter("province_id"));
			Long city_id = getHttpServletRequest().getParameter("city_id") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"city_id"));
			String live_address = getHttpServletRequest().getParameter(
					"live_address");
			Integer max_degree = getHttpServletRequest().getParameter(
					"max_degree") == null ? null
					: Integer.valueOf(getHttpServletRequest().getParameter(
							"max_degree"));
			String business_type = getHttpServletRequest().getParameter(
					"business_type");
			String position_name = getHttpServletRequest().getParameter(
					"position_name");
			BigDecimal salary = getHttpServletRequest().getParameter("salary") == null ? null
					: (new BigDecimal(getHttpServletRequest().getParameter(
							"salary")));
			String fileByteStr = getHttpServletRequest().getParameter(
					"fileByteStr");
			String user_avatar = getHttpServletRequest().getParameter(
					"user_avatar");
			String introduction = getHttpServletRequest().getParameter(
					"introduction");
			String email = getHttpServletRequest().getParameter("email");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.updateUserInfo(phone, nick_name,
					gender, is_marriage, province_id, city_id, live_address,
					max_degree, business_type, position_name, salary,
					fileByteStr, user_avatar, introduction, email, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app实名认证
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("certificateUser")
	public String certificateUser() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String user_name = getHttpServletRequest()
					.getParameter("user_name");
			String card_number = getHttpServletRequest().getParameter(
					"card_number");
			String beforeByteStr = getHttpServletRequest().getParameter(
					"beforeByteStr");
			String card_before_pic = getHttpServletRequest().getParameter(
					"card_before_pic");
			String behindByteStr = getHttpServletRequest().getParameter(
					"behindByteStr");
			String card_behind_pic = getHttpServletRequest().getParameter(
					"card_behind_pic");
			String handByteStr = getHttpServletRequest().getParameter(
					"handByteStr");
			String card_hand_pic = getHttpServletRequest().getParameter(
					"card_hand_pic");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.certificateUser(phone, user_name,
					card_number, beforeByteStr, card_before_pic, behindByteStr,
					card_behind_pic, handByteStr, card_hand_pic, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app银行卡绑定
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("bindingCard")
	public String bindingCard() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String account_name = getHttpServletRequest().getParameter(
					"account_name");
			String bank_name = getHttpServletRequest()
					.getParameter("bank_name");
			String card_number = getHttpServletRequest().getParameter(
					"card_number");
			String bank_province = getHttpServletRequest().getParameter(
					"bank_province");
			String bank_city = getHttpServletRequest()
					.getParameter("bank_city");
			String bank_branch_name = getHttpServletRequest().getParameter(
					"bank_branch_name");
			String draw_password = getHttpServletRequest().getParameter(
					"draw_password");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.bindingCard(phone, account_name,
					bank_name, card_number, bank_province, bank_city,
					bank_branch_name, draw_password, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app资格证登记
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("qualificationRegister")
	public String qualificationRegister() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String user_name = getHttpServletRequest()
					.getParameter("user_name");
			String quali_num = getHttpServletRequest()
					.getParameter("quali_num");
			String qualiByteStr = getHttpServletRequest().getParameter(
					"qualiByteStr");
			String quali_pic = getHttpServletRequest()
					.getParameter("quali_pic");
			String quali_type = getHttpServletRequest().getParameter(
					"quali_type");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.qualificationRegister(phone,
					user_name, quali_num, qualiByteStr, quali_pic, quali_type,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app实名认证查询
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryCerti")
	public String queryCerti() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryCerti(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app银行卡绑定查询
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryBankCard")
	public String queryBankCard() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryBankCard(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app资格证查询
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryQualiNum")
	public String queryQualiNum() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryQualiNum(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app升级信息查询
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appUpgradeQuery")
	public String appUpgradeQuery() {
		try {
			String objStr = appWebService.appUpgradeQuery();
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app下载
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action(value = "index_guide", results = {
			@Result(name = "android", location = "/WEB-INF/content/index_guide/appAndroid.jsp"),
			@Result(name = "ios", location = "/WEB-INF/content/index_guide/app.jsp") })
	public String appDownload() {
		try {
			String objStr = appWebService.appDownload();
			if ("Android".equals(objStr)) {
				return "android";
			}
			if ("iOS".equals(objStr)) {
				return "ios";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		// write(objStr);
	}

	/**
	 * app快速注册
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("fastRegister")
	public String fastRegister() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String invitation_code = getHttpServletRequest().getParameter(
					"invitation_code");
			String reg_type = getHttpServletRequest().getParameter("reg_type");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.fastRegister(phone, invitation_code,
					reg_type, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app方案开户
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appSaveProgram")
	public String appSaveProgram() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String match_money = getHttpServletRequest().getParameter(
					"match_money");
			int num = getHttpServletRequest().getParameter("num") == null ? null
					: Integer.valueOf(getHttpServletRequest().getParameter(
							"num"));
			int programType = getHttpServletRequest().getParameter(
					"programType") == null ? null : Integer
					.valueOf(getHttpServletRequest()
							.getParameter("programType"));
			int tradeTimeType = getHttpServletRequest().getParameter(
					"tradeTimeType") == null ? null : Integer
					.valueOf(getHttpServletRequest().getParameter(
							"tradeTimeType"));
			int cycleNum = getHttpServletRequest().getParameter("cycleNum") == null ? null
					: Integer.valueOf(getHttpServletRequest().getParameter(
							"cycleNum"));
			String integral = getHttpServletRequest().getParameter("integral");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appSaveProgram(phone, match_money,
					num, programType, tradeTimeType, cycleNum, integral, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app追加保证金
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appAddSafeMoney")
	public String appAddSafeMoney() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			Long programId = getHttpServletRequest().getParameter("programId") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"programId"));
			String money = getHttpServletRequest().getParameter("money");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appAddSafeMoney(phone, programId,
					money, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app查询可提取的利润
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryDrawBenefits")
	public String queryDrawBenefits() {
		try {
			Long programId = getHttpServletRequest().getParameter("programId") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"programId"));
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryDrawBenefits(programId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app提取利润
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appDrawBenefits")
	public String appDrawBenefits() {
		try {
			Long programId = getHttpServletRequest().getParameter("programId") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"programId"));
			String money = getHttpServletRequest().getParameter("money");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appDrawBenefits(programId, money,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app充值
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appRechargeMoney")
	public String appRechargeMoney() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String bankId = getHttpServletRequest().getParameter("bankId");
			String money = getHttpServletRequest().getParameter("money");
			String fileByteStr = getHttpServletRequest().getParameter(
					"fileByteStr");
			String recharge_pic = getHttpServletRequest().getParameter(
					"recharge_pic");
			String recharge_id = getHttpServletRequest().getParameter(
					"recharge_id");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appRechargeMoney(phone, bankId,
					money, fileByteStr, recharge_pic, recharge_id, sign);
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
			String objStr = appWebService.appRechargeRecords(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app提现
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appDrawMoney")
	public String appDrawMoney() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String bankId = getHttpServletRequest().getParameter("bankId");
			String drawPassword = getHttpServletRequest().getParameter(
					"drawPassword");
			String phoneCode = getHttpServletRequest()
					.getParameter("phoneCode");
			String money = getHttpServletRequest().getParameter("money");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appDrawMoney(phone, bankId,
					drawPassword, phoneCode, money, sign);
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
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appDrawRecords(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app在线留言
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appMessageInfo")
	public String appMessageInfo() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			int type = getHttpServletRequest().getParameter("type") == null ? null
					: Integer.valueOf(getHttpServletRequest().getParameter(
							"type"));
			String content = getHttpServletRequest().getParameter("content");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appMessageInfo(phone, type, content,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app账户余额和可用积分
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appAccountInfo")
	public String appAccountInfo() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appAccountInfo(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app续约
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appRenewal")
	public String appRenewal() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			Long programId = getHttpServletRequest().getParameter("programId") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"programId"));
			int cycleNum = getHttpServletRequest().getParameter("cycleNum") == null ? null
					: Integer.valueOf(getHttpServletRequest().getParameter(
							"cycleNum"));
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appRenewal(phone, programId,
					cycleNum, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app注册发钱
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appRegisterIncome")
	public String appRegisterIncome() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appRegisterIncome(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app查询用户相关费率
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appQueryUserRate")
	public String appQueryUserRate() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appQueryUserRate(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app提现发送手机验证码
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appDrawMoneyPhoneCode")
	public String appDrawMoneyPhoneCode() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appDrawMoneyPhoneCode(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app查询方案
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appQueryUserPrograms")
	public String appQueryUserPrograms() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appQueryUserPrograms(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app关闭方案
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appCloseProgram")
	public String appCloseProgram() {
		try {
			Long programId = getHttpServletRequest().getParameter("programId") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"programId"));
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appCloseProgram(programId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app删除方案
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appDeleteProgram")
	public String appDeleteProgram() {
		try {
			Long programId = getHttpServletRequest().getParameter("programId") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"programId"));
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appDeleteProgram(programId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app绑定(解绑)微信号
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appBindWeixinCode")
	public String appBindWeixinCode() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String password = getHttpServletRequest().getParameter("password");
			String weixinCode = getHttpServletRequest().getParameter("weixinCode");
			String type = getHttpServletRequest().getParameter("type");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appBindWeixinCode(phone, password, weixinCode, type, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app查询省份和城市信息
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appQueryProvinceAndCity")
	public String appQueryProvinceAndCity() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appQueryProvinceAndCity(sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 绑定邮箱发送短信验证码
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("bindEmailCode")
	public String bindEmailCode() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String email = getHttpServletRequest().getParameter("email");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.bindEmailCode(phone, email, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * app查询交易中的方案
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("appQueryActivatedPrograms")
	public String appQueryActivatedPrograms() {
		try {
			String queryDate = getHttpServletRequest()
					.getParameter("queryDate");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.appQueryActivatedPrograms(queryDate,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 绑定邮箱
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("bindEmail")
	public String bindEmail() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String email = getHttpServletRequest().getParameter("email");
			String emailCode = getHttpServletRequest()
					.getParameter("emailCode");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.bindEmail(phone, email, emailCode,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 比赛数据统计
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryGameInfo")
	public String queryGameInfo() {
		try {
			String queryDate = getHttpServletRequest()
					.getParameter("queryDate");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryGameInfo(queryDate, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据手机号统计比赛数据
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryGameInfoByPhone")
	public String queryGameInfoByPhone() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryGameInfoByPhone(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 注册发钱活动有效性
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryRegisterActivity")
	public String queryRegisterActivity() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryRegisterActivity(sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询用户留言记录
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryUserMessage")
	public String queryUserMessage() {
		try {
			Long user_id = getHttpServletRequest().getParameter("user_id") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"user_id"));
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryUserMessage(user_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询手机端银联支付的tn
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryChinaPayTN")
	public String queryChinaPayTN() {
		try {
			String money = getHttpServletRequest().getParameter("money");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.queryChinaPayTN(money, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改合伙人备注名
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("updateRemarkName")
	public String updateRemarkName() {
		try {
			Long user_id = getHttpServletRequest().getParameter("user_id") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"user_id"));
			Long relevance_id = getHttpServletRequest().getParameter(
					"relevance_id") == null ? null : Long
					.valueOf(getHttpServletRequest().getParameter(
							"relevance_id"));
			String remarkName = getHttpServletRequest().getParameter(
					"remarkName");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.updateRemarkName(user_id,
					relevance_id, remarkName, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检测手机号码是否已经被注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("isRegister")
	public String isRegister() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.isRegister(phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检测用户是否报名了该次比赛
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("isRegistration")
	public String isRegistration() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String gameId = getHttpServletRequest().getParameter("gameId");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.isRegistration(phone, gameId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 报名参赛
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveGame")
	public String saveGame() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String phone = getHttpServletRequest().getParameter("phone");
			String gameId = getHttpServletRequest().getParameter("gameId");
			String competitionNum = getHttpServletRequest().getParameter(
					"competition_num");
			String objStr = appWebService.saveGame(phone, gameId,
					competitionNum, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 单独发送验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendCode")
	public String sendCode() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String phone = getHttpServletRequest().getParameter("phone");
			String ip = getHttpServletRequest().getParameter("ip");
			String objStr = appWebService.sendCode(ip, phone, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// -----------------------------------解套者联盟APP接口---------------------------------------------------------

	// 今日最新盈利+所有开户账号基本信息
	@Action("accountList")
	public String accountList() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appWebService.accountList(userId, sign);
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
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appWebService.profitOrLossDetail(userId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 应缴明细
	@Action("payAble")
	public String payAbleDetail() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appWebService.payAbleDetail(userId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 未缴费用记录明细
	@Action("noPayAble")
	public String noPayAbleDateil() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appWebService.noPayAbleDateil(userId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 根据账号id 查询账号信息
	@Action("accountDateil")
	public String accountDatil() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String stockId = getHttpServletRequest().getParameter("id");
			String objStr = appWebService.accountDatil(stockId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 删除账号的信息 isDel = 0
	@Action("delAccount")
	public String delAccount() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String stockId = getHttpServletRequest().getParameter("id");
			String isDel = getHttpServletRequest().getParameter("is_del");
			String objStr = appWebService.delAccount(stockId, isDel, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 添加一个账号信息
	@Action("addAccountInfo")
	public String addAccountInfo() {
		try {
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
					"partnerAccount");
			// 股票类型参数
			String accountType = getHttpServletRequest().getParameter(
					"account_type");
			String objStr = appWebService.addAccountInfo(userId, openUser,
					openEquity, salesDept, capitalAccount, capitalPassword,
					partnerAccount, accountType, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 点击按钮 改变当前用户状态
	@Action("addStatusRecord")
	public String addStatusRecord() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String accountId = getHttpServletRequest().getParameter(
					"account_id");
			String objStr = appWebService.addStatusRecord(accountId, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ---------------------END----------------------------------------
	// 资产明细
	@Action("assetsAll")
	public String assetsAll() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String objStr = appWebService.assetsAll(user_id, sign);
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
			String user_id = getHttpServletRequest().getParameter("user_id");
			String objStr = appWebService.inComeDetail(user_id, sign);
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
			String user_id = getHttpServletRequest().getParameter("user_id");
			String objStr = appWebService.outComeDetail(user_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 手机号码和邀请码是否存在
	@Action("isPhoneAndInvitationCode")
	public String isPhoneAndInvitationCode() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String phone = getHttpServletRequest().getParameter("phone");
			String invitationCode = getHttpServletRequest().getParameter(
					"invitation_code");
			String objStr = appWebService.isPhoneAndInvitationCode(phone,
					invitationCode, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 验证验证码和手机是否匹配
	@Action("isCheckCode")
	public String isCheckCode() {
		try {
			String phone = getHttpServletRequest().getParameter("phone");
			String phoneCode = getHttpServletRequest().getParameter(
					"phone_code");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appWebService.isCheckCode(phone, phoneCode, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 支付宝支付通知
	@SuppressWarnings("rawtypes")
	@Action("notify_url")
	public String notify_url() {
		try {
			String user_id = getHttpServletRequest().getParameter("body");
			String out_trade_no = getHttpServletRequest().getParameter(
					"out_trade_no");
			String trade_no = getHttpServletRequest().getParameter("trade_no");
			String trade_status = getHttpServletRequest().getParameter(
					"trade_status");
			String total_fee = getHttpServletRequest()
					.getParameter("total_fee");
			Map requestParams = getHttpServletRequest().getParameterMap();
			String objStr = appWebService.notify_url(user_id, out_trade_no,
					trade_no, trade_status, total_fee, requestParams);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
