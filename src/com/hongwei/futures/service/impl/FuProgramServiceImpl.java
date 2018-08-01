package com.hongwei.futures.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongwei.futures.dao.FuAddMarginDao;
import com.hongwei.futures.dao.FuAdminDao;
import com.hongwei.futures.dao.FuBadCreditDao;
import com.hongwei.futures.dao.FuContinueContractDao;
import com.hongwei.futures.dao.FuDrawProfitsDao;
import com.hongwei.futures.dao.FuGameDao;
import com.hongwei.futures.dao.FuManageFeeDao;
import com.hongwei.futures.dao.FuProgramDao;
import com.hongwei.futures.dao.FuProgramUpDao;
import com.hongwei.futures.dao.FuServerDao;
import com.hongwei.futures.dao.FuSmsLogDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuAddMargin;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuBadCredit;
import com.hongwei.futures.model.FuContinueContract;
import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuManageFee;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuProgramUp;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuContinueContractService;
import com.hongwei.futures.service.FuManageFeeService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.util.MoneyDetailUtil;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.zhongqi.ZhongqiService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuProgramServiceImpl implements FuProgramService {
	@Autowired
	private FuContinueContractService fuContinueContractService;
	@Autowired
	private FuProgramDao fuProgramDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	@Autowired
	private FuManageFeeDao fuManageFeeDao;
	@Autowired
	private FuAddMarginDao fuAddMarginDao;
	@Autowired
	private FuDrawProfitsDao fuDrawProfitsDao;
	@Autowired
	private FuServerDao fuServerDao;
	@Autowired
	private FuProgramUpDao fuProgramUpDao;
	@Autowired
	private FuSmsLogDao fuSmsLogDao;
	@Autowired
	private FuAdminDao fuAdminDao;
	@Autowired
	private ZhongqiService zhongqiService;
	@Autowired
	private HhrStatService hhrStatService;
	@Autowired
	private FuManageFeeService fuManageFeeService;
	@Autowired
	private FuContinueContractDao continueContractDao;
	@Autowired
	private FuGameDao fuGameDao;
	@Autowired
	private FuBadCreditDao fuBadCreditDao;

	// ====================== 基本 C R U D 方法 ===========================
	@Override
	public FuProgram get(Long id) {
		return fuProgramDao.get(id);
	}

	@Override
	public void save(FuProgram entity) {
		fuProgramDao.save(entity);
	}

	@Override
	public void delete(Long id) {
		fuProgramDao.delete(id);
	}

	@Override
	public List<FuProgram> findToTradeByUser(Long userId, int programType) {
		return fuProgramDao.findToTradeByUser(userId, programType);
	}

	@Override
	public Integer countProgramByUser(Map<String, Object> map) {
		return fuProgramDao.countProgramByUser(map);
	}
	
	@Override
	public Integer countProgramByUser2(Map<String, Object> map) {
		return fuProgramDao.countProgramByUser2(map);
	}

	@Override
	public List<FuProgram> findProgramByUser(int i, int pageSize,
			Map<String, Object> map) {
		return fuProgramDao.findProgramByUser(i, pageSize, map);
	}

	@Override
	public List<FuProgram> findAllTradeProgram(Map<String, Object> map) {
		return fuProgramDao.findAllTradeProgram(map);
	}

	@Override
	public List<FuProgram> findAllTradeProgram() {
		return fuProgramDao.findAllTradeProgram();
	}
	
	@Override
	public List<FuProgram> findOpenProgramByParams(){
		return fuProgramDao.findOpenProgramByParams();
	}
	/**
	 * 系统所有正在交易的增配子方案
	 * @return
	 */
	@Override
	public List<FuProgram> findAllTradeSonProgram(){
		return fuProgramDao.findAllTradeSonProgram();
	}
	
	@Override
	public List<FuProgram> findAllDueProgram(){
		return fuProgramDao.findAllDueProgram();
	}
	
	@Override
	public List<FuProgram> findProgramUpByProgram(Long id) {
		return fuProgramDao.findProgramUpByProgram(id);
	}
	
	@Override
	public List<FuProgram> findSonProgramByProgram(Long id) {
		return fuProgramDao.findSonProgramByProgram(id);
	}
	
	public List<FuProgram> findProgramIsAcrossCabin(){
		return fuProgramDao.findProgramIsAcrossCabin();
	}
	/**
	 * 已删除方案数目
	 */
	public Integer countDeleteProgram(Map<String, Object> map){
		return fuProgramDao.countDeleteProgram(map);
	}
	/**
	 * 已删除方案列表
	 */
	public List<FuProgram> findDeleteProgramList(int i, int pageSize,Map<String, Object> map){
		return fuProgramDao.findDeleteProgramList(i, pageSize, map);
	}

	/**
	 * 前端配资-自动
	 */
	public void saveInfoAuto(FuProgram program, FuUser fuUser, Long ppId) {
		if (ppId == null) {
			List<FuServer> servers = fuServerDao.findListAvalible(program.getMatchMoney().add(program.getSafeMoney()));
			if (servers.size() > 0) {
				FuServer server = servers.get(0);
				server.setNowNumber(server.getNowNumber() + 1);
				server.setServerMoney(server.getServerMoney().subtract(program.getMatchMoney()).subtract(program.getSafeMoney()));
				int account = server.getStartNumber() + server.getNowNumber();
				fuServerDao.save(server);
				// 客户开户中是不能给予（开户服务器、开户IP地址、服务器交易端口）
				if (program.getStatus() <= 1) {
					program.setTradeServiceName(null);
					program.setTradeIp(null);
					program.setTradePort(null);
					program.setTradeAccount(null);
				} else {
					program.setTradeServiceName(server.getServerName());
					program.setTradeIp(server.getServerIp());
					program.setTradePort(server.getPortNumber());
					program.setTradeAccount(account);
				}
				String pwd = fuUser.getCardNumber().substring(
						fuUser.getCardNumber().length() - 6);
				program.setTradePassword(pwd);
				program.setFuServer(server);
				program.setRoleId(server.getUsertypeId());
				if (program.getDoubleNum() == 1) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId1() : server.getMonthId1());
				}
				if (program.getDoubleNum() == 2) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId2() : server.getMonthId2());
				}
				if (program.getDoubleNum() == 3) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId3() : server.getMonthId3());
				}
				if (program.getDoubleNum() == 4) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId4() : server.getMonthId4());
				}
				if (program.getDoubleNum() == 5) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId5() : server.getMonthId5());
				}
				if (program.getDoubleNum() == 6) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId6() : server.getMonthId6());
				}
				if (program.getDoubleNum() == 7) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId7() : server.getMonthId7());
				}
				if (program.getDoubleNum() == 8) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId8() : server.getMonthId8());
				}
				if (program.getDoubleNum() == 9) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId9() : server.getMonthId9());
				}
				if (program.getDoubleNum() == 10) {
					program.setGroupId(program.getProgramType() == 1 ? server
							.getDayId10() : server.getMonthId10());
				}
			}
		} else {
			// 升级方案
			FuProgram p = fuProgramDao.get(ppId);
			p.setProgramWay(2);
			fuProgramDao.save(p);
			// 客户开户中是不能给予（开户服务器、开户IP地址、服务器交易端口）
			if (program.getStatus() <= 1) {
				program.setTradeServiceName(null);
				program.setTradeIp(null);
				program.setTradePort(null);
			} else {
				program.setTradeServiceName(p.getTradeServiceName());
				program.setTradeIp(p.getTradeIp());
				program.setTradePort(p.getTradePort());
			}
			program.setTradeAccount(p.getTradeAccount());
			program.setTradePassword(p.getTradePassword());
			program.setFuServer(p.getFuServer());
			program.setRoleId(p.getRoleId());
			program.setGroupId(p.getGroupId());
		}

		fuProgramDao.save(program);
		// 个人账户中的数据变化
		fuUser.setAccountTotalMoney(fuUser.getAccountTotalMoney().add(program.getMatchMoney()).add(program.getSafeMoney()));
		fuUser.setMatchMoney(fuUser.getMatchMoney().add(program.getMatchMoney()));
		fuUser.setSafeMoney(fuUser.getSafeMoney().add(program.getSafeMoney()));
		fuUser.setFeeTotal(fuUser.getFeeTotal() == null ? program.getManageMoney() : (fuUser.getFeeTotal().add(program.getManageMoney())));

		// 是否产生配资明细--支出风险保证金
		fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(program.getSafeMoney()));
		fuUserDao.save(fuUser);
		moneyDetailUtil.saveNewFuMoneyDetail(fuUser, program, null, null, 28, program.getSafeMoney(), fuUser.getAccountBalance(), false);
		// 管理费支出明细
		fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(program.getManageMoney()));
		fuUserDao.save(fuUser);
		moneyDetailUtil.saveNewFuMoneyDetail(fuUser, program, null, null, 34, program.getManageMoney(), fuUser.getAccountBalance(), false);

		// 待保证金添加
		FuAddMargin addMargin = new FuAddMargin();
		addMargin.setFuProgram(program);
		addMargin.setFuUser(fuUser);
		addMargin.setMoney(program.getSafeMoney());
		if (program.getProgramType() == 1) {
			addMargin.setRemark("新日配保证金");
		} else {
			addMargin.setRemark("新月配保证金");
		}
		addMargin.setState(0);// 0未处理，1通过，2取消
		addMargin.setTime(new Date());
		addMargin.setType(1);// 余额支付
		fuAddMarginDao.save(addMargin);
	}

	/**
	 * 前端配资-手动
	 */
	@Override
	public void saveInfo(FuProgram program, FuUser fuUser, Long ppId) {
		if (ppId == null) {
			program.setTradeServiceName(null);
			program.setTradeIp(null);
			program.setTradePort(null);
			program.setTradeAccount(null);
		}
		fuProgramDao.save(program);
		fuUserDao.save(fuUser);
	}

	/**
	 * 后台开户审核方案
	 */
	@Override
	public void saveCheck(FuProgram program, int flag, FuAdmin admin,Long serverId) {
		try {
			FuUser fuUser = program.getFuUser();
			if (flag == 2) { // 审核通过
				if(program.getAddMatchId()!=null){//增配子方案
					FuProgram primaryPro=fuProgramDao.get(program.getAddMatchId());//主方案
					Integer paymentId=zhongqiService.addPaymentBySonProgram(primaryPro.getFuUser(), primaryPro, program, primaryPro.getFuServer());
					if(paymentId>0){
						program.setStatus(1);//审核中
						program.setOpenPaymentId(paymentId);
					}else{
						program.setStatus(3);//开户插入众期库失败
					}
				}else{//主方案或者减配子方案
					List<Object> list=zhongqiService.saveProgram(program.getFuUser(), program, program.getFuServer());
					if(Integer.valueOf(list.get(0).toString())>0 && Integer.valueOf(list.get(1).toString())>0 && Integer.valueOf(list.get(2).toString())>0){
						program.setStatus(1);//审核中
						program.setOpenUserId(Integer.valueOf(list.get(0).toString()));
						program.setOpenPaymentId(Integer.valueOf(list.get(1).toString()));
						program.setOpenTriggerId(Integer.valueOf(list.get(2).toString()));
					}else{
						program.setStatus(3);//开户插入众期库失败
					}
				}
			}
			if (flag == 3) { // 拒绝
				//拒绝直接改方案状态，同时返还消耗的点劵
				fuUser.setIntegral(fuUser.getIntegral().add(program.getExpenseScore()));
				fuUserDao.save(fuUser);
				program.setStatus(3);//拒绝开户
			}
			fuProgramDao.save(program);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 结算主方案操作,传入子方案对象（前台）
	 */
	@Override
	public String saveOverProgramByBefore(FuProgram pro) {
		BigDecimal matchMoney;
		if(pro.getAddMatchId()!=null){
			FuProgram primaryPro=fuProgramDao.get(pro.getAddMatchId());//主方案
			fuProgramDao.save(primaryPro);
			matchMoney=primaryPro.getMatchMoney();
		}else{
			matchMoney=BigDecimal.ZERO;
		}
		//风险度（风险度>0有持仓，否则无持仓）
		BigDecimal risk = zhongqiService.findRisk(pro.getFuServer(), pro.getTradeAccount());
		if (risk.compareTo(BigDecimal.ZERO)==1) {
			return "有持仓不能清算";
		}
		//动态权益
		BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(), pro.getTradeAccount());
		//（动态权益-配资金额）<0  则账户穿仓
		if(balance.subtract(pro.getMatchMoney().add(matchMoney)).compareTo(BigDecimal.ZERO)==-1){
			saveAcrossCabin(pro, pro.getMatchMoney().add(matchMoney).subtract(balance));
			return "账户已穿仓";
		}
		pro.setStatus(6);//结算中
		fuProgramDao.save(pro);
		return "清算成功";
	}
	
	/**
	 * 结算主方案操作,传入子方案对象（盘后后台任务开始结算，做出金操作）
	 */
	@Override
	public String saveOverProgramByTask(FuProgram pro) {
		BigDecimal matchMoney;
		if(pro.getAddMatchId()!=null){
			FuProgram primaryPro=fuProgramDao.get(pro.getAddMatchId());//主方案
			fuProgramDao.save(primaryPro);
			matchMoney=primaryPro.getMatchMoney();
		}else{
			matchMoney=BigDecimal.ZERO;
		}
		//风险度（风险度>0有持仓，否则无持仓）
		BigDecimal risk = zhongqiService.findRisk(pro.getFuServer(), pro.getTradeAccount());
		if (risk.compareTo(BigDecimal.ZERO)==1) {
			return "有持仓不能清算";
		}
		//动态权益
		BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(), pro.getTradeAccount());
		//（动态权益-配资金额）<0  则账户穿仓
		if(balance.subtract(pro.getMatchMoney().add(matchMoney)).compareTo(BigDecimal.ZERO)==-1){
			saveAcrossCabin(pro, pro.getMatchMoney().add(matchMoney).subtract(balance));
			return "账户已穿仓";
		}
		//客户交易账号除实盘资金外的金额
		BigDecimal drawMoney=balance.subtract(pro.getMatchMoney()).subtract(matchMoney);
		if(drawMoney.compareTo(BigDecimal.ZERO)==-1){
			drawMoney=BigDecimal.ZERO;
		}
		
		List<Object> list=zhongqiService.programStop(pro.getFuServer(),pro,pro.getMatchMoney().add(matchMoney),drawMoney);
		if(Integer.valueOf(list.get(0).toString())>0 && Integer.valueOf(list.get(1).toString())>0){
			pro.setStatus(6);
			pro.setIncome(drawMoney);
			pro.setCloseMatchId(Integer.valueOf(list.get(0).toString()));
			pro.setCloseBlanceId(Integer.valueOf(list.get(1).toString()));
			fuProgramDao.save(pro);
			return "清算成功";
		}else{
			pro.setIncome(drawMoney);
			pro.setStatus(4);//待结算
			fuProgramDao.save(pro);
			return "清算失败";
		}
	}
	
	/**
	 * 结算主方案操作,传入子方案对象（后台管理员手动结算）
	 */
	@Override
	public String saveOverProgramByAfter(FuProgram pro,FuAdmin fuAdmin) {
		BigDecimal matchMoney;
		if(pro.getAddMatchId()!=null){
			FuProgram primaryPro=fuProgramDao.get(pro.getAddMatchId());//主方案
			primaryPro.setAdminClear(fuAdmin);
			fuProgramDao.save(primaryPro);
			matchMoney=primaryPro.getMatchMoney();
		}else{
			matchMoney=BigDecimal.ZERO;
		}
		//风险度（风险度>0有持仓，否则无持仓）
		BigDecimal risk = zhongqiService.findRisk(pro.getFuServer(), pro.getTradeAccount());
		if (risk.compareTo(BigDecimal.ZERO)==1) {
			return "有持仓不能清算";
		}
		//动态权益
		BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(), pro.getTradeAccount());
		//（动态权益-配资金额）<0  则账户穿仓
		if(balance.subtract(pro.getMatchMoney().add(matchMoney)).compareTo(BigDecimal.ZERO)==-1){
			saveAcrossCabin(pro, pro.getMatchMoney().add(matchMoney).subtract(balance));
			return "账户已穿仓";
		}
		//客户交易账号除实盘资金外的金额
		BigDecimal drawMoney=balance.subtract(pro.getMatchMoney()).subtract(matchMoney);
		if(drawMoney.compareTo(BigDecimal.ZERO)==-1){
			drawMoney=BigDecimal.ZERO;
		}
		
		if(pro.getCloseMatchId()==null||pro.getCloseMatchId()<=0||pro.getCloseBlanceId()==null||pro.getCloseMatchId()<=0){
			List<Object> list=zhongqiService.programStop(pro.getFuServer(),pro,pro.getMatchMoney().add(matchMoney),drawMoney);
			if(Integer.valueOf(list.get(0).toString())>0 && Integer.valueOf(list.get(1).toString())>0){
				pro.setIncome(drawMoney);
				pro.setStatus(6);//结算中
				pro.setCloseMatchId(Integer.valueOf(list.get(0).toString()));
				pro.setCloseBlanceId(Integer.valueOf(list.get(1).toString()));
				pro.setAdminClear(fuAdmin);
				fuProgramDao.save(pro);
				return "清算成功";
			}else{
				pro.setIncome(drawMoney);
				pro.setStatus(4);//待结算
				fuProgramDao.save(pro);
				return "清算失败";
			}
		}else{
			pro.setIncome(drawMoney);
			pro.setStatus(6);//结算中
			pro.setAdminClear(fuAdmin);
			fuProgramDao.save(pro);
			return "清算成功";
		}
	}
	
	/**
	 * 结算到期增配子方案操作（任务）
	 */
	@Override
	public String saveOverSonProgram(FuProgram pro) {
		FuProgram primaryPro=fuProgramDao.get(pro.getAddMatchId());//主方案
		
		//动态权益
		BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(), pro.getTradeAccount());
		//（动态权益-配资金额）<0  则账户穿仓
		if(balance.subtract(pro.getMatchMoney().add(primaryPro.getMatchMoney())).compareTo(BigDecimal.ZERO)==-1){
			saveAcrossCabin(pro, pro.getMatchMoney().add(primaryPro.getMatchMoney()).subtract(balance));
			return "账户已穿仓";
		}
		//方案结算到网站的金额
		BigDecimal drawMoney=BigDecimal.ZERO;
		
		//持仓占用保证金
		BigDecimal currMargin = zhongqiService.findCurrMargin(pro.getFuServer(), pro.getTradeAccount());
		
		//隔夜倍数
		BigDecimal geYe;
		if(pro.getDoubleNum()>5){//不隔夜
			geYe=new BigDecimal(Property.getProperty("OverNight_Not"));
		}else{
			if(new BigDecimal(Property.getProperty("OverNight_Good_Rate")).compareTo(new BigDecimal(Property.getProperty("OverNight_Stock_Index_Rate")))==-1){
				geYe=new BigDecimal(Property.getProperty("OverNight_Good_Rate"));
			}else{
				geYe=new BigDecimal(Property.getProperty("OverNight_Stock_Index_Rate"));
			}
		}
		
		//风险度（风险度>0有持仓，否则无持仓）
		BigDecimal risk = zhongqiService.findRisk(pro.getFuServer(), pro.getTradeAccount());
		if (risk.compareTo(BigDecimal.ZERO)==1) {//有持仓
			//若（客户保证金a＋客户保证金b）－持仓占用保证金／隔夜倍数>0
			if((primaryPro.getSafeMoney().add(pro.getSafeMoney())).subtract(currMargin.divide(geYe)).compareTo(BigDecimal.ZERO)==1){
				drawMoney=balance.subtract(primaryPro.getMatchMoney()).subtract(pro.getMatchMoney()).subtract(primaryPro.getSafeMoney());//这里
			}else{//若（客户保证金a＋客户保证金b）－持仓占用保证金／隔夜倍数<=0
				//清算金额b <=（动态权益－配资金额a－客户保证金a－配资金额b）－（持仓占用保证金／隔夜倍数－客户保证金a）；
				drawMoney=balance.subtract(primaryPro.getMatchMoney()).subtract(pro.getMatchMoney()).subtract(primaryPro.getSafeMoney()).subtract(currMargin.divide(geYe).subtract(primaryPro.getSafeMoney()));
			}
		}else{//无持仓
			drawMoney=balance.subtract(primaryPro.getMatchMoney()).subtract(pro.getMatchMoney()).subtract(primaryPro.getSafeMoney());
		}
		if(drawMoney.compareTo(BigDecimal.ZERO)==-1){
			drawMoney=BigDecimal.ZERO;
		}
		List<Object> list=zhongqiService.sonProgramStop(pro.getFuServer(), primaryPro, pro, pro.getMatchMoney(), drawMoney);
		if(Integer.valueOf(list.get(0).toString())>0 && Integer.valueOf(list.get(1).toString())>0 && Integer.valueOf(list.get(2).toString())>0){
			pro.setIncome(drawMoney);
			pro.setStatus(6);//结算中
			pro.setCloseMatchId(Integer.valueOf(list.get(1).toString()));
			pro.setCloseBlanceId(Integer.valueOf(list.get(2).toString()));
			fuProgramDao.save(pro);
			return "清算成功";
		}else{
			pro.setIncome(drawMoney);
			pro.setStatus(4);//待结算
			fuProgramDao.save(pro);
			return "清算失败";
		}
	}
	
	/**
	 * 线下结算到期增配子方案操作（后台）
	 */
	@Override
	public String saveOfflineOverSonProgram(FuProgram pro,FuAdmin fuAdmin) {
		FuProgram primaryPro=fuProgramDao.get(pro.getAddMatchId());//主方案
		
		//动态权益
		BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(), pro.getTradeAccount());
		
		//方案结算到网站的金额
		BigDecimal drawMoney=BigDecimal.ZERO;
		
		//持仓占用保证金
		BigDecimal currMargin = zhongqiService.findCurrMargin(pro.getFuServer(), pro.getTradeAccount());
		
		//隔夜倍数
		BigDecimal geYe;
		if(pro.getDoubleNum()>5){//不隔夜
			geYe=new BigDecimal(Property.getProperty("OverNight_Not"));
		}else{
			if(new BigDecimal(Property.getProperty("OverNight_Good_Rate")).compareTo(new BigDecimal(Property.getProperty("OverNight_Stock_Index_Rate")))==-1){
				geYe=new BigDecimal(Property.getProperty("OverNight_Good_Rate"));
			}else{
				geYe=new BigDecimal(Property.getProperty("OverNight_Stock_Index_Rate"));
			}
		}
		
		//风险度（风险度>0有持仓，否则无持仓）
		BigDecimal risk = zhongqiService.findRisk(pro.getFuServer(), pro.getTradeAccount());
		if (risk.compareTo(BigDecimal.ZERO)==1) {//有持仓
			//若（客户保证金a＋客户保证金b）－持仓占用保证金／隔夜倍数>0
			if((primaryPro.getSafeMoney().add(pro.getSafeMoney())).subtract(currMargin.divide(geYe)).compareTo(BigDecimal.ZERO)==1){
				drawMoney=balance.subtract(primaryPro.getMatchMoney()).subtract(pro.getMatchMoney()).subtract(primaryPro.getSafeMoney());//这里
			}else{//若（客户保证金a＋客户保证金b）－持仓占用保证金／隔夜倍数<=0
				//清算金额b <=（动态权益－配资金额a－客户保证金a－配资金额b）－（持仓占用保证金／隔夜倍数－客户保证金a）；
				drawMoney=balance.subtract(primaryPro.getMatchMoney()).subtract(pro.getMatchMoney()).subtract(primaryPro.getSafeMoney()).subtract(currMargin.divide(geYe).subtract(primaryPro.getSafeMoney()));
			}
		}else{//无持仓
			drawMoney=balance.subtract(primaryPro.getMatchMoney()).subtract(pro.getMatchMoney()).subtract(primaryPro.getSafeMoney());
		}
		if(drawMoney.compareTo(BigDecimal.ZERO)==-1){
			drawMoney=BigDecimal.ZERO;
		}
		pro.setIncome(drawMoney);
		pro.setCloseMatchId(null);
		pro.setCloseBlanceId(null);
		pro.setFuAdmin(fuAdmin);
		fuProgramDao.save(pro);
		saveCloseProgramTask(pro.getFuServer(),pro.getFuUser(),pro);
		return "清算成功";
	}
	
	/**
	 * 线下结算主方案操作,传入子方案对象（后台）
	 */
	@Override
	public String saveOverProgramOffline(FuProgram pro,FuAdmin fuAdmin) {
		BigDecimal matchMoney;
		if(pro.getAddMatchId()!=null){
			FuProgram primaryPro=fuProgramDao.get(pro.getAddMatchId());//主方案
			primaryPro.setAdminClear(fuAdmin);
			fuProgramDao.save(primaryPro);
			matchMoney=primaryPro.getMatchMoney();
		}else{
			matchMoney=BigDecimal.ZERO;
		}
		
		//动态权益
		BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(), pro.getTradeAccount());
		
		//客户交易账号除实盘资金外的金额
		BigDecimal drawMoney=balance.subtract(pro.getMatchMoney()).subtract(matchMoney);
		if(drawMoney.compareTo(BigDecimal.ZERO)==-1){
			drawMoney=BigDecimal.ZERO;
		}
		pro.setIncome(drawMoney);
		pro.setCloseMatchId(null);
		pro.setCloseBlanceId(null);
		pro.setAdminClear(fuAdmin);
		fuProgramDao.save(pro);
		saveCloseProgramTask(pro.getFuServer(),pro.getFuUser(),pro);
		return "清算成功";
	}

	/**
	 * 追加保证金（前台）
	 */
	@Override
	public void saveAddConfirmMoney(FuProgram pro, BigDecimal money) {
		int paymentId=zhongqiService.savePayment(money, pro, pro.getFuServer());
		
		FuUser fuUser = pro.getFuUser();
		FuAddMargin addMargin = new FuAddMargin();
		addMargin.setFuProgram(pro);
		addMargin.setFuUser(fuUser);
		addMargin.setMoney(money);
		addMargin.setRemark("追加保证金");
		addMargin.setTime(new Date());
		addMargin.setType(1);// 余额支付
		if(paymentId>0){
			addMargin.setState(1);// 0待审核1审核中2同意3拒绝
			addMargin.setPaymentId(paymentId);
		}else{
			addMargin.setState(3);// 0待审核1审核中2同意3拒绝
		}
		fuAddMarginDao.save(addMargin);
	}

	/**
	 * 日配未到期自动处理方案周期内下个工作日的管理费
	 */
	@Override
	public void saveDayManageMoney(FuProgram pro, Integer cycleNum, BigDecimal money) {
		//产生管理费
		FuManageFee mf=fuManageFeeDao.findfeeByProidLast(pro.getId());
		FuManageFee manageFee = new FuManageFee();
		manageFee.setFuProgram(pro);
		manageFee.setFuUser(pro.getFuUser());
		manageFee.setMoney(money);
		manageFee.setPayCycle(cycleNum);
		manageFee.setType(1);// 余额支付
		manageFee.setAccountBalance(pro.getFuUser().getAccountBalance());
		manageFee.setFuAdmin(fuAdminDao.get(new Long(1)));
		manageFee.setCheckTime(new Date());
		manageFee.setComment("自动审核");
		manageFee.setFeeType("续交日配管理费");
		//服务周期开始、结束时间=上次服务周期结束日期+一天
		Calendar calen = Calendar.getInstance();
		calen.setTime(mf.getEndTime());
		calen.add(Calendar.DAY_OF_MONTH, 1);
		manageFee.setBeginTime(calen.getTime());
		manageFee.setEndTime(calen.getTime());
		
		//余额不足，那么就提取利润来充当管理费
		if(pro.getFuUser().getAccountBalance().compareTo(money) == -1){
			int paymentId=zhongqiService.drawProfit(pro.getFuServer(), pro, money);
			if(paymentId==0){
				manageFee.setState(3);
				fuManageFeeDao.save(manageFee);
				
				//出金处理失败，那么方案改为待结算
				pro.setStatus(4);
				fuProgramDao.save(pro);
			}else{
				//产生出金申请
				FuDrawProfits profits = new FuDrawProfits();
				profits.setFuProgram(pro);
				profits.setFuUser(pro.getFuUser());
				profits.setMoney(money);
				profits.setRemark("账户余额不足出金续交管理费");
				profits.setTime(new Date());
				profits.setType(0);
				profits.setState(2);
				profits.setFuAdmin(fuAdminDao.get(new Long(1)));
				profits.setCheckTime(new Date());
				profits.setComment("自动审核");
				profits.setPaymentId(paymentId);
				fuDrawProfitsDao.save(profits);
				//如果出金操作没得到反馈结果就一直查询
				Map<String, Object> map=new HashMap<String, Object>();
				do {
					map=zhongqiService.findResultByInvestorPaymentId(pro.getFuServer(), profits.getPaymentId());
				} while (map.containsKey("result")&&Integer.valueOf(map.get("result").toString())==0);
				//如果出金操作成功，那么管理费也成功审核
				if(map.get("result").toString().equals("1")){
					profits.setState(2);
					fuDrawProfitsDao.save(profits);
					saveDrawProfitsTask(profits, pro.getFuUser(), pro);//出金成功，余额增加，写明细，发短信
					
					manageFee.setState(2);
					fuManageFeeDao.save(manageFee);
					fuManageFeeService.saveManageMoney(manageFee);
				}else{
					profits.setState(3);
					fuDrawProfitsDao.save(profits);
					
					manageFee.setState(3);
					fuManageFeeDao.save(manageFee);
					
					//出金处理失败，那么方案改为待结算
					pro.setStatus(4);
					fuProgramDao.save(pro);
				}
			}
		}else{//余额充足
			manageFee.setState(2);
			fuManageFeeDao.save(manageFee);
			fuManageFeeService.saveManageMoney(manageFee);
		}
	}

	/**
	 * 月配未到期自动处理方案周期内下个月的管理费
	 */
	@Override
	public void saveMonthManageMoney(FuProgram pro, Integer cycleNum, BigDecimal money) {
		//产生管理费
		FuManageFee mf=fuManageFeeDao.findfeeByProidLast(pro.getId());
		FuManageFee manageFee = new FuManageFee();
		manageFee.setFuProgram(pro);
		manageFee.setFuUser(pro.getFuUser());
		manageFee.setMoney(money);
		manageFee.setPayCycle(cycleNum);
		manageFee.setType(1);// 余额支付
		manageFee.setAccountBalance(pro.getFuUser().getAccountBalance());
		manageFee.setFuAdmin(fuAdminDao.get(new Long(1)));
		manageFee.setCheckTime(new Date());
		manageFee.setComment("自动审核");
		manageFee.setFeeType("续交月配管理费");
		//服务周期开始时间=上次服务周期结束日期+一天
		Calendar calen = Calendar.getInstance();
		calen.setTime(mf.getEndTime());
		calen.add(Calendar.DAY_OF_MONTH, 1);
		manageFee.setBeginTime(calen.getTime());
		//服务周期结束时间=上次服务周期结束日期+一个月
		Calendar calen2 = Calendar.getInstance();
		calen2.setTime(mf.getEndTime());
		calen2.add(Calendar.MONTH, 1);
		manageFee.setEndTime(calen2.getTime());
		
		//余额不足，那么就提取利润来充当管理费
		if(pro.getFuUser().getAccountBalance().compareTo(money) == -1){
			int paymentId=zhongqiService.drawProfit(pro.getFuServer(), pro, money);
			if(paymentId==0){
				manageFee.setState(3);
				fuManageFeeDao.save(manageFee);
				
				//出金处理失败，那么方案改为待结算
				pro.setStatus(4);
				fuProgramDao.save(pro);
			}else{
				//产生出金申请
				FuDrawProfits profits = new FuDrawProfits();
				profits.setFuProgram(pro);
				profits.setFuUser(pro.getFuUser());
				profits.setMoney(money);
				profits.setRemark("账户余额不足出金续交管理费");
				profits.setTime(new Date());
				profits.setType(0);
				profits.setState(2);
				profits.setFuAdmin(fuAdminDao.get(new Long(1)));
				profits.setCheckTime(new Date());
				profits.setComment("自动审核");
				profits.setPaymentId(paymentId);
				fuDrawProfitsDao.save(profits);
				//如果出金操作没得到反馈结果就一直查询
				Map<String, Object> map=new HashMap<String, Object>();
				do {
					map=zhongqiService.findResultByInvestorPaymentId(pro.getFuServer(), profits.getPaymentId());
				} while (map.containsKey("result")&&Integer.valueOf(map.get("result").toString())==0);
				//如果出金操作成功，那么管理费也成功审核
				if(map.get("result").toString().equals("1")){
					profits.setState(2);
					fuDrawProfitsDao.save(profits);
					saveDrawProfitsTask(profits, pro.getFuUser(), pro);//出金成功，余额增加，写明细，发短信
					
					manageFee.setState(2);
					fuManageFeeDao.save(manageFee);
					fuManageFeeService.saveManageMoney(manageFee);
				}else{
					profits.setState(3);
					fuDrawProfitsDao.save(profits);
					
					manageFee.setState(3);
					fuManageFeeDao.save(manageFee);
					
					//出金处理失败，那么方案改为待结算
					pro.setStatus(4);
					fuProgramDao.save(pro);
				}
			}
		}else{//余额充足
			manageFee.setState(2);
			fuManageFeeDao.save(manageFee);
			fuManageFeeService.saveManageMoney(manageFee);
		}
	}
	
	/**
	 * 任务（方案超出交易关闭日期的）自动续约，自动扣除管理费，余额不足自己出金补充管理费
	 */
	@Override
	public void saveProgramAutoContinue(FuProgram pro, Integer cycleNum, BigDecimal money, String introduce){
		// 产生续约记录
		FuContinueContract continueContract = new FuContinueContract();
		continueContract.setFuProgram(pro);
		continueContract.setFuUser(pro.getFuUser());
		continueContract.setCycle(cycleNum);
		continueContract.setIntroduction(introduce);
		continueContract.setTime(new Date());
		continueContract.setFuAdmin(fuAdminDao.get(new Long(1)));
		continueContract.setCheckTime(new Date());
		continueContract.setComment("方案到期自动续约");
		continueContractDao.save(continueContract);
		String msg="您的方案["+pro.getId()+"]续约审核通过。";
		
		// 产生管理费		
		FuManageFee mf=fuManageFeeDao.findfeeByProidLast(pro.getId());
		FuManageFee manageFee = new FuManageFee();
		manageFee.setFuProgram(pro);
		manageFee.setFuUser(pro.getFuUser());
		manageFee.setMoney(money);
		manageFee.setPayCycle(cycleNum);
		manageFee.setType(1);// 余额支付
		manageFee.setAccountBalance(pro.getFuUser().getAccountBalance());
		manageFee.setFuAdmin(fuAdminDao.get(new Long(1)));
		manageFee.setCheckTime(new Date());
		manageFee.setComment("自动审核");
		//服务周期开始时间=上次服务周期结束日期+一天
		Calendar calen = Calendar.getInstance();
		calen.setTime(mf.getEndTime());
		calen.add(Calendar.DAY_OF_MONTH, 1);
		manageFee.setBeginTime(calen.getTime());
		if(pro.getProgramType()==1){
			manageFee.setFeeType("续约日配管理费");
			manageFee.setEndTime(calen.getTime());
		}else{
			//服务周期结束时间=上次服务周期结束日期+一个月
			manageFee.setFeeType("续约月配管理费");
			Calendar calen2 = Calendar.getInstance();
			calen2.setTime(mf.getEndTime());
			calen2.add(Calendar.MONTH, 1);
			manageFee.setEndTime(calen2.getTime());
		}
		//余额不足，那么就提取利润来充当管理费
		if(pro.getFuUser().getAccountBalance().compareTo(money) == -1){
			int paymentId=zhongqiService.drawProfit(pro.getFuServer(), pro, money);
			if(paymentId==0){
				manageFee.setState(3);
				fuManageFeeDao.save(manageFee);
				
				continueContract.setResult(3);
				continueContractDao.save(continueContract);
				
				//出金处理失败，那么方案改为待结算
				pro.setStatus(4);
				fuProgramDao.save(pro);
			}else{
				//产生出金申请
				FuDrawProfits profits = new FuDrawProfits();
				profits.setFuProgram(pro);
				profits.setFuUser(pro.getFuUser());
				profits.setMoney(money);
				profits.setRemark("账户余额不足出金续交管理费");
				profits.setTime(new Date());
				profits.setType(0);
				profits.setState(2);
				profits.setPaymentId(paymentId);
				profits.setFuAdmin(fuAdminDao.get(new Long(1)));
				profits.setCheckTime(new Date());
				profits.setComment("自动审核");
				fuDrawProfitsDao.save(profits);
				//如果出金操作没得到反馈结果就一直查询
				Map<String, Object> map=new HashMap<String, Object>();
				do {
					map=zhongqiService.findResultByInvestorPaymentId(pro.getFuServer(), profits.getPaymentId());
				} while (map.containsKey("result")&&Integer.valueOf(map.get("result").toString())==0);
				//如果出金操作成功，那么管理费也成功审核，续约也成功审核
				if(map.get("result").toString().equals("1")){
					profits.setState(2);
					fuDrawProfitsDao.save(profits);
					saveDrawProfitsTask(profits, pro.getFuUser(), pro);//出金成功，余额增加，写明细，发短信
					
					manageFee.setState(2);
					fuManageFeeDao.save(manageFee);
					fuManageFeeService.saveManageMoney(manageFee);
					
					continueContract.setResult(2);
					fuContinueContractService.saveContAndPro(continueContract,msg);
				}else{
					profits.setState(3);
					fuDrawProfitsDao.save(profits);
					
					manageFee.setState(3);
					fuManageFeeDao.save(manageFee);
					
					continueContract.setResult(3);
					continueContractDao.save(continueContract);
					
					//出金处理失败，那么方案改为待结算
					pro.setStatus(4);
					fuProgramDao.save(pro);
				}
			}
		}else{//余额充足
			manageFee.setState(2);
			fuManageFeeDao.save(manageFee);
			fuManageFeeService.saveManageMoney(manageFee);
			
			continueContract.setResult(2);
			fuContinueContractService.saveContAndPro(continueContract,msg);
		}
	}

	/**
	 * 前台手动续约
	 */
	@Override
	public void saveProgramContinue(FuProgram pro, Integer cycleNum, BigDecimal money, String introduce) {
		// 产生续约记录
		FuContinueContract continueContract = new FuContinueContract();
		continueContract.setCycle(cycleNum);
		continueContract.setFuProgram(pro);
		continueContract.setFuUser(pro.getFuUser());
		continueContract.setIntroduction(introduce);
		continueContract.setResult(2);// 0待审核1审核中2同意3拒绝
		continueContract.setTime(new Date());
		continueContract.setFuAdmin(fuAdminDao.get(new Long(1)));
		continueContract.setCheckTime(new Date());
		continueContract.setComment("用户手动续约");
		continueContractDao.save(continueContract);
		String msg="您的方案["+pro.getId()+"]续约审核通过。";
		fuContinueContractService.saveContAndPro(continueContract,msg);
	}

	/**
	 * 提取利润
	 */
	@Override
	public String saveDrowMoney(FuProgram pro, BigDecimal money) {
		int paymentId=zhongqiService.drawProfit(pro.getFuServer(), pro, money);
		if(paymentId==0){
			return "提取利润失败！";
		}
		FuDrawProfits profits = new FuDrawProfits();
		profits.setFuProgram(pro);
		profits.setFuUser(pro.getFuUser());
		profits.setMoney(money);
		profits.setRemark("提取利润");
		profits.setTime(new Date());
		profits.setType(0);
		profits.setCheckTime(new Date());
		profits.setFuAdmin(fuAdminDao.get(new Long(1)));
		profits.setState(1);
		profits.setComment("自动审核");
		profits.setPaymentId(paymentId);
		fuDrawProfitsDao.save(profits);
		return profits.getId().toString();
	}

	/**
	 * 参加期货大赛
	 */
	@Override
	public void saveGame(FuProgram pro, FuGame game) {
		fuProgramDao.save(pro);
		fuGameDao.save(game);
		FuUser u = pro.getFuUser();
		u.setAccountBalance(u.getAccountBalance().subtract(pro.getSafeMoney()));
		u.setAccountTotalMoney(u.getAccountTotalMoney().add(pro.getMatchMoney()).add(pro.getSafeMoney()));
		u.setMatchMoney(u.getMatchMoney().add(pro.getMatchMoney()));
		u.setSafeMoney(u.getSafeMoney().add(pro.getSafeMoney()));
		fuUserDao.save(u);

	}

	@Override
	public Integer countProgram(Map<String, Object> map) {
		return fuProgramDao.countProgram(map);
	}

	@Override
	public List<FuProgram> findProgramList(int i, int pageSize,
			Map<String, Object> map) {
		return fuProgramDao.findProgramList(i, pageSize, map);
	}
	
	@Override
	public Integer countWaitProgram(Map<String, Object> map) {
		return fuProgramDao.countWaitProgram(map);
	}

	@Override
	public List<FuProgram> findWaitProgramList(int i, int pageSize,
			Map<String, Object> map) {
		return fuProgramDao.findWaitProgramList(i, pageSize, map);
	}

	@Override
	public FuProgram findGameProgram(Long userId, Long gameId) {
		return fuProgramDao.findGameProgram(userId, gameId);
	}

	@Override
	public List<FuProgram> findGameOrder(Long gameId, int flag) {
		return fuProgramDao.findGameOrder(gameId, flag);
	}

	@Override
	public Integer countGameProgram(Map<String, Object> map) {
		return fuProgramDao.countGameProgram(map);
	}

	@Override
	public List<FuProgram> findGameProgramList(int i, int pageSize,
			Map<String, Object> map) {
		return fuProgramDao.findGameProgramList(i, pageSize, map);
	}

	@Override
	public List<FuProgram> findOverProgramList(Map<String, Object> map) {
		return fuProgramDao.findOverProgramList(map);
	}

	@Override
	public void saveProgramContinueByAccount(FuProgram pro, FuUser user, BigDecimal money) {
		// 提取利润明细
		moneyDetailUtil.saveNewFuMoneyDetail(user, pro, null, null, 30, money, user.getAccountBalance(), true);
		Calendar c = Calendar.getInstance();
		c.setTime(pro.getCloseTime());
		c.add(Calendar.DATE, 1);
		Date beginTime = c.getTime();

		pro.setManageMoney(pro.getManageMoney().add(money));// 方案总管理费
		pro.setCycleNum(pro.getCycleNum() + 1);// 方案总周期

		Calendar cal = Calendar.getInstance();
		cal.setTime(pro.getCloseTime());
		if (pro.getProgramType() == 2) {// 趋势之王
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
			pro.setCloseTime(cal.getTime());
		} else {
			for (int i = 0; i < 1; i++) {
				cal.add(Calendar.DATE, 1);
				int weekday = cal.get(Calendar.DAY_OF_WEEK);
				while (weekday == 1 || weekday == 7) {
					cal.add(Calendar.DATE, 1);
					weekday = cal.get(Calendar.DAY_OF_WEEK);
				}
			}
			pro.setCloseTime(cal.getTime());
		}

		fuProgramDao.save(pro);

		// 待利息(管理费)支付
		FuManageFee manageFee = new FuManageFee();
		manageFee.setFuProgram(pro);
		manageFee.setFuUser(user);
		manageFee.setMoney(money);
		manageFee.setPayCycle(1);
		manageFee.setPayTime(new Date());
		manageFee.setState(0);// 未审核
		manageFee.setType(1);// 余额支付
		manageFee.setAccountBalance(user.getAccountBalance());
		if (pro.getProgramType() == 1) {
			manageFee.setFeeType("续日配管理费");
		} else {
			manageFee.setFeeType("续月配管理费");
		}
		manageFee.setBeginTime(beginTime);
		manageFee.setEndTime(pro.getCloseTime());

		fuManageFeeDao.save(manageFee);

		// 续交管理费明细
		moneyDetailUtil.saveNewFuMoneyDetail(user, pro, null, null, 36, money, user.getAccountBalance(), false);

		// 产生续约记录
		FuContinueContract continueContract = new FuContinueContract();
		continueContract.setCycle(1);
		continueContract.setFuProgram(pro);
		continueContract.setFuUser(user);
		continueContract.setIntroduction("");
		continueContract.setResult(0);// 0申请，1通过，2取消，3拒绝
		continueContract.setTime(new Date());
		continueContractDao.save(continueContract);
	}

	/**
	 * 保存降级信息
	 */
	@Override
	public void saveDownInfo(FuUser u, FuProgram pro) {
		fuProgramDao.save(pro);
		fuUserDao.save(u);
	}

	/**
	 * 保存升级信息
	 */
	@Override
	public void saveProgramUpInfo(FuProgram program, FuProgramUp programUp) {
		fuProgramDao.save(program);
		fuProgramUpDao.save(programUp);

		FuUser fuUser = program.getFuUser();

		// 个人账户中的数据变化
		fuUser.setAccountTotalMoney(fuUser.getAccountTotalMoney().add(programUp.getTotalMatchMoney()));
		fuUser.setMatchMoney(fuUser.getMatchMoney().add(programUp.getMatchMoney()));
		fuUser.setSafeMoney(fuUser.getSafeMoney().add(programUp.getSafeMoney()));
		fuUser.setFeeTotal(fuUser.getFeeTotal() == null ? programUp.getManageMoney() : (fuUser.getFeeTotal().add(programUp.getManageMoney())));

		// 是否产生配资明细--支出风险保证金
		fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(programUp.getSafeMoney()));
		fuUserDao.save(fuUser);
		moneyDetailUtil.saveNewFuMoneyDetail(fuUser, program, null, null, 28,
				programUp.getSafeMoney(), fuUser.getAccountBalance(), false);
		// 管理费支出明细
		fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(programUp.getManageMoney()));
		fuUserDao.save(fuUser);
		moneyDetailUtil.saveNewFuMoneyDetail(fuUser, program, null, null, 28,
				programUp.getManageMoney(), fuUser.getAccountBalance(), false);

		// 待保证金添加
		FuAddMargin addMargin = new FuAddMargin();
		addMargin.setFuProgram(program);
		addMargin.setFuUser(fuUser);
		addMargin.setMoney(programUp.getSafeMoney());
		addMargin.setRemark("方案升级支付保证金");
		addMargin.setState(0);// 0未处理，1通过，2取消
		addMargin.setTime(new Date());
		addMargin.setType(1);// 余额支付
		fuAddMarginDao.save(addMargin);

		// 待利息（管理费）支付
		FuManageFee manageFee = new FuManageFee();
		manageFee.setFuProgram(program);
		manageFee.setFuUser(fuUser);
		manageFee.setMoney(programUp.getManageMoney());
		manageFee.setPayCycle(programUp.getDayNum());
		manageFee.setPayTime(new Date());
		manageFee.setState(0);// 未审核
		manageFee.setType(1);// 余额支付
		manageFee.setFlag(1);// 方案升级时的管理费明细
		manageFee.setAccountBalance(fuUser.getAccountBalance());
		manageFee.setFeeType("方案升级支付管理费");
		fuManageFeeDao.save(manageFee);
	}

	@Override
	public void saveProgramUp(FuProgram program, FuProgramUp programUp,
			FuUser fuUser) {
		if (fuUser != null)
			fuUserDao.save(fuUser);
		fuProgramDao.save(program);
		fuProgramUpDao.save(programUp);
	}

	@Override
	public Integer countWaitOverProgram(Map<String, Object> map) {
		return fuProgramDao.countWaitOverProgram(map);
	}

	@Override
	public List<FuProgram> findWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map) {
		return fuProgramDao.findWaitOverProgramList(i, pageSize, map);
	}
	
	@Override
	public Integer countOfflineWaitOverProgram(Map<String, Object> map) {
		return fuProgramDao.countOfflineWaitOverProgram(map);
	}

	@Override
	public List<FuProgram> findOfflineWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map) {
		return fuProgramDao.findOfflineWaitOverProgramList(i, pageSize, map);
	}

	@Override
	public Integer countTradeInfo(Map<String, Object> map) {
		return fuProgramDao.countTradeInfo(map);
	}

	@Override
	public List<FuProgram> findTradeInfoList(int i, int pageSize,
			Map<String, Object> map) {
		return fuProgramDao.findTradeInfoList(i, pageSize, map);
	}

	@Override
	public List<FuProgram> findTradeInfoList2(Map<String, Object> map) {
		return fuProgramDao.findTradeInfoList2(map);
	}

	@Override
	public List<FuProgram> findAllTradeInfoList() {
		return fuProgramDao.findAllTradeInfoList();
	}

	@Override
	public Long findCountMatchMoneyByTrade(Integer tradeAccount) {
		return 0l;
	}

	/**
	 * 根据条件查询方案集合
	 */
	@Override
	public List<FuProgram> findProgramByParams(Map<String, Object> map) {
		return fuProgramDao.findProgramByParams(map);
	}
	
	/**
	 * 保存减配方案
	 */
	public FuProgram saveSubProgramAjax(FuRate param,FuProgram primaryPro,BigDecimal safeMoney,BigDecimal managerMoney,BigDecimal matchMoney,int num){
		/**
		 * 创建新的方案
		 */
		FuProgram subProgram=new FuProgram();
		subProgram.setSubMatchId(primaryPro.getId());
		subProgram.setFuUser(primaryPro.getFuUser());
		
		//变动的部分（风控线，实盘资金，风险保证金，总操盘资金，倍率）
		subProgram.setCloseLine(matchMoney.multiply(new BigDecimal(10000)).add(safeMoney.multiply(param.getFlatlinePercent()==null?new BigDecimal(0.00):param.getFlatlinePercent())));//亏损平仓线
		subProgram.setWarnLine(matchMoney.multiply(new BigDecimal(10000)).add(safeMoney.multiply(param.getWarnlinePercent()==null?new BigDecimal(0.00):param.getWarnlinePercent())));//亏损警戒线
		subProgram.setMatchMoney(matchMoney.multiply(new BigDecimal(10000)));//实盘资金
		subProgram.setSafeMoney(safeMoney);
		subProgram.setTotalMatchMoney(matchMoney.multiply(new BigDecimal(10000)).add(safeMoney));//总操盘
		subProgram.setDoubleNum(num);
		
		subProgram.setCycleNum(primaryPro.getCycleNum());
		subProgram.setManageMoney(managerMoney);
		subProgram.setEndManageMoneyTime(primaryPro.getEndManageMoneyTime());
		subProgram.setNextManageMoneyTime(primaryPro.getNextManageMoneyTime());
		subProgram.setMoneyPercent(primaryPro.getMoneyPercent());
		subProgram.setProgramType(primaryPro.getProgramType());
		subProgram.setProgramWay(primaryPro.getProgramWay());//正常交易
		subProgram.setCommisionPercent(primaryPro.getCommisionPercent());
		subProgram.setCreateTime(new Date());
		subProgram.setTradeTime(new Date());
		subProgram.setCloseTime(primaryPro.getCloseTime());
		subProgram.setFuAdmin(fuAdminDao.get(1L));
		subProgram.setCheckTime(new Date());
		subProgram.setFuServer(primaryPro.getFuServer());
		subProgram.setGroupId(primaryPro.getGroupId());
		subProgram.setRoleId(primaryPro.getRoleId());
		subProgram.setAddSafeMoney(primaryPro.getAddSafeMoney());
		subProgram.setComment(primaryPro.getComment());
		subProgram.setIncome(primaryPro.getIncome());
		subProgram.setAgreeTime(new Date());
		subProgram.setAgreeIp(ServletActionContext.getRequest().getRemoteAddr());
		subProgram.setExpenseScore(BigDecimal.ZERO);
		subProgram.setTradePort(primaryPro.getTradePort());
		subProgram.setTradeIp(primaryPro.getTradeIp());
		subProgram.setTradeServiceName(primaryPro.getTradeServiceName());
		List<Object> list = zhongqiService.saveSubProgram(primaryPro.getFuUser(),primaryPro,subProgram,primaryPro.getFuServer());
		if(Integer.valueOf(list.get(0).toString())>0 && Integer.valueOf(list.get(1).toString())>0){
			subProgram.setOpenPaymentId(Integer.valueOf(list.get(0).toString()));
			subProgram.setOpenTriggerId(Integer.valueOf(list.get(1).toString()));
			subProgram.setStatus(1);//审核中
		}else{
			subProgram.setStatus(3);//减配开户插入众期库失败
		}
		if(num<=5){
			subProgram.setOvernightGoodRate(new BigDecimal(Property.getProperty("OverNight_Good_Rate")));
			subProgram.setOvernightStockIndexRate(new BigDecimal(Property.getProperty("OverNight_Stock_Index_Rate")));
		}else{
			subProgram.setOvernightGoodRate(new BigDecimal(Property.getProperty("OverNight_Not")));
			subProgram.setOvernightStockIndexRate(new BigDecimal(Property.getProperty("OverNight_Not")));
		}
		subProgram.setGoodsFee(primaryPro.getGoodsFee());
		subProgram.setStockIndexFee(primaryPro.getStockIndexFee());
		subProgram.setSafeMoneyRate(primaryPro.getSafeMoneyRate());
		fuProgramDao.save(subProgram);
		return subProgram;
	}
	
	/**
	 * 保存期货大赛方案(一期)
	 * @param param
	 * @param fuUser
	 */
	public void saveProgramGame(FuRate param,FuUser fuUser){
		FuServer fuServer=fuServerDao.findServerByUserTypeId(2);
		List<Object> list=zhongqiService.addUserProgramGame(fuUser,fuServer);
		Integer userid=Integer.valueOf(list.get(0).toString());//新增用户的id
		Integer paymentid=Integer.valueOf(list.get(1).toString());//入金的id
		Integer triggerid=Integer.valueOf(list.get(2).toString());//风控规则的id
		Integer userNum=Integer.valueOf(list.get(3).toString());//交易账户
		if(userid>0 && paymentid>0 && triggerid>0 && userNum>0){
			FuGame game=new FuGame();
			game.setFuUser(fuUser);
			game.setGameMoney(new BigDecimal(1000000));
			game.setGameTime(new Date());
			game.setJoinNum((game.getJoinNum()==null?0:game.getJoinNum())+1);
			game.setTradeAccount(userNum);
			game.setIsTrade(0);
			game.setPaymentId(null);
			fuGameDao.save(game);
			
//			Map<String, Object> map1;
//			Map<String, Object> map2;
//			Map<String, Object> map3;
//			while(true) {
//				try {
//					map1=zhongqiService.findResultByInvestorAddId(fuServer, userid);
//					map2=zhongqiService.findResultByInvestorPaymentId(fuServer, paymentid);
//					map3=zhongqiService.findResultByInvestorTriggerId(fuServer, triggerid);
//					if(Integer.valueOf(map1.get("result").toString())>0 && Integer.valueOf(map2.get("result").toString())>0 && Integer.valueOf(map3.get("result").toString())>0){
//						break;
//					}else{
//						Thread.sleep(500);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			if(Integer.valueOf(map1.get("result").toString())>0 && Integer.valueOf(map2.get("result").toString())>0 && Integer.valueOf(map3.get("result").toString())>0){
				//发送短信
				try {
					String msg="您好，模拟期指大赛报名成功。软件类型：众期资管平台；"+fuServer.getServerName()+"；交易账户："+userNum+"，密码是您手机号后6位，请尽快修改密码。";
					String message = URLDecoder.decode(msg,"UTF-8");
					//保存短信信息到数据库日志表
					FuSmsLog log = new FuSmsLog();
					log.setFuUser(fuUser);
					log.setContent(message);
					log.setPrio(1);
					log.setReason("期货大赛报名");
					log.setDestination(fuUser.getPhone());
					log.setPlanTime(new Date());
					log.setType(1);// 短信
					log.setState(0);
					fuSmsLogDao.save(log);
				} catch (Exception e) {
					e.printStackTrace();
				}
//			}else{
//				//删除失败的game记录
//				fuGameDao.delete(game.getId());
//				//发送短信
//				try {
//					String msg="您好，模拟期指大赛报名失败，请重新报名！";
//					String message = URLDecoder.decode(msg,"UTF-8");
//					//保存短信信息到数据库日志表
//					FuSmsLog log = new FuSmsLog();
//					log.setFuUser(fuUser);
//					log.setContent(message);
//					log.setPrio(1);
//					log.setReason("期货大赛报名");
//					log.setDestination(fuUser.getPhone());
//					log.setPlanTime(new Date());
//					log.setType(1);// 短信
//					log.setState(0);
//					fuSmsLogDao.save(log);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
		}else{
			//发送短信
			try {
				String msg="您好，模拟期指大赛报名失败，请重新报名！";
				String message = URLDecoder.decode(msg,"UTF-8");
				//保存短信信息到数据库日志表
				FuSmsLog log = new FuSmsLog();
				log.setFuUser(fuUser);
				log.setContent(message);
				log.setPrio(1);
				log.setReason("期货大赛报名");
				log.setDestination(fuUser.getPhone());
				log.setPlanTime(new Date());
				log.setType(1);// 短信
				log.setState(0);
				fuSmsLogDao.save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 追加保证金的处理结果任务
	 */
	@Override
	public void saveAddMarginTask(FuAddMargin addMargin,FuUser fuUser,FuProgram pro){
		try {
			addMargin.setState(2);
			addMargin.setCheckTime(new Date());
			addMargin.setFuAdmin(fuAdminDao.get(new Long(1)));
			addMargin.setComment("自动审核");
			fuAddMarginDao.save(addMargin);
			
			// 个人账户中的数据变化
			fuUser.setAccountTotalMoney(fuUser.getAccountTotalMoney().add(addMargin.getMoney()));  //账户总资产  + 保证金
			fuUser.setSafeMoney(fuUser.getSafeMoney() == null ? addMargin.getMoney() : (fuUser.getSafeMoney().add(addMargin.getMoney())));  //用户风险保证金总金额+保证金
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(addMargin.getMoney()));  //用户账户余额-保证金
			fuUserDao.save(fuUser);
			//保存明细
			moneyDetailUtil.saveNewFuMoneyDetail(fuUser, pro, null, null, 31, addMargin.getMoney(), fuUser.getAccountBalance(), false);
			//总保证金增加
			pro.setAddSafeMoney(pro.getAddSafeMoney() == null ? addMargin.getMoney() : (pro.getAddSafeMoney().add(addMargin.getMoney())));  //方案保证金+保证金
			fuProgramDao.save(pro);
			
			
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FuSmsLog log = new FuSmsLog();
			String msg="";
			if(pro.getStatus()==7){
				FuServer fuServer = pro.getFuServer();
				BigDecimal balance=zhongqiService.findBalance(fuServer, pro.getTradeAccount());
				BigDecimal matchMoney=pro.getMatchMoney();
				//如果是增配子方案
				if(pro.getAddMatchId()!=null && pro.getAddMatchId()>0){
					FuProgram primayPro=fuProgramDao.get(pro.getAddMatchId());
					matchMoney=matchMoney.add(primayPro.getMatchMoney());
				}
				//如果是主方案，并且下面有有交易中的子方案
				List<FuProgram> list=fuProgramDao.findSonProgramByProgram(pro.getId());
				if(list.size()>0 && (list.get(0).getStatus()==2||list.get(0).getStatus()==7)){
					matchMoney=matchMoney.add(list.get(0).getMatchMoney());
				}
				BigDecimal money=matchMoney.subtract(balance);
				if(money.compareTo(BigDecimal.ZERO)==1){//穿仓补钱不够
					msg="您还需要为方案["+pro.getId()+"]追加保证金"+money+"元，才能解除此方案和个人账户的已穿仓状态。";
					log.setReason("穿仓追加保证金");
				}else{
					msg="您在"+sdf.format(addMargin.getTime())+"申请追加方案["+pro.getId()+"]的保证金成功，追加金额为"+addMargin.getMoney()+"元，此方案和个人账户的已穿仓状态已解除。";
					log.setReason("穿仓追加保证金");
					
					fuUser.setIsAcrossCabin(0);
					fuUserDao.save(fuUser);
					
					pro.setStatus(2);
					fuProgramDao.save(pro);
					//如果是增配子方案
					if(pro.getAddMatchId()!=null && pro.getAddMatchId()>0){
						FuProgram primayPro=fuProgramDao.get(pro.getAddMatchId());
						if(primayPro.getStatus()==7){
							primayPro.setStatus(2);
							fuProgramDao.save(primayPro);
						}
					}
					//如果是主方案，并且下面有有交易中的子方案
					List<FuProgram> list2=fuProgramDao.findSonProgramByProgram(pro.getId());
					if(list2.size()>0 && list2.get(0).getStatus()==7){
						list.get(0).setStatus(2);
						fuProgramDao.save(list.get(0));
					}
				}
			}else{
				msg="您在"+sdf.format(addMargin.getTime())+"申请追加方案["+pro.getId()+"]的保证金成功，追加金额为"+addMargin.getMoney()+"元。";
				log.setReason("追加保证金");
			}
			String message=URLDecoder.decode(msg,"UTF-8");
			//保存短信信息到数据库日志表
			log.setFuUser(pro.getFuUser());
			log.setContent(message);
			log.setPrio(1);
			
			log.setDestination(pro.getFuUser().getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogDao.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 提取利润的处理结果任务
	 */
	@Override
	public void saveDrawProfitsTask(FuDrawProfits profits,FuUser fuUser,FuProgram pro){
		profits.setState(2);
		fuDrawProfitsDao.save(profits);
		
		// 余额添加
		fuUser.setAccountBalance(fuUser.getAccountBalance().add(profits.getMoney()));
		fuUserDao.save(fuUser);
		
		// 提取利润明细
		moneyDetailUtil.saveNewFuMoneyDetail(fuUser, pro, null, null, 29, profits.getMoney(), fuUser.getAccountBalance(), true);
		
		//发送短信
		String msg="您的方案["+pro.getId()+"]提取利润成功，提盈金额为"+profits.getMoney()+"元。";
		String message="";
		try {
			message = URLDecoder.decode(msg,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//保存短信信息到数据库日志表
		FuSmsLog log = new FuSmsLog();
		log.setFuUser(pro.getFuUser());
		log.setContent(message);
		log.setPrio(1);
		log.setReason("提取利润");
		log.setDestination(pro.getFuUser().getPhone());
		log.setPlanTime(new Date());
		log.setType(1);// 短信
		log.setState(0);
		fuSmsLogDao.save(log);
	}
	
	/**
	 * 结算方案成功的处理结果任务
	 */
	@Override
	public void saveCloseProgramTask(FuServer fuServer,FuUser fuUser,FuProgram pro){
		pro.setStatus(5);
		pro.setCloseTime(new Date());
		if(pro.getAdminClear()==null){
			pro.setAdminClear(fuAdminDao.get(1L));
		}
		pro.setClearTime(new Date());
		fuProgramDao.save(pro);
		
		BigDecimal oldMatchMoney;
		BigDecimal oldSafeMoney;
		Long addMatchId=null;
		//如果当前方案有主方案，并且当前方案是提前结算的，那么是通过主方案来结算的，所以主次方案一起结算
		if(pro.getAddMatchId()!=null && pro.getClearTime().getTime()<pro.getCloseTime().getTime()){
			FuProgram primaryPro=fuProgramDao.get(pro.getAddMatchId());//主方案
			primaryPro.setStatus(5);
			primaryPro.setCloseTime(new Date());
			if(primaryPro.getAdminClear()==null){
				primaryPro.setAdminClear(fuAdminDao.get(1L));
			}
			primaryPro.setClearTime(new Date());
			fuProgramDao.save(primaryPro);
			oldMatchMoney=primaryPro.getMatchMoney();
			oldSafeMoney=primaryPro.getSafeMoney();
			addMatchId=primaryPro.getId();
		}else{
			oldMatchMoney=BigDecimal.ZERO;
			oldSafeMoney=BigDecimal.ZERO;
		}
		
		fuServer.setServerMoney(fuServer.getServerMoney().add(pro.getMatchMoney()).add(oldMatchMoney));//服务器金额增加两个方案的实盘资金
		fuServerDao.merge(fuServer);
				
		// 用户余额增加
		// 个人账户中总资产，实盘配资和风险保证金减去
		fuUser.setAccountTotalMoney(fuUser.getAccountTotalMoney().subtract(pro.getMatchMoney()).subtract(pro.getSafeMoney()).subtract(oldMatchMoney).subtract(oldSafeMoney));
		fuUser.setMatchMoney(fuUser.getMatchMoney().subtract(pro.getMatchMoney()).subtract(oldMatchMoney));
		fuUser.setSafeMoney(fuUser.getSafeMoney().subtract(pro.getSafeMoney()).subtract(oldSafeMoney));
		fuUser.setAccountBalance(fuUser.getAccountBalance().add(pro.getIncome()));
		fuUserDao.merge(fuUser);
		
		// 方案结算明细
		moneyDetailUtil.saveNewFuMoneyDetail(fuUser, pro, null, null, 32, pro.getIncome(), fuUser.getAccountBalance(), true);
		
		//发短信
		String message="";
		String str="";
		//如果当前方案有主方案，并且当前方案是提前结算的
		if(pro.getAddMatchId()!=null&&pro.getClearTime().getTime()<pro.getCloseTime().getTime()){
			str="方案["+addMatchId+"]和增配子";
		}
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			message = URLDecoder.decode("您的"+str+"方案["+pro.getId()+"]结算成功，交易账户出金金额为"+pro.getIncome()+",出金到账时间为："+sdf.format(new Date())+"。","UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FuSmsLog log = new FuSmsLog();
		log.setFuUser(pro.getFuUser());
		log.setContent(message);
		log.setPrio(1);
		log.setReason("方案结算");
		log.setDestination(pro.getFuUser().getPhone());
		log.setPlanTime(new Date());
		log.setType(1);// 短信
		log.setState(0);
		fuSmsLogDao.merge(log);
	}
	
	/**
	 * 后台同意(主方案/增配子方案)开户的处理结果任务
	 */
	@Override
	public void saveCheckProgramTask(FuUser fuUser,FuProgram program){
		try {
			/**
			 * 方案部分
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			program.setEndManageMoneyTime(sdf.parse(sdf.format(cal.getTime())));// 最后一次交管理费的时间
			//下次付息时间
			Calendar cale = Calendar.getInstance();
			if(program.getProgramType()==1){//日配
				cale.setTime(program.getEndManageMoneyTime());
				cale.set(Calendar.DATE, cale.get(Calendar.DATE)+1);
			}else{//月配
				cale.setTime(program.getEndManageMoneyTime());
				cale.set(Calendar.MONTH, cale.get(Calendar.MONTH)+1);
			}
			program.setNextManageMoneyTime(sdf.parse(sdf.format(cale.getTime())));// 下一次交管理费的时间
			program.setStatus(2);
			fuProgramDao.save(program);
			
			/**
			 * 保证金添加
			 */
			FuAddMargin addMargin = new FuAddMargin();
			addMargin.setFuProgram(program);
			addMargin.setFuUser(fuUser);
			addMargin.setMoney(program.getSafeMoney());
			if (program.getProgramType() == 1) {
				addMargin.setRemark("新日配保证金");
			} else {
				addMargin.setRemark("新月配保证金");
			}
			addMargin.setState(2);// 0待审核1审核中2同意3拒绝
			addMargin.setTime(new Date());
			addMargin.setType(1);// 余额支付
			addMargin.setCheckTime(new Date());
			addMargin.setFuAdmin(program.getFuAdmin());
			addMargin.setComment("开户通过自动审核");
			fuAddMarginDao.save(addMargin);
			
			/**
			 * 管理费部分
			 */
			FuManageFee manageFee = new FuManageFee();
			manageFee.setFuProgram(program);
			manageFee.setFuUser(fuUser);
			manageFee.setCheckTime(new Date());
			manageFee.setFuAdmin(program.getFuAdmin());
			manageFee.setComment("开户通过自动审核");
			manageFee.setMoney(program.getManageMoney());
			manageFee.setPayCycle(program.getCycleNum());
			manageFee.setPayTime(new Date());
			manageFee.setState(2);// 0待审核1审核中2同意3拒绝
			manageFee.setType(1);// 余额支付
			manageFee.setAccountBalance(fuUser.getAccountBalance());
			manageFee.setPayTime(new Date());
			manageFee.setBeginTime(new Date());
			if (program.getProgramType() == 1) {
				manageFee.setFeeType("新日配管理费");
				manageFee.setEndTime(new Date());
			} else {
				manageFee.setFeeType("新月配管理费");
				//服务周期结束时间=开始时间一个月后的前一天
				Calendar calen = Calendar.getInstance();
				calen.setTime(program.getTradeTime());
				calen.add(Calendar.MONTH, 1);
				calen.add(Calendar.DAY_OF_MONTH, -1);
				manageFee.setEndTime(sdf.parse(sdf.format(calen.getTime())));
			}
			fuManageFeeDao.save(manageFee);
			
			/**
			 * 个人账户
			 */
			fuUser.setAccountTotalMoney(fuUser.getAccountTotalMoney().add(program.getMatchMoney()).add(program.getSafeMoney()));
			fuUser.setMatchMoney(fuUser.getMatchMoney().add(program.getMatchMoney()));
			fuUser.setSafeMoney(fuUser.getSafeMoney().add(program.getSafeMoney()));
			fuUser.setFeeTotal(fuUser.getFeeTotal() == null ? program.getManageMoney() : (fuUser.getFeeTotal().add(program.getManageMoney())));
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(program.getSafeMoney()));//余额扣除风险保证金
			fuUserDao.save(fuUser);
			/**
			 * 明细部分
			 */
			moneyDetailUtil.saveNewFuMoneyDetail(fuUser, program, null, null, 28, program.getSafeMoney(), fuUser.getAccountBalance(), false);
			
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(program.getManageMoney()));//余额扣除管理费
			fuUserDao.save(fuUser);
			moneyDetailUtil.saveNewFuMoneyDetail(fuUser, program, null, null, 34, program.getManageMoney(), fuUser.getAccountBalance(), false);
			
			/**
			 * 合伙人返利
			 */
			FuUser parentUser = fuUserDao.findFuUserById(program.getFuUser().getHhrParentID());
			if(parentUser != null){
				if(parentUser.getHhrType()!=null && parentUser.getHhrType() == 0){
					hhrStatService.updateHhrIncome(parentUser.getId(), program.getMatchMoney(), 3, program.getId());
				}
			}
			/**
			 * 发送短信
			 */
			String msg="";
			if(program.getFuServer().getClientType()==1){
				msg="您的账户已开通。软件类型：众期资管平台；"+program.getFuServer().getServerName()+"；交易账户："+program.getTradeAccount()+"，密码是您手机号后6位，请尽快修改密码。";
			}
			if(program.getFuServer().getClientType()==2){
				msg="您的账户已开通。软件类型：博易大师－鑫管家版；交易账户："+program.getTradeAccount()+"，密码是您手机号后6位，请尽快修改密码。";
			}
			if(program.getFuServer().getClientType()==3){
				msg="您的账户已开通。软件类型：博易大师－金牛版；交易账户："+program.getTradeAccount()+"，密码是您手机号后6位，请尽快修改密码。";
			}
			String message = URLDecoder.decode(msg,"UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(program.getFuUser());
			log.setContent(message);
			log.setPrio(1);
			log.setReason("告知开户成功");
			log.setDestination(program.getFuUser().getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogDao.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 减配的处理结果任务
	 */
	@Override
	public void saveCheckSubProgramTask(FuUser fuUser,FuProgram primaryPro,FuProgram subProgram){
		primaryPro.setStatus(5);//原方案交易结束
		fuProgramDao.save(primaryPro);
		
		subProgram.setStatus(2);//减配新方案开始交易
		subProgram.setTradeAccount(primaryPro.getTradeAccount());
		subProgram.setTradePassword(primaryPro.getFuUser().getPhone().substring(5, 11));
		fuProgramDao.save(subProgram);
		
		/**
		 * 发送短信
		 */
		String message="";
		try {
			message = URLDecoder.decode("您的方案["+primaryPro.getId()+"]减配成功。","UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FuSmsLog log = new FuSmsLog();
		log.setFuUser(subProgram.getFuUser());
		log.setContent(message);
		log.setPrio(1);
		log.setReason("减配开户成功");
		log.setDestination(subProgram.getFuUser().getPhone());
		log.setPlanTime(new Date());
		log.setType(1);// 短信
		log.setState(0);
		fuSmsLogDao.save(log);
	}
	
	/**
	 * 保存已穿仓的方案
	 * @param pro
	 */
	public void saveAcrossCabin(FuProgram pro,BigDecimal money){
		try {
			FuUser fuUser=pro.getFuUser();
			fuUser.setIsAcrossCabin(1);
			fuUserDao.save(fuUser);
			pro.setStatus(7);
			fuProgramDao.save(pro);
			if(pro.getAddMatchId()!=null && pro.getAddMatchId()>0){
				FuProgram primayPro=fuProgramDao.get(pro.getAddMatchId());
				primayPro.setStatus(7);
				fuProgramDao.save(primayPro);
			}
			
			
			/**
			 * 发送短信
			 */
			String message = URLDecoder.decode("您的方案["+pro.getId()+"]已穿仓，请立即追加保证金"+money+"元到此方案，否则会影响您的正常使用。","UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(fuUser);
			log.setContent(message);
			log.setPrio(1);
			log.setReason("方案穿仓通知");
			log.setDestination(fuUser.getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogDao.save(log);
			
			/**
			 * 不良记录
			 */
			FuBadCredit badCredit=new FuBadCredit();
			badCredit.setFuUser(fuUser);
			badCredit.setFuProgram(pro);
			badCredit.setMoney(money);
			badCredit.setType(1);
			badCredit.setDetail(null);
			badCredit.setTime(new Date());
			badCredit.setState(0);
			fuBadCreditDao.save(badCredit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FuProgram> findProgramsByDate(String queryDate) {
		return fuProgramDao.findProgramsByDate(queryDate);
	}

	@Override
	public List<FuProgram> findToTradeByUser(Long userId) {
		return fuProgramDao.findToTradeByUser(userId);
	}

	public void saveGame(FuRate param, FuUser user, Integer competitionNum) {
		FuServer fuServer = fuServerDao.findServerByUserTypeId(2);
		/*
		 * List<Object> list=zhongqiService.addUserProgramGame(user,fuServer);
		 * Integer userid=Integer.valueOf(list.get(0).toString());//新增用户的id
		 * Integer paymentid=Integer.valueOf(list.get(1).toString());//入金的id
		 * Integer triggerid=Integer.valueOf(list.get(2).toString());//风控规则的id
		 * Integer userNum=Integer.valueOf(list.get(3).toString());//交易账户
		 * if(userid>0 && paymentid>0 && triggerid>0 && userNum>0){
		 */
		// 查询 771开头 最大的userName
		FuGame fuGame = fuGameDao.findMaxByGameNum(771, competitionNum);
		Integer userNum = fuGame.getTradeAccount()+1;
		FuGame game = new FuGame();
		game.setFuUser(user);
		game.setGameMoney(new BigDecimal(1000000));
		game.setGameTime(new Date());
		game.setJoinNum((game.getJoinNum() == null ? 0 : game.getJoinNum()) + 1);
		game.setTradeAccount(userNum);
		game.setIsTrade(0);
		game.setPaymentId(null);
		game.setCompetitionNum(competitionNum);
		fuGameDao.save(game);

		// Map<String, Object> map1;
		// Map<String, Object> map2;
		// Map<String, Object> map3;
		// while(true) {
		// try {
		// map1=zhongqiService.findResultByInvestorAddId(fuServer, userid);
		// map2=zhongqiService.findResultByInvestorPaymentId(fuServer,
		// paymentid);
		// map3=zhongqiService.findResultByInvestorTriggerId(fuServer,
		// triggerid);
		// if(Integer.valueOf(map1.get("result").toString())>0 &&
		// Integer.valueOf(map2.get("result").toString())>0 &&
		// Integer.valueOf(map3.get("result").toString())>0){
		// break;
		// }else{
		// Thread.sleep(500);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// if(Integer.valueOf(map1.get("result").toString())>0 &&
		// Integer.valueOf(map2.get("result").toString())>0 &&
		// Integer.valueOf(map3.get("result").toString())>0){
		// 发送短信
		try {
			String msg = "您好，您已成功报名模拟期指大赛。登陆超级合伙人官方网站，下载超级合伙人PC端参加比赛。" +
					"交易账户："+userNum+",初始密码是您手机号的后6位。";
			/*String msg = "您好，模拟期指大赛报名成功。软件类型：众期资管平台；"
					+ fuServer.getServerName() + "；交易账户：" + userNum
					+ "，密码是您手机号后6位，请尽快修改密码。";*/
			String message = URLDecoder.decode(msg, "UTF-8");
			// 保存短信信息到数据库日志表
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(user);
			log.setContent(message);
			log.setPrio(1);
			log.setReason("期货大赛报名");
			log.setDestination(user.getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogDao.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }else{
		// //删除失败的game记录
		// fuGameDao.delete(game.getId());
		// //发送短信
		// try {
		// String msg="您好，模拟期指大赛报名失败，请重新报名！";
		// String message = URLDecoder.decode(msg,"UTF-8");
		// //保存短信信息到数据库日志表
		// FuSmsLog log = new FuSmsLog();
		// log.setFuUser(fuUser);
		// log.setContent(message);
		// log.setPrio(1);
		// log.setReason("期货大赛报名");
		// log.setDestination(fuUser.getPhone());
		// log.setPlanTime(new Date());
		// log.setType(1);// 短信
		// log.setState(0);
		// fuSmsLogDao.save(log);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		/*
		 * }else{ //发送短信 try { String msg="您好，模拟期指大赛报名失败，请重新报名！"; String message
		 * = URLDecoder.decode(msg,"UTF-8"); //保存短信信息到数据库日志表 FuSmsLog log = new
		 * FuSmsLog(); log.setFuUser(user); log.setContent(message);
		 * log.setPrio(1); log.setReason("期货大赛报名");
		 * log.setDestination(user.getPhone()); log.setPlanTime(new Date());
		 * log.setType(1);// 短信 log.setState(0); fuSmsLogDao.save(log); } catch
		 * (Exception e) { e.printStackTrace(); } }
		 */
	}
}
