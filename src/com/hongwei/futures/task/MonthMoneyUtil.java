package com.hongwei.futures.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;

public class MonthMoneyUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	private Object lock = new Object();
	private volatile boolean isRunning = false;
	
	/**
	 * 检查方案是否已经结束(20:50)
	 */
	@Override
	public void run() {
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			// 所有的交易日期都只给 yyyy-MM-dd
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//用来获取当前时间的日历对象
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
	
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("programType", "2");// 月配
			List<FuProgram> programList = fuProgramService.findAllTradeProgram(map);// 所以正在交易的月配方案
			for (int i = 0; programList != null && i < programList.size(); i++) {
				FuProgram pro = programList.get(i);
				FuUser fuUser = fuUserService.get(pro.getFuUser().getId());
				// 即将到期的月配方案短信提醒
				Calendar cal = Calendar.getInstance();
				cal.setTime(pro.getCloseTime());
				cal.add(Calendar.DAY_OF_MONTH, -1);
				if (cal.getTime().getTime() == calendar.getTime().getTime()) {// 结束交易的时间-1等于当前时间
					// 发送到期提醒的短信
					String message = "您的方案[" + pro.getId() + "]即将在一天后到期。";
					// 保存短信信息到数据库日志表
					FuSmsLog log = new FuSmsLog();
					log.setFuUser(fuUser);
					log.setContent(message);
					log.setPrio(1);
					log.setReason("方案到期提醒");
					log.setDestination(fuUser.getPhone());
					log.setPlanTime(new Date());
					log.setType(1);// 短信
					log.setState(0);
					fuSmsLogService.save(log);
				}
	
				// 月配方案已经结束
				if (pro.getCloseTime().getTime() <= calendar.getTime().getTime()) {
					//月配到期续约申请,暂时停用
					BigDecimal manageMoney = pro.getMatchMoney().multiply(pro.getMoneyPercent());
					fuProgramService.saveProgramAutoContinue(pro, 1, manageMoney, "方案到期续约");// 封装操作
					System.out.println("-----已到期的月配方案" + pro.getId() + "自动续约------" + new Date());
					
	//				String result=zhongqiService.programStop(pro.getFuServer(),pro);
	//				System.out.println("----到期月配方案"+pro.getId()+result+"----");
				} 
				
				
				
				//月配方案未到期
				else {
					// 月配管理费即将到期的短信提醒
					Calendar calendar1 = Calendar.getInstance();
					calendar1.setTime(pro.getNextManageMoneyTime());
					calendar1.add(Calendar.DAY_OF_MONTH, -1);
					calendar1.getTime();// 得到下一次交管理费的前一天
					
					Calendar calendar2 = Calendar.getInstance();
					calendar2.setTime(pro.getNextManageMoneyTime());
					calendar2.add(Calendar.DAY_OF_MONTH, -3);
					calendar2.getTime();// 得到下一次交管理费的前三天
	
					if (calendar1.getTime().getTime() == calendar.getTime().getTime()) {//提前一天
						// 发送到期提醒的短信
						String message = "您的方案[" + pro.getId() + "]当月缴纳管理费用将在一天后到期，请及时查询您的账户金额确保账户余额（非交易账户资金）充足，在方案周期内，金额充足系统会帮您自动续交下月管理费，如果网站余额不足我们将会对您的交易账户进行禁止操作处理，感谢您的配合。";
						// 保存短信信息到数据库日志表
						FuSmsLog log = new FuSmsLog();
						log.setFuUser(fuUser);
						log.setContent(message);
						log.setPrio(1);
						log.setReason("管理费到期提醒");
						log.setDestination(fuUser.getPhone());
						log.setPlanTime(new Date());
						log.setType(1);// 短信
						log.setState(0);
						fuSmsLogService.save(log);
					}
					
					if (calendar2.getTime().getTime() == calendar.getTime().getTime()) {//提前三天
						// 发送到期提醒的短信
						String message = "您的方案[" + pro.getId() + "]当月缴纳管理费用将在三天后到期，请及时查询您的账户金额确保账户余额（非交易账户资金）充足，在方案周期内，金额充足系统会帮您自动续交下月管理费，如果网站余额不足我们将会对您的交易账户进行禁止操作处理，感谢您的配合。";
						// 保存短信信息到数据库日志表
						FuSmsLog log = new FuSmsLog();
						log.setFuUser(fuUser);
						log.setContent(message);
						log.setPrio(1);
						log.setReason("管理费到期提醒");
						log.setDestination(fuUser.getPhone());
						log.setPlanTime(new Date());
						log.setType(1);// 短信
						log.setState(0);
						fuSmsLogService.save(log);
					}
					
					
					
					// 月配续交管理费
					String nowdate = sdf.format(new Date());
					String nextManageMoneyTime = sdf.format(pro.getNextManageMoneyTime());
					if (!nowdate.equals(nextManageMoneyTime)) {
						System.out.println("-----未到期的月配方案" + pro.getId() + "----当前时间不等于下次交管理费的时间");				
					}else{
						// 在结束交易之前        并且当前时间等于下次交管理费时间
						//续交管理费申请
						BigDecimal manageMoney = pro.getMatchMoney().multiply(pro.getMoneyPercent());
						fuProgramService.saveMonthManageMoney(pro, 1, manageMoney);// 封装操作
						System.out.println("-----未到期的月配方案" + pro.getId() + "续交管理费------" + new Date());
					}
				}
			}
			isRunning = false;
		}
	}

	public static void main(String args[]) {
		Long value = 1000283799l + 108000000l;
		System.out.println(value);
	}

}