package com.hongwei.futures.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuAddMargin;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAddMarginService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.zhongqi.ZhongqiService;

public class AddMarginUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private ZhongqiService zhongqiService;
	@Autowired
	private FuAddMarginService fuAddMarginService;
	
	private Object lock = new Object();
	private volatile boolean isRunning = false;

	/**
	 * 检查方案是否已经结束(20:50)
	 */
	@Override
	public void run() {
		System.out.println("追加保证金任务开始"+new Date());
		addMargin();
		System.out.println("追加保证金任务结束"+new Date());
	}
	
	/**
	 * 追加保证金的处理结果
	 */
	public void addMargin(){
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("state", 1);
			List<FuAddMargin> list=fuAddMarginService.findSafeMoneyList(0, Integer.MAX_VALUE, map);//审核中追加保证金的记录
			for (int i=0;i<list.size();i++) {
				FuAddMargin addMargin=list.get(i);
				FuProgram pro = fuProgramService.get(addMargin.getFuProgram().getId());
				FuUser fuUser = pro.getFuUser();
				FuServer fuServer = pro.getFuServer();
				map=zhongqiService.findResultByInvestorPaymentId(fuServer, addMargin.getPaymentId());
				if(map.containsKey("result")&&Integer.valueOf(map.get("result").toString())>0){
					if(map.get("result").toString().equals("1")){
						fuProgramService.saveAddMarginTask(addMargin, fuUser, pro);
						System.out.println("追加保证金成功");
					}else{
						addMargin.setState(3);
						addMargin.setCheckTime(null);
						addMargin.setFuAdmin(null);
						addMargin.setComment(null);
						fuAddMarginService.save(addMargin);
						System.out.println("追加保证金失败");
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