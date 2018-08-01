package com.hongwei.futures.web.modulesforapp.usermanage.impl;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAppointmentInfo;
import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.model.FuDrawMoney;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrRemark;
import com.hongwei.futures.model.HhrStat;
import com.hongwei.futures.model.SysBank;
import com.hongwei.futures.model.SysBranch;
import com.hongwei.futures.model.SysCity;
import com.hongwei.futures.model.SysProvince;
import com.hongwei.futures.service.FuAppointmentInfoService;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.service.FuDrawMoneyService;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrRemarkService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.service.SysBankService;
import com.hongwei.futures.service.SysBranchService;
import com.hongwei.futures.service.SysCityService;
import com.hongwei.futures.service.SysProvinceService;
import com.hongwei.futures.util.ComParatorHhrUtil;
import com.hongwei.futures.util.ConfigureUtil;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.DesUtil;
import com.hongwei.futures.web.modulesforapp.usermanage.AppTradeUserManagerService;

public class AppTradeUserManagerServiceImpl implements AppTradeUserManagerService {
	protected final Log log = LogFactory.getLog(AppTradeUserManagerServiceImpl.class); 
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private HhrStatService hhrStatService;
	@Autowired
	private HhrRemarkService hhrRemarkService;
	@Autowired
	private FuBankCardService fuBankCardService; 
	@Autowired
	private SysBankService sysBankService;
	@Autowired
	private SysProvinceService  sysProvinceService;
	@Autowired
	private SysCityService  sysCityService;
	@Autowired
	private SysBranchService sysBranchService;
	@Autowired
	private FuSmsLogService	fuSmsLogService;
	@Autowired
	private FuMoneyDetailService  fuMoneyDetailService;
	@Autowired
	private FuDrawMoneyService fuDrawMoneyService;
	@Autowired
	private FuRechargeService fuRechargeService;
	@Autowired
	private FuAppointmentInfoService appointmentInfoService;
	
//	银联充值
	public String appUnionPayRecharge(String userId, String sign, String version) {
//		String result = DesUtil.webserviceSignVerify(sign);
		JSONObject obj = new JSONObject();
		try {
			
//			if("success".equals(result)){
				if("2.0.0".equals(version)) {
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser) {
						obj.put("is_success", 0);
						obj.put("message", "参数不正确");
						System.out.println(obj.toString()+ "查询的用户为空");
						return obj.toString();
					}
					// 余额
					obj.put("account_balance", fuUser.getAccountBalance());
					
					Properties properties = ConfigureUtil.getValue("appMessage.properties");
					Object counter_fee = properties.get("counterFee");
					Object alterMessage = properties.get("alertMessage");
					// 手续费率
					obj.put("counter_fee", counter_fee);
					obj.put("alter_message", alterMessage);
					// 温馨提示信息
					obj.put("is_success", 1);
					obj.put("message", "查询银联信息成功");
					
				}
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj.toString());
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	public String partnersList(String userId, String curPage, String pageSize, String sign, String version) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
				
					if (null == curPage || "".equals(curPage)) {
						curPage = "0";
					}
					
					if (null == pageSize || "".equals(pageSize)) {
						pageSize = "10000";
					}
					//List<HhrStat> stat = hhrStatService.findUserByParentId(Long.parseLong(userId));
					List<HhrStat> stat = hhrStatService.findUserByParentId(Long.parseLong(userId), Integer.parseInt(curPage), Integer.parseInt(pageSize)); 
					List<Object> partners = new ArrayList<Object>();
					if (null != stat && stat.size() > 0) {
						for (HhrStat hhrStat : stat) {
							Map<String, Object> map2 = new HashMap<String, Object>();
							map2.put("id", hhrStat.getId());
							map2.put("account_name", hhrStat.getFuUser().getAccountName());
							map2.put("user_id", hhrStat.getFuUser().getId());
							map2.put("firstly_partner_num", hhrStat.getFirstlyPartnerNum()==null?"0":hhrStat.getFirstlyPartnerNum());
							map2.put("secondary_partner_num", hhrStat.getSecondaryPartnerNum()==null?"0":hhrStat.getSecondaryPartnerNum());
							map2.put("monthly_stock_endowment", hhrStat.getMonthlyStockEndowment()==null?"0":hhrStat.getMonthlyStockEndowment());
							map2.put("monthly_futures_endowment", hhrStat.getMonthlyFuturesEndowment()==null?"0":hhrStat.getMonthlyFuturesEndowment());
							map2.put("nick_name", hhrStat.getFuUser().getNickName()==null?"":hhrStat.getFuUser().getNickName());
							map2.put("user_avatar", hhrStat.getFuUser().getUserAvatar()==null?"":hhrStat.getFuUser().getUserAvatar());
							HhrRemark hhrRemark = hhrRemarkService.findRemarkByUserId(Long.parseLong(userId), hhrStat.getFuUser().getId());
							map2.put("remark_name", hhrRemark==null?"":hhrRemark.getRemarkName());
							map2.put("phone", hhrStat.getFuUser().getPhone()==null?"":hhrStat.getFuUser().getPhone());
							map2.put("introduction", hhrStat.getFuUser().getIntroduction()==null?"":hhrStat.getFuUser().getIntroduction());
							// firstly_partner_num  + secondary_partner_num
							Integer firstNum = null == hhrStat.getFirstlyPartnerNum() ? 0 : hhrStat.getFirstlyPartnerNum();
							Integer secondaryNum = null == hhrStat.getSecondaryPartnerNum() ? 0 : hhrStat.getSecondaryPartnerNum();
							map2.put("num", (firstNum + secondaryNum));
							partners.add(map2);
						}
						

						// 排序
						ComParatorHhrUtil sort = new ComParatorHhrUtil();
						Comparator<Object> c = Collections.reverseOrder(sort);
						Collections.sort(partners, c);
						obj.put("partners", partners);
					} 
					
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					//封装上级用户
					FuUser parentUser=fuUserService.get(fuUser.getHhrParentID());
					HhrStat hhrStat2=hhrStatService.findHhrStatByUser(parentUser.getId());
					if(null == hhrStat2) {
						obj.put("parentUser", "");
					}else {
						Map<String, Object> map4 = new HashMap<String, Object>();
						
						map4.put("id", parentUser.getId());
						map4.put("account_name", parentUser.getAccountName());
						map4.put("user_id", parentUser.getId());
						map4.put("firstly_partner_num", hhrStat2.getFirstlyPartnerNum()==null?"0":hhrStat2.getFirstlyPartnerNum());
						map4.put("secondary_partner_num", hhrStat2.getSecondaryPartnerNum()==null?"0":hhrStat2.getSecondaryPartnerNum());
						map4.put("monthly_stock_endowment", hhrStat2.getMonthlyStockEndowment()==null?"0":hhrStat2.getMonthlyStockEndowment());
						map4.put("monthly_futures_endowment", hhrStat2.getMonthlyFuturesEndowment()==null?"0":hhrStat2.getMonthlyFuturesEndowment());
						map4.put("nick_name", parentUser.getNickName()==null?"":parentUser.getNickName());
						map4.put("user_avatar", parentUser.getUserAvatar()==null?"":parentUser.getUserAvatar());
						HhrRemark hhrRemark = hhrRemarkService.findRemarkByUserId(Long.parseLong(userId), parentUser.getId());
						map4.put("remark_name", hhrRemark==null?"":hhrRemark.getRemarkName());
						map4.put("phone", parentUser.getPhone()==null?"":parentUser.getPhone());
						map4.put("introduction", parentUser.getIntroduction()==null?"":parentUser.getIntroduction());
						map4.put("num", "1");
						obj.put("parentUser", map4);
					}
					
