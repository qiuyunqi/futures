package com.hongwei.futures.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.service.FuProgramService;

public class ClearSonProgramUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	
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
			System.out.println("结算到期增配子方案任务开始"+new Date());
			//用来获取当前时间的日历对象
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			List<FuProgram> programList = fuProgramService.findAllTradeSonProgram();// 所以正在交易的增配子方案
			for (int i = 0; programList != null && i < programList.size(); i++) {
				FuProgram pro = programList.get(i);
				// 方案已经结束
				if (calendar.getTime().getTime() > pro.getCloseTime().getTime()) {
					String result=fuProgramService.saveOverSonProgram(pro);
					System.out.println("----结算到期增配子方案"+pro.getId()+result+"----");
				} 
			}
			System.out.println("结算到期增配子方案任务结束"+new Date());
			isRunning = false;
		}
	}

	public static void main(String args[]) {
		Long value = 1000283799l + 108000000l;
		System.out.println(value);
	}

}