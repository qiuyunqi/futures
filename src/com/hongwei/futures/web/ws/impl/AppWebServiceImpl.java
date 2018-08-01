package com.hongwei.futures.web.ws.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import nl.bitwalker.useragentutils.UserAgent;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;

import com.alipay.util.AlipayNotify;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ServiceException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSErrorCode;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.model.FuDrawMoney;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuIpBlacklist;
import com.hongwei.futures.model.FuMessage;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuProgramUp;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrAppTemplate;
import com.hongwei.futures.model.HhrAppVersion;
import com.hongwei.futures.model.HhrLogin;
import com.hongwei.futures.model.HhrOrgPerson;
import com.hongwei.futures.model.HhrPromoteParameter;
import com.hongwei.futures.model.HhrQualiRegister;
import com.hongwei.futures.model.HhrRemark;
import com.hongwei.futures.model.HhrStat;
import com.hongwei.futures.model.HhrStatDetail;
import com.hongwei.futures.model.PhoneCode;
import com.hongwei.futures.model.StockStatusRecord;
import com.hongwei.futures.model.SysBank;
import com.hongwei.futures.model.SysBranch;
import com.hongwei.futures.model.SysCity;
import com.hongwei.futures.model.SysProvince;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.service.FuDrawMoneyService;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuIpBlacklistService;
import com.hongwei.futures.service.FuMessageService;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuProgramUpService;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.service.FuServerService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrAppTemplateService;
import com.hongwei.futures.service.HhrAppVersionService;
import com.hongwei.futures.service.HhrLoginService;
import com.hongwei.futures.service.HhrOrgPersonService;
import com.hongwei.futures.service.HhrPromoteParameterService;
import com.hongwei.futures.service.HhrQualiRegisterService;
import com.hongwei.futures.service.HhrRemarkService;
import com.hongwei.futures.service.HhrStatDetailService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.service.PhoneCodeService;
import com.hongwei.futures.service.StockStatusRecordService;
import com.hongwei.futures.service.SysBankService;
import com.hongwei.futures.service.SysBranchService;
import com.hongwei.futures.service.SysCityService;
import com.hongwei.futures.service.SysProvinceService;
import com.hongwei.futures.util.Base64;
import com.hongwei.futures.util.CommonUtils;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.MailEngine;
import com.hongwei.futures.util.MoneyDetailUtil;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.RegexChk;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.ws.AppWebService;
import com.hongwei.futures.zhongqi.ZhongqiService;

@SuppressWarnings("all")
public class AppWebServiceImpl implements AppWebService {

	private String[] types = new String[] { ".bmp", ".png", ".gif", ".jpeg", ".pjpeg", ".jpg" };
	// private static final String ACCESS_ID = "VyeVZti9TY3t2NOU";
	// private static final String ACCESS_KEY =
	// "lR05tqefMFeEShbPvg7CbsAxtBGPka";

	@Resource
	private FuUserService fuUserService;
	@Resource
	private FuAdminService fuAdminService;
	@Resource
	private FuBankCardService fuBankCardService;
	@Resource
	private HhrLoginService hhrLoginService;
	@Resource
	private HhrStatService hhrStatService;
	@Resource
	private HhrStatDetailService hhrStatDetailService;
	@Resource
	private FuSmsLogService fuSmsLogService;
	@Resource
	private HhrQualiRegisterService hhrQualiRegisterService;
	@Resource
	private HhrAppVersionService hhrAppVersionService;
	@Resource
	private SysBankService sysBankService;
	@Resource
	private SysProvinceService sysProvinceService;
	@Resource
	private SysCityService sysCityService;
	@Resource
	private SysBranchService sysBranchService;
	@Resource
	private HhrOrgPersonService hhrOrgPersonService;
	@Resource
	private FuRateService fuRateService;
	@Resource
	private FuProgramService fuProgramService;
	@Resource
	private ZhongqiService zhongqiService;
	@Resource
	private FuRechargeService fuRechargeService;
	@Resource
	private FuDrawMoneyService fuDrawMoneyService;
	@Resource
	private FuMessageService fuMessageService;
	@Autowired
	private FuProgramUpService fuProgramUpService;
	@Autowired
	private HhrPromoteParameterService promoteParameterService;
	@Autowired
	private MailEngine mailEngine;
	@Autowired
	private FuGameService fuGameService;
	@Autowired
	private FuServerService fuServerService;
	@Autowired
	private HhrAppTemplateService hhrAppTemplateService;
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Autowired
	private HhrRemarkService hhrRemarkService;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private StockStatusRecordService stockStatusRecordService;
	@Autowired
	private PhoneCodeService phoneCodeService;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	@Autowired
	private FuIpBlacklistService fuIpBlacklistService;
	@Autowired
	private FuParameterService fuParameterService;

