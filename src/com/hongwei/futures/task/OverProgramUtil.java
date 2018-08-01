package com.hongwei.futures.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.zhongqi.ZhongqiService;

public class OverProgramUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private ZhongqiService zhongqiService;
	
	private Object lock = new Object();
	private volatile boolean isRunning = false;

	/**
	 * 检查方案是否已经结束(20:50)
	 */
	@Override
	public void run() {
		System.out.println("结算方案任务开始"+new Date());
		closeProgram();
		System.out.println("结算方案任务结束"+new Date());
	}
	/**
	 * 结算的处理结果
	 */
	public void closeProgram(){
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("status", 6);
			List<FuProgram> list=fuProgramService.findProgramByParams(map);//结算中的方案
			for (int i=0;i<list.size();i++) {
				FuProgram pro=list.get(i);
				String result="清算成功";
				if(pro.getCloseMatchId()==null||pro.getCloseMatchId()<=0||pro.getCloseBlanceId()==null||pro.getCloseBlanceId()<=0){
					result=fuProgramService.saveOverProgramByTask(pro);
				}
				if(result.equals("清算成功")){
					FuUser fuUser = pro.getFuUser();
					FuServer fuServer = pro.getFuServer();
					Map<String, Object> map1=zhongqiService.findResultByInvestorPaymentId(fuServer, pro.getCloseMatchId());
					Map<String, Object> map2=zhongqiService.findResultByInvestorPaymentId(fuServer, pro.getCloseBlanceId());
					if(map1.containsKey("result")&&map2.containsKey("result")&&Integer.valueOf(map1.get("result").toString())>0&&Integer.valueOf(map2.get("result").toString())>0){
						if(map1.get("result").toString().equals("1")&&map2.get("result").toString().equals("1")){
							fuProgramService.saveCloseProgramTask(fuServer, fuUser, pro);
							System.out.println("结算方案"+pro.getId()+"成功");
						}else{
							pro.setStatus(4);
							pro.setAdminClear(null);
							pro.setClearTime(null);
							if(pro.getAddMatchId()!=null){
								FuProgram primaryPro=fuProgramService.get(pro.getAddMatchId());//主方案
								primaryPro.setStatus(4);
								primaryPro.setAdminClear(null);
								primaryPro.setClearTime(null);
								fuProgramService.save(primaryPro);
							}
							fuProgramService.save(pro);
							System.out.println("结算方案"+pro.getId()+"失败");
						}
					}
				}else{
					pro.setStatus(4);
					pro.setAdminClear(null);
					pro.setClearTime(null);
					if(pro.getAddMatchId()!=null){
						FuProgram primaryPro=fuProgramService.get(pro.getAddMatchId());//主方案
						primaryPro.setStatus(4);
						primaryPro.setAdminClear(null);
						primaryPro.setClearTime(null);
						fuProgramService.save(primaryPro);
					}
					fuProgramService.save(pro);
					System.out.println("结算方案"+pro.getId()+"失败");
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