					obj.put("message", "查询合伙人列表成功");
					obj.put("is_success", 1);
				}
//			}else{
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(DateUtil.getLongStrFromDate(new Date()) + "---->app_usermanage/partnersList.htm");
		return obj.toString();
	}

	@Override
	public String deleteCard(String userId, String cardNum, String sign, String version) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
					
					if(null == cardNum || "".equals(cardNum)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数cardNum");
						return obj.toString();
					}
					
					// 根据银行卡号, 用户的id将这张银行卡状态置为0
					int state = 0;
					int ret = fuBankCardService.deleteCard(Long.parseLong(userId), cardNum, state);
					if(ret == 1){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("userId", Long.parseLong(userId));
						map.put("state", 1);
						Integer num = fuBankCardService.countFuBankCard(map);
						// 查询绑定的银行卡张数
						obj.put("card_num", num);						
						obj.put("is_success",1);
						obj.put("message", "删除这张银行卡成功");
					}else {
						obj.put("is_success", 0);
						obj.put("message", "银行卡不存在");
					}
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 实名认证的第三步  绑定银行卡
	public String bindCard(String userId, String userName,
			String indentityCard, String phone, String phoneCode,
			String drawPwd1, String drawPwd2, String userCard,
			String cardNum, String bankName,  String bankProvince, 
			String bankCity, String bankBranchName, String sign,
			String version) {
		
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if(null == version || "".equals(version)) {
					obj.put("is_success", 0);
					obj.put("message", "缺少参数");
					System.out.println(obj.toString()+ "缺少参数version");
					return obj.toString();
				}
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser) {
						obj.put("is_success", 0);
						obj.put("message", "参数不正确");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
					if(null == userName || "".equals(userName)) {
						if(null == fuUser.getUserName() || "".equals(fuUser.getUserName())) {
							obj.put("is_success", 0);
							obj.put("message", "姓名不能为空, 请输入");
							System.out.println(obj.toString()+ "缺少参数userName");
							return obj.toString();
						}
						userName = fuUser.getUserName();
						
					}
//					FuUser user = fuUserService.findUserByUserName(userName);
//					if(null != user) {
//						obj.put("is_success", 0);
//						obj.put("message", "姓名已存在, 请输入");
//						System.out.println(obj.toString());
//						return obj.toString();
//					}
					
					if(null == indentityCard || "".equals(indentityCard)) {
						if(null == fuUser.getCardNumber() || "".equals(fuUser.getCardNumber())){
							obj.put("is_success", 0);
							obj.put("message", "身份证不能为空, 请输入");
							System.out.println(obj.toString()+ "缺少参数indentityCard");
							return obj.toString();
						}
						indentityCard = fuUser.getCardNumber();
					}
					if(null == phone || "".equals(phone)) {
						if(null == fuUser.getPhone() || "".equals(fuUser.getPhone())){
							obj.put("is_success", 0);
							obj.put("message", "缺少参数");
							System.out.println(obj.toString()+ "缺少参数phone");
							return obj.toString();
						}
						phone = fuUser.getPhone();
					}
					
					if(null == phoneCode || "".equals(phoneCode)) {
						if(null == fuUser.getPhone() || "".equals(fuUser.getPhone())){
							obj.put("is_success", 0);
							obj.put("message", "请输入短信验证码");
							System.out.println(obj.toString()+ "缺少参数phoneCode");
							return obj.toString();
						}
					}
					
					if(null == drawPwd1 || "".equals(drawPwd1)) {
						if(null == fuUser.getDrawPassword() || "".equals(fuUser.getDrawPassword())) {
							obj.put("is_success", 0);
							obj.put("message", "交易密码不能为空, 请输入");
							System.out.println(obj.toString());
							return obj.toString();
						}
						drawPwd1 = fuUser.getDrawPassword();
					}else {
						fuUser.setDrawPassword(drawPwd1);
					}
					
					if(null == drawPwd2 || "".equals(drawPwd2)) {
						if(null == fuUser.getDrawPassword() || "".equals(fuUser.getDrawPassword())) {
							obj.put("is_success", 0);
							obj.put("message", "交易密码不能为空, 请输入");
							System.out.println(obj.toString());
							return obj.toString();
						}
						drawPwd2 = fuUser.getDrawPassword();
					}else {
						fuUser.setDrawPassword(drawPwd1);
					}
					
					if(!drawPwd1.equals(drawPwd2)) {
						obj.put("is_success", 0);
						obj.put("message", "两次输入密码不一致,请重新输入");
						System.out.println(obj.toString());
						return obj.toString();
					}
					if(null == userCard || "".equals(userCard)){
						obj.put("message", "开户姓名为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}		
					if(cardNum ==null || "".equals(cardNum)){
						obj.put("message", "银行卡号为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					cardNum = cardNum.replace(" ", "");
					if(cardNum.length()<15 || cardNum.length() >=21){
						obj.put("message", "银行卡号不正确, 请重新输入");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					// 根据银行卡号 查询这个银行卡时候已经被绑定
//					FuBankCard bankCard = fuBankCardService.findBankCardByNum(cardNum);
//					if(null != bankCard) {
//						obj.put("message", "这张银行卡已经被绑定，请重新输入");
//						obj.put("is_success", 0);
//						System.out.println(obj);
//						return obj.toString();
//					}
					
					// 根据银行卡, 银行的开户行, 绑定卡的名称 查询这张卡
					List<FuBankCard> cardList = fuBankCardService.findByUserAndBankAndCard(userCard, bankName, cardNum);
					if(null !=cardList && cardList.size() > 0) {   //查询出
						for (FuBankCard fuBankCard : cardList) {
							if(fuBankCard.getState() == 0) { // 未审核
								fuBankCard.setUserId(fuUser.getId());					
								fuBankCard.setAccountName(userCard);
								fuBankCard.setCardNumber(cardNum);
													
								Long sysBankId = null;
								Long sysCityId = null;
								if(bankName != null && !"".equals(bankName)){
									SysBank sysBank = sysBankService.findBy(bankName);
									if(sysBank!=null){
										fuBankCard.setBankNameId(sysBank.getId());
										fuBankCard.setBankName(bankName);
										sysBankId = sysBank.getId();
									}
								}
								// 网省点地址		
								if(bankProvince != null && !"".equals(bankProvince)){
									SysProvince sysProvince = sysProvinceService.findByName(bankProvince);
									if(sysProvince!=null){
										fuBankCard.setBankProvince(sysProvince.getId());
									}						
								}
								
								// 网店市地址
								if(bankCity != null && !"".equals(bankCity)){
									SysCity sysCity = sysCityService.findByCityName(bankCity);
									if(sysCity!=null){
										fuBankCard.setBankCity(sysCity.getId());
										sysCityId = sysCity.getId();
									}
								}
								// 	网点支行名称			
								if(sysBankId!=null && sysCityId!=null && bankBranchName!=null && !"".equals(bankBranchName)){
									SysBranch sysBranch = sysBranchService.findByParams(sysBankId, sysCityId, bankBranchName);
									if(sysBranch!=null){
										fuBankCard.setBankBranch(sysBranch.getId());
										fuBankCard.setBankBranchName(bankBranchName);
									}
								}
								
								fuBankCard.setBankAddress(bankProvince+bankCity+bankBranchName);			
								fuBankCard.setState(1);					
								fuBankCardService.save(fuBankCard);
							}else {
								obj.put("message", "这张银行卡已经被绑定，请重新输入");
								obj.put("is_success", 0);
								System.out.println(obj);
								return obj.toString();
							}
						}
					}else {
						// 绑定银行卡
						FuBankCard fuBankCard = new FuBankCard();
						fuBankCard.setUserId(fuUser.getId());					
						fuBankCard.setAccountName(userCard);
						fuBankCard.setCardNumber(cardNum);
											
						Long sysBankId = null;
						Long sysCityId = null;
						if(bankName != null && !"".equals(bankName)){
							SysBank sysBank = sysBankService.findBy(bankName);
							if(sysBank!=null){
								fuBankCard.setBankNameId(sysBank.getId());
								fuBankCard.setBankName(bankName);
								sysBankId = sysBank.getId();
							}
						}
						// 网省点地址		
						if(bankProvince != null && !"".equals(bankProvince)){
							SysProvince sysProvince = sysProvinceService.findByName(bankProvince);
							if(sysProvince!=null){
								fuBankCard.setBankProvince(sysProvince.getId());
							}						
						}
						
						// 网店市地址
						if(bankCity != null && !"".equals(bankCity)){
							SysCity sysCity = sysCityService.findByCityName(bankCity);
							if(sysCity!=null){
								fuBankCard.setBankCity(sysCity.getId());
								sysCityId = sysCity.getId();
							}
						}
						// 	网点支行名称			
						if(sysBankId!=null && sysCityId!=null && bankBranchName!=null && !"".equals(bankBranchName)){
							SysBranch sysBranch = sysBranchService.findByParams(sysBankId, sysCityId, bankBranchName);
							if(sysBranch!=null){
								fuBankCard.setBankBranch(sysBranch.getId());
								fuBankCard.setBankBranchName(bankBranchName);
							}
						}
						
						fuBankCard.setBankAddress(bankProvince+bankCity+bankBranchName);			
						fuBankCard.setState(1);					
						fuBankCardService.save(fuBankCard);
					}
					// 绑定实名
					fuUser.setUserName(userName);
					fuUser.setCardNumber(indentityCard);
					// 0未认证，1待认证，2已认证，3信息有误
					if(fuUser.getIsCheckCard() == 0) {
						fuUser.setIsCheckCard(2);
					}
					
					fuUserService.save(fuUser);
					//0未认证，1待认证，2已认证，3信息有误 
					obj.put("is_check_card", fuUser.getIsCheckCard() == null ? 0 : fuUser.getIsCheckCard());
					// 银行卡的张数
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("userId", fuUser.getId());
					map.put("state", 1);
					Integer num = fuBankCardService.countFuBankCard(map);
					obj.put("card_num", num);
					obj.put("real_name", fuUser.getUserName());
					obj.put("is_draw_password", fuUser.getDrawPassword() == null ? 0 : 1);
					obj.put("message", "绑定成功");
					obj.put("is_success", 1);
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 实名认证第一步
	public String realCertification(String userId, String userName,
			String indentityCard, String phone, String phoneCode, String sign,
			String version) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser) {
						obj.put("is_success", 0);
						obj.put("message", "参数不正确");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
					if(null == userName || "".equals(userName)) {
						obj.put("is_success", 0);
						obj.put("message", "姓名不能为空, 请输入");
						System.out.println(obj.toString()+ "缺少参数userName");
						return obj.toString();
					}
					/*FuUser user = fuUserService.findUserByUserName(userName);
					if(null != user) {
						obj.put("is_success", 0);
						obj.put("message", "姓名已存在, 请重新输入");
						System.out.println(obj.toString());
						return obj.toString();
					}*/
					if(null == indentityCard || "".equals(indentityCard)) {
						obj.put("is_success", 0);
						obj.put("message", "身份证不能为空, 请输入");
						System.out.println(obj.toString()+ "缺少参数indentityCard");
						return obj.toString();
					}
					FuUser user1 = fuUserService.findUserByCardNumber(indentityCard);
					if(null != user1) {
						obj.put("is_success", 0);
						obj.put("message", "身份证已绑定, 请重新输入");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
					if(null == phone || "".equals(phone)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数phone");
						return obj.toString();
					}
					
					if(null == phoneCode || "".equals(phoneCode)) {
						obj.put("is_success", 0);
						obj.put("message", "请输入短信验证码");
						System.out.println(obj.toString()+ "缺少参数phoneCode");
						return obj.toString();
					}
					if(phone.equals(fuUser.getPhone()) && phoneCode.equals(fuUser.getPhoneCode())) {
						//
						obj.put("is_success", 1);
						obj.put("message", "验证成功");
						System.out.println(obj.toString());
						return obj.toString();
					}else {
						obj.put("is_success", 0);
						obj.put("message", "验证码错误, 请重新输入");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 判断两次交易密码是否一致的
	public String confirmDrawPwd(String drawPwd1, String drawPwd2, String sign, String version) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == drawPwd1 || "".equals(drawPwd1)) {
						obj.put("is_success", 0);
						obj.put("message", "交易密码不能为空, 请输入");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
					if(null == drawPwd2 || "".equals(drawPwd2)) {
						obj.put("is_success", 0);
						obj.put("message", "交易密码不能为空, 请输入");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
					if(!drawPwd1.equals(drawPwd2)){
						obj.put("is_success", 0);
						obj.put("message", "两次交易密码不一致, 请重新输入");
						System.out.println(obj.toString());
						return obj.toString();
					}else {
						obj.put("is_success", 1);
						obj.put("message", "验证码交易密码成功");
						System.out.println(obj.toString());
						return obj.toString();
					}
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	//判断用输入的交易密码是否是正确的
	public String checkDrawPwd(String userId, String drawPwd, String version,
			String sign) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
					if(null == drawPwd || "".equals(drawPwd)){
						obj.put("is_success", 0);
						obj.put("message", "交易密码不能为空,请输入");
						System.out.println(obj.toString()+ "缺少参数drawPwd");
						return obj.toString();
					}
					
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser){
						obj.put("is_success", 0);
						obj.put("message", "该用户不存在");
						System.out.println(obj.toString()+ "user对象是空");
						return obj.toString();
					}
					
					if(!drawPwd.equals(fuUser.getDrawPassword())){
						obj.put("is_success", 0);
						obj.put("message", "交易输入错误, 请重新输入");
						System.out.println(obj.toString());
						return obj.toString();
						
					}
					obj.put("is_success", 1);
					obj.put("message", "交易密码正确");
					
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 根据user的主键获取用户的基本信息
	public String getUserInfo(String userId, String version, String sign) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
					
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser){
						obj.put("is_success", 0);
						obj.put("message", "该用户不存在");
						System.out.println(obj.toString()+ "user对象是空");
						return obj.toString();
					}
					
					obj.put("user_id", fuUser.getId());
					obj.put("invitation_code",fuUser.getInvitationCode() == null ? "": fuUser.getInvitationCode());
					obj.put("introduction", fuUser.getIntroduction()==null?"":fuUser.getIntroduction());
					obj.put("nick_name", fuUser.getNickName()==null?"":fuUser.getNickName());
					obj.put("email", fuUser.getEmail()==null?"":fuUser.getEmail());
					obj.put("phone", fuUser.getPhone() == null ? "" : fuUser.getPhone());
					obj.put("account_balance", fuUser.getAccountBalance() == null ? 0.00 : fuUser.getAccountBalance());
					obj.put("user_avatar", fuUser.getUserAvatar() == null ? "" : fuUser.getUserAvatar());
//					0未认证，1待认证，2已认证，3信息有误 
					obj.put("is_check_card", fuUser.getIsCheckCard() == null ? 0 : fuUser.getIsCheckCard());
					// 银行卡的张数
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("userId", fuUser.getId());
					map.put("state", 1);
					Integer num = fuBankCardService.countFuBankCard(map);
					obj.put("card_num", num);
					// 交易密码是否存在
					obj.put("is_draw_password", fuUser.getDrawPassword() == null ? 0 : 1);
					// 用户的真实姓名
					obj.put("real_name", fuUser.getUserName() == null ? "" : fuUser.getUserName());
					obj.put("is_success", 1);
					obj.put("message", "获取个人信息成功");
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 发送实名认证的验证码
	public String sendRealCode(String phone, String version, String sign) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if(null == version || "".equals(version)) {
					obj.put("is_success", 0);
					obj.put("message", "参数不正确");
					System.out.println(obj.toString() +"缺少参数version");
					return obj.toString();
				}
				if("2.0.0".equals(version)){
					if(null == phone || "".equals(phone)) {
						obj.put("is_success", 0);
						obj.put("message", "请输入手机号码");
						System.out.println(obj.toString() +"缺少参数phone");
						return obj.toString();
					}
					
					DecimalFormat format = new DecimalFormat("0000");
					String code = format.format(Math.random() * 9999);			
					String message = URLDecoder.decode("超级合伙人验证码：" + code + "，用于实名认证，泄露验证码有资金风险，请妥善保管。", "UTF-8");
					FuSmsLog log = new FuSmsLog();
					log.setContent(message);
					log.setPrio(1);
					log.setReason("用户注册");
					log.setDestination(phone);
					log.setPlanTime(new Date());
					log.setType(1);// 短信
					log.setState(0);
					fuSmsLogService.save(log);
					// 保存验证码
					FuUser user = fuUserService.findUserByPhone(phone);
					user.setPhoneCode(code);
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.HOUR_OF_DAY, 1);
					user.setPhoneCodeTime(cal.getTime());
					fuUserService.save(user);
					
					obj.put("message", "请求成功");
					obj.put("is_success", 1);
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 根据用查询用户的全部资产明细
	 * @param userId			用户id
	 * @param sign
	 * @return
	 */
	public String assetsAll(String userId, String curPage, String pageSize, String version, String sign) {
		JSONObject obj = new JSONObject();
		try{
			if(null == version || "".equals(version)){
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj + "缺少version");
				return obj.toString();
			}
			if("2.0.0".equals(version)) {
				if(null == userId || "".equals(userId)){
					obj.put("is_success", 0);
					obj.put("message", "请先登录!");
					System.out.println(obj);
					return obj.toString();
				}
				
				FuUser user = fuUserService.get(Long.parseLong(userId));
				if(null == user){
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					System.out.println(obj);
					return obj.toString();
				}
				
				if (null == curPage || "".equals(curPage)) {
					curPage = "0";
				}
				
				if (null == pageSize || "".equals(pageSize)) {
					pageSize = "1000";
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", user.getId());
				map.put("money3", 0);
				List<FuMoneyDetail> moneyDeatilList = fuMoneyDetailService.findListBy(Integer.parseInt(curPage), Integer.parseInt(pageSize), map);
				
				List<Object> list = new ArrayList<Object>();
				if(moneyDeatilList != null && moneyDeatilList.size() > 0){
					for (FuMoneyDetail fuMoneyDetail : moneyDeatilList) {
						Map<String, Object> detailMap = new HashMap<String, Object>();
						detailMap.put("assets_name", fuMoneyDetail.getFuDictionary().getName());
						Date time = fuMoneyDetail.getTime();
						String createTime = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
						detailMap.put("create_time", createTime);
						
						if(fuMoneyDetail.getIsIncome()){ // 1 : 收入
							if(null == fuMoneyDetail.getMoney()){
								detailMap.put("money", 0.00);
							}else {
								detailMap.put("money", "+"+fuMoneyDetail.getMoney().abs());
							}
						}else {
							if(null == fuMoneyDetail.getMoney()){
								detailMap.put("money", 0.00);
							}else {
								detailMap.put("money", "-"+fuMoneyDetail.getMoney().abs());
							}
						}
						detailMap.put("account_balance_before", fuMoneyDetail.getAccountBalanceAfter() == null ? 0.00 : fuMoneyDetail.getAccountBalanceAfter());
						list.add(detailMap);
					}
					
					obj.put("is_success", 1);
					obj.put("message", "查询用户资产明细成功");
					obj.put("assetsList", list);
				}else{
					obj.put("is_success", 2);
					obj.put("assetsList", "[]");
					obj.put("message", "没有用户资产明细");
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统更新中, 请等下重试");
		}
		log.info(obj.toString());
		return obj.toString();
	}

//	根据用户查询用户的收入明细
	public String inComeDetail(String userId, String curPage, String pageSize, String version, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
//			if("success".equals(result)){
				if(null == version || "".equals(version)){
					obj.put("is_success", 0);
					obj.put("message", "缺少参数");
					System.out.println(obj + "缺少version");
					return obj.toString();
				}
				if("2.0.0".equals(version)) {
					if(userId == null || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "请先登录!");
						System.out.println(obj);
						return obj.toString();
					}
					FuUser user = fuUserService.get(Long.parseLong(userId));
					if(user == null){
						obj.put("is_success", 0);
						obj.put("message", "用户不存在");
						System.out.println(obj);
						return obj.toString();
					}
					if (null == curPage || "".equals(curPage)) {
						curPage = "0";
					}
					
					if (null == pageSize || "".equals(pageSize)) {
						pageSize = "1000";
					}
					// 收入 是 1
					List<FuMoneyDetail> moneyDetailList = fuMoneyDetailService.findInComeByUserId(Long.parseLong(userId), 1, Integer.parseInt(curPage), Integer.parseInt(pageSize));
					if(null != moneyDetailList && moneyDetailList.size() > 0){
						List<Object> list = new ArrayList<Object>();
						for (FuMoneyDetail fuMoneyDetail : moneyDetailList) {
							Map<String, Object> detailMap = new HashMap<String, Object>();
							detailMap.put("assets_name", fuMoneyDetail.getFuDictionary().getName());
							Date time = fuMoneyDetail.getTime();
							String createTime = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
							
							detailMap.put("create_time", createTime);
							
							if(fuMoneyDetail.getIsIncome()){ // 1 : 收入
								if(null == fuMoneyDetail.getMoney()){
									detailMap.put("money", 0.00);
								}else {
									detailMap.put("money", "+"+fuMoneyDetail.getMoney().abs());
								}
							}else {
								if(null == fuMoneyDetail.getMoney()){
									detailMap.put("money", 0.00);
								}else {
									detailMap.put("money", "-"+fuMoneyDetail.getMoney().abs());
								}
							}
							detailMap.put("account_balance_before", fuMoneyDetail.getAccountBalanceAfter() == null ? 0.00 : fuMoneyDetail.getAccountBalanceAfter());
							list.add(detailMap);
						}
						
						obj.put("is_success", 1);
						obj.put("message", "查询用户收入资产明细成功");
						obj.put("assetsList", list);
					}else {
						obj.put("is_success", 2);
						obj.put("message", "用户没有收入资产明细");
						obj.put("assetsList", "[]");
					}
				}
				
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统更新中, 请等下重试");
		}
		System.out.println(obj);
		return obj.toString();
	}

	//	根据用户查询用户的支出明细
	public String outComeDetail(String userId, String curPage, String pageSize, String version, String sign) {
		JSONObject obj = new JSONObject();
		String result = DesUtil.webserviceSignVerify(sign);
		try{
			if("success".equals(result)){
				if(null == version || "".equals(version)){
					obj.put("is_success", 0);
					obj.put("message", "缺少参数");
					System.out.println(obj + "缺少version");
					return obj.toString();
				}
				if("2.0.0".equals(version)) {
					if(null == userId || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "请先登录!");
						System.out.println(obj);
						return obj.toString();
					}
					
					FuUser user = fuUserService.get(Long.parseLong(userId));
					if(null == user){
						obj.put("is_success", 0);
						obj.put("message", "用户不存在");
						System.out.println(obj);
						return obj.toString();
					}
					if (null == curPage || "".equals(curPage)) {
						curPage = "0";
					}
					
					if (null == pageSize || "".equals(pageSize)) {
						pageSize = "1000";
					}
					// 收入 是 1
					List<FuMoneyDetail> monetDetailList = fuMoneyDetailService.findInComeByUserId(Long.parseLong(userId), 0, Integer.parseInt(curPage), Integer.parseInt(pageSize));
					
					if(null != monetDetailList && monetDetailList.size() > 0) {
						List<Object> list = new ArrayList<Object>();
						for (FuMoneyDetail fuMoneyDetail : monetDetailList) {
							Map<String, Object> detailMap = new HashMap<String, Object>();
							detailMap.put("assets_name", fuMoneyDetail.getFuDictionary().getName());
							Date time = fuMoneyDetail.getTime();
							String createTime = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
							
							detailMap.put("create_time", createTime);
							
							if(fuMoneyDetail.getIsIncome()){ // 1 : 收入
								if(null == fuMoneyDetail.getMoney()){
									detailMap.put("money", 0.00);
								}else {
									detailMap.put("money", "+"+fuMoneyDetail.getMoney().abs());
								}
							}else {
								if(null == fuMoneyDetail.getMoney()){
									detailMap.put("money", 0.00);
								}else {
									detailMap.put("money", "-"+fuMoneyDetail.getMoney().abs());
								}
							}
							detailMap.put("account_balance_before", fuMoneyDetail.getAccountBalanceAfter() == null ? 0.00 : fuMoneyDetail.getAccountBalanceAfter());
							list.add(detailMap);
						}
						obj.put("is_success", 1);
						obj.put("message", "查询用户支出资产明细成功");
						obj.put("assetsList", list);
					}else {
						obj.put("is_success", 2);
						obj.put("message", "用户没有支出资产明细");
						obj.put("assetsList", "[]");
					}
					
				}
				
			}else {
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统更新中, 请等下重试");
		}
		System.out.println(obj);
		return obj.toString();
	}
	
 
	// 用户提取现金
	public String appDrawMoney(String userId, String drawPwd, String cardNum, String money,
			String version, String sign) {
		JSONObject obj = new JSONObject();
		String result = DesUtil.webserviceSignVerify(sign);
		try{
			if("success".equals(result)){
				if(null == version || "".equals(version)){
					obj.put("is_success", 0);
					obj.put("message", "缺少参数");
					System.out.println(obj + "缺少version");
					return obj.toString();
				}
				if("2.0.0".equals(version)) {
					if(null == userId || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "请先登录!");
						System.out.println(obj);
						return obj.toString();
					}
					
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser){
						obj.put("message", "用户不存在");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					if(null == money || "".equals(money)) {
						obj.put("message", "提取现金不能是空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					
					if(Double.parseDouble(money) < 0) {
						obj.put("message", "提取现金只是大于0的数值");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
//					if(!StringUtil.isNumeric(money)){
//						obj.put("message", "提款金额必须是正数");
//						obj.put("is_success", 0);
//						System.out.println(obj);
//						return obj.toString();
//					}
					if(fuUser.getAccountBalance().compareTo(new BigDecimal(money))==-1){
				    		obj.put("message", "账户余额不足");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
				    }
					if(null == drawPwd || "".equals(drawPwd)){
						obj.put("message", "缺少参数");
						obj.put("is_success", 0);
						System.out.println(obj + "缺少drawPwd参数");
						return obj.toString();
					}
					if(!drawPwd.equals(fuUser.getDrawPassword())) {
						obj.put("message", "输入的交易密码不对， 请重新输入");
						obj.put("is_success", 0);
						System.out.println(obj + "缺少drawPwd参数");
						return obj.toString();
					}
					
				    if(fuUser.getIsCheckCard()!=2){
				    	obj.put("message", "用户未实名认证");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
				    if(fuUser.getIsAcrossCabin()==1){
				    	obj.put("message", "用户已穿仓");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					if(cardNum == null || "".equals(cardNum)){
						obj.put("message", "银行卡号为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}	
					FuBankCard bankCard = fuBankCardService.findBankCardByNum(cardNum);
					if(null == bankCard) {
						obj.put("message", "该银行卡没有绑定， 请先绑定银行卡");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					if(money == null || "".equals(money)){
						obj.put("message", "提款金额为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}	
					
				    fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(new BigDecimal(money)));
					FuDrawMoney drawMoney = new FuDrawMoney();
					drawMoney.setFuUser(fuUser);
					drawMoney.setFuBankCard(bankCard);
					drawMoney.setDrawMoney(new BigDecimal(money));
					drawMoney.setDrawTime(new Date());
					drawMoney.setStatus(0);//默认是未审核
					drawMoney.setState(1);//正常
					//fuDrawMoneyService.save(drawMoney);
					fuDrawMoneyService.saveDrawMoney(drawMoney, fuUser, 9, false);// 保存用户和提现，写明细
					obj.put("message", "提款申请成功");
					obj.put("is_success", 1);		
				}else{
					obj.put("message", result);
					obj.put("is_success", 0);
				}
			}else {
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统更新中, 请等下重试");
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 添加绑定银行卡
	public String addBindCard(String userId, String drawPwd, String userCard,
			String cardNum, String bankName, String bankProvince,
			String bankCity, String bankBranchName, String version, String sign) {
		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
//			String result = "success";
			if("success".equals(result)){
				if(null == version || "".equals(version)) {
					obj.put("is_success", 0);
					obj.put("message", "缺少参数");
					System.out.println(obj.toString()+ "缺少参数version");
					return obj.toString();
				}
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数userId");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser) {
						obj.put("is_success", 0);
						obj.put("message", "参数不正确");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
					if(null == drawPwd || "".equals(drawPwd)) {
						obj.put("is_success", 0);
						obj.put("message", "交易密码不能为空, 请输入");
						System.out.println(obj.toString());
						return obj.toString();
					}
					if(!drawPwd.equals(fuUser.getDrawPassword())){
						obj.put("is_success", 0);
						obj.put("message", "交易密码不正确, 请重新输入");
						System.out.println(obj.toString());
						return obj.toString();
					}
					if(null == userCard || "".equals(userCard)){
						obj.put("message", "开户姓名为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}		
					if(cardNum ==null || "".equals(cardNum)){
						obj.put("message", "银行卡号为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					if(cardNum.length()<15 || cardNum.length() >=21){
						obj.put("message", "银行卡号不正确, 请重新输入");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					// 根据银行卡, 银行的开户行, 绑定卡的名称 查询这张卡
					List<FuBankCard> cardList = fuBankCardService.findByUserAndBankAndCard(userCard, bankName, cardNum);
					if(null !=cardList && cardList.size() > 0) {   //查询出
						for (FuBankCard fuBankCard : cardList) {
							if(fuBankCard.getState() == 0) { // 未审核
								fuBankCard.setUserId(fuUser.getId());					
								fuBankCard.setAccountName(userCard);
								fuBankCard.setCardNumber(cardNum);
													
								Long sysBankId = null;
								Long sysCityId = null;
								if(bankName != null && !"".equals(bankName)){
									SysBank sysBank = sysBankService.findBy(bankName);
									if(sysBank!=null){
										fuBankCard.setBankNameId(sysBank.getId());
										fuBankCard.setBankName(bankName);
										sysBankId = sysBank.getId();
									}
								}
								// 网省点地址		
								if(bankProvince != null && !"".equals(bankProvince)){
									SysProvince sysProvince = sysProvinceService.findByName(bankProvince);
									if(sysProvince!=null){
										fuBankCard.setBankProvince(sysProvince.getId());
									}						
								}
								
								// 网店市地址
								if(bankCity != null && !"".equals(bankCity)){
									SysCity sysCity = sysCityService.findByCityName(bankCity);
									if(sysCity!=null){
										fuBankCard.setBankCity(sysCity.getId());
										sysCityId = sysCity.getId();
									}
								}
								// 	网点支行名称			
								if(sysBankId!=null && sysCityId!=null && bankBranchName!=null && !"".equals(bankBranchName)){
									SysBranch sysBranch = sysBranchService.findByParams(sysBankId, sysCityId, bankBranchName);
									if(sysBranch!=null){
										fuBankCard.setBankBranch(sysBranch.getId());
										fuBankCard.setBankBranchName(bankBranchName);
									}
								}
								
								fuBankCard.setBankAddress(bankProvince+bankCity+bankBranchName);			
								fuBankCard.setState(1);					
								fuBankCardService.save(fuBankCard);
							}else {
								obj.put("message", "这张银行卡已经被绑定，请重新输入");
								obj.put("is_success", 0);
								System.out.println(obj);
								return obj.toString();
							}
						}
					}else {
						// 绑定银行卡
						FuBankCard fuBankCard = new FuBankCard();
						fuBankCard.setUserId(fuUser.getId());					
						fuBankCard.setAccountName(userCard);
						fuBankCard.setCardNumber(cardNum);
											
						Long sysBankId = null;
						Long sysCityId = null;
						if(bankName != null && !"".equals(bankName)){
							SysBank sysBank = sysBankService.findBy(bankName);
							if(sysBank!=null){
								fuBankCard.setBankNameId(sysBank.getId());
								fuBankCard.setBankName(bankName);
								sysBankId = sysBank.getId();
							}
						}
						// 网省点地址		
						if(bankProvince != null && !"".equals(bankProvince)){
							SysProvince sysProvince = sysProvinceService.findByName(bankProvince);
							if(sysProvince!=null){
								fuBankCard.setBankProvince(sysProvince.getId());
							}						
						}
						
						// 网店市地址
						if(bankCity != null && !"".equals(bankCity)){
							SysCity sysCity = sysCityService.findByCityName(bankCity);
							if(sysCity!=null){
								fuBankCard.setBankCity(sysCity.getId());
								sysCityId = sysCity.getId();
							}
						}
						// 	网点支行名称			
						if(sysBankId!=null && sysCityId!=null && bankBranchName!=null && !"".equals(bankBranchName)){
							SysBranch sysBranch = sysBranchService.findByParams(sysBankId, sysCityId, bankBranchName);
							if(sysBranch!=null){
								fuBankCard.setBankBranch(sysBranch.getId());
								fuBankCard.setBankBranchName(bankBranchName);
							}
						}
						
						fuBankCard.setBankAddress(bankProvince+bankCity+bankBranchName);			
						fuBankCard.setState(1);					
						fuBankCardService.save(fuBankCard);
					}
							
					//0未认证，1待认证，2已认证，3信息有误 
					obj.put("is_check_card", fuUser.getIsCheckCard() == null ? 0 : fuUser.getIsCheckCard());
					// 银行卡的张数
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("userId", fuUser.getId());
					map.put("state", 1);
					Integer num = fuBankCardService.countFuBankCard(map);
					obj.put("card_num", num);
					obj.put("real_name", fuUser.getUserName());
					obj.put("is_draw_password", fuUser.getDrawPassword() == null ? 0 : 1);
					obj.put("message", "绑定成功");
					obj.put("is_success", 1);
				}
			}else {
				obj.put("message", result);
				obj.put("is_success", 0);
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 查询用户的充值记录
	public String appRechargeRecords(String phone, String sign, String version) {
		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
//			String result = "success";
			if("success".equals(result)){
				if(null == version || "".equals(version)) {
					obj.put("is_success", 0);
					obj.put("message", "缺少参数");
					System.out.println(obj.toString()+ "缺少参数version");
					return obj.toString();
				}
				if("2.0.0".equals(version)){
					if(null == phone || "".equals(phone)) {
						obj.put("is_success", 0);
						obj.put("message", "缺少参数");
						System.out.println(obj.toString()+ "缺少参数phone");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.findUserByAccount(phone);
					if(null == fuUser) {
						obj.put("is_success", 0);
						obj.put("message", "参数错误");
						System.out.println(obj.toString()+ "参数不正确phone, 用户不存在");
						return obj.toString();
					}
					List<FuRecharge> list = fuRechargeService.findListByUserId(fuUser.getId());
					List<Object> recharges = new ArrayList<Object>();
					if (null != list && list.size() > 0) {
						for (FuRecharge recharge : list) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", recharge.getId());
							map.put("user_id", recharge.getFuUser().getId());
							map.put("recharge_time", DateUtil.getStrFromDate(recharge.getRechargeTime(), "yyyy-MM-dd HH:mm"));
							map.put("order_num", recharge.getOrderNum());
//							map.put("type", "银行转账");
							map.put("type", recharge.getRechargeBank());
							map.put("recharge_money", recharge.getRechargeMoney()==null?"0":recharge.getRechargeMoney());
							/*map.put("recharge_status",recharge.getRechargeStatus()==0?"未审核":recharge.getRechargeStatus()==1?"审核中":recharge.getRechargeStatus()==2?"成功":"拒绝");
							String message = "";
							if(recharge.getRechargeStatus() == 0) {
								message = "未审核";
							}else if(recharge.getRechargeStatus() == 1) {
								message = "审核中";
							} else if(recharge.getRechargeStatus() == 2) {
								message = "成功";
							}else {
								message = "拒绝";
							}
							map.put("recharge_status", message);*/
							// 0: 未审核 1: 审核中 2:成功 3:拒绝
							map.put("recharge_status", recharge.getRechargeStatus() == null ? 0 : recharge.getRechargeStatus());
							recharges.add(map);
						}
						obj.put("message", "查询成功");
						obj.put("recharges", recharges);
						obj.put("is_success", 1);	
					}else {
						obj.put("message", "未查询到充值记录");
						obj.put("recharges", "[]");
						obj.put("is_success", 2);
					}
				}
			}else {
				obj.put("message", result);
				obj.put("is_success", 0);
			}
		}catch(Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 预约开户信息
	public String appointment(String sign, String version) {
		JSONObject obj = new JSONObject();
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if(null == version || "".equals(version)) {
					obj.put("is_success", 0);
					obj.put("message", "缺少参数");
					System.out.println(obj.toString()+ "缺少参数version");
					return obj.toString();
				}
				if("2.0.0".equals(version)){
					// 加载json格式的文件
					List<FuAppointmentInfo> infoList = appointmentInfoService.findAll();
					if(null != infoList && infoList.size() > 0 ) {
						List<Object> list = new ArrayList<Object>();
						for (FuAppointmentInfo fa : infoList) {
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("avatar", fa.getAvatar() == null ? "" : fa.getAvatar());
							map.put("qualify_id", fa.getQualifyId() == null ? "" : fa.getQualifyId());
							map.put("qualify_name", "从业资格证号");
							map.put("user_name", fa.getUserName() == null ? "" : fa.getUserName());
							map.put("securities_name", fa.getSecuritiesName() == null ? "" : fa.getSecuritiesName());
							map.put("phone", fa.getPhone() == null ? "" : fa.getPhone());
							String[] split = fa.getTag().split("#");
							List<String> tagList = Arrays.asList(split);
							map.put("tags", tagList);
							list.add(map);
						}
						obj.put("is_success", 1);
						obj.put("message", "查询预约人的信息成功");
						obj.put("info_list", list);
						System.out.println(obj.toString());
						return obj.toString();
					}else {
						obj.put("is_success", 2);
						obj.put("message", "暂无预约人信息");
						obj.put("info_list", "[]");
						System.out.println(obj.toString());
						return obj.toString();
					}
				
					// 保存信息
					/*FuAppointmentInfo a1 = new FuAppointmentInfo();
					a1.setAvatar("http://wx.hhr360.com/images/p_01.png");
					a1.setPhone("18310150480");
					a1.setQualifyId("S0340113030068");
					a1.setTag("从业8年#明细客户经理#7*24小时指导");
					a1.setUserName("段文武");
					a1.setSecuritiesName("东莞证券");
					a1.setOrderNum(0);
					a1.setIsDel(0);
					
					FuAppointmentInfo a2 = new FuAppointmentInfo();
					a2.setAvatar("http://wx.hhr360.com/images/p_02.png");
					a2.setPhone("18810526716");
					a2.setQualifyId("S0340114080067");
					a2.setTag("从业8年#明细客户经理#7*24小时指导");
					a2.setUserName("李芳");
					a2.setSecuritiesName("东莞证券");
					a2.setOrderNum(1);
					a2.setIsDel(0);
					
					FuAppointmentInfo a3 = new FuAppointmentInfo();
					a3.setAvatar("http://wx.hhr360.com/images/p_03.png");
					a3.setPhone("15210536485");
					a3.setQualifyId("S0340113040079");
					a3.setTag("从业4年#经验丰富#7*24小时指导");
					a3.setUserName("王盾");
					a3.setSecuritiesName("东莞证券");
					a3.setOrderNum(2);
					a3.setIsDel(0);
					
					FuAppointmentInfo a4 = new FuAppointmentInfo();
					a4.setAvatar("wx.hhr360.com/images/p_04.png");
					a4.setPhone("13520926547");
					a4.setQualifyId("S0340113010058");
					a4.setTag("从业3年#高级客户经理#7*24小时指导");
					a4.setUserName("马婷婷");
					a4.setSecuritiesName("东莞证券");
					a4.setOrderNum(3);
					a4.setIsDel(0);
					
					FuAppointmentInfo a5 = new FuAppointmentInfo();
					a5.setAvatar("http://wx.hhr360.com/images/p_05.png");
					a5.setPhone("13653975385");
					a5.setQualifyId("S0340415060018");
					a5.setTag("乐于沟通#期货从业资格证#7*24小时指导");
					a5.setUserName("秦毫");
					a5.setSecuritiesName("东莞证券");
					a5.setOrderNum(4);
					a5.setIsDel(0);
					
					FuAppointmentInfo a6 = new FuAppointmentInfo();
					a6.setAvatar("http://wx.hhr360.com/images/p_06.png");
					a6.setPhone("15726606758");
					a6.setQualifyId("S0340113080036");
					a6.setTag("真诚热情#金牌客户经理#7*24小时指导");
					a6.setUserName("杨洋");
					a6.setSecuritiesName("东莞证券");
					a6.setOrderNum(5);
					a6.setIsDel(0);
					
					FuAppointmentInfo a7 = new FuAppointmentInfo();
					a7.setAvatar("http://wx.hhr360.com/images/p_07.png");
					a7.setPhone("18610966800");
					a7.setQualifyId("S0340411212007");
					a7.setTag("从业5年#明星客户经理#7*24小时指导");
					a7.setUserName("李丹");
					a7.setSecuritiesName("东莞证券");
					a7.setOrderNum(6);
					a7.setIsDel(0);
					
					FuAppointmentInfo a8 = new FuAppointmentInfo();
					a8.setAvatar("http://wx.hhr360.com/images/p_08.png");
					a8.setPhone("17710222450");
					a8.setQualifyId("S0340115040102");
					a8.setTag("坦率热情#明星客户经理#7*24小时指导");
					a8.setUserName("陈天琪");
					a8.setSecuritiesName("东莞证券");
					a8.setOrderNum(7);
					a8.setIsDel(0);
					
					appointmentInfoService.save(a1);
					appointmentInfoService.save(a2);
					appointmentInfoService.save(a3);
					appointmentInfoService.save(a4);
					appointmentInfoService.save(a5);
					appointmentInfoService.save(a6);
					appointmentInfoService.save(a7);
					appointmentInfoService.save(a8);*/
					
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch(Exception e) {
			e.printStackTrace();
			obj.put("message", "系统更新中, 请等下重试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 提现记录
	public String appDrawRecords(String phone, String version, String sign) {
		JSONObject obj = new JSONObject();	
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			if("success".equals(result)){
				if(null == version || "".equals(version)) {
					obj.put("message", "参数不正确");
					obj.put("is_success", 0);
					System.out.println(obj + "缺少参数 version");
					return obj.toString();
				}
				if("2.0.0".equals(version)) {
					if(null == phone || "".equals(phone)){
						obj.put("message", "手机号为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					
					FuUser fuUser = fuUserService.findUserByPhone(phone);
					if (null == fuUser) {
						obj.put("message", "手机号不存在");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					List<FuDrawMoney> list = fuDrawMoneyService.findListByUserId(fuUser.getId());
					List<Object> draws = new ArrayList<Object>();
					if (list != null) {
						for (FuDrawMoney draw : list) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", draw.getId());
							map.put("user_id", draw.getFuUser().getId());
							map.put("draw_time", sdf.format(draw.getDrawTime()));
							map.put("bank_name", draw.getFuBankCard()==null?"":(draw.getFuBankCard().getBankName()==null?"":draw.getFuBankCard().getBankName()));
							map.put("card_number", draw.getFuBankCard()==null?"":(draw.getFuBankCard().getCardNumber()==null?"":draw.getFuBankCard().getCardNumber()));					
							map.put("draw_money", draw.getDrawMoney()==null?"0":draw.getDrawMoney());
//							map.put("status", draw.getStatus()==0?"未审核":draw.getStatus()==1?"审核中":draw.getStatus()==2?"成功":"拒绝");
							// 0: 未审核 1: 审核中 2:成功 3:拒绝
							map.put("recharge_status", draw.getStatus() == null ? 0 : draw.getStatus());
							draws.add(map);
						}
						obj.put("message", "查询成功");
						obj.put("recharges", draws);
						obj.put("is_success", 1);		
					} else {
						obj.put("messge", "暂无提款记录");
						obj.put("recharges", "[]");
						obj.put("is_success", 2);
					}	
				}
				
			}else{
				obj.put("message", result);
				obj.put("is_success", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();				
	}

	// 获取全部的银行名称
	public String getAllBankName(String version, String sign) {
		JSONObject obj = new JSONObject();	
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			if("success".equals(result)){
				if(null == version || "".equals(version)) {
					obj.put("message", "参数不正确");
					obj.put("is_success", 0);
					System.out.println(obj + "缺少参数 version");
					return obj.toString();
				}
				if("2.0.0".equals(version)) {
					List<SysBank> bankList = sysBankService.findAllBank();
					List<Object> banks = new ArrayList<Object>();
					for (SysBank sysBank : bankList) {
						banks.add(sysBank.getBankName() == null ? "银行" : sysBank.getBankName());
					}
					obj.put("message", "查询全部银行名称成功");
					obj.put("is_success", 1);
					obj.put("banks", banks);
				}
				
			}else{
				obj.put("message", result);
				obj.put("is_success", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();	
	}
}
