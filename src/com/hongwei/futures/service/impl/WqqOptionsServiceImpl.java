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
import com.hongwei.futures.dao.FuAdminDao;
import com.hongwei.futures.dao.FuParameterDao;
import com.hongwei.futures.dao.FuRateDao;
import com.hongwei.futures.dao.FuRechargeDao;
import com.hongwei.futures.dao.FuSmsLogDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.dao.HhrStatDao;
import com.hongwei.futures.dao.HhrStatDetailDao;
import com.hongwei.futures.dao.WqqContentsDao;
import com.hongwei.futures.dao.WqqOptionsDao;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrStat;
import com.hongwei.futures.model.HhrStatDetail;
import com.hongwei.futures.model.WqqContents;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.util.MoneyDetailUtil;
import com.hongwei.futures.util.WebUtil;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class WqqOptionsServiceImpl implements WqqOptionsService {
	@Autowired
	private WqqOptionsDao wqqOptionsDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private FuSmsLogDao fuSmsLogDao;
	@Autowired
	private FuParameterDao  fuParameterDao;
	@Autowired
	private FuRateDao fuRateDao;
	@Autowired
	private HhrStatDao  hhrStatDao;
	@Autowired
	private HhrStatDetailDao hhrStatDetailDao;
	@Autowired
	private FuAdminDao fuAdminDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	@Autowired
	private FuRechargeDao fuRechargeDao;
	@Autowired
	private WqqContentsDao wqqContentsDao;
	
	
	//====================== 基本 C R U D 方法  ===========================
	public WqqOptions get(Long id) {
		return wqqOptionsDao.get(id);
	}
	
	public void save(WqqOptions entity) {
		wqqOptionsDao.save(entity);
	}
	
	public void delete(Long id) {
		wqqOptionsDao.delete(id);
	}

	@Override
	public List<WqqOptions> queryOptionsList(int i, int pageSize, Map<String, Object> map) {
		return wqqOptionsDao.queryOptionsList(i, pageSize, map);
	}

	@Override
	public Integer countOptions(Map<String, Object> map) {
		return wqqOptionsDao.countOptions(map);
	}

	// 获取微期权的验证码短信息
	public void saveSendCode(String phone) {
		DecimalFormat df = new DecimalFormat("0000");
		String code = df.format(Math.random() * 9999);
		String message="";
		try {
			message = URLDecoder.decode("欢迎购买微期权，您的手机验证码是："+code+"，请填入购买界面以完成购买", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FuSmsLog log = new FuSmsLog();
		log.setContent(message);
		log.setPrio(1);
		log.setRegCode(code);
		log.setReason("微期权验证码");
		log.setDestination(phone);
		log.setPlanTime(new Date());
		log.setType(4);// 语音短信
		log.setState(0);
		fuSmsLogDao.save(log);
	}
	
	//将密码以短信的形式发送给用户
	public void sendPwdWqq(String phone,String newPass){
		String message="";
		try {
			message = URLDecoder.decode("欢迎注册成为超级合伙人，您的密码是:" + newPass , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FuSmsLog log = new FuSmsLog();
		log.setContent(message);
		log.setPrio(1);
		log.setReason("用户快速注册");
		log.setDestination(phone);
		log.setPlanTime(new Date());
		log.setType(1);// 短信
		log.setState(0);
		fuSmsLogDao.save(log);
	}

	public void saveWqqRegister(String phone, String newPass, String invitationCode){
		FuUser user=new FuUser();
		user.setAccountName(phone);
		user.setPhone(phone);
		user.setNickName(phone);
		user.setPassword(newPass);
		user.setIntegral(BigDecimal.ZERO);//积分
		user.setIsAcrossCabin(0);//是否穿仓
		user.setIsPhoneReg(4);//微期权注册
		user.setSafeMoney(new BigDecimal(0.00));//总风险保证金
		user.setMatchMoney(new BigDecimal(0.00));//总配资金额
		user.setFreezeMoney(new BigDecimal(0.00));//冻结金额
		user.setExtendPersonNum(0);//推广人数
		user.setBorrowPersonNum(0);//配资用户
		user.setExchangeMoney(new BigDecimal(0.00));//已兑佣金
		user.setCommissionTotal(new BigDecimal(0.00));//佣金总额
		user.setAccountBalance(new BigDecimal(0.00));//账户余额
		user.setAccountTotalMoney(new BigDecimal(0.00));//总资产
		user.setIsBindEmail(0);//邮箱未绑定
		user.setIsCheckCard(0);//未实名认证
		user.setVisitIp(1);//访问IP数
		user.setVisitNum(1);//访问次数
		user.setSafeLevel(1);//安全等级
		user.setRegisterTime(new Date());
		user.setIntegral(new BigDecimal(0.00));
		fuUserDao.save(user);  
		//新注册用户同时创建一条个人费率
		saveRate(user);
		//默认登录
		String token = UUID.randomUUID().toString();
		WebUtil.addCookie(ServletActionContext.getResponse(), "user_token", token, 8640000);
		user.setLoginToken(token);
		user.setLoginTime(new Date());
		user.setState(1);
		user.setRegisterIp(IP4.getIP4(ServletActionContext.getRequest()));//访问IP
		// 产生随机邀请码
		while (true) {
			double rand = new Random().nextDouble();
			String invitcode = new String(rand + "").substring(2, 14);
			Integer count = fuUserDao.countInvitationCode(invitcode);
			if (count < 1) {
				user.setInvitationCode(invitcode);
				break;
			}
		}
		// 确定上级用户
		user.setHhrParentID(fuUserDao.findUserByRegInvitationcode(invitationCode).getId());
		user.setRecommend(fuUserDao.findUserByRegInvitationcode(invitationCode));
		user.setLastLoginIp(IP4.getIP4(ServletActionContext.getRequest()));
		user.setHhrLevel(fuUserDao.findUserByRegInvitationcode(invitationCode).getHhrLevel()+1);//合伙人等级为上级等级+1
		fuUserDao.save(user);
					
		// 统计表信息写入
		HhrStat hhrStat = new HhrStat();
		hhrStat.setFuUser(user);
		hhrStat.setStatDate(new Date());
		hhrStat.setHhrParentID(user.getHhrParentID());
		hhrStatDao.save(hhrStat);
		
		FuUser parentUser = fuUserDao.findFuUserById(user.getHhrParentID());
		if(null != parentUser){
			// 上级合伙人信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user.getHhrParentID());
			HhrStat parentStat = hhrStatDao.findStatDataByMap2(map);
			if (null != parentStat) {
				if(parentStat.getFirstlyPartnerNum()==null){
					parentStat.setFirstlyPartnerNum(1);
				}else{
					parentStat.setFirstlyPartnerNum(parentStat.getFirstlyPartnerNum() + 1);
				}
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);
				
				//上级合伙人明细
				HhrStatDetail parentStatDetail = hhrStatDetailDao.findHhrStatDetailByUserAndDate(parentUser.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				if(parentStatDetail != null){
					parentStatDetail.setExtendPersonNew(parentStatDetail.getExtendPersonNew()==null?1:(parentStatDetail.getExtendPersonNew()+1));
				}else{
					parentStatDetail = new HhrStatDetail();
					parentStatDetail.setFuUser(parentUser);
					parentStatDetail.setExtendPersonNew(1);
					parentStatDetail.setStatDate(new Date());
					parentStatDetail.setCreateDate(new Date());
				}
				parentStatDetail.setStatDate(new Date());
				hhrStatDetailDao.save(parentStatDetail);
				
				//上级的上级合伙人信息
				HhrStat ppStat = hhrStatDao.findHhrStatByUser(parentStat.getHhrParentID());
				if(ppStat != null){
					if(ppStat.getSecondaryPartnerNum()==null){
						ppStat.setSecondaryPartnerNum(1);
					}else{
						ppStat.setSecondaryPartnerNum(ppStat.getSecondaryPartnerNum() + 1);
					}
					ppStat.setStatDate(new Date());
					hhrStatDao.save(ppStat);
					//从这一层用户开始往上回溯, 每个用户的次级合伙人都+1
					updatePartnerNum(ppStat);
				}
			}else {
				parentStat = new HhrStat();
				parentStat.setFuUser(parentUser);
				parentStat.setFirstlyPartnerNum(1);   //一级会员数目
				parentStat.setExtendPersonNew(1);     //今日新增会员
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);
			}
		}
	}
	
	//从这一层用户开始往上回溯, 每个用户的次级合伙人都+1
	public void updatePartnerNum(HhrStat ppStat) {
		if(ppStat.getHhrParentID() != null && ppStat.getHhrParentID() != 0){
			HhrStat upStat = hhrStatDao.findHhrStatByUser(ppStat.getHhrParentID());
			if(upStat != null){
				if(upStat.getSecondaryPartnerNum()==null){
					upStat.setSecondaryPartnerNum(1);
				}else{
					upStat.setSecondaryPartnerNum(upStat.getSecondaryPartnerNum() + 1);
				}
				hhrStatDao.save(upStat);
			}			
			updatePartnerNum(upStat);
		}
		
	}

	// 创建费率
	public void saveRate(FuUser user) {
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
			fuRate.setFuAdmin(fuAdminDao.get(1L));
			fuRate.setCreatetime(new Date());
			fuRate.setUpdateuser(1L);
			fuRate.setUpdatetime(new Date());
			fuRateDao.save(fuRate);
		}
	}

	public int checkCode(String code, String phone) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("destination", phone);
		map.put("type", 4);
		FuSmsLog sms = fuSmsLogDao.findLogByMap(map);//根据目标手机号得到最新一条注册短信对象
		if(sms!=null){
			if(!code.equals(sms.getRegCode())){
				return -2;//验证码不对
			}else{
				return 0;//通过
			}
		}else{
			return -1;//手机号不对
		}
	}
	
	public void saveAliOption(WqqOptions options,String out_trade_no,String total_fee)throws Exception{
		options.setTradeNo(out_trade_no);
		options.setIsPay(1);
		options.setPayTime(new Date());
		wqqOptionsDao.save(options);
		
		//充值记录
		FuRecharge recharge=new FuRecharge();
		recharge.setFuUser(options.getFuUser());
		recharge.setType(3);
		recharge.setRechargeBank("支付宝支付");
		recharge.setRechargeAccount(null);
		recharge.setPayStatus(1);//已付款
		recharge.setOrderNum(out_trade_no);
		recharge.setPayTime(new Date());
		recharge.setRechargeStatus(2);//通过
		recharge.setFuAdmin(fuAdminDao.get(1L));
		recharge.setCheckTime(new Date());
		recharge.setRechargeMoney(new BigDecimal(total_fee));
		recharge.setRechargeTime(new Date());
		recharge.setState(1);
		recharge.setProceedsType(0);
		recharge.setPayType(1);
		fuRechargeDao.save(recharge);
		
		FuUser user=options.getFuUser();
		user.setRechargeMoney((user.getRechargeMoney()==null?new BigDecimal(0.00):user.getRechargeMoney()).add(new BigDecimal(total_fee)));
		user.setAccountBalance(user.getAccountBalance().add(new BigDecimal(total_fee)));
		fuUserDao.save(user);
		
		//充值明细
		moneyDetailUtil.saveNewFuMoneyDetail(options.getFuUser(),null,null,"支付宝充值",8,new BigDecimal(total_fee),options.getFuUser().getAccountBalance(),true);
		
		
		user.setAccountBalance(user.getAccountBalance().subtract(new BigDecimal(total_fee)));
		fuUserDao.save(user);
	
		//出金明细
		moneyDetailUtil.saveNewFuMoneyDetail(options.getFuUser(),null,null,"微期权费用",15,new BigDecimal(total_fee),options.getFuUser().getAccountBalance(),false);
		
		//发送短信
		String message = URLDecoder.decode("微期权购买成功，购买时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"，订单号："+out_trade_no+"，金额："+total_fee+"元。", "UTF-8");
		FuSmsLog log = new FuSmsLog();
		log.setContent(message);
		log.setPrio(1);
		log.setRegCode(null);
		log.setReason("微期权支付成功");
		log.setDestination(options.getFuUser().getPhone());
		log.setPlanTime(new Date());
		log.setType(1);// 短信
		log.setState(0);
		fuSmsLogDao.save(log);
		System.out.println("支付成功---同步回调---"+new Date());
	}

	/**
	 * 根据userId 查询用户的所有的订单 
	 */
	public List<WqqOptions> getInfoByUserId(Long userId) {
		return wqqOptionsDao.getInfoByUserId(userId);
	}

	// **  保存期权支付（网站余额）
	public void saveOptionPayWeb(FuUser user, String tradeno, BigDecimal money, int isPay, Integer guessType, Long contentsId) {
		//网站余额
		user.setAccountBalance(user.getAccountBalance().subtract(money));
		fuUserDao.save(user);
		//资金明细
		moneyDetailUtil.saveNewFuMoneyDetail(user,null,null,null,15,money,user.getAccountBalance(),false);
		
		WqqOptions options=new WqqOptions();
		options.setFuUser(user);
		options.setTradeNo(tradeno);
		options.setMoney(money);
		options.setPanicTime(new Date());
		options.setIsPay(isPay);
		options.setPayTime(new Date());
		options.setGuessType(guessType);
		options.setWqqContents(wqqContentsDao.get(contentsId));
		wqqOptionsDao.save(options);
	}
	
	/**
	 * 保存微期权承兑
	 * @param wqqOptions
	 * @param acceptFactor
	 * @param orderIncome
	 */
	public void saveWqqFactor(Long contentsId,FuAdmin acceptAdmin,BigDecimal acceptFactor,Integer guessType){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("contentsId", contentsId);
		WqqContents wqqContents=wqqContentsDao.get(contentsId);
		wqqContents.setAcceptFactor(acceptFactor);
		wqqContents.setAcceptResult(guessType);//吧正确竞猜答案存入主表
		wqqContents.setState(3);//已承兑
		wqqContents.setAcceptAdmin(acceptAdmin);
		wqqContents.setAcceptTime(new Date());
		wqqContentsDao.save(wqqContents);
		
		List<WqqOptions> list=wqqOptionsDao.queryOptionsList(0, Integer.MAX_VALUE, map);//得到这次微期权的所有购买记录
		for (WqqOptions wqqOptions : list) {
			
			if(wqqOptions.getGuessType().equals(guessType) && acceptFactor.compareTo(BigDecimal.ZERO)==1){//竞猜类型等于正确答案且承兑系数大于0，发送短信，余额增加，写明细，写入收益
				BigDecimal orderIncome = wqqOptions.getMoney().multiply(acceptFactor).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
				wqqOptions.setOrderIncome(orderIncome);
				wqqOptionsDao.save(wqqOptions);
				//网站余额
				FuUser user=wqqOptions.getFuUser();
				user.setAccountBalance(user.getAccountBalance().add(orderIncome));
				fuUserDao.save(user);
				//资金明细
				moneyDetailUtil.saveNewFuMoneyDetail(user,null,null,null,38,orderIncome,user.getAccountBalance(),true);
				//发送短信
				String message="";
				try {
					message = URLDecoder.decode(wqqContents.getName()+"已到期，收益为"+acceptFactor+"倍，请查收。感谢您的参与！如有疑问请致电：4000320898。", "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				FuSmsLog log = new FuSmsLog();
				log.setContent(message);
				log.setPrio(1);
				log.setRegCode(null);
				log.setReason("微期权收益承兑");
				log.setDestination(wqqOptions.getFuUser().getPhone());
				log.setPlanTime(new Date());
				log.setType(1);// 短信
				log.setState(0);
				fuSmsLogDao.save(log);
			}else{//竞猜类型不等于正确答案或者承兑系数为0，只发送短信
				//发送短信
				String message="";
				try {
					message = URLDecoder.decode(wqqContents.getName()+"已到期，未达到保本线，无收益。感谢您的参与！如有疑问请致电：4000320898。", "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				FuSmsLog log = new FuSmsLog();
				log.setContent(message);
				log.setPrio(1);
				log.setRegCode(null);
				log.setReason("微期权收益承兑");
				log.setDestination(wqqOptions.getFuUser().getPhone());
				log.setPlanTime(new Date());
				log.setType(1);// 短信
				log.setState(0);
				fuSmsLogDao.save(log);
				
				wqqOptions.setOrderIncome(new BigDecimal(0));
				wqqOptionsDao.save(wqqOptions);
			}
		}
	}
}

