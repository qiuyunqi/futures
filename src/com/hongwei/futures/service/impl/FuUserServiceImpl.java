package com.hongwei.futures.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuParameterDao;
import com.hongwei.futures.dao.FuPromoteDao;
import com.hongwei.futures.dao.FuRateDao;
import com.hongwei.futures.dao.FuSmsLogDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.dao.HhrPromoteParameterDao;
import com.hongwei.futures.dao.HhrStatDao;
import com.hongwei.futures.dao.HhrStatDetailDao;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuPromote;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.FuUserBak;
import com.hongwei.futures.model.HhrPromoteParameter;
import com.hongwei.futures.model.HhrStat;
import com.hongwei.futures.model.HhrStatDetail;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.WebUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuUserServiceImpl implements FuUserService {
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private HhrStatDao hhrStatDao;
	@Autowired
	private HhrStatDetailDao hhrStatDetailDao;
	@Autowired
	private FuPromoteDao fuPromoteDao;
	@Autowired
	private FuSmsLogDao fuSmsLogDao;
	@Autowired
	private FuParameterDao fuParameterDao;
	@Autowired
	private FuRateDao fuRateDao;
	@Autowired
	private HhrPromoteParameterDao hhrPromoteParameterDao;
	@Autowired
	private HhrStatService hhrStatService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	// ====================== 基本 C R U D 方法 ===========================
	@Override
	public FuUser get(Long id) {
		return fuUserDao.get(id);
	}

	@Override
	public void save(FuUser entity) {
		fuUserDao.save(entity);
	}

	@Override
	public void saveRate(FuUser user) {
		fuUserDao.save(user);
		// 注册的时候给每个用户设置默认费率
		FuParameter fuParameter = fuParameterDao.findParameter();
		if (null != fuParameter) {
			FuRate fuRate = new FuRate();
			fuRate.setFuUser(user);
			fuRate.setWarnlinePercent(fuParameter.getWarnlinePercent());
			fuRate.setFlatlinePercent(fuParameter.getFlatlinePercent());
			fuRate.setCommissionPercent(fuParameter.getCommissionPercent());
			fuRate.setFeeDay(fuParameter.getFeeDay());
			fuRate.setFeePercent(fuParameter.getFeePercent());
			fuRate.setInterestPercent(fuParameter.getInterestPercent());
			fuRate.setShortNum(fuParameter.getShortNum());
			fuRate.setLongNum(fuParameter.getLongNum());
			FuAdmin fuAdmin = new FuAdmin();
			fuAdmin.setId(1L);
			fuRate.setFuAdmin(fuAdmin);
			fuRate.setCreatetime(new Date());
			fuRate.setUpdateuser(1L);
			fuRate.setUpdatetime(new Date());
			fuRateDao.save(fuRate);
		}
	}

	@Override
	public void delete(Long id) {
		fuUserDao.delete(id);
	}

	@Override
	public FuUser findUserByUserName(String userName) {
		return fuUserDao.findUserByUserName(userName);
	}

	@Override
	public FuUser findUserByNickName(String nickName) {
		return fuUserDao.findUserByNickName(nickName);
	}

	@Override
	public FuUser findLoginByToken(String token) {
		return fuUserDao.findLoginByToken(token);
	}

	@Override
	public FuUser findUserByPhone(String phone) {
		return fuUserDao.findUserByPhone(phone);
	}

	@Override
	public FuUser findUserByEmail(String email) {
		return fuUserDao.findUserByEmail(email);
	}

	@Override
	public FuUser findUserByAccount(String accountName) {
		return fuUserDao.findUserByAccount(accountName);
	}

	@Override
	public void savePromoteUser(FuUser FuUserByPromote, FuUser FuUserByPromoted, FuPromote promote) {
		fuUserDao.save(FuUserByPromote);
		fuUserDao.save(FuUserByPromoted);
		fuPromoteDao.save(promote);

	}

	@Override
	public Integer countUser(Map<String, Object> map) {
		return fuUserDao.countUser(map);
	}

	@Override
	public List<FuUser> findUserList(int i, int pageSize, Map<String, Object> map) {
		return fuUserDao.findUserList(i, pageSize, map);
	}

	@Override
	public Integer countCheckUser(Map<String, Object> map) {
		return fuUserDao.countCheckUser(map);
	}

	@Override
	public Integer countCheckUser2(Map<String, Object> map) {
		return fuUserDao.countCheckUser2(map);
	}

	@Override
	public List<FuUser> findCheckUserList(int i, int j, Map<String, Object> map) {
		return fuUserDao.findCheckUserList(i, j, map);
	}

	@Override
	public void saveCheckEmail(FuUser fuUser) {
		fuUserDao.save(fuUser);
		// 发送邮件
		FuSmsLog log = new FuSmsLog();
		log.setPlanTime(new Date());
		log.setFuUser(fuUser);
		log.setContent(fuUser.getAccountName() + "您好，请完成Email验证，您可以在验证页面输入以下验证码：" + fuUser.getEmailCode());
		log.setPrio(1);
		if (fuUser.getIsBindEmail() == 1)
			log.setReason("修改邮箱绑定");
		else
			log.setReason("邮箱绑定");
		log.setDestination(fuUser.getEmail());
		log.setType(2);// 邮箱
		log.setSendTime(new Date());
		log.setState(1);
		fuSmsLogDao.save(log);

	}

	@Override
	public void saveReg(FuUser user, FuSmsLog log) {
		fuUserDao.save(user);
		log.setFuUser(user);
		fuSmsLogDao.save(log);

	}

	@Override
	public int countRegister() {
		return fuUserDao.countRegister();
	}

	@Override
	public BigDecimal countTotalMoney() {
		return fuUserDao.countTotalMoney();
	}

	@Override
	public FuUser findUserByRegPhone(String phone) {
		return fuUserDao.findUserByRegPhone(phone);
	}

	@Override
	public FuUser findUserByRegAccountName(String accountName) {
		return fuUserDao.findUserByRegAccountName(accountName);
	}

	/**
	 * 根据邀请码查找注册时的用户
	 * 
	 * @return
	 */
	@Override
	public FuUser findUserByRegInvitationcode(String invitation_code) {
		return fuUserDao.findUserByRegInvitationcode(invitation_code);
	}

	/**
	 * 根据map参数得到用户数据
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public Object[] findUserDataByMap(Map<String, Object> map) {
		return fuUserDao.findUserDataByMap(map);
	}

	/**
	 * 查询所有邀请码
	 */
	@Override
	public Integer countInvitationCode(String code) {
		return fuUserDao.countInvitationCode(code);
	}

	@Override
	public FuUser findFuUserById(Long userId) {
		return fuUserDao.findFuUserById(userId);
	}

	/**
	 * 判断用户是否已实名认证
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<FuUser> findListByUser(Long userId) {
		return fuUserDao.findListByUser(userId);
	}

	/**
	 * 昵称重复的用户
	 * 
	 * @param userId
	 * @param nickName
	 * @return
	 */
	@Override
	public List<FuUser> findSameNickNameUser(String nickName) {
		return fuUserDao.findSameNickNameUser(nickName);
	}

	/**
	 * 根据上层id查找用户
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<FuUser> findListByParentId(Long userId) {
		return fuUserDao.findListByParentId(userId);
	}

	/**
	 * 返回合伙人树结构数据
	 * 
	 * @return
	 */
	@Override
	public String findUserTree(Long id) {
		return fuUserDao.findUserTree(id);
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@Override
	public List<FuUser> findAllUsers() {
		return fuUserDao.findAllUsers();
	}

	@Override
	public void updatePartnerNum(HhrStat stat) {
		if (stat.getHhrParentID() != null && stat.getHhrParentID() != 0) {
			HhrStat upStat = hhrStatDao.findHhrStatByUser(stat.getHhrParentID());
			if (upStat != null) {
				if (upStat.getSecondaryPartnerNum() == null) {
					upStat.setSecondaryPartnerNum(1);
				} else {
					upStat.setSecondaryPartnerNum(upStat.getSecondaryPartnerNum() + 1);
				}
				hhrStatDao.save(upStat);
			}
			updatePartnerNum(upStat);
		}
	}

	/**
	 * 网站新用户注册
	 * 
	 * @return
	 */
	@Override
	public String saveUser(String phone, String password, String nickName, String phoneCode, String invitationCode, int regType, String avatar) {
		FuUser user = new FuUser();
		user.setAccountName(phone);
		user.setPhone(phone);
		user.setPassword(password);
		user.setNickName(nickName);
		user.setUserAvatar(avatar); // 设置用户头像
		user.setIntegral(BigDecimal.ZERO);// 积分
		user.setPhoneCode(phoneCode);// 注册验证码
		user.setIsAcrossCabin(0);// 是否穿仓
		user.setIsPhoneReg(regType);// 网站注册
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
		user.setIntegral(new BigDecimal(0.00));
		saveRate(user); // 新注册用户同时创建一条个人费率
		// 默认登录
		String token = UUID.randomUUID().toString();
		WebUtil.addCookie(ServletActionContext.getResponse(), "user_token", token, 8640000);
		user.setLoginToken(token);
		user.setLoginTime(new Date());
		user.setState(1);
		user.setRegisterIp(IP4.getIP4(ServletActionContext.getRequest()));// 访问IP

		// 产生随机邀请码
		while (true) {
			double rand = new Random().nextDouble();
			String invitcode = new String(rand + "").substring(2, 14);
			Integer count = countInvitationCode(invitcode);
			if (count < 1) {
				user.setInvitationCode(invitcode);
				break;
			}
		}
		// 确定上级用户
		user.setHhrParentID(findUserByRegInvitationcode(invitationCode).getId());
		user.setRecommend(findUserByRegInvitationcode(invitationCode));// 权属人
		user.setLastLoginIp(IP4.getIP4(ServletActionContext.getRequest()));
		user.setHhrLevel(findUserByRegInvitationcode(invitationCode).getHhrLevel() + 1);// 合伙人等级为上级等级+1
		save(user);
		// 统计表信息写入
		HhrStat hhrStat = new HhrStat();
		hhrStat.setFuUser(user);
		hhrStat.setStatDate(new Date());
		hhrStat.setHhrParentID(user.getHhrParentID());
		hhrStatDao.save(hhrStat);

		FuUser parentUser = findFuUserById(user.getHhrParentID());
		if (null != parentUser) {
			// 上级合伙人信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user.getHhrParentID());
			HhrStat parentStat = hhrStatDao.findStatDataByMap2(map);
			if (null != parentStat) {
				if (parentStat.getFirstlyPartnerNum() == null) {
					parentStat.setFirstlyPartnerNum(1);
				} else {
					parentStat.setFirstlyPartnerNum(parentStat.getFirstlyPartnerNum() + 1);
				}
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);

				// 上级合伙人明细
				HhrStatDetail parentStatDetail = hhrStatDetailDao.findHhrStatDetailByUserAndDate(parentUser.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				if (parentStatDetail != null) {
					parentStatDetail.setExtendPersonNew(parentStatDetail.getExtendPersonNew() == null ? 1 : (parentStatDetail.getExtendPersonNew() + 1));
				} else {
					parentStatDetail = new HhrStatDetail();
					parentStatDetail.setFuUser(parentUser);
					if (parentUser.getHhrParentID() == Long.parseLong("0") || parentUser.getHhrParentID() == null) {
						parentStatDetail.setHhrParentID(0L);
					}
					parentStatDetail.setExtendPersonNew(1);
					parentStatDetail.setCreateDate(new Date());
				}
				parentStatDetail.setStatDate(new Date());
				hhrStatDetailDao.save(parentStatDetail);

				// 上级的上级合伙人信息
				HhrStat ppStat = hhrStatDao.findHhrStatByUser(parentStat.getHhrParentID());
				if (ppStat != null) {
					if (ppStat.getSecondaryPartnerNum() == null) {
						ppStat.setSecondaryPartnerNum(1);
					} else {
						ppStat.setSecondaryPartnerNum(ppStat.getSecondaryPartnerNum() + 1);
					}
					ppStat.setStatDate(new Date());
					hhrStatDao.save(ppStat);
					// 从这一层用户开始往上回溯, 每个用户的次级合伙人都+1
					updatePartnerNum(ppStat);
				}
			} else {
				parentStat = new HhrStat();
				parentStat.setFuUser(parentUser);
				parentStat.setFirstlyPartnerNum(1); // 一级会员数目
				parentStat.setExtendPersonNew(1); // 今日新增会员
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);
			}

			// 判断活动是否已开启,并且活动费用余额是否足够, 暂定50
			HhrPromoteParameter param = hhrPromoteParameterDao.findParameter();
			if (null != param) {
				if (param.getIsOpen() == 1 && param.getTotalMoney().compareTo(new BigDecimal(50)) == 1) {
					String[] moneyArr = param.getLineMoney().split(",");
					// 从数组中随机抽取一个值,作为初始发钱金额
					int rand = new Random().nextInt(moneyArr.length);
					BigDecimal initialMoney = new BigDecimal(moneyArr[rand]);
					hhrStatService.updateHhrIncome(user.getId(), initialMoney, 1, null);
					param.setTotalMoney(param.getTotalMoney().subtract(initialMoney));
					hhrPromoteParameterDao.save(param);
				}
			}
		}
		save(user);

		// 论坛注册
		// DiscuzUtil.reg(phone, phone.substring(5, 11), phone + "@hhr360.com");
		return user.getInvitationCode();
	}

	@Override
	public FuUser findUserByWeixinCode(String weixinCode) {
		return fuUserDao.findUserByWeixinCode(weixinCode);
	}

	@Override
	public FuUser findUserByCardNumber(String cardNumber) {
		return fuUserDao.findUserByCardNumber(cardNumber);
	}

	@Override
	public List<Object[]> queryUserAccountBalanceList(int i, int pageSize, Map<String, Object> map) {
		return fuUserDao.queryUserAccountBalanceList(i, pageSize, map);
	}

	@Override
	public Integer countUserAccountBalance(Map<String, Object> map) {
		return fuUserDao.countUserAccountBalance(map);
	}

	public FuUser findUserByRegIp(String registerIp) {
		return fuUserDao.findUserByRegIp(registerIp);
	}

	public Integer findCountByRegIp(String registerIp) {
		return fuUserDao.findCountByRegIp(registerIp);
	}

	@Override
	public List<Object[]> queryAllUserAccountBalanceList(Map<String, Object> map) {
		return fuUserDao.queryAllUserAccountBalanceList(map);
	}

	@Override
	public List<String> getUserList(int i, int j) {
		return fuUserDao.getUserList(i, j);
	}

	@Override
	public void saveMoveHhr(Long id, Long parentId) {
		// 当前用户
		FuUser user = fuUserDao.get(id);
		FuUser parent = fuUserDao.get(parentId);
		user.setHhrParentID(parent.getId());
		user.setHhrLevel(parent.getHhrLevel() == null ? 1 : parent.getHhrLevel() + 1);
		fuUserDao.save(user);

		// 当前hhrStat
		HhrStat userStat = hhrStatDao.findHhrStatByUser(id);
		HhrStat parentStat = hhrStatDao.findHhrStatByUser(parentId);
		userStat.setHhrParentID(parentStat.getFuUser().getId());
		userStat.setHhrLevel(parentStat.getFuUser().getHhrLevel() == null ? 1 : parentStat.getFuUser().getHhrLevel() + 1);
		hhrStatDao.save(userStat);

		updateHhrLevel(user);
		updateHhrStatLevel(userStat);
	}

	// 更新合伙人level
	public void updateHhrLevel(FuUser user) {
		List<FuUser> secondList = fuUserDao.findListByParentId(user.getId());
		for (FuUser u : secondList) {
			u.setHhrLevel(user.getHhrLevel() == null ? 1 : user.getHhrLevel() + 1);
			fuUserDao.save(u);
			updateHhrLevel(u);
		}
	}

	// 更新hhrStat level
	public void updateHhrStatLevel(HhrStat stat) {
		List<HhrStat> secondList = hhrStatDao.findUserByParentId(stat.getFuUser().getId());
		for (HhrStat h : secondList) {
			h.setHhrLevel(stat.getFuUser().getHhrLevel() == null ? 1 : stat.getFuUser().getHhrLevel() + 1);
			hhrStatDao.save(h);
			updateHhrStatLevel(h);
		}
	}

	// 修改用户的信息(关于交易团队)
	public void updateHhrLevelInfo(FuUser fuUser, Integer hhrType, String name) {
		fuUser.setHhrType(hhrType);
		FuTransaction fuTransaction = null;
		fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
		if (hhrType == 2) {
			if (null == fuTransaction) {
				fuTransaction = new FuTransaction();
			}
			fuTransaction.setFuUser(fuUser);
			fuTransaction.setName(name);
			fuTransactionService.save(fuTransaction);
		} else {
			if (null != fuTransaction) {
				fuTransactionService.delete(fuTransaction.getId());
			}
		}
		fuUserDao.save(fuUser);
	}

	// 微信存储用户
	public void saveWeiXinUser(String phone, String password, String nickName, String phoneCode, String invitationCode, int regType, String avatar, String openId) {
		FuUser user = new FuUser();
		user.setAccountName(phone);
		user.setPhone(phone);
		user.setPassword(password);
		user.setNickName(nickName);
		user.setUserAvatar(avatar); // 设置用户头像
		user.setIntegral(BigDecimal.ZERO);// 积分
		user.setPhoneCode(phoneCode);// 注册验证码
		user.setIsAcrossCabin(0);// 是否穿仓
		user.setIsPhoneReg(regType);// 网站注册
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
		user.setIntegral(new BigDecimal(0.00));
		if (null != openId && !"".equals(openId)) {
			user.setWeixinCode(openId);
		}
		saveRate(user); // 新注册用户同时创建一条个人费率
		// 默认登录
		String token = UUID.randomUUID().toString();
		WebUtil.addCookie(ServletActionContext.getResponse(), "user_token", token, 8640000);
		user.setLoginToken(token);
		user.setLoginTime(new Date());
		user.setState(1);
		user.setRegisterIp(IP4.getIP4(ServletActionContext.getRequest()));// 访问IP

		// 产生随机邀请码
		while (true) {
			double rand = new Random().nextDouble();
			String invitcode = new String(rand + "").substring(2, 14);
			Integer count = countInvitationCode(invitcode);
			if (count < 1) {
				user.setInvitationCode(invitcode);
				break;
			}
		}
		// 确定上级用户
		user.setHhrParentID(findUserByRegInvitationcode(invitationCode).getId());
		user.setRecommend(findUserByRegInvitationcode(invitationCode));// 权属人
		user.setLastLoginIp(IP4.getIP4(ServletActionContext.getRequest()));
		user.setHhrLevel(findUserByRegInvitationcode(invitationCode).getHhrLevel() + 1);// 合伙人等级为上级等级+1
		save(user);
		// 统计表信息写入
		HhrStat hhrStat = new HhrStat();
		hhrStat.setFuUser(user);
		hhrStat.setStatDate(new Date());
		hhrStat.setHhrParentID(user.getHhrParentID());
		hhrStatDao.save(hhrStat);

		FuUser parentUser = findFuUserById(user.getHhrParentID());
		if (null != parentUser) {
			// 上级合伙人信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user.getHhrParentID());
			HhrStat parentStat = hhrStatDao.findStatDataByMap2(map);
			if (null != parentStat) {
				if (parentStat.getFirstlyPartnerNum() == null) {
					parentStat.setFirstlyPartnerNum(1);
				} else {
					parentStat.setFirstlyPartnerNum(parentStat.getFirstlyPartnerNum() + 1);
				}
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);

				// 上级合伙人明细
				HhrStatDetail parentStatDetail = hhrStatDetailDao.findHhrStatDetailByUserAndDate(parentUser.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				if (parentStatDetail != null) {
					parentStatDetail.setExtendPersonNew(parentStatDetail.getExtendPersonNew() == null ? 1 : (parentStatDetail.getExtendPersonNew() + 1));
				} else {
					parentStatDetail = new HhrStatDetail();
					parentStatDetail.setFuUser(parentUser);
					if (parentUser.getHhrParentID() == Long.parseLong("0") || parentUser.getHhrParentID() == null) {
						parentStatDetail.setHhrParentID(0L);
					}
					parentStatDetail.setExtendPersonNew(1);
					parentStatDetail.setCreateDate(new Date());
				}
				parentStatDetail.setStatDate(new Date());
				hhrStatDetailDao.save(parentStatDetail);

				// 上级的上级合伙人信息
				HhrStat ppStat = hhrStatDao.findHhrStatByUser(parentStat.getHhrParentID());
				if (ppStat != null) {
					if (ppStat.getSecondaryPartnerNum() == null) {
						ppStat.setSecondaryPartnerNum(1);
					} else {
						ppStat.setSecondaryPartnerNum(ppStat.getSecondaryPartnerNum() + 1);
					}
					ppStat.setStatDate(new Date());
					hhrStatDao.save(ppStat);
					// 从这一层用户开始往上回溯, 每个用户的次级合伙人都+1
					updatePartnerNum(ppStat);
				}
			} else {
				parentStat = new HhrStat();
				parentStat.setFuUser(parentUser);
				parentStat.setFirstlyPartnerNum(1); // 一级会员数目
				parentStat.setExtendPersonNew(1); // 今日新增会员
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);
			}

			// 判断活动是否已开启,并且活动费用余额是否足够, 暂定50
			HhrPromoteParameter param = hhrPromoteParameterDao.findParameter();
			if (null != param) {
				if (param.getIsOpen() == 1 && param.getTotalMoney().compareTo(new BigDecimal(50)) == 1) {
					String[] moneyArr = param.getLineMoney().split(",");
					// 从数组中随机抽取一个值,作为初始发钱金额
					int rand = new Random().nextInt(moneyArr.length);
					BigDecimal initialMoney = new BigDecimal(moneyArr[rand]);
					hhrStatService.updateHhrIncome(user.getId(), initialMoney, 1, null);
					param.setTotalMoney(param.getTotalMoney().subtract(initialMoney));
					hhrPromoteParameterDao.save(param);
				}
			}
		}

		DecimalFormat format = new DecimalFormat("0000");
		String code = format.format(Math.random() * 9999);
		String message;
		try {
			message = URLDecoder.decode("您的合伙人APP密码是:" + code + "，请妥善保管。", "UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setContent(message);
			log.setPrio(1);
			log.setReason("发送APP密码");
			log.setDestination(phone);
			log.setPlanTime(new Date());
			log.setType(1);
			log.setRegCode(code);
			log.setState(0);
			fuSmsLogService.save(log);
			code = CommonUtil.getMd5(code);
			user.setPassword(code);
			save(user);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	// 通过微信号和手机号查询是否存在用户
	public FuUser findUserByWeiXinAndPhone(String weixinCode, String phone) {
		return this.fuUserDao.findUserByWeiXinAndPhone(weixinCode, phone);
	}

	public List<FuUserBak> findUserByHhrParentId(Long hhrParentId) {
		return fuUserDao.findUserByHhrParentId(hhrParentId);
	}

	@Override
	public List<FuUser> findListByParentIdCount(Long userId, Integer count) {
		return fuUserDao.findListByParentIdCount(userId, count);
	}

}