	/**
	 * 登录
	 */
	@Override
	public String login(String phone, String password, String sign) {
		JSONObject obj = new JSONObject();
		try {
			if (ServletActionContext.getRequest().getMethod().equals("POST")) {
				// String result = DesUtil.webserviceSignVerify(sign);
				// if("success".equals(result)){
				if (phone != null && !"".equals(phone) && password != null && !"".equals(password)) {
					FuUser fuUser = fuUserService.findUserByRegPhone(phone);
					if (null != fuUser) {
						if (fuUser.getState() == 0) {
							obj.put("message", "用户已删除");
							obj.put("is_success", 0);
							System.out.println(obj);
							return obj.toString();
						}
						if (fuUser.getPassword().equals(password)) {
							if (fuUser.getNickName() != null) {
								// String ucsynlogin =
								// DiscuzUtil.login(fuUser.getPhone(),
								// CommonUtil.getMd5(fuUser.getPhone().substring(5,
								// 11)));
								// obj.put("ucsynlogin", ucsynlogin);
							}
							obj.put("user_id", fuUser.getId());
							obj.put("invitation_code", fuUser.getInvitationCode() == null ? "" : fuUser.getInvitationCode());
							obj.put("introduction", fuUser.getIntroduction() == null ? "" : fuUser.getIntroduction());
							obj.put("nick_name", fuUser.getNickName() == null ? "" : fuUser.getNickName());
							obj.put("email", fuUser.getEmail() == null ? "" : fuUser.getEmail());
							obj.put("phone", phone);
							obj.put("account_balance", fuUser.getAccountBalance() == null ? 0.00 : fuUser.getAccountBalance());
							obj.put("user_avatar", fuUser.getUserAvatar() == null ? "" : fuUser.getUserAvatar());
							// 0未认证，1待认证，2已认证，3信息有误
							obj.put("is_check_card", fuUser.getIsCheckCard() == null ? 0 : fuUser.getIsCheckCard());
							// 银行卡的张数
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("userId", fuUser.getId());
							map.put("state", 1);
							Integer num = fuBankCardService.countFuBankCard(map);
							obj.put("card_num", num);
							obj.put("totken", fuUser.getRyToken() == null ? "" : fuUser.getRyToken());
							// 交易密码是否存在
							obj.put("is_draw_password", fuUser.getDrawPassword() == null ? 0 : 1);
							// 用户的真实姓名
							obj.put("real_name", fuUser.getUserName() == null ? "" : fuUser.getUserName());
							obj.put("is_success", 1);
							obj.put("message", "登录成功");
							HhrLogin login = new HhrLogin();
							login.setFuUser(fuUser);
							login.setIp(IP4.getIP4(ServletActionContext.getRequest()));
							login.setLoginTime(new Date());
							hhrLoginService.save(login);
						} else {
							obj.put("message", "密码错误");
							obj.put("is_success", 0);
						}
					} else {
						obj.put("message", "该手机号尚未注册");
						obj.put("is_success", 0);
					}
				} else {
					obj.put("message", "缺少参数");
					obj.put("is_success", 0);
				}
				// }else{
				// obj.put("message", result);
				// obj.put("is_success", 0);
				// }
			} else {
				obj.put("message", "非法请求");
				obj.put("is_success", 0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 注册
	 */
	@Override
	public String register(String phone, String password, String nickName, String invitation_code, String reg_type, String code, String avatar, String avatarStr, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String res = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(res)){

			// ip在黑名单里
			FuIpBlacklist ipBlacklist = fuIpBlacklistService.findBlackByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			if (null != ipBlacklist && ipBlacklist.getIsBlack() == 1) {
				obj.put("message", "请不要频繁注册");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			// ip注册数量超出限制
			Integer count = fuUserService.findCountByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			FuParameter fuParameter = fuParameterService.findParameter();
			if (count >= fuParameter.getRegNum()) {
				obj.put("message", "您的IP今天已经超出了注册数量");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			// 当前时间超出最后次注册时间加上注册间隔时间
			FuUser user = fuUserService.findUserByRegIp(IP4.getIP4(ServletActionContext.getRequest()));
			if (null != user) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(user.getRegisterTime());
				cal.add(Calendar.MINUTE, fuParameter.getRegInterval());
				if (cal.getTime().getTime() > (new Date().getTime())) {
					obj.put("message", "请不要频繁注册");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (phone != null && !"".equals(phone)) {
				if (phone.length() != 11) {
					obj.put("message", "手机号长度错误");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			if (fuUserService.findUserByRegPhone(phone) != null) {
				obj.put("message", "该手机号已被注册");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (password == null || "".equals(password)) {
				obj.put("message", "密码为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			/*
			 * if(code == null || "".equals(code)){ obj.put("message",
			 * "验证码不能为空"); obj.put("is_success", 0); System.out.println(obj);
			 * return obj.toString(); }
			 */
			if (!"4".equals(reg_type)) {
				if (nickName == null || "".equals(nickName)) {
					obj.put("message", "昵称为空");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
				if (fuUserService.findUserByNickName(nickName) != null) {
					obj.put("message", "昵称已被注册");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			if (invitation_code == null || "".equals(invitation_code)) {
				obj.put("message", "邀请码或邀请人手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			/*
			 * String[] strs = phoneCode.split("#"); String sessionPhone =
			 * strs[0]; String sessionCode = strs[1]; if(sessionPhone != phone
			 * || sessionCode != code){ obj.put("message", "验证码不匹配");
			 * obj.put("is_success", 0); System.out.println(obj); return
			 * obj.toString(); }
			 */
			/*
			 * PhoneCode pc = phoneCodeService.getByPhone(phone); if(pc ==
			 * null){ obj.put("is_success", 0); obj.put("message", "请重新获取验证码");
			 * System.out.println(obj.toString()); return obj.toString(); }
			 * String userPhone = pc.getPhone(); String userCode = pc.getCode();
			 * if(!userPhone.equals(phone) || !userCode.equals(code)){
			 * obj.put("is_success", 0); obj.put("message", "验证码不正确!");
			 * System.out.println(obj.toString()); return obj.toString(); }
			 */
			if (invitation_code != null && !"".equals(invitation_code)) {
				// 邀请码
				if (invitation_code.length() == 12) {
					if (fuUserService.findUserByRegInvitationcode(invitation_code) == null) {
						obj.put("message", "邀请码不存在");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
				} else {
					// 邀请人手机号
					if (fuUserService.findUserByPhone(invitation_code) == null) {
						obj.put("message", "邀请人手机号不存在");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					} else {
						invitation_code = fuUserService.findUserByPhone(invitation_code).getInvitationCode();
					}
				}
			}
			// 添加用户头像
			if (avatarStr != null && !"".equals(avatarStr) && avatar != null && !"".equals(avatar)) {
				// 将字符串转换为byte字节数组
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] fileByte = decoder.decodeBuffer(avatarStr);
				for (int i = 0; i < fileByte.length; ++i) {
					if (fileByte[i] < 0) {// 调整异常数据
						fileByte[i] += 256;
					}
				}
				avatar = imageFileUpload(fileByte, avatar, "_face");
			} else {
				avatar = null;
			}
			String result = fuUserService.saveUser(phone, password, nickName, null, invitation_code, Integer.valueOf(reg_type), avatar);
			if (result != null) {
				// 登录
				FuUser fuUser = fuUserService.findUserByRegPhone(phone);
				obj.put("user_id", fuUser.getId());
				obj.put("invitation_code", fuUser.getInvitationCode() == null ? "" : fuUser.getInvitationCode());
				obj.put("introduction", fuUser.getIntroduction() == null ? "" : fuUser.getIntroduction());
				obj.put("nick_name", fuUser.getNickName() == null ? "" : fuUser.getNickName());
				obj.put("email", fuUser.getEmail() == null ? "" : fuUser.getEmail());
				obj.put("phone", phone);
				obj.put("account_balance", fuUser.getAccountBalance() == null ? 0.00 : fuUser.getAccountBalance());
				obj.put("user_avatar", fuUser.getUserAvatar() == null ? "" : fuUser.getUserAvatar());
				// 0未认证，1待认证，2已认证，3信息有误
				obj.put("", fuUser.getIsCheckCard() == null ? 0 : fuUser.getIsCheckCard());
				// 银行卡的张数
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("userId", fuUser.getId());
				Integer num = fuBankCardService.countFuBankCard(map);
				obj.put("card_num", num);
				// 交易密码是否存在
				obj.put("is_draw_password", fuUser.getPassword() == null ? 0 : 1);
				// 用户的真实姓名
				obj.put("real_name", fuUser.getUserName() == null ? "" : fuUser.getUserName());
				obj.put("totken", fuUser.getRyToken() == null ? "" : fuUser.getRyToken());
				HhrLogin login = new HhrLogin();
				login.setFuUser(fuUser);
				login.setIp(IP4.getIP4(ServletActionContext.getRequest()));
				login.setLoginTime(new Date());
				hhrLoginService.save(login);
				obj.put("is_success", 1);
				obj.put("message", result);
			} else {
				obj.put("message", "上级用户不存在");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", res);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 主界面
	 */
	@Override
	public String index(Long user_id, String weixin_code, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (user_id == null && (weixin_code == null || "".equals(weixin_code))) {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (weixin_code != null && !"".equals(weixin_code)) {
				FuUser u = fuUserService.findUserByWeixinCode(weixin_code);
				if (u == null) {
					obj.put("message", "没有此用户");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				} else {
					user_id = u.getId();
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user_id);
			Object[] user = fuUserService.findUserDataByMap(map);
			if (user != null) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", user[0]);
				map1.put("account_name", user[1] == null ? "" : user[1]);
				map1.put("user_avatar", user[2] == null ? "" : user[2]);
				map1.put("phone", user[3] == null ? "" : user[3]);
				map1.put("is_check_card", user[4]);
				map1.put("user_name", user[5] == null ? "" : user[5]);
				map1.put("card_number", user[6] == null ? "" : user[6]);
				map1.put("gender", user[7] == null ? "" : "0".equals(user[7].toString()) ? "男" : "1".equals(user[7].toString()) ? "女" : "");
				map1.put("is_marriage", user[8] == null ? "" : "0".equals(user[8].toString()) ? "" : "1".equals(user[8].toString()) ? "未婚" : "2".equals(user[8].toString()) ? "已婚" : "3".equals(user[8].toString()) ? "离异" : "4".equals(user[8].toString()) ? "丧偶" : "");
				/*
				 * String cityName = ""; if(user[19]!=null){ Long cityId =
				 * Long.valueOf(user[19].toString());
				 * if(sysCityService.get(cityId)!=null){ cityName =
				 * sysCityService.get(cityId).getCityName(); } }
				 * map1.put("live_address", user[18]==null?"":user[18] +
				 * cityName + (user[9]==null?"":user[9]));
				 */
				map1.put("live_address", user[9] == null ? "" : user[9]);
				map1.put("max_degree", user[10] == null ? "" : "0".equals(user[10].toString()) ? "初中及以下" : "1".equals(user[10].toString()) ? "高中" : "2".equals(user[10].toString()) ? "大专" : "3".equals(user[10].toString()) ? "本科" : "4".equals(user[10].toString()) ? "本科以上" : "其它");
				map1.put("business_type", user[11] == null ? "" : user[11]);
				map1.put("position_name", user[12] == null ? "" : user[12]);
				map1.put("salary", user[13] == null ? "0" : (user[13].toString()));
				map1.put("introduction", user[14] == null ? "" : user[14]);
				map1.put("nick_name", user[15] == null ? "" : user[15]);
				map1.put("email", user[16] == null ? "" : user[16]);
				map1.put("invitation_code", user[17] == null ? "" : user[17]);
				map1.put("draw_password", user[20] == null ? "" : user[20]);
				map1.put("province_id", user[21] == null ? "" : user[21]);
				map1.put("city_id", user[19] == null ? "" : user[19]);
				map1.put("is_boss", "0");
				List<HhrQualiRegister> userQuali = hhrQualiRegisterService.findQualiByUser(user_id, "2");
				List<FuBankCard> userBank = fuBankCardService.findListByUser(user_id);
				if (userQuali != null && userQuali.size() > 0) {
					map1.put("quali_checked", "1");
				} else {
					map1.put("quali_checked", "0");
				}
				if (userBank != null && userBank.size() > 0) {
					map1.put("has_bank", "1");
				} else {
					map1.put("has_bank", "0");
				}
				obj.put("user", map1);
				Object[] stat = hhrStatService.findStatDataByMap(map);
				map.put("stat_date", sdf.format(new Date()));
				map.put("yyyy_mm", ym.format(new Date()));
				List<Object[]> detail = hhrStatDetailService.findStatDataByMap(map);
				Map<String, Object> map2 = new HashMap<String, Object>();
				if (stat != null) {
					map2.put("hhr_level", stat[0] != null ? stat[0] : "0");
					map2.put("firstly_partner_num", stat[1] != null ? stat[1] : "0");
					map2.put("secondary_partner_num", stat[2] != null ? stat[2] : "0");
					map2.put("extend_person_new", stat[3] != null ? stat[3] : "0");
					map2.put("interest_return_coefficient", stat[4] != null ? stat[4] : "0");
					map2.put("charges_return_coefficient", stat[5] != null ? stat[5] : "0");
					map2.put("daily_income", detail.get(0) != null ? (detail.get(0)[0] != null ? detail.get(0)[0] : "0") : "0");
					map2.put("monthly_income", detail.get(1) != null ? (detail.get(1)[0] != null ? detail.get(1)[0] : "0") : "0");
					// map2.put("daily_income", stat[6] != null ? stat[6]:"0");
					// map2.put("monthly_income", stat[7] != null ?
					// stat[7]:"0");
					map2.put("total_income", stat[8] != null ? stat[8] : "0");
				} else {
					map2.put("hhr_level", "0");
					map2.put("firstly_partner_num", "0");
					map2.put("secondary_partner_num", "0");
					map2.put("extend_person_new", "0");
					map2.put("interest_return_coefficient", "0");
					map2.put("charges_return_coefficient", "0");
					map2.put("daily_income", "0");
					map2.put("monthly_income", "0");
					map2.put("total_income", "0");
				}
				obj.put("stat", map2);
				HhrAppTemplate appTemp = hhrAppTemplateService.get(1L);
				obj.put("appTemplate", appTemp.getTemplate());
				obj.put("message", "成功");
				obj.put("is_success", 1);
			} else {
				obj.put("message", "没有此用户！");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app获取用户合伙人列表
	 * 
	 * @param user_id
	 * @return
	 */
	@Override
	public String partners(Long user_id, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			boolean flag = false;
			if (user_id != null) {
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 封装user对象
				FuUser fuUser = fuUserService.get(user_id);
				if (null != fuUser) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("phone", fuUser.getPhone());
					map1.put("user_avatar", fuUser.getUserAvatar());
					map1.put("account_name", fuUser.getAccountName());
					map1.put("register_time", fuUser.getRegisterTime() == null ? null : sdf2.format(fuUser.getRegisterTime()));
					map1.put("last_login_time", fuUser.getLoginTime() == null ? null : sdf2.format(fuUser.getLoginTime()));
					obj.put("user", map1);
				} else {
					obj.put("message", "用户不存在");
					obj.put("is_success", 0);
				}

				// 封装partners对象,当前用户的所有下级hhrstat
				List<HhrStat> stat = hhrStatService.findUserByParentId(user_id);
				List<Object> partners = new ArrayList<Object>();
				if (null != stat) {
					for (HhrStat hhrStat : stat) {
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("id", hhrStat.getId());
						map2.put("account_name", hhrStat.getFuUser().getAccountName());
						map2.put("user_id", hhrStat.getFuUser().getId());
						map2.put("firstly_partner_num", hhrStat.getFirstlyPartnerNum() == null ? "0" : hhrStat.getFirstlyPartnerNum());
						map2.put("secondary_partner_num", hhrStat.getSecondaryPartnerNum() == null ? "0" : hhrStat.getSecondaryPartnerNum());
						map2.put("monthly_stock_endowment", hhrStat.getMonthlyStockEndowment() == null ? "0" : hhrStat.getMonthlyStockEndowment());
						map2.put("monthly_futures_endowment", hhrStat.getMonthlyFuturesEndowment() == null ? "0" : hhrStat.getMonthlyFuturesEndowment());
						map2.put("nick_name", hhrStat.getFuUser().getNickName() == null ? "" : hhrStat.getFuUser().getNickName());
						map2.put("user_avatar", hhrStat.getFuUser().getUserAvatar() == null ? "" : hhrStat.getFuUser().getUserAvatar());
						HhrRemark hhrRemark = hhrRemarkService.findRemarkByUserId(user_id, hhrStat.getFuUser().getId());
						map2.put("remark_name", hhrRemark == null ? "" : hhrRemark.getRemarkName());
						map2.put("phone", hhrStat.getFuUser().getPhone() == null ? "" : hhrStat.getFuUser().getPhone());
						map2.put("introduction", hhrStat.getFuUser().getIntroduction() == null ? "" : hhrStat.getFuUser().getIntroduction());
						partners.add(map2);
					}
					obj.put("partners", partners);
					flag = true;
				}

				// 封装statinfo对象
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", user_id);
				map.put("stat_date", sdf.format(new Date()));
				HhrStat hhrStat = hhrStatService.findStatDataByMap2(map);
				Map<String, Object> map3 = new HashMap<String, Object>();
				if (null != hhrStat) {
					map3.put("id", hhrStat.getId());
					map3.put("user_id", hhrStat.getFuUser().getId());
					map3.put("stat_date", hhrStat.getStatDate() == null ? "" : sdf.format(hhrStat.getStatDate()));
					map3.put("hhr_parentID", hhrStat.getHhrParentID());
					map3.put("hhr_parent_inivtation", null);
					map3.put("extend_person_new", hhrStat.getExtendPersonNew() == null ? "0" : hhrStat.getExtendPersonNew());
					map3.put("total_income", hhrStat.getTotalIncome() == null ? "0" : hhrStat.getTotalIncome());
					map3.put("account_income_balance", hhrStat.getAccountIncomeBalance() == null ? "0" : hhrStat.getAccountIncomeBalance());
					map3.put("daily_income", hhrStat.getDailyIncome() == null ? "0" : hhrStat.getDailyIncome());
					map3.put("stock_endowment", hhrStat.getStockEndowment() == null ? "0" : hhrStat.getStockEndowment());
					map3.put("futures_endowment", hhrStat.getFuturesEndowment() == null ? "0" : hhrStat.getFuturesEndowment());
					map3.put("stock_commission", hhrStat.getStockCommission() == null ? "0" : hhrStat.getStockCommission());
					map3.put("futures_commission", hhrStat.getFuturesCommission() == null ? "0" : hhrStat.getFuturesCommission());
					map3.put("contribution_income", hhrStat.getContributionIncome() == null ? "0" : hhrStat.getContributionIncome());
					map3.put("interest_return_coefficient", hhrStat.getInterestReturnCoefficient() == null ? "0" : hhrStat.getInterestReturnCoefficient());
					map3.put("charges_return_coefficient", hhrStat.getChargesReturnCoefficient() == null ? "0" : hhrStat.getChargesReturnCoefficient());
					map3.put("bonus_coefficient", hhrStat.getBonusCoefficient() == null ? "0" : hhrStat.getBonusCoefficient());
					map3.put("last_login_ip_today", hhrStat.getLastLoginIpToday());
					map3.put("is_true", true);
					obj.put("statinfo", map3);
				} else {
					map3.put("id", null);
					map3.put("user_id", null);
					map3.put("stat_date", null);
					map3.put("hhr_parentID", null);
					map3.put("hhr_parent_inivtation", null);
					map3.put("extend_person_new", "0");
					map3.put("total_income", "0");
					map3.put("account_income_balance", "0");
					map3.put("daily_income", "0");
					map3.put("stock_endowment", "0");
					map3.put("futures_endowment", "0");
					map3.put("stock_commission", "0");
					map3.put("futures_commission", "0");
					map3.put("contribution_income", "0");
					map3.put("interest_return_coefficient", "0");
					map3.put("charges_return_coefficient", "0");
					map3.put("bonus_coefficient", "0");
					map3.put("last_login_ip_today", null);
					map3.put("is_true", false);
					obj.put("statinfo", map3);
					// obj.put("message", "无hhrStat数据");
					obj.put("is_success", 0);
				}

				// 封装上级用户
				FuUser parentUser = fuUserService.get(fuUser.getHhrParentID());
				HhrStat hhrStat2 = hhrStatService.findHhrStatByUser(parentUser.getId());
				Map<String, Object> map4 = new HashMap<String, Object>();
				map4.put("id", parentUser.getId());
				map4.put("account_name", parentUser.getAccountName());
				map4.put("user_id", parentUser.getId());
				map4.put("firstly_partner_num", hhrStat2.getFirstlyPartnerNum() == null ? "0" : hhrStat2.getFirstlyPartnerNum());
				map4.put("secondary_partner_num", hhrStat2.getSecondaryPartnerNum() == null ? "0" : hhrStat2.getSecondaryPartnerNum());
				map4.put("monthly_stock_endowment", hhrStat2.getMonthlyStockEndowment() == null ? "0" : hhrStat2.getMonthlyStockEndowment());
				map4.put("monthly_futures_endowment", hhrStat2.getMonthlyFuturesEndowment() == null ? "0" : hhrStat2.getMonthlyFuturesEndowment());
				map4.put("nick_name", parentUser.getNickName() == null ? "" : parentUser.getNickName());
				map4.put("user_avatar", parentUser.getUserAvatar() == null ? "" : parentUser.getUserAvatar());
				HhrRemark hhrRemark = hhrRemarkService.findRemarkByUserId(user_id, parentUser.getId());
				map4.put("remark_name", hhrRemark == null ? "" : hhrRemark.getRemarkName());
				map4.put("phone", parentUser.getPhone() == null ? "" : parentUser.getPhone());
				map4.put("introduction", parentUser.getIntroduction() == null ? "" : parentUser.getIntroduction());
				obj.put("parentUser", map4);
				flag = true;
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}

			if (flag) {
				obj.put("message", "查询合伙人列表成功");
				obj.put("is_success", 1);
			} else {
				obj.put("message", "查询合伙人列表失败");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 修改密码
	 */
	@Override
	public String updatePwd(String phone, String newpwd, String phone_code, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone) && newpwd != null && !"".equals(newpwd) && phone_code != null && !"".equals(phone_code)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (null == fuUser) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					if (phone.equals(fuUser.getPhone()) && phone_code.equals(fuUser.getPhoneCode())) {
						fuUser.setPassword(newpwd);
						fuUserService.save(fuUser);
						obj.put("message", "修改成功");
						obj.put("is_success", 1);
					} else {
						obj.put("message", "手机号码和验证码不匹配");
						obj.put("is_success", 0);
					}
				}
			} else {
				obj.put("message", "非法请求");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 修改密码发送手机验证码
	 */
	@Override
	public String updatePwdPhoneCode(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByRegPhone(phone);
				if (null != fuUser) {
					DecimalFormat format = new DecimalFormat("0000");
					String code = format.format(Math.random() * 9999);
					String message = URLDecoder.decode("[修改密码]您的手机验证码是：" + code, "UTF-8");
					FuSmsLog log = new FuSmsLog();
					log.setContent(message);
					log.setPrio(1);
					log.setReason("修改密码");
					log.setDestination(phone);
					log.setPlanTime(new Date());
					log.setType(1);// 短信

					fuUser.setPhoneCode(code);// 保存注册验证码到用户表
					// 验证码过期时间（1个小时）
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.HOUR_OF_DAY, 1);
					fuUser.setPhoneCodeTime(cal.getTime());

					log.setState(0);
					obj.put("message", "请求成功");
					obj.put("is_success", 1);
					// obj.put("phone_code", code);
					fuUserService.saveReg(fuUser, log);
				} else {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "生成手机验证码失败");
			obj.put("is_success", 1);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 注册发送短信验证码
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String regPhoneCode(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			if (ServletActionContext.getRequest().getMethod().equals("POST")) {
				// String result = DesUtil.webserviceSignVerify(sign);
				// if("success".equals(result)){
				FuUser u = fuUserService.findUserByRegPhone(phone);
				if (null == u) {
					DecimalFormat format = new DecimalFormat("0000");
					String code = format.format(Math.random() * 9999);
					String message = URLDecoder.decode("欢迎注册成为超级合伙人，您的手机验证码是:" + code + "，请填入注册界面完成注册。", "UTF-8");
					FuSmsLog log = new FuSmsLog();
					log.setContent(message);
					log.setPrio(1);
					log.setReason("用户注册");
					log.setDestination(phone);
					log.setPlanTime(new Date());
					FuParameter fuParameter = fuParameterService.findParameter();
					if (fuParameter.getMessageType() == 1) { // 暂时注释，后面开启
						log.setType(4);
					} else {
						log.setType(3);
					}
					// log.setType(4);
					log.setState(0);
					log.setRegCode(code); // 发送的码
					obj.put("message", "验证码发送成功");
					obj.put("is_success", 1);
					fuSmsLogService.save(log);

					PhoneCode pc = phoneCodeService.getByPhone(phone);
					if (pc != null) {
						phoneCodeService.delete(pc.getId());
					}
					PhoneCode p = new PhoneCode();
					p.setPhone(phone);
					p.setCode(code);
					phoneCodeService.save(p);
					System.out.println(code);
				} else {
					obj.put("message", "手机号已存在");
					obj.put("is_success", 0);
				}
				// }else{
				// obj.put("message", result);
				// obj.put("is_success", 0);
				// }
			} else {
				obj.put("message", "invalid request");
				obj.put("is_success", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 个人资料
	 * 
	 * @param phone
	 * @param fileByteStr
	 * @param user_avatar
	 * @param introduction
	 * @param nick_name
	 * @param email
	 * @return
	 */
	@Override
	public String updateUserInfo(String phone, String nick_name, Integer gender, Integer is_marriage, Long province_id, Long city_id, String live_address, Integer max_degree, String business_type, String position_name, BigDecimal salary, String fileByteStr, String user_avatar, String introduction, String email, String sign) {
		JSONObject obj = new JSONObject();
		String saveUrl = null;
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (fuUser == null) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					if (nick_name != null && !"".equals(nick_name)) {
						if (null != fuUser.getNickName() && !"".equals(fuUser.getNickName()) && nick_name.equals(fuUser.getNickName())) {
							obj.put("message", "昵称已存在,请重新输入");
							obj.put("is_success", 0);
							System.out.println(obj);
							return obj.toString();
						} else {
							fuUser.setNickName(nick_name);
						}
					}
					if (gender != null) {
						fuUser.setGender(gender);
					}
					if (is_marriage != null) {
						fuUser.setIsMarriage(is_marriage);
					}
					if (province_id != null) {
						SysProvince pro = sysProvinceService.get(province_id);
						if (pro != null) {
							fuUser.setProvinceId(province_id);
							fuUser.setProvinceName(pro.getProvinceName());
						}
					}
					if (city_id != null) {
						SysCity city = sysCityService.get(city_id);
						if (city != null) {
							fuUser.setCityId(city_id);
							fuUser.setCityName(city.getCityName());
						}
					}
					if (live_address != null && !"".equals(live_address)) {
						fuUser.setLiveAddress(live_address);
					}
					if (max_degree != null) {
						fuUser.setMaxDegree(max_degree);
					}
					if (business_type != null && !"".equals(business_type)) {
						fuUser.setBusinessType(business_type);
					}
					if (position_name != null && !"".equals(position_name)) {
						fuUser.setPositionName(position_name);
					}
					if (salary != null) {
						fuUser.setSalary(salary);
					}
					if (fileByteStr != null && !"".equals(fileByteStr) && user_avatar != null && !"".equals(user_avatar)) {
						// 将字符串转换为byte字节数组
						BASE64Decoder decoder = new BASE64Decoder();
						byte[] fileByte = decoder.decodeBuffer(fileByteStr);
						for (int i = 0; i < fileByte.length; ++i) {
							if (fileByte[i] < 0) {// 调整异常数据
								fileByte[i] += 256;
							}
						}
						saveUrl = imageFileUpload(fileByte, user_avatar, "_face");
						if (saveUrl != null) {
							fuUser.setUserAvatar(saveUrl);
						} else {
							obj.put("message", "头像上传失败");
							obj.put("is_success", 0);
							System.out.println(obj);
							return obj.toString();
						}
					}
					if (introduction != null && !"".equals(introduction)) {
						fuUser.setIntroduction(introduction);
					}
					if (email != null && !"".equals(email)) {
						fuUser.setEmail(email);
					}
					fuUserService.save(fuUser);
					obj.put("message", "修改成功");
					obj.put("is_success", 1);
					obj.put("saveUrl", saveUrl == null ? (fuUser.getUserAvatar() == null ? "" : fuUser.getUserAvatar()) : saveUrl);
					obj.put("introduction", fuUser.getIntroduction() == null ? "" : fuUser.getIntroduction());
					obj.put("nick_name", fuUser.getNickName() == null ? "" : fuUser.getNickName());
					obj.put("email", fuUser.getEmail() == null ? "" : fuUser.getEmail());
				}
			} else {
				obj.put("message", "非法请求");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	public String imageFileUpload(byte[] fileByte, String user_avatar, String suffix) throws Exception {
		String fileType = ".jpg";
		for (int i = 0; i < types.length; i++) {
			if (types[i].equalsIgnoreCase(user_avatar.substring(user_avatar.lastIndexOf(".")))) {
				if (types[i].endsWith(".gif"))
					fileType = ".gif";
				if (types[i].endsWith(".png"))
					fileType = ".png";
			}
		}
		String fileName = (System.currentTimeMillis() + (new Random(999999).nextLong())) + suffix + fileType;
		try {
			InputStream input = new ByteArrayInputStream(fileByte);
			String bucketName = "hhr360oss";
			// 使用默认的OSS服务器地址创建OSSClient对象。
			OSSClient client = new OSSClient(Property.getProperty("OSS_ACCESS_ID"), Property.getProperty("OSS_ACCESS_KEY"));
			ensureBucket(client, bucketName);
			ObjectMetadata objectMeta = new ObjectMetadata();
			objectMeta.setContentLength(fileByte.length);
			client.putObject(bucketName, fileName, input, objectMeta);
			String saveUrl = Property.getProperty("OSS_URL") + fileName;
			// response.put("fileName", user_avatar);
			// response.put("fileSize", (int) size / 1024);
			// response.put("error", 0);
			// response.put("url", saveUrl);
			// response.put("saveDir", saveUrl);
			return saveUrl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void ensureBucket(OSSClient client, String bucketName) throws OSSException, ClientException {
		try {
			// 创建bucket
			client.createBucket(bucketName);
			client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
		} catch (ServiceException e) {
			if (!OSSErrorCode.BUCKES_ALREADY_EXISTS.equals(e.getErrorCode())) {
				// 如果Bucket已经存在，则忽略
				throw e;
			}
		}
	}

	/**
	 * app实名认证
	 * 
	 * @param phone
	 * @param user_name
	 * @param card_number
	 * @param card_before_pic
	 * @param card_behind_pic
	 * @return
	 */
	@Override
	public String certificateUser(String phone, String user_name, String card_number, String beforeByteStr, String card_before_pic, String behindByteStr, String card_behind_pic, String handByteStr, String card_hand_pic, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (fuUser == null) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					String beforeUrl = null;
					String behindUrl = null;
					String handUrl = null;
					if (user_name == null || "".equals(user_name)) {
						obj.put("message", "真实姓名为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					if (card_number == null || "".equals(card_number)) {
						obj.put("message", "身份证号为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					// 如果一个身份证号码已经被某个手机号认证，那么就不得拿来在其他手机号下认证
					FuUser idUser = fuUserService.findUserByCardNumber(card_number);
					if (null != idUser) {
						obj.put("message", "身份证号已经被认证");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					fuUser.setUserName(user_name);
					fuUser.setCardNumber(card_number);
					if (fuUser.getIsCheckCard() == null || fuUser.getIsCheckCard() == 0) {
						fuUser.setSafeLevel(fuUser.getSafeLevel() == null ? 1 : (fuUser.getSafeLevel() + 1));
					}
					fuUser.setIsCheckCard(1);
					BASE64Decoder decoder = new BASE64Decoder();
					if (beforeByteStr != null && !"".equals(beforeByteStr) && card_before_pic != null && !"".equals(card_before_pic)) {
						byte[] beforeByte = decoder.decodeBuffer(beforeByteStr);
						beforeUrl = imageFileUpload(beforeByte, card_before_pic, "_identity");
					}
					if (behindByteStr != null && !"".equals(behindByteStr) && card_behind_pic != null && !"".equals(card_behind_pic)) {
						byte[] behindByte = decoder.decodeBuffer(behindByteStr);
						behindUrl = imageFileUpload(behindByte, card_behind_pic, "_identity");
					}
					if (handByteStr != null && !"".equals(handByteStr) && card_hand_pic != null && !"".equals(card_hand_pic)) {
						byte[] handByte = decoder.decodeBuffer(handByteStr);
						handUrl = imageFileUpload(handByte, card_hand_pic, "_identity");
					}

					if (beforeUrl != null) {
						fuUser.setCardBeforePic(beforeUrl);
					} else {
						obj.put("message", "身份证正面照片上传失败");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}

					if (behindUrl != null) {
						fuUser.setCardBehindPic(behindUrl);
					} else {
						obj.put("message", "身份证背面照片上传失败");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}

					if (handUrl != null) {
						fuUser.setCardHandPic(handUrl);
					} else {
						obj.put("message", "身份证手持照片上传失败");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					fuUserService.save(fuUser);
					obj.put("message", "请求成功");
					obj.put("is_check_card", fuUser.getIsCheckCard() == null ? "" : fuUser.getIsCheckCard());
					obj.put("is_success", 1);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			obj.put("message", "非法请求");
			obj.put("is_success", 0);
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app银行卡绑定
	 * 
	 * @param phone
	 * @param account_name
	 * @param bank_name
	 * @param card_number
	 * @param bank_province
	 * @param bank_city
	 * @param bank_branch_name
	 * @param draw_password
	 * @return
	 */
	@Override
	public String bindingCard(String phone, String account_name, String bank_name, String card_number, String bank_province, String bank_city, String bank_branch_name, String draw_password, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (fuUser == null) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					// 实名认证之后才能绑定银行卡
					List<FuUser> checkedUserList = fuUserService.findListByUser(fuUser.getId());
					if (null != checkedUserList && checkedUserList.size() == 0) {
						obj.put("message", "用户还未实名认证");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					if (account_name == null || "".equals(account_name)) {
						obj.put("message", "开户姓名为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					if (card_number == null || "".equals(card_number)) {
						obj.put("message", "银行卡号为空");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
					FuBankCard fuBankCard = new FuBankCard();
					fuBankCard.setUserId(fuUser.getId());
					fuBankCard.setAccountName(account_name);
					fuBankCard.setCardNumber(card_number);

					Long sysBankId = null;
					Long sysCityId = null;
					if (bank_name != null && !"".equals(bank_name)) {
						SysBank sysBank = sysBankService.findBy(bank_name);
						if (sysBank != null) {
							fuBankCard.setBankNameId(sysBank.getId());
							fuBankCard.setBankName(bank_name);
							sysBankId = sysBank.getId();
						}
					}

					if (bank_province != null && !"".equals(bank_province)) {
						SysProvince sysProvince = sysProvinceService.findByName(bank_province);
						if (sysProvince != null) {
							fuBankCard.setBankProvince(sysProvince.getId());
						}
					}

					if (bank_city != null && !"".equals(bank_city)) {
						SysCity sysCity = sysCityService.findByCityName(bank_city);
						if (sysCity != null) {
							fuBankCard.setBankCity(sysCity.getId());
							sysCityId = sysCity.getId();
						}
					}

					if (sysBankId != null && sysCityId != null && bank_branch_name != null && !"".equals(bank_branch_name)) {
						SysBranch sysBranch = sysBranchService.findByParams(sysBankId, sysCityId, bank_branch_name);
						if (sysBranch != null) {
							fuBankCard.setBankBranch(sysBranch.getId());
							fuBankCard.setBankBranchName(bank_branch_name);
						}
					}

					if (draw_password != null && !"".equals(draw_password)) {
						fuUser.setDrawPassword(CommonUtils.getMd5(draw_password));
					}

					fuBankCard.setBankAddress(bank_province + bank_city + bank_branch_name);
					fuBankCard.setState(1);
					fuBankCardService.save(fuBankCard);
					obj.put("message", "绑定成功");
					obj.put("is_success", 1);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			obj.put("message", "非法请求");
			obj.put("is_success", 0);
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app资格证登记
	 * 
	 * @param card_number
	 * @return
	 */
	@Override
	public String qualificationRegister(String phone, String user_name, String quali_num, String qualiByteStr, String quali_pic, String quali_type, String sign) {
		JSONObject obj = new JSONObject();
		String saveUrl = null;
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (null == fuUser) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					// 实名认证之后才能进行资格证登记, 资格证登记时, 先到网站数据中校验, 如找到记录, 则说明是有效证书, 允许登记
					List<FuUser> checkedUserList = fuUserService.findListByUser(fuUser.getId());
					if (checkedUserList.size() == 0) {
						obj.put("message", "用户还未实名认证");
						obj.put("is_success", 0);
					} else if (user_name == null || "".equals(user_name)) {
						obj.put("message", "真实姓名为空");
						obj.put("is_success", 0);
					} else if (quali_num == null || "".equals(quali_num)) {
						obj.put("message", "资格证号为空");
						obj.put("is_success", 0);
					} else if (quali_type == null || "".equals(quali_type)) {
						obj.put("message", "资格证类型为空");
						obj.put("is_success", 0);
					} else {
						List<HhrQualiRegister> qualiNumList = hhrQualiRegisterService.countByQualiNum(quali_num);
						List<HhrOrgPerson> dataList = hhrOrgPersonService.countByUserAndCer(fuUser.getUserName(), quali_num);
						List<HhrQualiRegister> qualiedList = hhrQualiRegisterService.countByUserAndType(fuUser.getId(), Integer.valueOf(quali_type));
						if (qualiNumList.size() > 0) {
							obj.put("message", "资格证号不能重复");
							obj.put("is_success", 0);
						} else if (dataList.size() == 0) {
							obj.put("message", "资格证号无效");
							obj.put("is_success", 0);
						} else if (qualiedList.size() > 0) {
							obj.put("message", "您已上传过此类型的资格证书");
							obj.put("is_success", 0);
						} else {
							HhrQualiRegister h = new HhrQualiRegister();
							h.setFuUser(fuUser);
							h.setUserName(user_name);
							h.setQualiNum(quali_num);
							if (qualiByteStr != null && !"".equals(qualiByteStr) && quali_pic != null && !"".equals(quali_pic)) {
								BASE64Decoder decoder = new BASE64Decoder();
								byte[] fileByte = decoder.decodeBuffer(qualiByteStr);
								saveUrl = imageFileUpload(fileByte, quali_pic, "quali");
								if (saveUrl != null) {
									h.setQualiPic(quali_pic);
								} else {
									obj.put("message", "资格证照片上传失败");
									obj.put("is_success", 0);
									System.out.println(obj);
									return obj.toString();
								}
							}
							h.setCreateDate(new Date());
							h.setIsChecked(2);
							h.setType(Integer.valueOf(quali_type));
							hhrQualiRegisterService.save(h);

							// 资格认证后对用户发钱
							/*
							 * HhrStatDetail regIncomeDetail =
							 * hhrStatDetailService
							 * .findHhrStatDetailByUserAndIncomeType
							 * (fuUser.getId(), 1); if(regIncomeDetail == null){
							 * HhrPromoteParameter param =
							 * promoteParameterService.findParameter();
							 * if(param.getIsOpen() != 0 &&
							 * param.getTotalMoney().compareTo(new
							 * BigDecimal(100)) != -1){
							 * hhrStatService.updateHhrIncome(fuUser.getId(),
							 * param.getLineMoney(), 1, null);
							 * param.setTotalMoney
							 * (param.getTotalMoney().subtract(new
							 * BigDecimal(100)));
							 * promoteParameterService.save(param); } }
							 */
							obj.put("message", "登记成功");
							obj.put("is_success", 1);
							// obj.put("saveUrl",saveUrl);
						}
					}
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			obj.put("message", "非法请求");
			obj.put("is_success", 0);
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 修改提款密码
	 * 
	 * @param phone
	 * @param new_drawpwd
	 * @param phone_code
	 * @return
	 */
	@Override
	public String updateDrawPwd(String phone, String new_drawpwd, String phone_code, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && new_drawpwd != null && phone_code != null && !"".equals(phone) && !"".equals(new_drawpwd) && !"".equals(phone_code)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (null == fuUser) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					if (phone.equals(fuUser.getPhone()) && phone_code.equals(fuUser.getPhoneCode())) {
						fuUser.setDrawPassword(new_drawpwd);
						fuUserService.save(fuUser);
						obj.put("message", "修改成功");
						obj.put("is_success", 1);
					} else {
						obj.put("message", "手机号码和验证码没有匹配");
						obj.put("is_success", 0);
					}
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			obj.put("message", "非法请求");
			obj.put("is_success", 0);
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 修改提款密码发送短信验证码
	 * 
	 * @param phone
	 */
	@Override
	public String updateDrawPwdPhoneCode(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByRegPhone(phone);
				if (null != fuUser) {
					DecimalFormat format = new DecimalFormat("0000");
					String code = format.format(Math.random() * 9999);
					String message = URLDecoder.decode("[修改提款密码]您的手机验证码是：" + code, "UTF-8");
					FuSmsLog log = new FuSmsLog();
					log.setContent(message);
					log.setPrio(1);
					log.setReason("修改提款密码");
					log.setDestination(phone);
					log.setPlanTime(new Date());
					log.setType(1);// 短信

					fuUser.setPhoneCode(code);// 保存注册验证码到用户表
					// 验证码过期时间（1个小时）
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.HOUR_OF_DAY, 1);
					fuUser.setPhoneCodeTime(cal.getTime());

					log.setState(0);
					obj.put("message", "请求成功");
					obj.put("is_success", 1);
					// obj.put("phone_code", code);
					fuUserService.saveReg(fuUser, log);
				} else {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "生成手机验证码失败");
			obj.put("is_success", 1);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app实名认证查询
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String queryCerti(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (fuUser == null) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					obj.put("user_name", fuUser.getUserName() == null ? "" : fuUser.getUserName());
					obj.put("card_number", fuUser.getCardNumber() == null ? "" : fuUser.getCardNumber());
					obj.put("card_before_pic", fuUser.getCardBeforePic() == null ? "" : fuUser.getCardBeforePic());
					obj.put("card_behind_pic", fuUser.getCardBehindPic() == null ? "" : fuUser.getCardBehindPic());
					obj.put("is_check_card", fuUser.getIsCheckCard() == null ? "" : fuUser.getIsCheckCard());
					obj.put("message", "查询成功");
					obj.put("is_success", 1);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			obj.put("message", "非法请求");
			obj.put("is_success", 0);
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app银行卡绑定查询
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String queryBankCard(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (fuUser == null) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					List<FuBankCard> bankList = fuBankCardService.findListByUser(fuUser.getId());
					List<Object> bankCards = new ArrayList<Object>();
					for (FuBankCard b : bankList) {
						Map<String, Object> bankMap = new HashMap<String, Object>();
						bankMap.put("card_number", b.getCardNumber() == null ? "" : b.getCardNumber());
						bankMap.put("bank_name", b.getBankName() == null ? "" : b.getBankName());
						bankCards.add(bankMap);
					}
					obj.put("bankCards", bankCards);
					obj.put("has_drawpwd", (fuUser.getDrawPassword() == null || "".equals(fuUser.getDrawPassword())) ? 0 : 1);
					obj.put("message", "查询成功");
					obj.put("is_success", 1);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			obj.put("message", "非法请求");
			obj.put("is_success", 0);
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app资格证查询
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String queryQualiNum(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (fuUser == null) {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				} else {
					List<HhrQualiRegister> qualiList = hhrQualiRegisterService.findQualiByUser(fuUser.getId(), null);
					List<Object> qualis = new ArrayList<Object>();
					for (HhrQualiRegister q : qualiList) {
						Map<String, Object> qualiMap = new HashMap<String, Object>();
						qualiMap.put("quali_num", q.getQualiNum() == null ? "" : q.getQualiNum());
						qualiMap.put("type", q.getType() == null ? "" : q.getType());
						qualis.add(qualiMap);
					}
					obj.put("qualis", qualis);
					obj.put("message", "查询成功");
					obj.put("is_success", 1);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			obj.put("message", "非法请求");
			obj.put("is_success", 0);
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app升级查询
	 * 
	 * @return
	 */
	@Override
	public String appUpgradeQuery() {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		// if("success".equals(result)){
		String userAgentStr = ServletActionContext.getRequest().getHeader("User-Agent");
		UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
		String sysName = userAgent.getOperatingSystem().getName();
		List<HhrAppVersion> list = null;
		if ("Android".equals(sysName)) {
			list = hhrAppVersionService.findVersionList(1L);
		}
		if (userAgentStr.indexOf("iOS") != -1) {
			list = hhrAppVersionService.findVersionList(2L);
		}
		obj.put("version_code", list == null ? "" : (list.size() != 0 ? (list.get(0).getVersionCode() == null ? "" : list.get(0).getVersionCode()) : ""));
		obj.put("version_name", list == null ? "" : (list.size() != 0 ? (list.get(0).getVersionName() == null ? "" : list.get(0).getVersionName()) : ""));
		obj.put("download_url", list == null ? "" : (list.size() != 0 ? (list.get(0).getDownloadUrl() == null ? "" : list.get(0).getDownloadUrl()) : ""));
		obj.put("update_log", list == null ? "" : (list.size() != 0 ? (list.get(0).getUpdateLog() == null ? "" : list.get(0).getUpdateLog()) : ""));
		obj.put("force_update", list == null ? "" : (list.size() != 0 ? (list.get(0).getForceUpdate() == null ? "" : list.get(0).getForceUpdate()) : ""));
		obj.put("message", "查询成功");
		obj.put("is_success", 1);
		/*
		 * }else{ obj.put("message", result); obj.put("is_success", 0); }
		 */
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app下载
	 * 
	 * @return
	 */
	@Override
	public String appDownload() {
		String userAgentStr = ServletActionContext.getRequest().getHeader("User-Agent");
		UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
		String sysName = userAgent.getOperatingSystem().getName();
		// JSONObject obj = new JSONObject();
		// List<HhrAppVersion> list = null;
		if ("Android".equals(sysName)) {
			// list = hhrAppVersionService.findVersionList(1L);
			return "Android";
		} else if (userAgentStr.indexOf("iOS") != -1 || sysName.indexOf("iPhone") != -1) {
			// list = hhrAppVersionService.findVersionList(2L);
			return "iOS";
		} else {
			return null;
		}
		// obj.put("download_url",
		// list==null?"":(list.size()!=0?(list.get(0).getDownloadUrl()==null?"":list.get(0).getDownloadUrl()):""));
		// obj.put("message", "查询成功");
		// obj.put("is_success", 1);
		// System.out.println(obj);
		// return obj.toString();
	}

	/**
	 * app快速注册
	 * 
	 * @param phone
	 * @param invitation_code
	 * @param reg_type
	 * @return
	 */
	@Override
	public String fastRegister(String phone, String invitation_code, String reg_type, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (invitation_code == null || "".equals(invitation_code)) {
				obj.put("message", "邀请码为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (fuUserService.findUserByRegPhone(phone) != null) {
				obj.put("message", "该手机号已被注册");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (fuUserService.findUserByRegInvitationcode(invitation_code) == null) {
				obj.put("message", "邀请码不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuUser user = new FuUser();
			user.setAccountName(phone);
			user.setPhone(phone);
			user.setNickName("");
			user.setIntegral(BigDecimal.ZERO);// 积分
			user.setIsAcrossCabin(0);// 是否穿仓
			user.setIsPhoneReg(Integer.valueOf(reg_type));// 网站注册
			user.setSafeMoney(new BigDecimal(0.00));// 总风险保证金
			user.setMatchMoney(new BigDecimal(0.00));// 总配资金额
			user.setFreezeMoney(new BigDecimal(0.00));// 冻结金额
			user.setExtendPersonNum(0);// 推广人数
			user.setBorrowPersonNum(0);// 配资用户
			user.setExchangeMoney(new BigDecimal(0.00));// 已兑佣金
			user.setCommissionTotal(new BigDecimal(0.00));// 佣金总额
			user.setAccountBalance(new BigDecimal(0.00));// 账户余额
			user.setAccountTotalMoney(new BigDecimal(0.00));// 总资产
			user.setIsBindEmail(0);// 邮箱未绑定
			user.setIsCheckCard(0);// 未实名认证
			user.setVisitIp(1);// 访问IP数
			user.setVisitNum(1);// 访问次数
			user.setSafeLevel(1);// 安全等级
			user.setRegisterTime(new Date());
			user.setRegisterTime(new Date());
			user.setIntegral(new BigDecimal(0.00));
			fuUserService.saveRate(user); // 新注册用户同时创建一条个人费率
			user.setState(1);
			user.setRegisterIp(IP4.getIP4(ServletActionContext.getRequest()));// 访问IP
			// 产生随机邀请码
			while (true) {
				double rand = new Random().nextDouble();
				String invitcode = new String(rand + "").substring(2, 14);
				Integer count = fuUserService.countInvitationCode(invitcode);
				if (count < 1) {
					user.setInvitationCode(invitcode);
					break;
				}
			}
			// 确定上级用户
			user.setHhrParentID(fuUserService.findUserByRegInvitationcode(invitation_code).getId());
			user.setRecommend(fuUserService.findUserByRegInvitationcode(invitation_code));
			user.setLastLoginIp(IP4.getIP4(ServletActionContext.getRequest()));
			user.setHhrLevel(fuUserService.findUserByRegInvitationcode(invitation_code).getHhrLevel() + 1);// 合伙人等级为上级等级+1

			// 将密码以短信的形式发送给用户
			double rand = new Random().nextDouble();
			String newPass = new String(rand + "").substring(2, 8);
			String message = URLDecoder.decode("欢迎注册成为超级合伙人，您的密码是:" + newPass, "UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setContent(message);
			log.setPrio(1);
			log.setReason("用户快速注册");
			log.setDestination(phone);
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogService.save(log);
			user.setPassword(CommonUtils.getMd5(newPass));
			fuUserService.save(user);

			// 统计表信息写入
			HhrStat hhrStat = new HhrStat();
			hhrStat.setFuUser(user);
			hhrStat.setStatDate(new Date());
			hhrStat.setHhrParentID(user.getHhrParentID());
			hhrStatService.save(hhrStat);

			FuUser parentUser = fuUserService.findFuUserById(user.getHhrParentID());
			if (null != parentUser) {
				// 上级合伙人信息
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", user.getHhrParentID());
				HhrStat parentStat = hhrStatService.findStatDataByMap2(map);
				if (null != parentStat) {
					if (parentStat.getFirstlyPartnerNum() == null) {
						parentStat.setFirstlyPartnerNum(1);
					} else {
						parentStat.setFirstlyPartnerNum(parentStat.getFirstlyPartnerNum() + 1);
					}
					parentStat.setStatDate(new Date());
					hhrStatService.save(parentStat);

					// 上级合伙人明细
					HhrStatDetail parentStatDetail = hhrStatDetailService.findHhrStatDetailByUserAndDate(parentUser.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					if (parentStatDetail != null) {
						parentStatDetail.setExtendPersonNew(parentStatDetail.getExtendPersonNew() == null ? 1 : (parentStatDetail.getExtendPersonNew() + 1));
					} else {
						parentStatDetail = new HhrStatDetail();
						parentStatDetail.setFuUser(parentUser);
						parentStatDetail.setExtendPersonNew(1);
						parentStatDetail.setStatDate(new Date());
						parentStatDetail.setCreateDate(new Date());
					}
					parentStatDetail.setStatDate(new Date());
					hhrStatDetailService.save(parentStatDetail);

					// 上级的上级合伙人信息
					HhrStat ppStat = hhrStatService.findHhrStatByUser(parentStat.getHhrParentID());
					if (ppStat != null) {
						if (ppStat.getSecondaryPartnerNum() == null) {
							ppStat.setSecondaryPartnerNum(1);
						} else {
							ppStat.setSecondaryPartnerNum(ppStat.getSecondaryPartnerNum() + 1);
						}
						ppStat.setStatDate(new Date());
						hhrStatService.save(ppStat);
						// 从这一层用户开始往上回溯, 每个用户的次级合伙人都+1
						fuUserService.updatePartnerNum(ppStat);
					}
				} else {
					parentStat = new HhrStat();
					parentStat.setFuUser(parentUser);
					parentStat.setFirstlyPartnerNum(1); // 一级会员数目
					parentStat.setExtendPersonNew(1); // 今日新增会员
					parentStat.setStatDate(new Date());
					hhrStatService.save(parentStat);
				}
				obj.put("is_success", 1);
				obj.put("phone", phone);
				obj.put("invite_code", user.getInvitationCode());
				obj.put("message", "注册成功,密码已短信至用户");
			} else {
				obj.put("message", "上级用户不存在");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app方案开户
	 * 
	 * @param phone
	 * @param match_money
	 * @param num
	 * @param programType
	 * @param tradeTimeType
	 * @param cycleNum
	 * @param integral
	 * @return
	 */
	@Override
	public String appSaveProgram(String phone, String match_money, int num, int programType, int tradeTimeType, int cycleNum, String integral, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (fuUser.getIsCheckCard() != 2) {
				obj.put("message", "用户未实名认证");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());
			if (param == null) {
				obj.put("message", "系统参数还未设置,请联系客服");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser.getId());
			map.put("programType", programType);
			int count = fuProgramService.countProgramByUser2(map);
			if (programType == 2 && param.getLongNum() != null && count > param.getLongNum()) {// 超出了交易个数
				obj.put("message", "超过了最大方案数");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			// 计算管理费和保证金
			BigDecimal matchMoney = new BigDecimal(match_money);
			BigDecimal safeMoney = matchMoney.divide(new BigDecimal(num), 0, BigDecimal.ROUND_HALF_UP);// 风险保证金
			BigDecimal managerMoney = new BigDecimal(0.00); // 管理费
			if (cycleNum > 3) {
				managerMoney = matchMoney.multiply(param.getInterestPercent() == null ? new BigDecimal(0.00) : param.getInterestPercent());
			} else {
				managerMoney = matchMoney.multiply(param.getFeePercent() == null ? new BigDecimal(0.00) : param.getFeePercent());
			}

			/**
			 * 月配余额不足
			 */
			if (programType == 2 && fuUser.getAccountBalance().compareTo(safeMoney.add(managerMoney).subtract(new BigDecimal(integral))) == -1) {
				obj.put("message", "账户余额小于风险保证金");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			/**
			 * 创建新的方案
			 */
			FuProgram program = new FuProgram();
			program.setStatus(0);// 待审核
			program.setCreateTime(new Date());
			program.setCloseLine(matchMoney.add(safeMoney.multiply(param.getFlatlinePercent() == null ? new BigDecimal(0.00) : param.getFlatlinePercent())));// 亏损平仓线
			program.setWarnLine(matchMoney.add(safeMoney.multiply(param.getWarnlinePercent() == null ? new BigDecimal(0.00) : param.getWarnlinePercent())));// 亏损警戒线
			program.setFuUser(fuUser);
			program.setMatchMoney(matchMoney);// //------实盘资金--------不加风险保证金
			program.setSafeMoney(safeMoney);
			program.setTotalMatchMoney(matchMoney.add(safeMoney));// 总操盘
			program.setCycleNum(cycleNum);
			program.setManageMoney(managerMoney);// 月配是每个月来扣除管理费
			program.setMoneyPercent(param.getFeePercent() == null ? new BigDecimal(0.00) : param.getFeePercent());
			program.setProgramType(programType);
			program.setProgramWay(0);// 正常交易
			program.setDoubleNum(num);
			program.setCommisionPercent(param.getCommissionPercent() == null ? new BigDecimal(0.00) : param.getCommissionPercent());

			// 下个交易日，应该是审核过后的第一个交易日
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar cal = Calendar.getInstance();
			if (tradeTimeType == 1) {// 今天
				program.setTradeTime(sdf.parse(sdf.format(cal.getTime())));
			} else {// 下个交易日
				int weekday = cal.get(Calendar.DAY_OF_WEEK);
				if (weekday == 1) {// 今天星期天
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
				} else if (weekday == 7) {// 今天星期六
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 2);
				} else if (weekday == 6) {// 今天星期五
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 3);
				} else {// 其他
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
				}
				program.setTradeTime(sdf.parse(sdf.format(cal.getTime())));
			}
			if (programType == 2) {// 趋势之王的结束日期可以确定
				if (tradeTimeType == 1) {
					Calendar calen = Calendar.getInstance();
					calen.add(Calendar.MONTH, cycleNum);
					calen.add(Calendar.DAY_OF_MONTH, -1);
					program.setCloseTime(sdf.parse(sdf.format(calen.getTime())));
				}
				if (tradeTimeType == 2) {
					Calendar calen = Calendar.getInstance();
					calen.add(Calendar.MONTH, cycleNum);
					program.setCloseTime(sdf.parse(sdf.format(calen.getTime())));
				}
			}
			program.setAgreeTime(new Date());
			program.setAgreeIp(ServletActionContext.getRequest().getRemoteAddr());
			program.setExpenseScore(new BigDecimal(integral));
			fuUser.setIntegral(fuUser.getIntegral().subtract(new BigDecimal(integral)));// 先减去积分，审核被拒绝就返还积分
			fuProgramService.saveInfo(program, fuUser, null);
			obj.put("message", "方案发起成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app追加保证金
	 * 
	 * @param phone
	 * @param programId
	 * @param money
	 * @return
	 */
	@Override
	public String appAddSafeMoney(String phone, Long programId, String money, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			if (programId == null) {
				obj.put("message", "方案ID为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			if (money == null || "".equals(money)) {
				obj.put("message", "追加金额为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			if (fuUser.getAccountBalance().compareTo(new BigDecimal(money)) == -1) {
				obj.put("message", "账户余额不足");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuProgram pro = fuProgramService.get(programId);
			fuProgramService.saveAddConfirmMoney(pro, new BigDecimal(money));
			// 发送短信
			String message = URLDecoder.decode("您的账户追加方案[" + pro.getId() + "]的保证金成功，追加金额为" + money + "元。", "UTF-8");
			// 保存短信信息到数据库日志表
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(pro.getFuUser());
			log.setContent(message);
			log.setPrio(1);
			log.setReason("追加保证金");
			log.setDestination(pro.getFuUser().getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogService.save(log);
			obj.put("message", "入金成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app查询可提取的利润
	 * 
	 * @param programId
	 * @return
	 */
	@Override
	public String queryDrawBenefits(Long programId, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			FuProgram pro = fuProgramService.get(programId);
			BigDecimal benefit = zhongqiService.getBenefit(pro.getFuServer(), pro, BigDecimal.ZERO, BigDecimal.ZERO);
			obj.put("message", "查询成功");
			obj.put("benefit", benefit.toString());
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app提取利润
	 * 
	 * @param programId
	 * @param money
	 * @return
	 */
	@Override
	public String appDrawBenefits(Long programId, String money, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			FuProgram pro = fuProgramService.get(programId);
			if (money == null || "".equals(money) || new BigDecimal(money).compareTo(BigDecimal.ZERO) == 0) {
				obj.put("message", "提取金额不正确");
				obj.put("is_success", 0);
			}
			Calendar cal1 = Calendar.getInstance();
			int weekday = cal1.get(Calendar.DAY_OF_WEEK);
			if (weekday == 1 || weekday == 7) {// 周末
				obj.put("message", "非工作日,无法提取");
				obj.put("is_success", 0);
			}
			String profitsId = fuProgramService.saveDrowMoney(pro, new BigDecimal(money));
			obj.put("profitsId", profitsId);
			obj.put("message", "请求发送成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app充值
	 * 
	 * @param phone
	 * @param bankId
	 * @param money
	 * @param fileByteStr
	 * @param recharge_pic
	 * @return
	 */
	@Override
	public String appRechargeMoney(String phone, String bankId, String money, String fileByteStr, String recharge_pic, String recharge_id, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (bankId == null) {
				obj.put("message", "银行卡号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (money == null || "".equals(money)) {
				obj.put("message", "充值金额为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
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
				obj.put("message", "充值金额必须是正数");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuRecharge recharge = new FuRecharge();
			recharge.setFuUser(fuUser);
			recharge.setOrderNum(String.valueOf((int) ((Math.random() * 1000000 + 100000))));// 随机产生订单号
			recharge.setType(4);
			FuBankCard bankCard = fuBankCardService.findBankCardByNum(bankId);
			recharge.setRechargeBank(bankCard != null ? bankCard.getBankName() : "");
			recharge.setRechargeAccount(bankCard != null ? bankCard.getCardNumber() : "");
			recharge.setRechargeMoney(new BigDecimal(drawM));
			recharge.setRechargeTime(new Date());
			recharge.setRechargeStatus(0);// 待审核
			recharge.setState(1);
			if (fileByteStr != null && !"".equals(fileByteStr) && recharge_pic != null && !"".equals(recharge_pic)) {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] fileByte = decoder.decodeBuffer(fileByteStr);
				String saveUrl = imageFileUpload(fileByte, recharge_pic, "_recharge");
				if (saveUrl != null) {
					recharge.setRechargePic(saveUrl);
				} else {
					obj.put("message", "汇款图片上传失败");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			} else {
				if (recharge_id != null && !"".equals(recharge_id)) {
					recharge.setRechargeId(recharge_id);
				} else {
					obj.put("message", "汇款图片和汇款编号都为空");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			fuRechargeService.save(recharge);
			obj.put("message", "充值请求发送成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app充值记录
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String appRechargeRecords(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			List<FuRecharge> list = fuRechargeService.findListByUserId(fuUser.getId());
			List<Object> recharges = new ArrayList<Object>();
			if (list != null) {
				for (FuRecharge recharge : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", recharge.getId());
					map.put("user_id", recharge.getFuUser().getId());
					map.put("recharge_time", sdf.format(recharge.getRechargeTime()));
					map.put("order_num", recharge.getOrderNum());
					// map.put("type", "银行转账");
					map.put("type", recharge.getRechargeBank());
					map.put("recharge_money", recharge.getRechargeMoney() == null ? "0" : recharge.getRechargeMoney());
					map.put("recharge_status", recharge.getRechargeStatus() == 0 ? "未审核" : recharge.getRechargeStatus() == 1 ? "审核中" : recharge.getRechargeStatus() == 2 ? "成功" : "拒绝");
					recharges.add(map);
				}
				obj.put("message", "查询成功");
				obj.put("recharges", recharges);
				obj.put("is_success", 1);
			} else {
				obj.put("messge", "无充值记录");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app提现
	 * 
	 * @param phone
	 * @param bankId
	 * @param money
	 * @param fileByteStr
	 * @param recharge_pic
	 * @return
	 */
	@Override
	public String appDrawMoney(String phone, String bankId, String drawPassword, String phoneCode, String money, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (fuUser.getAccountBalance().compareTo(new BigDecimal(money)) == -1) {
				obj.put("message", "账户余额不足");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (fuUser.getIsCheckCard() != 2) {
				obj.put("message", "用户未实名认证");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (fuUser.getIsAcrossCabin() == 1) {
				obj.put("message", "用户已穿仓");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (bankId == null || "".equals(bankId)) {
				obj.put("message", "银行卡号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (drawPassword == null || "".equals(drawPassword)) {
				obj.put("message", "提款密码为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			} else {
				if (!drawPassword.equals(fuUser.getDrawPassword())) {
					obj.put("message", "提款密码错误");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			if (phoneCode == null || "".equals(phoneCode)) {
				obj.put("message", "手机验证码为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (fuUser.getPhoneCode() != null) {
				if (!phoneCode.equals(fuUser.getPhoneCode()) || !phone.equals(fuUser.getPhone())) {
					obj.put("message", "手机验证码不正确");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			if (money == null || "".equals(money)) {
				obj.put("message", "提款金额为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (!StringUtil.isNumeric(money)) {
				obj.put("message", "提款金额必须是正数");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(new BigDecimal(money)));
			fuUserService.save(fuUser);
			FuDrawMoney drawMoney = new FuDrawMoney();
			drawMoney.setFuUser(fuUser);
			drawMoney.setFuBankCard(fuBankCardService.findBankCardByNum(bankId));
			drawMoney.setDrawMoney(new BigDecimal(money));
			drawMoney.setDrawTime(new Date());
			drawMoney.setStatus(0);// 默认是未审核
			drawMoney.setState(1);// 正常
			fuDrawMoneyService.save(drawMoney);
			obj.put("message", "提款申请成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app提现记录
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String appDrawRecords(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				return obj.toString();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			List<FuDrawMoney> list = fuDrawMoneyService.findListByUserId(fuUser.getId());
			List<Object> draws = new ArrayList<Object>();
			if (list != null && list.size() > 0) {
				for (FuDrawMoney draw : list) {
					if (null != draw) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", draw.getId());
						map.put("user_id", draw.getFuUser().getId());
						map.put("draw_time", sdf.format(draw.getDrawTime()));
						map.put("bank_name", draw.getFuBankCard() == null ? "" : (draw.getFuBankCard().getBankName() == null ? "" : draw.getFuBankCard().getBankName()));
						map.put("card_number", draw.getFuBankCard() == null ? "" : (draw.getFuBankCard().getCardNumber() == null ? "" : draw.getFuBankCard().getCardNumber()));
						map.put("draw_money", draw.getDrawMoney() == null ? "0" : draw.getDrawMoney());
						map.put("status", draw.getStatus() == 0 ? "未审核" : draw.getStatus() == 1 ? "审核中" : draw.getStatus() == 2 ? "成功" : "拒绝");
						draws.add(map);
					}

				}
				obj.put("message", "查询成功");
				obj.put("recharges", draws);
				obj.put("is_success", 1);
			} else {
				obj.put("messge", "暂无提款记录");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app提现发送手机验证码
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String appDrawMoneyPhoneCode(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone != null && !"".equals(phone)) {
				FuUser fuUser = fuUserService.findUserByRegPhone(phone);
				if (null != fuUser) {
					DecimalFormat format = new DecimalFormat("0000");
					String code = format.format(Math.random() * 9999);
					String message = URLDecoder.decode("[提款]您的手机验证码是：" + code, "UTF-8");
					FuSmsLog log = new FuSmsLog();
					log.setContent(message);
					log.setPrio(1);
					log.setReason("提款");
					log.setDestination(phone);
					log.setPlanTime(new Date());
					log.setType(1);// 短信

					fuUser.setPhoneCode(code);// 保存注册验证码到用户表
					// 验证码过期时间（1个小时）
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.HOUR_OF_DAY, 1);
					fuUser.setPhoneCodeTime(cal.getTime());

					log.setState(0);
					obj.put("message", "请求成功");
					obj.put("is_success", 1);
					// obj.put("phone_code", code);
					fuUserService.saveReg(fuUser, log);
				} else {
					obj.put("message", "手机号不存在");
					obj.put("is_success", 0);
				}
			} else {
				obj.put("message", "缺少参数");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "生成手机验证码失败");
			obj.put("is_success", 1);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app在线留言
	 * 
	 * @param phone
	 * @param type
	 * @param content
	 * @return
	 */
	@Override
	public String appMessageInfo(String phone, Integer type, String content, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			if (type == null) {
				obj.put("message", "留言类型为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			if (content == null || "".equals(content)) {
				obj.put("message", "留言内容为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuMessage message = new FuMessage();
			message.setContent(content);
			message.setFuUser(fuUser);
			message.setState(1);
			message.setTime(new Date());
			message.setType(type);
			fuMessageService.save(message);
			obj.put("message", "留言成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app账户余额和可用积分
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String appAccountInfo(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			obj.put("account_balance", fuUser.getAccountBalance() == null ? "0" : fuUser.getAccountBalance());
			obj.put("integral", fuUser.getIntegral() == null ? "0" : fuUser.getIntegral());
			obj.put("message", "查询成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app续约
	 * 
	 * @param programId
	 * @param money
	 * @return
	 */
	@Override
	public String appRenewal(String phone, Long programId, Integer cycleNum, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			if (programId == null) {
				obj.put("message", "方案ID为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			if (cycleNum == null) {
				obj.put("message", "续约周期为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuProgram pro = fuProgramService.get(programId);
			// 续约时临时升级的配资不考虑
			BigDecimal matchMoney = pro.getMatchMoney();
			// 根据方案查找未结束的临时升级记录
			List<FuProgramUp> upList = fuProgramUpService.findProgramUpByPidAndNoDeal(programId);
			for (int i = 0; upList != null && i < upList.size(); i++) {
				FuProgramUp pu = upList.get(i);
				matchMoney = matchMoney.subtract(pu.getMatchMoney());
			}
			BigDecimal i;
			if (pro.getProgramType() == 1) {
				i = pro.getMoneyPercent().multiply(matchMoney.divide(new BigDecimal(10000)));
			} else {
				i = pro.getMoneyPercent().multiply(matchMoney);
			}
			BigDecimal money = i.multiply(new BigDecimal(cycleNum));
			if (fuUser.getAccountBalance().compareTo(money) == -1) {
				obj.put("message", "余额不足");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			fuProgramService.saveProgramContinue(pro, cycleNum, money, null);
			obj.put("message", "续约成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app注册发钱
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String appRegisterIncome(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			// 判断活动是否已开启
			HhrPromoteParameter param = promoteParameterService.findParameter();
			if (param.getIsOpen() == 0) {
				obj.put("message", "活动尚未开启");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			// 判断活动费用余额是否足够, 暂定50
			if (param.getTotalMoney().compareTo(new BigDecimal(50)) == -1) {
				obj.put("message", "活动费用不足");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			// 判断用户是否实名认证
			/*
			 * List<FuUser> checkedUserList =
			 * fuUserService.findListByUser(fuUser.getId()); if(checkedUserList
			 * != null && checkedUserList.size() == 0){ obj.put("message",
			 * "用户未实名认证"); obj.put("is_success", 0); System.out.println(obj);
			 * return obj.toString(); }
			 */

			// 判断用户是否资格认证
			/*
			 * List<HhrQualiRegister> qualiUserList =
			 * hhrQualiRegisterService.findQualiByUser(fuUser.getId(), null);
			 * if(qualiUserList != null && qualiUserList.size() == 0){
			 * obj.put("message", "用户未资格认证"); obj.put("is_success", 0);
			 * System.out.println(obj); return obj.toString(); }
			 */

			String[] moneyArr = param.getLineMoney().split(",");
			// 从数组中随机抽取一个值,作为初始发钱金额
			int rand = new Random().nextInt(moneyArr.length);
			BigDecimal initialMoney = new BigDecimal(moneyArr[rand]);
			hhrStatService.updateHhrIncome(fuUser.getId(), initialMoney, 1, null);
			param.setTotalMoney(param.getTotalMoney().subtract(initialMoney));
			promoteParameterService.save(param);
			obj.put("money", initialMoney);
			obj.put("message", "发钱成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app查询用户相关费率
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String appQueryUserRate(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}

			FuRate rate = fuRateService.getByUserID(fuUser.getId());
			if (rate == null) {
				obj.put("message", "当前用户未设置相关费率");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			obj.put("warnline_percent", rate.getWarnlinePercent() == null ? "0" : rate.getWarnlinePercent());
			obj.put("flatline_percent", rate.getFlatlinePercent() == null ? "0" : rate.getFlatlinePercent());
			obj.put("fee_percent", rate.getFeePercent() == null ? "0" : rate.getFeePercent());
			obj.put("message", "查询成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app查询用户方案
	 * 
	 * @param phone
	 * @return
	 */
	@Override
	public String appQueryUserPrograms(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<FuProgram> longList = fuProgramService.findToTradeByUser(fuUser.getId());
			List<Object> programs = new ArrayList<Object>();
			if (longList != null && longList.size() > 0) {
				for (FuProgram p : longList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", p.getId());
					map.put("match_money", p.getMatchMoney());
					map.put("safe_money", p.getSafeMoney());
					map.put("money_percent", p.getMoneyPercent().multiply(new BigDecimal(100)));
					map.put("manage_money", p.getManageMoney());
					map.put("cycle_num", p.getCycleNum() + "个月");
					// map.put("status",
					// p.getStatus()==6?"结算中":p.getStatus()==5?"交易结束":p.getStatus()==4?"等待结算":p.getStatus()==3?"拒绝开户":p.getStatus()==2?"交易中":p.getStatus()==1?"审核中":p.getStatus()==0?"待审核":p.getStatus()==-1?"已关闭":p.getStatus()==-2?"已删除":"");
					map.put("status", p.getStatus() == null ? "" : p.getStatus());
					map.put("warn_line", p.getWarnLine());
					map.put("close_line", p.getCloseLine());
					map.put("trade_service_name", p.getTradeServiceName() == null ? "" : p.getTradeServiceName());
					map.put("trade_ip", p.getTradeIp() + ":" + p.getTradePort());
					map.put("trade_account", p.getTradeAccount());
					map.put("trade_time", p.getTradeTime() == null ? "" : sdf.format(p.getTradeTime()));
					map.put("close_time", p.getCloseTime() == null ? "" : sdf.format(p.getCloseTime()));
					map.put("next_manage_money_time", p.getNextManageMoneyTime() == null ? "" : sdf.format(p.getNextManageMoneyTime()));
					map.put("add_safe_money", p.getAddSafeMoney() == null ? "0" : p.getAddSafeMoney());
					map.put("user_id", p.getFuUser().getId() == null ? "" : p.getFuUser().getId());
					map.put("phone", p.getFuUser().getPhone() == null ? "" : p.getFuUser().getPhone());
					if (p.getFuServer() != null) {
						FuServer server = p.getFuServer();
						Map<String, Object> serverMap = new HashMap<String, Object>();
						serverMap.put("id", server.getId() == null ? "" : server.getId());
						serverMap.put("db_ip", server.getDbIp() == null ? "" : server.getDbIp());
						serverMap.put("db_port", server.getDbPort() == null ? "" : server.getDbPort());
						serverMap.put("db_name", server.getDbName() == null ? "" : server.getDbName());
						serverMap.put("db_read_username", server.getDbReadUserName() == null ? "" : server.getDbReadUserName());
						serverMap.put("db_read_password", server.getDbReadPassWord() == null ? "" : server.getDbReadPassWord());
						List<Object> list = new ArrayList<Object>();
						list.add(serverMap);
						map.put("server", list);
					} else {
						map.put("server", "");
					}
					programs.add(map);
				}
				obj.put("programs", programs);
				obj.put("message", "查询成功");
				obj.put("is_success", 1);
			} else {
				obj.put("message", "无方案数据");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app关闭方案
	 * 
	 * @param programId
	 * @return
	 */
	@Override
	public String appCloseProgram(Long programId, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (programId == null) {
				obj.put("message", "方案ID为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuProgram program = fuProgramService.get(programId);
			program.setStatus(-1);// 已关闭
			fuProgramService.save(program);
			obj.put("message", "关闭成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app删除方案
	 * 
	 * @param programId
	 * @return
	 */
	@Override
	public String appDeleteProgram(Long programId, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (programId == null) {
				obj.put("message", "方案ID为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuProgram pro = fuProgramService.get(programId);
			Integer status = pro.getStatus();
			if (status == -1 || status == 0 || status == 3 || status == 5) {
				fuProgramService.delete(programId);
				obj.put("message", "删除成功");
				obj.put("is_success", 1);
			} else {
				// 方案状态: -2已删除 1审核中 2交易中 4等待结算 6结算中
				if (status == -2) {
					obj.put("message", "方案已删除");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
				if (status == 1) {
					obj.put("message", "方案审核中,无法删除");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
				if (status == 2) {
					obj.put("message", "方案交易中,无法删除");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
				if (status == 4) {
					obj.put("message", "方案等待结算,无法删除");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
				if (status == 6) {
					obj.put("message", "方案结算中,无法删除");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app绑定微信号
	 * 
	 * @param phone
	 * @param password
	 * @param weixinCode
	 * @return
	 */
	@Override
	public String appBindWeixinCode(String phone, String password, String weixinCode, String type, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByRegPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if ("1".equals(type)) {
				if (password == null || "".equals(password)) {
					obj.put("message", "密码为空");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
				if (!fuUser.getPassword().equals(password)) {
					obj.put("message", "密码错误");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				}
			}
			if (weixinCode == null || "".equals(weixinCode)) {
				obj.put("message", "微信号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (type == null || "".equals(type)) {
				obj.put("message", "操作类型为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			// 通过微信号和手机号查询是否存在用户
			FuUser user = fuUserService.findUserByWeiXinAndPhone(weixinCode, phone);
			// 绑定微信号
			if ("1".equals(type)) {
				if (null == user) {
					fuUser.setWeixinCode(weixinCode);
				}
				obj.put("message", "绑定成功");
				obj.put("is_success", 1);
			}
			// 解绑微信号
			if ("2".equals(type)) {
				if (fuUser.getWeixinCode() == null || "".equals(fuUser.getWeixinCode())) {
					obj.put("message", "无微信号,无需解绑");
					obj.put("is_success", 0);
					System.out.println(obj);
					return obj.toString();
				} else {
					fuUser.setWeixinCode(null);
					obj.put("message", "解绑成功");
					obj.put("is_success", 1);
				}
			}
			fuUserService.save(fuUser);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 查询省份和城市信息
	 * 
	 * @return
	 */
	public String appQueryProvinceAndCity(String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			List<Object> provinces = new ArrayList<Object>();
			List<SysProvince> plist = sysProvinceService.findAllProvince();
			if (plist != null) {
				for (SysProvince p : plist) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", p.getId());
					map.put("province_name", p.getProvinceName() == null ? "" : p.getProvinceName());
					List<SysCity> citylist = sysCityService.findCityByProvince(p.getId());
					List<Object> cities = new ArrayList<Object>();
					for (SysCity c : citylist) {
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("id", c.getId());
						map2.put("city_name", c.getCityName());
						cities.add(map2);
					}
					map.put("cities", cities);
					provinces.add(map);
				}
				obj.put("provinces", provinces);
			} else {
				obj.put("message", "无省市数据");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 绑定邮箱发送验证码
	 */
	@Override
	public String bindEmailCode(String phone, String email, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (email == null || "".equals(email)) {
				obj.put("message", "邮箱为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (!RegexChk.checkEmail(email)) {
				obj.put("message", "邮箱格式错误");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser u = fuUserService.findUserByEmail(email);
			if (u != null && !u.getId().toString().equals(fuUser.getId().toString())) {
				obj.put("message", "邮箱已被占用");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			DecimalFormat format = new DecimalFormat("000000");
			String code = format.format(Math.random() * 999999);
			String[] emailAddresses = new String[1];
			emailAddresses[0] = email;
			mailEngine.sendMessage(emailAddresses, "资产管理平台<kefu@kunzhoudade.com>", fuUser.getAccountName() + "您好，请完成Email验证，您可以在验证页面输入以下验证码：" + code, fuUser.getAccountName() + "您好，请完成电子邮箱验证：" + code, null, null);
			// fuUser.setEmail(email);
			fuUser.setEmailCode(code);
			fuUserService.saveCheckEmail(fuUser);
			obj.put("message", "请求发送成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "操作失败");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * app查询交易中的方案
	 * 
	 * @return
	 */
	@Override
	public String appQueryActivatedPrograms(String queryDate, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (queryDate == null || "".equals(queryDate)) {
				obj.put("message", "查询日期为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<FuProgram> pList = fuProgramService.findProgramsByDate(queryDate);
			List<Object> programs = new ArrayList<Object>();
			if (pList != null) {
				for (FuProgram p : pList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", p.getId());
					map.put("match_money", p.getMatchMoney());
					map.put("safe_money", p.getSafeMoney());
					map.put("money_percent", p.getMoneyPercent().multiply(new BigDecimal(100)));
					map.put("manage_money", p.getManageMoney());
					map.put("cycle_num", p.getCycleNum() + "个月");
					map.put("status", p.getStatus() == 7 ? "已穿仓" : p.getStatus() == 6 ? "结算中" : p.getStatus() == 5 ? "交易结束" : p.getStatus() == 4 ? "等待结算" : p.getStatus() == 3 ? "拒绝开户" : p.getStatus() == 2 ? "交易中" : p.getStatus() == 1 ? "审核中" : p.getStatus() == 0 ? "待审核" : p.getStatus() == -1 ? "已关闭" : p.getStatus() == -2 ? "已删除" : "");
					map.put("warn_line", p.getWarnLine());
					map.put("close_line", p.getCloseLine());
					map.put("trade_service_name", p.getTradeServiceName() == null ? "" : p.getTradeServiceName());
					map.put("trade_ip", p.getTradeIp() + ":" + p.getTradePort());
					map.put("trade_account", p.getTradeAccount());
					map.put("trade_time", p.getTradeTime() == null ? "" : sdf.format(p.getTradeTime()));
					map.put("close_time", p.getCloseTime() == null ? "" : sdf.format(p.getCloseTime()));
					map.put("next_manage_money_time", p.getNextManageMoneyTime() == null ? "" : sdf.format(p.getNextManageMoneyTime()));
					map.put("add_safe_money", p.getAddSafeMoney() == null ? "0" : p.getAddSafeMoney());
					map.put("user_id", p.getFuUser().getId() == null ? "" : p.getFuUser().getId());
					map.put("phone", p.getFuUser().getPhone() == null ? "" : p.getFuUser().getPhone());
					if (p.getFuServer() != null) {
						FuServer server = p.getFuServer();
						Map<String, Object> serverMap = new HashMap<String, Object>();
						serverMap.put("id", server.getId() == null ? "" : server.getId());
						serverMap.put("db_ip", server.getDbIp() == null ? "" : server.getDbIp());
						serverMap.put("db_port", server.getDbPort() == null ? "" : server.getDbPort());
						serverMap.put("db_name", server.getDbName() == null ? "" : server.getDbName());
						serverMap.put("db_read_username", server.getDbReadUserName() == null ? "" : server.getDbReadUserName());
						serverMap.put("db_read_password", server.getDbReadPassWord() == null ? "" : server.getDbReadPassWord());
						List<Object> list = new ArrayList<Object>();
						list.add(serverMap);
						map.put("server", list);
					} else {
						map.put("server", "");
					}
					programs.add(map);
				}
				obj.put("programs", programs);
			} else {
				obj.put("messge", "无方案数据");
				obj.put("is_success", 0);
			}
			obj.put("message", "查询成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 绑定邮箱
	 */
	@Override
	public String bindEmail(String phone, String email, String emailCode, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (email == null || "".equals(email)) {
				obj.put("message", "邮箱为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (emailCode == null || "".equals(emailCode)) {
				obj.put("message", "邮箱验证码为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (!RegexChk.checkEmail(email)) {
				obj.put("message", "邮箱格式错误");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (StringUtil.isBlank(fuUser.getEmailCode())) {
				obj.put("message", "还未获取邮箱验证码");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (!fuUser.getEmailCode().equals(emailCode)) {
				obj.put("message", "邮箱验证码错误");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			fuUser.setEmail(email);
			if (fuUser.getIsBindEmail() == null || fuUser.getIsBindEmail() == 0) {
				fuUser.setSafeLevel(fuUser.getSafeLevel() + 1);
			}
			fuUser.setIsBindEmail(1);
			fuUserService.save(fuUser);
			obj.put("message", "邮箱绑定成功");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "操作失败");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 参赛用户统计
	 * 
	 * @return
	 */
	@Override
	public String queryGameInfo(String queryDate, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (queryDate == null || "".equals(queryDate)) {
				obj.put("message", "查询日期为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			List<FuGame> gameList = fuGameService.findGames(queryDate);
			List<Object> games = new ArrayList<Object>();
			FuServer server = fuServerService.findServerByUserTypeId(2);
			if (gameList != null && gameList.size() > 0) {
				for (FuGame g : gameList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("phone", g.getFuUser().getPhone() == null ? "" : g.getFuUser().getPhone());
					map.put("trade_account", g.getTradeAccount() == null ? "" : g.getTradeAccount());
					map.put("match_money", g.getGameMoney() == null ? "0" : g.getGameMoney().divide(new BigDecimal(2)));
					map.put("safe_money", g.getGameMoney() == null ? "0" : g.getGameMoney().divide(new BigDecimal(2)));
					if (server != null) {
						Map<String, Object> serverMap = new HashMap<String, Object>();
						serverMap.put("id", server.getId() == null ? "" : server.getId());
						serverMap.put("db_ip", server.getDbIp() == null ? "" : server.getDbIp());
						serverMap.put("db_port", server.getDbPort() == null ? "" : server.getDbPort());
						serverMap.put("db_name", server.getDbName() == null ? "" : server.getDbName());
						serverMap.put("db_read_username", server.getDbReadUserName() == null ? "" : server.getDbReadUserName());
						serverMap.put("db_read_password", server.getDbReadPassWord() == null ? "" : server.getDbReadPassWord());
						List<Object> list = new ArrayList<Object>();
						list.add(serverMap);
						map.put("server", list);
					} else {
						map.put("server", "");
					}
					games.add(map);
				}
				obj.put("is_success", 1);
				obj.put("message", "查询成功");
				obj.put("programs", games);
			} else {
				obj.put("is_success", 0);
				obj.put("message", "无数据");
			}
			// }else{
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 根据手机号统计比赛数据
	 * 
	 * @return
	 */
	@Override
	public String queryGameInfoByPhone(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "手机号为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findUserByPhone(phone);
			if (fuUser == null) {
				obj.put("message", "手机号不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			List<FuGame> gameList = fuGameService.findGames(fuUser.getId());
			List<Object> games = new ArrayList<Object>();
			FuServer server = fuServerService.findServerByUserTypeId(2);
			if (gameList != null && gameList.size() > 0) {
				for (FuGame g : gameList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("phone", g.getFuUser().getPhone() == null ? "" : g.getFuUser().getPhone());
					map.put("trade_account", g.getTradeAccount() == null ? "" : g.getTradeAccount());
					map.put("match_money", g.getGameMoney() == null ? "0" : g.getGameMoney().divide(new BigDecimal(2)));
					map.put("safe_money", g.getGameMoney() == null ? "0" : g.getGameMoney().divide(new BigDecimal(2)));
					if (server != null) {
						Map<String, Object> serverMap = new HashMap<String, Object>();
						serverMap.put("id", server.getId() == null ? "" : server.getId());
						serverMap.put("db_ip", server.getDbIp() == null ? "" : server.getDbIp());
						serverMap.put("db_port", server.getDbPort() == null ? "" : server.getDbPort());
						serverMap.put("db_name", server.getDbName() == null ? "" : server.getDbName());
						serverMap.put("db_read_username", server.getDbReadUserName() == null ? "" : server.getDbReadUserName());
						serverMap.put("db_read_password", server.getDbReadPassWord() == null ? "" : server.getDbReadPassWord());
						List<Object> list = new ArrayList<Object>();
						list.add(serverMap);
						map.put("server", list);
					} else {
						map.put("server", "");
					}
					games.add(map);
				}
				obj.put("is_success", 1);
				obj.put("message", "查询成功");
				obj.put("programs", games);
			} else {
				obj.put("is_success", 0);
				obj.put("message", "无数据");
			}
			// }else{
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 注册发钱活动是否结束
	 * 
	 * @param sign
	 * @return
	 */
	@Override
	public String queryRegisterActivity(String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			// 判断活动是否已开启
			HhrPromoteParameter param = promoteParameterService.findParameter();
			if (param.getIsOpen() == 0) {
				obj.put("message", "活动尚未开启");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			// 判断活动费用余额是否足够, 暂定50
			if (param.getTotalMoney().compareTo(new BigDecimal(50)) == -1) {
				obj.put("message", "活动费用不足");
				obj.put("is_success", 2);
				System.out.println(obj);
				return obj.toString();
			}
			obj.put("message", "活动正常");
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 查询用户留言记录
	 * 
	 * @return
	 */
	@Override
	public String queryUserMessage(Long user_id, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (user_id == null) {
				obj.put("message", "用户id为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.get(user_id);
			if (fuUser == null) {
				obj.put("message", "用户不存在");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			List<FuMessage> messageList = fuMessageService.findMessageByUser(user_id);
			List<Object> messages = new ArrayList<Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (messageList != null && messageList.size() > 0) {
				for (FuMessage m : messageList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", m.getId() == null ? "" : m.getId());
					map.put("user_id", m.getFuUser().getId() == null ? "" : m.getFuUser().getId());
					map.put("time", m.getTime() == null ? "" : sdf.format(m.getTime()));
					map.put("content", m.getContent() == null ? "" : m.getContent());
					map.put("reply_content", m.getReplyContent() == null ? "" : m.getReplyContent());
					map.put("reply_mark", m.getReplyMark() == null ? "" : m.getReplyMark());
					map.put("reply_time", m.getReplyTime() == null ? "" : sdf.format(m.getReplyTime()));
					messages.add(map);
				}
				obj.put("is_success", 1);
				obj.put("message", "留言查询成功");
				obj.put("messages", messages);
			} else {
				obj.put("is_success", 0);
				obj.put("message", "无留言数据");
			}
			// }else{
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 查询手机端银联支付的tn
	 * 
	 * @param tn
	 * @return
	 */
	public String queryChinaPayTN(String money, String sign) {
		// JSONObject obj = new JSONObject();
		// try {
		// String result = DesUtil.webserviceSignVerify(sign);
		// if("success".equals(result)){
		// /**
		// * 参数初始化
		// * 在java main 方式运行时必须每次都执行加载
		// * 如果是在web应用开发里,这个方写在可使用监听的方式写入缓存,无须在这出现
		// */
		// SDKConfig.getConfig().loadPropertiesFromSrc();//
		// 从classpath加载acp_sdk.properties文件
		//
		// /**
		// * 组装请求报文
		// */
		// Map<String, String> data = new HashMap<String, String>();
		// // 版本号
		// data.put("version", "5.0.0");
		// // 字符集编码 默认"UTF-8"
		// data.put("encoding", "UTF-8");
		// // 签名方法 01 RSA
		// data.put("signMethod", "01");
		// // 交易类型 01-消费
		// data.put("txnType", "01");
		// // 交易子类型 01:自助消费 02:订购 03:分期付款
		// data.put("txnSubType", "01");
		// // 业务类型
		// data.put("bizType", "000201");
		// // 渠道类型，07-PC，08-手机
		// data.put("channelType", "08");
		// // 前台通知地址 ，控件接入方式无作用
		// data.put("frontUrl",
		// "http://localhost:8080/ACPTest/acp_front_url.do");
		// // 后台通知地址
		// data.put("backUrl",
		// "http://222.222.222.222:8080/ACPTest/acp_back_url.do");
		// // 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		// data.put("accessType", "0");
		// // 商户号码，请改成自己的商户号
		// data.put("merId", Property.getProperty("CHINAPAY_MERCHANT_NO"));
		// // 商户订单号，8-40位数字字母
		// data.put("orderId", new SimpleDateFormat("yyyyMMddHHmmss").format(new
		// Date()));
		// // 订单发送时间，取系统时间
		// data.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new
		// Date()));
		// // 交易金额，单位分
		// data.put("txnAmt", money);
		// // 交易币种
		// data.put("currencyCode", "156");
		// // 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		// // data.put("reqReserved", "透传信息");
		// // 订单描述，可不上送，上送时控件中会显示该信息
		// // data.put("orderDesc", "订单描述");
		//
		// data = AppChinaPayBase.signData(data);
		//
		// // 交易请求url 从配置文件读取
		// String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();
		//
		// Map<String, String> resmap = AppChinaPayBase.submitUrl(data,
		// requestAppUrl);
		//
		// System.out.println("请求报文=["+data.toString()+"]");
		// System.out.println("应答报文=["+resmap.toString()+"]");
		// System.out.println("APP银联支付流水号="+resmap.get("tn").toString());
		// obj.put("is_success", 1);
		// obj.put("message", "APP银联支付流水号查询成功");
		// obj.put("messages", resmap.get("tn").toString());
		// }else{
		// obj.put("is_success", 0);
		// obj.put("message", result);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// obj.put("is_success", 0);
		// obj.put("message", "invalid request");
		// }
		// System.out.println(obj);
		return "obj.toString()";
	}

	/**
	 * 修改合伙人备注名
	 * 
	 * @param
	 * @return
	 */
	public String updateRemarkName(Long user_id, Long relevance_id, String remarkName, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (user_id == null) {
				obj.put("message", "用户id为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			if (relevance_id == null) {
				obj.put("message", "关联合伙人用户id为空");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuUserService.findFuUserById(user_id);
			FuUser relevanceUser = fuUserService.findFuUserById(relevance_id);
			HhrRemark hhrRemark = hhrRemarkService.findRemarkByUserId(user_id, relevance_id);
			if (hhrRemark == null) {
				hhrRemark = new HhrRemark();
				hhrRemark.setFuUser(fuUser);
				hhrRemark.setRelevanceUser(relevanceUser);
			}
			hhrRemark.setRemarkName(remarkName);
			hhrRemarkService.save(hhrRemark);
			obj.put("is_success", 1);
			obj.put("message", "备注名修改成功");
			// }else{
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 检测号码是否被注册
	public String isRegister(String phone, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("message", "请输入电话号码");
				obj.put("is_success", 0);
				return obj.toString();
			}

			FuUser user = fuUserService.findUserByAccount(phone);
			if (user != null) { // 已经被注册过
				obj.put("is_success", 1);
				obj.put("message", "已经被注册过");
			} else { // 没有被注册过
				obj.put("is_success", 2);
				obj.put("message", "没有被注册过");
			}
			// }else{
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 检测用户是否报名了该次比赛
	 * 
	 * @return
	 * @throws Exception
	 */
	public String isRegistration(String phone, String gameId, String sign) {
		JSONObject obj = new JSONObject();
		FuUser user = null;
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			user = fuUserService.findUserByAccount(phone);
			obj.put("phone", phone);
			obj.put("invite_code", user.getInvitationCode());
			// 根据用户以及比赛序列号查询用户是否参加过该赛事
			List<FuGame> list = fuGameService.findGameByUser(user, gameId);
			if (list != null && list.size() > 0) {
				obj.put("is_success", 0);
				obj.put("message", "你已经报名过!");
				return obj.toString();
			} else {
				obj.put("is_success", 1);
				obj.put("message", "可以报名");
			}
			// }else {
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}

	/**
	 * 报名参赛
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveGame(String phone, String gameId, String competitionNum, String sign) {
		JSONObject obj = new JSONObject();
		try {
			// String result = DesUtil.webserviceSignVerify(sign);
			// if("success".equals(result)){
			FuUser user = fuUserService.findUserByAccount(phone);
			if (user != null) {
				// 查询该用户是否有费率
				FuRate param = fuRateService.getByUserID(user.getId());
				if (param == null) {
					// 系统参数还没有设置，请联系客服
					obj.put("is_success", 0);
					obj.put("message", "系统参数还没有设置，请联系客服！");
					return obj.toString();
				}

				Calendar cal = Calendar.getInstance();
				Date time1 = cal.getTime();
				cal.set(Calendar.HOUR_OF_DAY, 16);
				cal.set(Calendar.MINUTE, 20);
				cal.set(Calendar.SECOND, 1);
				Date time2 = cal.getTime();
				cal.set(Calendar.HOUR_OF_DAY, 16);
				cal.set(Calendar.MINUTE, 50);
				cal.set(Calendar.SECOND, 0);
				Date time3 = cal.getTime();
				boolean dealing = time1.after(time2) && time1.before(time3);// 16:20-16:50时间段
				if (dealing == true) {
					obj.put("is_success", 0);
					obj.put("message", "服务器维护！");
					return obj.toString();
				}

				// 根据用户以及比赛序列号查询用户是否参加过该赛事
				List<FuGame> list = fuGameService.findGameByUser(user, gameId);
				if (list != null && list.size() > 0) {
					obj.put("is_success", 0);
					obj.put("message", "你已经报名过!");
					return obj.toString();
				}

				// 保存比赛
				fuProgramService.saveGame(param, user, Integer.parseInt(competitionNum));
				obj.put("is_success", 1);
				obj.put("message", "报名成功");
			} else {
				obj.put("is_success", 0);
				obj.put("message", "请先注册");
			}
			// }else {
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}

	/**
	 * 单独发送验证码
	 */
	public String sendCode(String ip, String phone, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			if (ServletActionContext.getRequest().getMethod().equals("POST")) {
				// if("success".equals(result)){
				if (null == ip || "".equals(ip)) {
					obj.put("message", "缺少ip参数");
					obj.put("is_success", 0);
					System.out.println(obj.toString());
					return obj.toString();
				}
				// 判断手机号或者是ip是否在黑名单中
				int flag = fuSmsLogService.examin(phone, ip);
				if (flag == 0) { // false
					obj.put("message", "请联系管理员解冻你的封印");
					obj.put("is_success", 0);
					System.out.println(obj.toString());
					return obj.toString();
				} else if (flag == 2) {
					obj.put("message", "您点击过于频繁, 请稍后再试");
					obj.put("is_success", 0);
					System.out.println(obj.toString());
					return obj.toString();
				}

				DecimalFormat format = new DecimalFormat("0000");
				String code = format.format(Math.random() * 9999);
				String message = URLDecoder.decode("欢迎注册成为超级合伙人，您的手机验证码是:" + code + "，请填入注册界面完成注册。", "UTF-8");
				FuSmsLog log = new FuSmsLog();
				log.setContent(message);
				log.setPrio(1);
				log.setReason("用户注册");
				log.setDestination(phone);
				log.setPlanTime(new Date());
				// log.setType(1);// 短信
				log.setType(4);// 语音短信
				log.setRegCode(code);
				log.setState(0);
				obj.put("message", "请求成功");
				obj.put("is_success", 1);
				// obj.put("phone_code", code);
				fuSmsLogService.save(log);
				// }else{
				// obj.put("message", result);
				// obj.put("is_success", 0);
				// }
			} else {
				obj.put("message", "invalid request");
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

	/**
	 * 查询这个用户下所有的开户账户 以及今日最新的盈利
	 * 
	 * @param userId
	 * @param sign
	 * @return
	 */
	public String accountList(String userId, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (userId == null || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(userId));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}

			// 根据用户 查询该用户并且是一个有效用户开的所有的账户信息
			Map<String, Object> parames = new HashMap<String, Object>();
			parames.put("isDel", 0);
			parames.put("userId", user.getId());
			List<FuStockAccount> stockAccountList = fuStockAccountService.findAccountByMap(0, 100, parames);
			BigDecimal totalProfit = new BigDecimal(0); // 今日总盈亏
			BigDecimal totalNoMangeFee = new BigDecimal(0); // 累计未缴管理费
			BigDecimal totalMustMangeFee = new BigDecimal(0); // 累计应缴管理费
			List<Object> list = new ArrayList<Object>();
			if (stockAccountList != null && stockAccountList.size() > 0) {
				for (FuStockAccount fuStockAccount : stockAccountList) {
					Map<String, Object> map = new HashMap<String, Object>();
					if (fuStockAccount == null) {
						map.put("id", 0);
						map.put("open_equity", "");
						map.put("partnter_account", "");
						map.put("capital_account", "");
					} else {
						map.put("id", fuStockAccount.getId());
						map.put("open_equity", fuStockAccount.getOpenEquity() == null ? "" : fuStockAccount.getOpenEquity());
						map.put("partnter_account", fuStockAccount.getPartnerAccount() == null ? "" : fuStockAccount.getPartnerAccount());
						map.put("capital_account", fuStockAccount.getCapitalAccount() == null ? "" : fuStockAccount.getCapitalAccount());
					}
					// 根据开户账户id 查询 开户详细信息
					FuStockMoneyDetail stockDetail = fuStockMoneyDetailService.findDetailByStockId(fuStockAccount.getId());
					if (stockDetail != null) {
						map.put("now_profit", stockDetail.getNowProfit() == null ? 0 : stockDetail.getNowProfit());
						map.put("manage_fee", stockDetail.getManageFee() == null ? 0 : stockDetail.getManageFee());
						totalProfit = totalProfit.add(stockDetail.getNowProfit());
					} else {
						map.put("now_profit", 0);
						map.put("manage_fee", 0);
					}

					int accountStatus = fuStockAccount.getState();
					map.put("account_status", accountStatus);

					list.add(map);
				}
			} else {
				obj.put("message", "没有数据!");
				obj.put("is_success", "1");
			}
			// 以及所有账户的今日盈利的和
			obj.put("day_total_profit", totalProfit == null ? 0 : totalProfit);
			// 累计
			Map<String, Object> infoParames = new HashMap<String, Object>();
			infoParames.put("userId", user.getId());
			FuStockMoneyInfo moneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(infoParames);
			if (moneyInfo == null) {
				// 所有账户的累计盈利
				obj.put("total_profit", 0);
				// 累计未缴管理费
				obj.put("none_total_fee", 0);
				// 累计应缴管理费
				obj.put("must_total_fee", 0);
			} else {
				// 所有账户的累计盈利
				obj.put("total_profit", moneyInfo.getProfitInfo() == null ? 0 : moneyInfo.getProfitInfo());
				// 累计未缴管理费
				obj.put("none_total_fee", moneyInfo.getNoneFeeInfo() == null ? 0 : moneyInfo.getNoneFeeInfo());
				// 累计应缴管理费
				obj.put("must_total_fee", moneyInfo.getMustFeeInfo() == null ? 0 : moneyInfo.getMustFeeInfo());
			}
			obj.put("stocks", list);

			obj.put("is_success", 1);
			obj.put("message", "Query release alliance success");
			System.out.println(obj.toString());
			return obj.toString();
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 每日盈亏流水
	 * 
	 * @param userId
	 *            用户唯一标识符
	 * @param sign
	 * @return
	 */
	public String profitOrLossDetail(String userId, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (userId == null || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(userId));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}

			// 根据userid 查询stock_money_detail
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(user.getId(), null, null);
			if (detailList == null || detailList.size() <= 0) {
				obj.put("is_success", 1);
				obj.put("message", "没有数据");
				System.out.println(obj);
				return obj.toString();
			}
			List<Object> list = new ArrayList<Object>();
			for (FuStockMoneyDetail fuStockMoneyDetail : detailList) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 开户劵商
				map.put("open_equity", fuStockMoneyDetail.getFuStockAccount().getOpenEquity() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getOpenEquity());
				map.put("partnter_account", fuStockMoneyDetail.getFuStockAccount().getPartnerAccount() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getPartnerAccount());
				// 当日盈利
				map.put("now_profit", fuStockMoneyDetail.getNowProfit() == null ? 0 : fuStockMoneyDetail.getNowProfit());
				map.put("trade_time", fuStockMoneyDetail.getTradeTime() == null ? "" : DateUtil.getStrFromDate(fuStockMoneyDetail.getTradeTime(), "yyyy-MM-dd"));
				map.put("capital_account", fuStockMoneyDetail.getFuStockAccount().getCapitalAccount() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getCapitalAccount());
				list.add(map);

			}
			obj.put("profits", list);
			obj.put("is_success", 1);
			obj.put("message", "每日盈亏详细查询成功");
			// }else {
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 应缴费明细
	public String payAbleDetail(String userId, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (userId == null || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(userId));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}
			// 根据userid 查询stock_money_detail
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(user.getId(), null, null);
			if (detailList == null || detailList.size() <= 0) {
				obj.put("is_success", 1);
				obj.put("message", "没有数据");
				System.out.println(obj);
				return obj.toString();
			}
			List<Object> list = new ArrayList<Object>();
			for (FuStockMoneyDetail fuStockMoneyDetail : detailList) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 开户劵商
				map.put("open_equity", fuStockMoneyDetail.getFuStockAccount().getOpenEquity() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getOpenEquity());
				map.put("partnter_account", fuStockMoneyDetail.getFuStockAccount().getPartnerAccount() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getPartnerAccount());
				// 当日管理费
				map.put("manage_fee", fuStockMoneyDetail.getManageFee() == null ? 0 : fuStockMoneyDetail.getManageFee());
				// 当日应退赔付
				map.put("quit_compen", fuStockMoneyDetail.getQuitCompen() == null ? 0 : fuStockMoneyDetail.getQuitCompen());
				map.put("trade_time", fuStockMoneyDetail.getTradeTime() == null ? "" : DateUtil.getStrFromDate(fuStockMoneyDetail.getTradeTime(), "yyyy-MM-dd"));
				list.add(map);

			}
			obj.put("payAbles", list);
			obj.put("is_success", 1);
			obj.put("message", "每日应缴详情查询成功");
			// }else {
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	@Override
	public String noPayAbleDateil(String userId, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (userId == null || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(userId));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}
			// 根据userid 查询stock_money_detail
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(user.getId(), null, null);
			if (detailList == null || detailList.size() <= 0) {
				obj.put("is_success", 1);
				obj.put("message", "没有数据");
				System.out.println(obj);
				return obj.toString();
			}
			List<Object> list = new ArrayList<Object>();
			for (FuStockMoneyDetail fuStockMoneyDetail : detailList) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 开户劵商
				// map.put("open_equity",
				// fuStockMoneyDetail.getFuStockAccount().getOpenEquity() ==
				// null ? "" :
				// fuStockMoneyDetail.getFuStockAccount().getOpenEquity());
				// map.put("partnter_account",
				// fuStockMoneyDetail.getFuStockAccount().getPartnerAccount() ==
				// null ? "" :
				// fuStockMoneyDetail.getFuStockAccount().getPartnerAccount());
				// 当日管理费
				map.put("pay_fee", fuStockMoneyDetail.getPayFee() == null ? 0 : fuStockMoneyDetail.getPayFee());
				// 当日应退赔付
				// map.put("quit_compen", fuStockMoneyDetail.getQuitCompen() ==
				// null ? 0 : fuStockMoneyDetail.getQuitCompen());
				map.put("trade_time", fuStockMoneyDetail.getTradeTime() == null ? "" : DateUtil.getStrFromDate(fuStockMoneyDetail.getTradeTime(), "yyyy-MM-dd"));
				list.add(map);

			}
			// 未缴费用统计
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("userId", user.getId());
			FuStockMoneyInfo findMoneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(m);
			obj.put("total_no_pay", findMoneyInfo.getNoneFeeInfo() == null ? 0 : findMoneyInfo.getNoneFeeInfo());
			// 已缴费用统计
			obj.put("total_pay", findMoneyInfo.getPayFeeInfo() == null ? 0 : findMoneyInfo.getPayFeeInfo());
			obj.put("noPayAbles", list);
			obj.put("is_success", 1);
			obj.put("message", "交付记录查询成功");
			// }else {
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 根据账号id查询账号的基本信息
	public String accountDatil(String stockId, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (stockId == null || "".equals(stockId)) {
				obj.put("is_success", 0);
				obj.put("message", "没有这个账号");
				return obj.toString();
			}
			FuStockAccount fsa = fuStockAccountService.findAccountById(Long.parseLong(stockId));
			if (fsa != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 开户id
				map.put("id", fsa.getId());
				// 开户人姓名
				map.put("open_user", fsa.getOpenUser() == null ? "" : fsa.getOpenUser());
				// 开户券商
				map.put("open_equity", fsa.getOpenEquity() == null ? "" : fsa.getOpenEquity());
				// 营业部
				map.put("sales_dept", fsa.getSalesDept() == null ? "" : fsa.getSalesDept());
				// 资金账号
				map.put("capital_account", fsa.getCapitalAccount() == null ? "" : fsa.getCapitalAccount());
				// 股东账号
				map.put("partner_account", fsa.getPartnerAccount() == null ? "" : fsa.getPartnerAccount());
				// 股东账号
				map.put("account_type", fsa.getAccountType() == null ? "" : fsa.getAccountType());
				// 是否删除
				map.put("is_del", fsa.getIsDel() == null ? "" : fsa.getIsDel());
				int statusBefore = 0; // 0:申请开启委托 1:申请暂停委托 3申请开启委托中 4申请暂停委托中
				// 状态 0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中
				if (fsa.getState() == 0) {
					statusBefore = 1;
				} else if (fsa.getState() == 1) {
					statusBefore = 0;
				} else if (fsa.getState() == 2) {
					statusBefore = 3;
				} else if (fsa.getState() == 3) {
					statusBefore = 4;
				} else if (fsa.getState() == 4) {
					statusBefore = 5;
				}
				map.put("status_before", statusBefore);
				obj.put("accInfo", map);
				obj.put("message", "查询账号的详细信息成功");
			} else {
				obj.put("message", "没有数据");
			}
			obj.put("is_success", 1);
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 根据stockId 设置idDel的状态值为0
	public String delAccount(String stockId, String isDel, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (stockId == null || "".equals(stockId)) {
				obj.put("is_success", 0);
				obj.put("message", "没有这个账号");
				return obj.toString();
			}
			FuStockAccount fsa = fuStockAccountService.findAccountById(Long.parseLong(stockId));
			if (fsa != null) {
				/*
				 * fsa.setIsDel(Integer.parseInt(isDel));
				 * fuStockAccountService.save(fsa);
				 */
				/* 返回当前用户可操作状态为 */
				// 查询最新一条数据的记录
				int accStatus = fsa.getState();
				// statusBefore 0:申请开启委托 1:申请暂停委托 3申请开启委托中 4申请暂停委托中 5申请结算中
				int statusBefore = 0;
				// accStatus 状态 0开启委托，1暂停委托，2申请开启委托中 3申请暂停委托中 4申请结算中
				if (accStatus == 0) {
					statusBefore = 1;
				} else if (accStatus == 1) {
					statusBefore = 0;
				} else if (accStatus == 2) {
					statusBefore = 3;
				} else if (accStatus == 3) {
					statusBefore = 4;
				} else if (accStatus == 4) {
					statusBefore = 5;
				}
				StockStatusRecord statusRecord = new StockStatusRecord();
				statusRecord.setOperationStatus(5);
				statusRecord.setChangeTime(new Date());
				statusRecord.setIsOperation(0);
				statusRecord.setAfterStatus(5);
				statusRecord.setFuUser(fsa.getFuUser());
				statusRecord.setFuStockAccount(fsa);
				stockStatusRecordService.save(statusRecord);
				fsa.setState(4);
				fuStockAccountService.save(fsa);
				obj.put("status_before", 5);
				obj.put("message", "删除这个账号信息成功");
				obj.put("is_success", 1);
			} else {
				obj.put("is_success", 0);
				obj.put("message", "账号不存在!");
				obj.put("is_success", 0);
			}
			// }else{
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 保存基本的账户信息
	public String addAccountInfo(String userId, String openUser, String openEquity, String salesDept, String capitalAccount, String capitalPassword, String partnerAccount, String accountType, String sign) {

		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (userId == null || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				System.out.println(obj);
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(userId));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				System.out.println(obj);
				return obj.toString();
			}

			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(user.getId());
			// 系统参数还没有设置，请联系客服
			if (param == null) {
				obj.put("is_success", 0);
				obj.put("message", "系统参数还没有设置，请联系客服");
				System.out.println(obj);
				return obj.toString();
			}
			/*
			 * if(user.getIsCheckCard()==null||user.getIsCheckCard()!=2){
			 * obj.put("is_success", 0); obj.put("message", "请先进行实名认证后，再进行操作");
			 * System.out.println(obj); return obj.toString(); }
			 */

			if (accountType == null || "".equals(accountType)) {
				accountType = "1";
			}
			Date nowDate = new Date();
			FuStockAccount stockAcc = new FuStockAccount();
			stockAcc.setFuUser(user);
			stockAcc.setOpenUser(openUser);
			stockAcc.setOpenEquity(openEquity);
			stockAcc.setSalesDept(salesDept);
			stockAcc.setCapitalAccount(capitalAccount);
			stockAcc.setCapitalPassword(new String(Base64.encode(capitalPassword.getBytes())));
			stockAcc.setPartnerAccount(partnerAccount);
			stockAcc.setAccountType(Integer.parseInt(accountType));
			stockAcc.setCreateTime(nowDate);
			stockAcc.setUpdateTime(nowDate);
			stockAcc.setState(0);
			stockAcc.setIsDel(0);
			// 根据user_id 和 capitalAccount 查询这个账户
			FuStockAccount fsa = fuStockAccountService.findAccountByUserIdAndCap(user.getId(), capitalAccount);
			if (fsa == null) {
				fuStockAccountService.save(stockAcc);
				obj.put("message", "股票账户添加成功");
			} else {
				obj.put("is_success", 0);
				obj.put("message", "账号已存在! 请联系客户");
				System.out.println(obj);
				return obj.toString();
			}
			obj.put("is_success", 1);
			// }else {
			// obj.put("message", result);
			// obj.put("is_success", 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 根据用查询用户的全部资产明细
	 * 
	 * @param user_id
	 *            用户id
	 * @param sign
	 * @return
	 */
	public String assetsAll(String user_id, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (user_id == null || "".equals(user_id)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				System.out.println(obj);
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(user_id));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				System.out.println(obj);
				return obj.toString();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			map.put("money3", 0);
			List<FuMoneyDetail> moneyDeatilList = fuMoneyDetailService.findListBy(0, 1000, map);
			List<Object> list = new ArrayList<Object>();
			if (moneyDeatilList != null && moneyDeatilList.size() > 0) {
				for (FuMoneyDetail fuMoneyDetail : moneyDeatilList) {
					Map<String, Object> detailMap = new HashMap<String, Object>();
					detailMap.put("assets_name", fuMoneyDetail.getFuDictionary().getName());
					Date time = fuMoneyDetail.getTime();
					String createTime = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
					detailMap.put("create_time", createTime);
					// detailMap.put("money", fuMoneyDetail.getMoney() == null ?
					// 0.00 : fuMoneyDetail.getMoney());
					if (fuMoneyDetail.getIsIncome()) { // 1 : 收入
						if (null == fuMoneyDetail.getMoney()) {
							detailMap.put("money", 0.00);
						} else {
							detailMap.put("money", "+" + fuMoneyDetail.getMoney().abs());
						}
					} else {
						if (null == fuMoneyDetail.getMoney()) {
							detailMap.put("money", 0.00);
						} else {
							detailMap.put("money", "-" + fuMoneyDetail.getMoney().abs());
						}
					}
					detailMap.put("account_balance_before", fuMoneyDetail.getAccountBalanceAfter() == null ? 0.00 : fuMoneyDetail.getAccountBalanceAfter());
					list.add(detailMap);
				}
				obj.put("is_success", 1);
				obj.put("message", "查询用户资产明细成功");
				obj.put("assetsList", list);
			} else {
				obj.put("is_success", 0);
				obj.put("message", "没有用户资产明细");
			}

			// }else {
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 根据用户查询用户的收入明细
	public String inComeDetail(String user_id, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (user_id == null || "".equals(user_id)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				System.out.println(obj);
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(user_id));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				System.out.println(obj);
				return obj.toString();
			}
			// 收入 是 1
			List<FuMoneyDetail> moneyDeatilList = fuMoneyDetailService.findInComeByUserId(Long.parseLong(user_id), 1, 0, 1000);
			if (moneyDeatilList == null || moneyDeatilList.size() <= 0) {
				obj.put("is_success", 0);
				obj.put("message", "用户没有收入资产明细");
				System.out.println(obj);
				return obj.toString();
			}
			List<Object> list = new ArrayList<Object>();
			for (FuMoneyDetail fuMoneyDetail : moneyDeatilList) {
				Map<String, Object> detailMap = new HashMap<String, Object>();
				detailMap.put("assets_name", fuMoneyDetail.getFuDictionary().getName());
				Date time = fuMoneyDetail.getTime();
				String createTime = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
				detailMap.put("create_time", createTime);
				// detailMap.put("money", fuMoneyDetail.getMoney() == null ?
				// 0.00 : fuMoneyDetail.getMoney());

				if (fuMoneyDetail.getIsIncome()) { // 1 : 收入
					if (null == fuMoneyDetail.getMoney()) {
						detailMap.put("money", 0.00);
					} else {
						detailMap.put("money", "+" + fuMoneyDetail.getMoney().abs());
					}
				} else {
					if (null == fuMoneyDetail.getMoney()) {
						detailMap.put("money", 0.00);
					} else {
						detailMap.put("money", "-" + fuMoneyDetail.getMoney().abs());
					}
				}
				detailMap.put("account_balance_before", fuMoneyDetail.getAccountBalanceAfter() == null ? 0.00 : fuMoneyDetail.getAccountBalanceAfter());
				list.add(detailMap);
			}
			obj.put("is_success", 1);
			obj.put("message", "查询用户收入资产明细成功");
			obj.put("assetsList", list);

			// }else {
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	@Override
	public String outComeDetail(String user_id, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (user_id == null || "".equals(user_id)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录!");
				System.out.println(obj);
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(user_id));
			if (user == null) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				System.out.println(obj);
				return obj.toString();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			// 收入 是 1
			List<FuMoneyDetail> moneyDeatilList = fuMoneyDetailService.findInComeByUserId(Long.parseLong(user_id), 0, 0, 1000);
			if (moneyDeatilList == null || moneyDeatilList.size() <= 0) {
				obj.put("is_success", 0);
				obj.put("message", "用户没有支出资产明细");
				System.out.println(obj);
				return obj.toString();
			}
			List<Object> list = new ArrayList<Object>();
			for (FuMoneyDetail fuMoneyDetail : moneyDeatilList) {
				Map<String, Object> detailMap = new HashMap<String, Object>();
				detailMap.put("assets_name", fuMoneyDetail.getFuDictionary().getName());
				Date time = fuMoneyDetail.getTime();
				String createTime = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
				detailMap.put("create_time", createTime);
				// fuMoneyDetail.getIsIncome()

				if (fuMoneyDetail.getIsIncome()) { // 1 : 收入
					if (null == fuMoneyDetail.getMoney()) {
						detailMap.put("money", 0.00);
					} else {
						detailMap.put("money", "+" + fuMoneyDetail.getMoney().abs());
					}
				} else {
					if (null == fuMoneyDetail.getMoney()) {
						detailMap.put("money", 0.00);
					} else {
						detailMap.put("money", "-" + fuMoneyDetail.getMoney().abs());
					}
				}
				detailMap.put("account_balance_before", fuMoneyDetail.getAccountBalanceAfter() == null ? 0.00 : fuMoneyDetail.getAccountBalanceAfter());
				list.add(detailMap);
			}
			obj.put("is_success", 1);
			obj.put("message", "查询用户支出资产明细成功");
			obj.put("assetsList", list);

			// }else {
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 添加一个用户改变状态记录
	 * 
	 * @param accountId
	 *            股票账户表的id
	 * @param accountStatus
	 *            账号当前的状态
	 * @param operationStatusBefore
	 *            用户当前可以操作的状态
	 * @param operationStatusAfter
	 *            用户操作后的状态
	 * @param userId
	 *            用户的id
	 * @param sign
	 *            加密密钥
	 * @return
	 */
	public String addStatusRecord(String accountId, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (accountId == null || "".equals(accountId)) {
				obj.put("is_success", 0);
				obj.put("message", "accountId不能为空!");
				System.out.println(obj);
				return obj.toString();
			}
			FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(accountId));
			if (fuStockAccount == null) {
				obj.put("is_success", 0);
				obj.put("message", accountId + "的股票账号不存在!");
				System.out.println(obj);
				return obj.toString();
			}
			FuUser fuUser = fuStockAccount.getFuUser();
			if (fuUser == null) {
				obj.put("is_success", 0);
				obj.put("message", fuStockAccount.getFuUser().getId() + "的用户不存在!");
				System.out.println(obj);
				return obj.toString();
			}

			int accStatus = fuStockAccount.getState();
			int statusBefore = 0; // 0:申请开启委托 1:申请暂停委托 3申请开启委托中 4申请暂停委托中
			// 状态 0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中
			if (accStatus == 0) {
				statusBefore = 1;
			} else if (accStatus == 1) {
				statusBefore = 0;
			} else if (accStatus == 2) {
				statusBefore = 3;
			} else if (accStatus == 3) {
				statusBefore = 4;
			}
			int statusAfter = 0;
			if (accStatus == 0 && statusBefore == 1) {
				statusAfter = 1;
				statusBefore = 4;
				accStatus = 3;
				obj.put("operation_status_before", 4);
			} else if (accStatus == 1 && statusBefore == 0) {
				statusAfter = 0;
				statusBefore = 3;
				accStatus = 2;
				// 用户当前可以操作的状态
				obj.put("operation_status_before", 3);
			} else {
				obj.put("is_success", 0);
				obj.put("message", "参数错误!");
				System.out.println(obj);
				return obj.toString();
			}
			// 根据表的id 和当前时间查询 是否有这条记录
			String currentDate = DateUtil.getStrFromDate(new Date(), "yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 1);
			Date time = calendar.getTime();
			String maxDate = DateUtil.getStrFromDate(time, "yyyy-MM-dd");

			StockStatusRecord ss = stockStatusRecordService.findStatusByCurrentDate(Long.parseLong(accountId), currentDate, maxDate);
			if (ss == null) {
				// 创建一个
				StockStatusRecord stockStatus = new StockStatusRecord();
				stockStatus.setChangeTime(new Date());
				stockStatus.setOperationStatus(statusBefore);
				stockStatus.setIsOperation(0);
				stockStatus.setAfterStatus(statusAfter);
				stockStatus.setFuStockAccount(fuStockAccount);
				stockStatus.setFuUser(fuUser);
				stockStatusRecordService.save(stockStatus);
			} else {
				ss.setOperationStatus(statusBefore);
				ss.setChangeTime(new Date());
				ss.setIsOperation(0);
				ss.setAfterStatus(statusAfter);
				ss.setFuStockAccount(fuStockAccount);
				ss.setFuUser(fuUser);
				stockStatusRecordService.save(ss);
			}
			fuStockAccount.setState(accStatus);
			fuStockAccountService.save(fuStockAccount);
			obj.put("is_success", 1);
			obj.put("message", "修改状态成功!");
			// }else {
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 验证验证码和邀请码是否存在
	public String isPhoneAndInvitationCode(String phone, String invitationCode, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if ("".equals(phone) || phone == null) {
				obj.put("is_success", 0);
				obj.put("message", "手机号码不能为空");
				System.out.println(obj.toString());
				return obj.toString();
			}
			// 判断手机号码是否存在
			if (fuUserService.findUserByRegPhone(phone) != null) {
				obj.put("message", "该手机号已被注册");
				obj.put("is_success", 0);
				System.out.println(obj);
				return obj.toString();
			}
			// 判断邀请码是否存在
			if (invitationCode != null && !"".equals(invitationCode)) {
				// 邀请码
				if (invitationCode.length() == 12) {
					if (fuUserService.findUserByRegInvitationcode(invitationCode) == null) {
						obj.put("message", "邀请码不存在");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
				} else {
					// 邀请人手机号
					if (fuUserService.findUserByPhone(invitationCode) == null) {
						obj.put("message", "邀请人手机号不存在");
						obj.put("is_success", 0);
						System.out.println(obj);
						return obj.toString();
					}
				}
				FuParameter fuParameter = fuParameterService.findParameter();
				obj.put("message_type", null == fuParameter.getMessageType() ? 0 : fuParameter.getMessageType());
				obj.put("message", "邀请码存在, 手机号不存在");
				obj.put("is_success", 1);
			} else {
				obj.put("is_success", 0);
				obj.put("message", "邀请码或邀请人手机号为空");
				System.out.println(obj.toString());
				return obj.toString();
			}
			// }else{
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj.toString());
		return obj.toString();
	}

	// 检查手机号码和验证码是否匹配 (注册)
	public String isCheckCode(String phone, String phoneCode, String sign) {
		JSONObject obj = new JSONObject();
		// String result = DesUtil.webserviceSignVerify(sign);
		try {
			// if("success".equals(result)){
			if (phone == null || "".equals(phone)) {
				obj.put("is_success", 0);
				obj.put("message", "手机号码不能为空");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if (phoneCode == null || "".equals(phoneCode)) {
				obj.put("is_success", 0);
				obj.put("message", "手机验证码不能为空");
				System.out.println(obj.toString());
				return obj.toString();
			}
			PhoneCode pc = phoneCodeService.getByPhone(phone);
			if (pc == null) {
				obj.put("is_success", 0);
				obj.put("message", "请重新获取验证码");
				System.out.println(obj.toString());
				return obj.toString();
			}
			String userPhone = pc.getPhone();
			String userCode = pc.getCode();
			if (!userPhone.equals(phone) || !userCode.equals(phoneCode)) {
				obj.put("is_success", 0);
				obj.put("message", "验证码不正确!");
				System.out.println(obj.toString());
				return obj.toString();
			}

			obj.put("is_success", 1);
			obj.put("message", "注册验证通过");
			// }else{
			// obj.put("is_success", 0);
			// obj.put("message", result);
			// }

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj.toString());
		return obj.toString();
	}

	/**
	 * 手机端支付宝支付通知
	 * 
	 * @param phone
	 * @param sign
	 * @param out_trade_no
	 * @param trade_no
	 * @param trade_status
	 * @param total_fee
	 * @return
	 */
	public String notify_url(String user_id, String out_trade_no, String trade_no, String trade_status, String total_fee, Map requestParams) {
		try {
			System.out.println("------------------------>进入支付宝回调接口<-------------------");
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			// Map requestParams =
			// this.getHttpServletRequest().getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verify(params)) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderNum", out_trade_no);
					Integer count = fuRechargeService.getCount(map);
					if (count == 0) {
						FuRecharge recharge = new FuRecharge();
						recharge.setFuUser(fuUserService.get(Long.valueOf(user_id)));
						recharge.setType(3);
						recharge.setRechargeBank("支付宝支付");
						recharge.setRechargeAccount(null);
						recharge.setPayStatus(1);// 已付款
						recharge.setOrderNum(out_trade_no);
						recharge.setPayTime(new Date());
						recharge.setRechargeStatus(2);// 通过
						recharge.setFuAdmin(fuAdminService.get(1L));
						recharge.setCheckTime(new Date());
						recharge.setRechargeMoney(new BigDecimal(total_fee));
						recharge.setRechargeTime(new Date());
						recharge.setState(1);
						recharge.setProceedsType(0);
						recharge.setPayType(2);
						fuRechargeService.save(recharge);

						// 网站余额
						FuUser fuUser = fuUserService.get(Long.valueOf(user_id));
						fuUser.setRechargeMoney((fuUser.getRechargeMoney() == null ? new BigDecimal(0.00) : fuUser.getRechargeMoney()).add(new BigDecimal(total_fee)));
						fuUser.setAccountBalance(fuUser.getAccountBalance().add(new BigDecimal(total_fee)));
						fuUserService.save(fuUser);

						// 资金明细
						moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, "支付宝充值", 8, new BigDecimal(total_fee), fuUser.getAccountBalance(), true);
						System.out.println("------------------------>结束支付宝回调接口 成功<-------------------");
						return "success";
					}
				} else if (trade_status.equals("WAIT_BUYER_PAY")) {
					System.out.println("等待买家付款");
				} else {
					System.out.println("买家付款失败");
				}
			} else {// 验证失败
				System.out.println("签名验证失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------>结束支付宝回调接口 失败<-------------------");
		return "fail";
	}
}
