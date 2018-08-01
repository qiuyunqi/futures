package com.hongwei.futures.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.zhongqi.ZhongqiService;

public class AcrossCabinUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private ZhongqiService zhongqiService;
	
	private Object lock = new Object();
	private volatile boolean isRunning = false;

	/**
	 * 
	 */
	@Override
	public void run() {
		System.out.println("查询方案是否穿仓任务开始"+new Date());
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			List<FuProgram> list=fuProgramService.findProgramIsAcrossCabin();//状态为2的方案
			for (int i=0;i<list.size();i++) {
				FuProgram pro = list.get(i);
				FuServer fuServer = pro.getFuServer();
				BigDecimal balance=zhongqiService.findBalance(fuServer, pro.getTradeAccount());//动态权益
				BigDecimal matchMoney=pro.getMatchMoney();//方案实盘资金
				if(pro.getAddMatchId()!=null && pro.getAddMatchId()>0){
					FuProgram primayPro=fuProgramService.get(pro.getAddMatchId());
					matchMoney=matchMoney.add(primayPro.getMatchMoney());
				}
				if(matchMoney.compareTo(balance)==1){
					fuProgramService.saveAcrossCabin(pro,matchMoney.subtract(balance));
				}
			}
			isRunning = false;
		}
		System.out.println("查询方案是否穿仓任务结束"+new Date());
	}
	
	
	public static void main(String args[]) {
		Long value = 1000283799l + 108000000l;
		System.out.println(value);
	}

}