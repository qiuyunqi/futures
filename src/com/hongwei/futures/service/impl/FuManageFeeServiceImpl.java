package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongwei.futures.dao.FuManageFeeDao;
import com.hongwei.futures.dao.FuProgramDao;
import com.hongwei.futures.dao.FuSmsLogDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuManageFee;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuManageFeeService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuManageFeeServiceImpl implements FuManageFeeService {
	@Autowired
	private FuManageFeeDao fuManageFeeDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private FuProgramDao fuProgramDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	@Autowired
	private HhrStatService hhrStatService;
	@Autowired
	private FuSmsLogDao fuSmsLogDao;

	// ====================== 基本 C R U D 方法 ===========================
	@Override
	public FuManageFee get(Long id) {
		return fuManageFeeDao.get(id);
	}

	@Override
	public void save(FuManageFee entity) {
		fuManageFeeDao.save(entity);
	}

	@Override
	public void delete(Long id) {
		fuManageFeeDao.delete(id);
	}

	@Override
	public Integer countFee(HashMap<String, Object> map) {
		return fuManageFeeDao.countFee(map);
	}

	@Override
	public List<FuManageFee> findFeeList(int i, int pageSize, HashMap<String, Object> map) {
		return fuManageFeeDao.findFeeList(i, pageSize, map);
	}

	@Override
	public List<FuManageFee> findFeeListByProgramId(Long id) {
		return fuManageFeeDao.findFeeListByProgramId(id);
	}
	
	/**
	 * 审核通过续交的管理费
	 */
	@Override
	public void saveManageMoney(FuManageFee fee) {
		try {
			//方案部分
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			FuProgram program = fee.getFuProgram();
			Calendar cal = Calendar.getInstance();
			program.setManageMoney(program.getManageMoney().add(fee.getMoney()));// 方案总管理费
			program.setEndManageMoneyTime(sdf.parse(sdf.format(cal.getTime())));// 最后一次交管理费的时间
			//下次付息时间
			Calendar cale = Calendar.getInstance();
			cale.setTime(program.getEndManageMoneyTime());
			if(program.getProgramType()==1){//日配
				cale.add(Calendar.DAY_OF_MONTH, 1);
			}else{//月配
				cale.add(Calendar.MONTH, 1);
			}
			program.setNextManageMoneyTime(sdf.parse(sdf.format(cale.getTime())));// 下一次交管理费的时间
			fuProgramDao.save(program);
			
			
			// 个人账户中的数据变化
			FuUser fuUser = fee.getFuUser();
			fuUser.setFeeTotal(fuUser.getFeeTotal() == null ? program.getManageMoney() : (fuUser.getFeeTotal().add(program.getManageMoney())));  //用户管理费总额+管理费
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(fee.getMoney())); // 账户账户余额-管理费
			fuUserDao.save(fuUser);

			
			//用户出入金明细
			// 续交的管理费
			moneyDetailUtil.saveNewFuMoneyDetail(fuUser, fee.getFuProgram(), null, null, 34, fee.getMoney(), fuUser.getAccountBalance(), false);
			
			
			//管理费部分
			fee.setPayTime(new Date());
			fuManageFeeDao.save(fee);
			
			/**
			 * 合伙人返利
			 */
			FuUser parentUser = fuUserDao.findFuUserById(program.getFuUser().getHhrParentID());
			if(parentUser != null){
				hhrStatService.updateHhrIncome(parentUser.getId(), program.getProgramType()==1?(program.getMatchMoney().divide(new BigDecimal(30)).setScale(2, BigDecimal.ROUND_HALF_UP)):program.getMatchMoney(), 4, program.getId());
			}
			
			//发送短信
			String message = URLDecoder.decode("您的账户扣除方案["+program.getId() +"]的管理费审核通过，管理费金额为"+fee.getMoney()+"元。","UTF-8");
			//保存短信信息到数据库日志表
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(fee.getFuUser());
			log.setContent(message);
			log.setPrio(1);
			log.setReason("扣除管理费");
			log.setDestination(fee.getFuUser().getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogDao.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 审核通过续约的管理费
	 */
	@Override
	public void saveContinueManageMoney(FuManageFee fee) {
		try {
			//方案部分
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			FuProgram program = fee.getFuProgram();
			Calendar cal = Calendar.getInstance();
			cal.setTime(program.getCloseTime());
			if (program.getProgramType() == 2) {// 趋势之王
				cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + fee.getPayCycle());
				program.setCloseTime(cal.getTime());
			} else {
				for (int i = 0; i < fee.getPayCycle(); i++) {
					cal.add(Calendar.DATE, 1);
					int weekday = cal.get(Calendar.DAY_OF_WEEK);
					while (weekday == 1 || weekday == 7) {
						cal.add(Calendar.DATE, 1);
						weekday = cal.get(Calendar.DAY_OF_WEEK);
					}
				}
				program.setCloseTime(cal.getTime());
			}
			program.setManageMoney(program.getManageMoney().add(fee.getMoney()));// 方案总管理费
			program.setCycleNum(program.getCycleNum() + 1);// 方案总周期
			// 审核之后才得到真正的开始交易时间和交管理费的时间
			program.setTradeTime(sdf.parse(sdf.format(new Date())));// 开始交易的时间
			program.setEndManageMoneyTime(sdf.parse(sdf.format(cal.getTime())));// 最后一次交管理费的时间
			//下次付息时间
			Calendar cale = Calendar.getInstance();
			cale.setTime(program.getEndManageMoneyTime());
			if(program.getProgramType()==1){//日配
				cale.add(Calendar.DAY_OF_MONTH, 1);
			}else{//月配
				cale.add(Calendar.MONTH, 1);
			}
			program.setNextManageMoneyTime(sdf.parse(sdf.format(cale.getTime())));// 下一次交管理费的时间
			fuProgramDao.save(program);
			
			
			// 个人账户中的数据变化
			FuUser fuUser = fee.getFuUser();
			fuUser.setFeeTotal(fuUser.getFeeTotal() == null ? program.getManageMoney() : (fuUser.getFeeTotal().add(program.getManageMoney())));  //用户管理费总额+管理费
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(fee.getMoney())); // 账户账户余额-管理费
			fuUserDao.save(fuUser);
			
			//用户出入金明细
			//续约的管理费
			moneyDetailUtil.saveNewFuMoneyDetail(fuUser, fee.getFuProgram(), null, null, 36, fee.getMoney(), fuUser.getAccountBalance(), false);
			
			//管理费部分
			fee.setPayTime(new Date());
			fuManageFeeDao.save(fee);
			
			//续约管理费发钱
			FuUser parentUser = fuUserDao.findFuUserById(program.getFuUser().getHhrParentID());
			if(parentUser != null){
				if(parentUser.getHhrType()!=null && parentUser.getHhrType() == 0){  //表示终端用户, 渠道用户不参与发钱
					hhrStatService.updateHhrIncome(parentUser.getId(), program.getProgramType()==1?(program.getMatchMoney().divide(new BigDecimal(30)).setScale(2, BigDecimal.ROUND_HALF_UP)):program.getMatchMoney(), 4, program.getId());
				}
			}
			
			//发送短信
			String message = URLDecoder.decode("您的账户扣除方案["+program.getId() +"]的管理费审核通过，管理费金额为"+fee.getMoney()+"元。","UTF-8");
			//保存短信信息到数据库日志表
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(fee.getFuUser());
			log.setContent(message);
			log.setPrio(1);
			log.setReason("扣除管理费");
			log.setDestination(fee.getFuUser().getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogDao.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
