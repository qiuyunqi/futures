package com.hongwei.futures.web.action.wxapi;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.DrawInfo;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrStat;
import com.hongwei.futures.model.HhrStatDetail;
import com.hongwei.futures.model.PhoneCode;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.DrawInfoService;
import com.hongwei.futures.service.FuIpBlacklistService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrStatDetailService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.service.PhoneCodeService;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.util.CommonUtils;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.DesUtil;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.web.action.BaseAction;

public class WeiXinApiAction extends BaseAction {
	private Log log = LogFactory.getLog(WeiXinApiAction.class);

	private static final long serialVersionUID = -8433273247312285248L;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private HhrStatService hhrStatService;
	@Autowired
	private HhrStatDetailService hhrStatDetailService;
	@Autowired
	private PhoneCodeService phoneCodeService;
	@Autowired
	private DrawInfoService drawInfoService;
	@Autowired
	private WqqOptionsService wqqOptionsService;
	@Autowired
	private FuIpBlacklistService fuIpBlacklistService;

	@Action("sendCode")
	public String sendCode() throws Exception {
		String phone = getHttpServletRequest().getParameter("phone");
		String sign = getHttpServletRequest().getParameter("sign");
		String ip = getHttpServletRequest().getParameter("ip");
		String result = DesUtil.webserviceSignVerify(sign);
		JSONObject obj = new JSONObject();
		try {
			if (ServletActionContext.getRequest().getMethod().equals("POST")) {
				if ("success".equals(result)) {
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
					String message = URLDecoder.decode("欢迎注册成为超级合伙人，您的手机验证码是:"
							+ code + "，请填入注册界面完成注册。", "UTF-8");
					FuSmsLog log = new FuSmsLog();
					log.setContent(message);
					log.setPrio(1);
					log.setReason("用户注册");
					log.setDestination(phone);
					log.setPlanTime(new Date());
					// log.setType(1);// 短信
					log.setRegCode(code);
					log.setType(4);// 语音验证码
					log.setState(0);
					obj.put("message", "请求成功");
					obj.put("is_success", 1);
					fuSmsLogService.save(log);
					// 保存
					PhoneCode pc = phoneCodeService.getByPhone(phone);
					if (pc != null) {
						phoneCodeService.delete(pc.getId());
					}
					PhoneCode p = new PhoneCode();
					p.setPhone(phone);
					p.setCode(code);
					phoneCodeService.save(p);
				} else {
					obj.put("message", result);
					obj.put("is_success", 0);
				}
			} else {
				obj.put("message", result);
				obj.put("is_success", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	/**
	 * 微信注册接口
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("register")
	public String register() throws Exception {
		String phone = getHttpServletRequest().getParameter("phone");
		String code = getHttpServletRequest().getParameter("code");
		String invitationCode = getHttpServletRequest().getParameter(
				"invitationCode");
		String reg_type = getHttpServletRequest().getParameter("reg_type");
		String sign = getHttpServletRequest().getParameter("sign");

		// 根据电话号码 验证码 邀请码 注册
		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				if (phone == null || "".equals(phone)) {
					obj.put("message", "手机号为空");
					obj.put("is_success", 0);
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				if (invitationCode == null || "".equals(invitationCode)) {
					obj.put("message", "邀请码为空");
					obj.put("is_success", 0);
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				if (invitationCode.length() == 12) {
					if (fuUserService
							.findUserByRegInvitationcode(invitationCode) == null) {
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
					} else {
						invitationCode = fuUserService.findUserByPhone(
								invitationCode).getInvitationCode();
					}
				}
				// if(fuUserService.findUserByRegInvitationcode(invitationCode)
				// == null ){
				// obj.put("message", "邀请码不存在");
				// obj.put("is_success", 0);
				// log.info(obj.toString());
				// write(obj.toString());
				// return null;
				// }
				if (null == code || "".equals(code)) {
					obj.put("message", "验证码不存在");
					obj.put("is_success", 0);
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				PhoneCode phoneCode = phoneCodeService.getByPhone(phone);
				if (null == phoneCode || !code.equals(phoneCode.getCode())) {
					obj.put("message", "验证码不正确");
					obj.put("is_success", 0);
					log.info(obj.toString());
					write(obj.toString());
					return null;
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
					Integer count = fuUserService
							.countInvitationCode(invitcode);
					if (count < 1) {
						user.setInvitationCode(invitcode);
						break;
					}
				}
				// 确定上级用户
				user.setHhrParentID(fuUserService.findUserByRegInvitationcode(
						invitationCode).getId());
				user.setRecommend(fuUserService
						.findUserByRegInvitationcode(invitationCode));
				user.setLastLoginIp(IP4.getIP4(ServletActionContext
						.getRequest()));
				user.setHhrLevel(fuUserService.findUserByRegInvitationcode(
						invitationCode).getHhrLevel() + 1);// 合伙人等级为上级等级+1

				// 将密码以短信的形式发送给用户
				double rand = new Random().nextDouble();
				String newPass = new String(rand + "").substring(2, 8);
				String message = URLDecoder.decode("欢迎注册成为超级合伙人，您的密码是:"
						+ newPass, "UTF-8");
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

				FuUser parentUser = fuUserService.findFuUserById(user
						.getHhrParentID());
				if (null != parentUser) {
					// 上级合伙人信息
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("user_id", user.getHhrParentID());
					HhrStat parentStat = hhrStatService.findStatDataByMap2(map);
					if (null != parentStat) {
						if (parentStat.getFirstlyPartnerNum() == null) {
							parentStat.setFirstlyPartnerNum(1);
						} else {
							parentStat.setFirstlyPartnerNum(parentStat
									.getFirstlyPartnerNum() + 1);
						}
						parentStat.setStatDate(new Date());
						hhrStatService.save(parentStat);

						// 上级合伙人明细
						HhrStatDetail parentStatDetail = hhrStatDetailService
								.findHhrStatDetailByUserAndDate(parentUser
										.getId(), new SimpleDateFormat(
										"yyyy-MM-dd").format(new Date()));
						if (parentStatDetail != null) {
							parentStatDetail
									.setExtendPersonNew(parentStatDetail
											.getExtendPersonNew() == null ? 1
											: (parentStatDetail
													.getExtendPersonNew() + 1));
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
						HhrStat ppStat = hhrStatService
								.findHhrStatByUser(parentStat.getHhrParentID());
						if (ppStat != null) {
							if (ppStat.getSecondaryPartnerNum() == null) {
								ppStat.setSecondaryPartnerNum(1);
							} else {
								ppStat.setSecondaryPartnerNum(ppStat
										.getSecondaryPartnerNum() + 1);
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
					// 记录这个人抽奖的基本信息
					DrawInfo drawInfo = new DrawInfo();
					drawInfo.setUserId(user.getId());
					drawInfo.setDrawNum(1);
					drawInfo.setIsFirstLogin(1);
					drawInfo.setOldDrawNum(0);
					drawInfo.setIsNewUser(0);
					drawInfoService.save(drawInfo);

					obj.put("is_success", 1);
					obj.put("user_id", user.getId());
					obj.put("is_new", drawInfo.getIsNewUser());
					obj.put("message", "注册成功,密码已短信至用户");
				} else {
					obj.put("message", "上级用户不存在");
					obj.put("is_success", 0);
				}
			} else {
				obj.put("message", result);
				obj.put("is_success", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("login")
	public String login() throws Exception {
		String sign = getHttpServletRequest().getParameter("sign");
		String phone = getHttpServletRequest().getParameter("phone");
		String password = getHttpServletRequest().getParameter("password");

		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				if (null == phone || "".equals(phone)) {
					obj.put("is_success", 0);
					obj.put("message", "手机号码不能为空");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				if (null == password || "".equals(password)) {
					obj.put("is_success", 0);
					obj.put("message", "密码不能为空");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (null == fuUser) {
					obj.put("message", "手机号码错误");
					obj.put("is_success", 0);
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				if (!password.equals(fuUser.getPassword())) {
					obj.put("message", "密码错误");
					obj.put("is_success", 0);
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				// 根据用户查询是第一次登陆
				DrawInfo drawInfo = drawInfoService.getByUserId(fuUser.getId());
				if (null == drawInfo) { // 老用户
					DrawInfo di = new DrawInfo();
					di.setUserId(fuUser.getId());
					di.setDrawNum(1);
					di.setIsFirstLogin(1);
					di.setOldDrawNum(0);
					di.setIsNewUser(1);
					drawInfoService.save(di);
				} else if (drawInfo.getIsFirstLogin() == 0) { // 是第一次登陆
					if (null == drawInfo.getDrawNum()) {
						drawInfo.setDrawNum(1);
					}
					if (drawInfo.getDrawNum() < 2) {
						drawInfo.setDrawNum(drawInfo.getDrawNum() + 1);
					}
					drawInfo.setIsFirstLogin(1);
					drawInfo.setOldDrawNum(0);
					drawInfo.setIsNewUser(1);
					drawInfoService.save(drawInfo);
				} else {
					if (drawInfo.getOldDrawNum() >= 2) {
						obj.put("message", "已经没有抽奖机会");
						obj.put("is_success", 0);
						log.info(obj.toString());
						System.out.println(obj);
						write(obj.toString());
						return null;
					}
				}
				DrawInfo di = drawInfoService.getByUserId(fuUser.getId());
				obj.put("message", "登陆成功");
				obj.put("is_success", 1);
				obj.put("draw_num", null == di ? 0 : di.getDrawNum());
				obj.put("user_id", fuUser.getId());
				obj.put("is_new", 1);
				log.info(obj.toString());
				System.out.println(obj);
				write(obj.toString());
				return null;
			} else {
				obj.put("message", result);
				obj.put("is_success", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("minus")
	public String minus() throws Exception {
		String sign = getHttpServletRequest().getParameter("sign");
		String userId = getHttpServletRequest().getParameter("userId");
		String prizeId = getHttpServletRequest().getParameter("prizeId");

		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				if (null == userId || "".equals(userId)) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				DrawInfo drawInfo = drawInfoService.getByUserId(Long
						.parseLong(userId));
				if (null == drawInfo) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				} else {
					drawInfo.setDrawNum(drawInfo.getDrawNum() - 1);
					if (null == drawInfo.getFirstDrawTime()) {
						drawInfo.setFirstDrawTime(new Date());
					} else {
						drawInfo.setSecondDrawTime(new Date());
					}
					drawInfo.setOldDrawNum(drawInfo.getOldDrawNum() == null ? 1
							: drawInfo.getOldDrawNum() + 1);
					drawInfo.setPrizeId(Integer.parseInt(prizeId)); // 奖品编号 1:
																	// pad 2:
																	// 小米手环
																	// 3:没有中奖
					drawInfoService.save(drawInfo);

					obj.put("is_success", 1);
					obj.put("message", "减少一次抽奖机会");
					obj.put("drawNum", drawInfo.getOldDrawNum());
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

			} else {
				obj.put("is_success", 0);
				obj.put("message", result);
				log.info(obj.toString());
				write(obj.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("addTimes")
	public String addTimes() throws Exception {
		String sign = getHttpServletRequest().getParameter("sign");
		String userId = getHttpServletRequest().getParameter("userId");

		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				if (null == userId || "".equals(userId)) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				DrawInfo drawInfo = drawInfoService.getByUserId(Long
						.parseLong(userId));
				if (null == drawInfo) {
					DrawInfo di = new DrawInfo();
					di.setUserId(Long.parseLong(userId));

					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				} else if (drawInfo.getOldDrawNum() >= 2) {
					obj.put("is_success", 0);
					obj.put("message", "抽奖机会已经用完");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				} else if (null != drawInfo.getFirstDrawTime()
						&& null != drawInfo.getSecondDrawTime()) {
					obj.put("is_success", 0);
					obj.put("message", "抽奖机会已经用完");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				} else {
					drawInfo.setDrawNum(drawInfo.getDrawNum() == null ? 1
							: drawInfo.getDrawNum() + 1);
					drawInfoService.save(drawInfo);

					obj.put("is_success", 1);
					obj.put("message", "增加次抽奖机会");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

			} else {
				obj.put("is_success", 0);
				obj.put("message", result);
				log.info(obj.toString());
				write(obj.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("todraw")
	public String todraw() throws Exception {
		String sign = getHttpServletRequest().getParameter("sign");
		String userId = getHttpServletRequest().getParameter("userId");

		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				if (null == userId || "".equals(userId)) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				FuUser fuUser = fuUserService.get(Long.parseLong(userId));
				if (null == fuUser) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				} else {
					DrawInfo drawInfo = drawInfoService.getByUserId(Long
							.parseLong(userId));
					obj.put("is_success", 1);
					obj.put("draw_num",
							null == drawInfo ? 0 : drawInfo.getOldDrawNum());
					obj.put("message", "查询用户成功");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

			} else {
				obj.put("is_success", 0);
				obj.put("message", result);
				log.info(obj.toString());
				write(obj.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("saveInfo")
	public String saveInfo() throws Exception {
		String sign = getHttpServletRequest().getParameter("sign");
		String userName = getHttpServletRequest().getParameter("userName");
		String phone = getHttpServletRequest().getParameter("phone");
		String address = getHttpServletRequest().getParameter("address");
		String drawName = getHttpServletRequest().getParameter("drawName");
		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				if (null == userName || "".equals(userName)) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				if (null == drawName || "".equals(drawName)) {
					obj.put("is_success", 0);
					obj.put("message", "奖品不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				if ("3".equals(drawName)) {
					obj.put("is_success", 0);
					obj.put("message", "没有中奖");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}
				FuUser fuUser = fuUserService.findUserByPhone(phone);
				if (null == fuUser) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				DrawInfo drawInfo = drawInfoService.getByUserId(fuUser.getId());
				if (null == drawInfo) {
					obj.put("is_success", 0);
					obj.put("message", "未知错误, 请联系管理员");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				} else {
					drawInfo.setUserName(userName);
					drawInfo.setPhone(phone);
					drawInfo.setAddress(address);
					if (null == drawInfo.getDrawName()
							|| "".equals(drawInfo.getDrawName())) {
						if (null == drawInfo.getFirstDrawTime()
								|| null == drawInfo.getSecondDrawTime()) {
							if ("1".equals(drawName)) {
								drawInfo.setDrawName("iPad");
							} else {
								drawInfo.setDrawName("小米手环");
							}
						} else {
							obj.put("is_success", 0);
							obj.put("message", "抽奖机会已经用完");
							log.info(obj.toString());
							write(obj.toString());
							return null;
						}
					} else {
						if (null == drawInfo.getFirstDrawTime()
								|| null == drawInfo.getSecondDrawTime()) {
							if ("1".equals(drawName)) {
								drawInfo.setDrawName(drawInfo.getDrawName()
										+ "#iPad");
							} else {
								drawInfo.setDrawName(drawInfo.getDrawName()
										+ "#小米手环");
							}
						} else {
							obj.put("is_success", 0);
							obj.put("message", "抽奖机会已经用完");
							log.info(obj.toString());
							write(obj.toString());
							return null;
						}
					}
					drawInfoService.save(drawInfo);

					obj.put("is_success", 1);
					obj.put("message", "保存快递信息成功");
					if (null != drawInfo.getFirstDrawTime()
							&& null != drawInfo.getSecondDrawTime()) {
						obj.put("drawTimes", 0); // 没有机会
					} else {
						obj.put("drawTimes", 1); // 有机会
					}
					obj.put("userId", fuUser.getId());
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

			} else {
				obj.put("is_success", 0);
				obj.put("message", result);
				log.info(obj.toString());
				write(obj.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("drawInfo")
	public String drawInfo() throws Exception {
		String sign = getHttpServletRequest().getParameter("sign");
		String userId = getHttpServletRequest().getParameter("userId");
		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				if (null == userId || "".equals(userId)) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				FuUser fuUser = fuUserService.get(Long.parseLong(userId));
				if (null == fuUser) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				}

				DrawInfo drawInfo = drawInfoService.getByUserId(fuUser.getId());
				if (null == drawInfo) {
					obj.put("is_success", 0);
					obj.put("message", "未知错误, 请联系管理员");
					log.info(obj.toString());
					write(obj.toString());
					return null;
				} else {
					obj.put("prizeId", drawInfo.getPrizeId());
					obj.put("drawNum", drawInfo.getOldDrawNum());
					obj.put("is_success", 1);
					obj.put("message", "查询中奖信息成功");
					System.out.println(obj);
					write(obj.toString());
					return null;
				}

			} else {
				obj.put("is_success", 0);
				obj.put("message", result);
				log.info(obj.toString());
				write(obj.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("getDrawInfo")
	public String getDrawInfo() throws Exception {
		String sign = getHttpServletRequest().getParameter("sign");
		JSONObject obj = new JSONObject();
		try {
			String result = DesUtil.webserviceSignVerify(sign);
			// String result = "success";
			if ("success".equals(result)) {
				List<DrawInfo> drawList = drawInfoService.getDrawInfo();
				if (null != drawList && drawList.size() > 0) {
					List<Object> list = new ArrayList<Object>();
					for (DrawInfo drawInfo : drawList) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						FuUser user = fuUserService.get(drawInfo.getUserId());
						if (null != user) {
							map.put("nickName", user.getNickName() == null ? ""
									: user.getNickName());
							map.put("userName",
									drawInfo.getUserName() == null ? ""
											: drawInfo.getUserName());
							map.put("phone", drawInfo.getPhone() == null ? ""
									: drawInfo.getPhone());
							map.put("address",
									drawInfo.getAddress() == null ? ""
											: drawInfo.getAddress());
							map.put("drawName",
									drawInfo.getDrawName() == null ? ""
											: drawInfo.getDrawName());
							list.add(map);
						} else {
							obj.put("is_success", 0);
							obj.put("message", "这个用户不存在");
							log.info(obj.toString());
							write(obj.toString());
							return null;
						}
					}
					obj.put("is_success", 1);
					obj.put("message", result);
					obj.put("drawList", list);
					write(obj.toString());
					return null;
				} else {
					obj.put("is_success", 0);
					obj.put("message", result);
					log.info(obj.toString());
					write(obj.toString());
					return null;

				}
			} else {
				obj.put("is_success", 0);
				obj.put("message", result);
				log.info(obj.toString());
				write(obj.toString());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		log.info(obj.toString());
		write(obj.toString());
		return null;
	}

	@Action("getOrderByUserId")
	public String getOrderByUserId() {
		try {
			String userId = this.getHttpServletRequest().getParameter("userId");
			String sign = this.getHttpServletRequest().getParameter("sign");
			String result = DesUtil.webserviceSignVerify(sign);
			JSONObject obj = new JSONObject();
			// String result = "success";
			if ("success".equals(result)) {
				if (null == userId || "".equals(userId)) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					System.out.println(obj.toString());
					write(obj.toString());
					return null;
				}
				FuUser fuUser = fuUserService.get(Long.parseLong(userId));
				if (null == fuUser) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					System.out.println(obj.toString());
					write(obj.toString());
					return null;

				}
				List<WqqOptions> wqqList = wqqOptionsService
						.getInfoByUserId(fuUser.getId());
				List<Object> list = new ArrayList<Object>();
				if (null != wqqList && wqqList.size() > 0) {
					for (WqqOptions wqqOptions : wqqList) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("tradeNo", wqqOptions.getTradeNo() == null ? "" : wqqOptions.getTradeNo());
						map.put("money", wqqOptions.getMoney() == null ? "" : wqqOptions.getMoney());
						map.put("tradeTime", wqqOptions.getPayTime() == null ? "" : DateUtil.getStrFromDate(wqqOptions.getPayTime(), "yyyy-MM-dd HH:mm"));
						map.put("guessType", wqqOptions.getGuessType() == null ? "" : wqqOptions.getGuessType() == 0 ? "看跌" : "看涨");
						map.put("title", wqqOptions.getWqqContents() == null ? "7天涨跌赢第2期" : wqqOptions.getWqqContents().getName() == null ? "7天涨跌赢第2期"
												: wqqOptions.getWqqContents().getName());
						map.put("orderIncome", null == wqqOptions ? "7天后开奖" : null == wqqOptions.getOrderIncome() ? null == wqqOptions.getWqqContents() ? "7天后开奖" :
							null == wqqOptions.getWqqContents().getEndTime() ? "7天后开奖" : DateUtil.getStrFromDate(wqqOptions.getWqqContents().getEndTime(), "yyyy-MM-dd")
									+ "兑付收益" : wqqOptions.getOrderIncome());
						list.add(map);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询我的订单成功");
					obj.put("wqqList", list);
					System.out.println(obj.toString());
					write(obj.toString());
					return null;

				} else {
					obj.put("is_success", 1);
					obj.put("message", "暂无订单信息");
					obj.put("wqqList", "");
					System.out.println(obj.toString());
					write(obj.toString());
					return null;
				}

			} else {
				obj.put("is_success", 0);
				obj.put("message", result);
				System.out.println(obj.toString());
				write(obj.toString());
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
