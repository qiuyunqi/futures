package com.hongwei.futures.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuDrawProfitsService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.zhongqi.ZhongqiService;

public class DrawProfitsUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private ZhongqiService zhongqiService;
	@Autowired
	private FuDrawProfitsService fuDrawProfitsService;

	private Object lock = new Object();
	private volatile boolean isRunning = false;
	/**
	 * 检查方案是否已经结束(20:50)
	 */
	@Override
	public void run() {
		System.out.println("提盈任务开始"+new Date());
		drawProfits();
		System.out.println("提盈任务结束"+new Date());
	}
	
	/**
	 * 提取利润的处理结果
	 */
	public void drawProfits(){
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("state", 1);
			List<FuDrawProfits> list=fuDrawProfitsService.findDrawList(0, Integer.MAX_VALUE, map);//审核中的提取利润记录
			for (int i=0;i<list.size();i++) {
				FuDrawProfits profits=list.get(i);
				FuProgram pro = fuProgramService.get(profits.getFuProgram().getId());
				FuUser fuUser = pro.getFuUser();
				FuServer fuServer = pro.getFuServer();
				map=zhongqiService.findResultByInvestorPaymentId(fuServer, profits.getPaymentId());
				if(map.containsKey("result")&&Integer.valueOf(map.get("result").toString())>0){
					if(map.get("result").toString().equals("1")){
						fuProgramService.saveDrawProfitsTask(profits, fuUser, pro);
						System.out.println("提取利润成功");
					}else{
						profits.setState(3);
						fuDrawProfitsService.save(profits);
						System.out.println("提取利润失败");
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