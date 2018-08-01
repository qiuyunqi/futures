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

public class DayMoneyUtil extends TimerTask {
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
			map.put("programType", "1");// 日配
			List<FuProgram> programList = fuProgramService.findAllTradeProgram(map);// 所以正在交易的日配方案
			for (int i = 0; programList != null && i < programList.size(); i++) {
				FuProgram pro = programList.get(i);
				FuUser fuUser = fuUserService.get(pro.getFuUser().getId());
				// 即将到期的方案提醒
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
				// 日配方案已经结束
				if (pro.getCloseTime().getTime() <= calendar.getTime().getTime()) {
					//日配到期续约申请,暂时停用
					BigDecimal manageMoney = pro.getMatchMoney().divide(new BigDecimal(10000)).multiply(pro.getMoneyPercent());
					fuProgramService.saveProgramAutoContinue(pro, 1, manageMoney, "方案到期续约");// 封装操作
					System.out.println("-----已到期的日配方案" + pro.getId() + "自动续约------" + new Date());
					
	//				String result=zhongqiService.programStop(pro.getFuServer(),pro);
	//				System.out.println("----到期日配方案"+pro.getId()+result+"----");
				} 
				
				
				
				
				//日配方案未到期
				else {
					String nowdate = sdf.format(new Date());
					String nextManageMoneyTime = sdf.format(pro.getNextManageMoneyTime());
					if (!nowdate.equals(nextManageMoneyTime)) {
						System.out.println("-----未到期的日配方案"+pro.getId()+"----当前时间不等于下次交管理费的时间");				
					}else{
						BigDecimal manageMoney = pro.getMatchMoney().divide(new BigDecimal(10000)).multiply(pro.getMoneyPercent());
						fuProgramService.saveDayManageMoney(pro, 1, manageMoney);// 封装操作
						System.out.println("-----未到期的日配方案" + pro.getId() + "续交管理费------" + new Date());
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