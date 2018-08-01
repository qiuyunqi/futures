package com.hongwei.futures.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuProgramService;

public class CloseProgramUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuParameterService fuParameterService;
	
	private Object lock = new Object();
	private volatile boolean isRunning = false;

	/**
	 * 检查待审核方案是否已经过期
	 */
	@Override
	public void run() {
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			System.out.println("关闭方案任务开始"+new Date());
			FuParameter param = fuParameterService.findParameter();
			//用来获取当前时间的日历对象
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			// 所有待审核的方案
			List<FuProgram> programList = fuProgramService.findAllDueProgram();
			
			for (int i = 0; programList != null && i < programList.size(); i++) {
				FuProgram pro = programList.get(i);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(pro.getCreateTime());
				cal.add(Calendar.HOUR_OF_DAY, param.getProgramDueTime());
				if (calendar.getTime().getTime()>cal.getTime().getTime()) {//当前时间>方案创建时间加上系统设置的过期时间   
					pro.setStatus(-1);//已关闭
					fuProgramService.save(pro);
					System.out.println("-----方案" + pro.getId() + "过期被关闭------" + new Date());
				}
			}
			System.out.println("关闭方案任务结束"+new Date());
			isRunning = false;
		}
	}
}