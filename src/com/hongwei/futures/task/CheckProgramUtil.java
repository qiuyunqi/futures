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
import com.hongwei.futures.service.FuServerService;
import com.hongwei.futures.zhongqi.ZhongqiService;

public class CheckProgramUtil extends TimerTask {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private ZhongqiService zhongqiService;
	@Autowired
	private FuServerService fuServerService;
	
	private Object lock = new Object();
	private volatile boolean isRunning = false;

	/**
	 * 检查方案是否已经结束(20:50)
	 */
	@Override
	public void run() {
		System.out.println("开户任务开始"+new Date());
		checkProgram();
		System.out.println("开户任务结束"+new Date());
	}
	/**
	 * 开户的处理结果
	 */
	public void checkProgram(){
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			List<FuProgram> list=fuProgramService.findOpenProgramByParams();//审核中的方案，且众期开户序号都不为空
			for (int i=0;i<list.size();i++) {
				FuProgram pro=list.get(i);
				FuUser fuUser = pro.getFuUser();
				FuServer fuServer = pro.getFuServer();
				Map<String, Object> map1=new HashMap<String, Object>();
				if(pro.getOpenUserId()!=null){//主方案
					map1=zhongqiService.findResultByInvestorAddId(fuServer, pro.getOpenUserId());//非增减配的主方案
				}else{//增减配方案
					map1.put("result", 1);
				}
				Map<String, Object> map2=zhongqiService.findResultByInvestorPaymentId(fuServer, pro.getOpenPaymentId());
				Map<String, Object> map3=new HashMap<String, Object>();
				if(pro.getAddMatchId()!=null){//增配子方案
					map3.put("result", 1);
				}else{//主方案或者减配子方案
					map3=zhongqiService.findResultByInvestorTriggerId(fuServer, pro.getOpenTriggerId());
				}
				if(map1.containsKey("result")&&map2.containsKey("result")&&map3.containsKey("result")&&Integer.valueOf(map1.get("result").toString())>0&&Integer.valueOf(map2.get("result").toString())>0&&Integer.valueOf(map3.get("result").toString())>0){
					if(map1.get("result").toString().equals("1")&&map2.get("result").toString().equals("1")&&map3.get("result").toString().equals("1")){
						//减配方案开户成功的操作
						if(pro.getSubMatchId()!=null){
							FuProgram primaryPro=fuProgramService.get(pro.getSubMatchId());//主方案
							fuProgramService.saveCheckSubProgramTask(fuUser,primaryPro,pro);
							System.out.println("减配子方案开户成功");
						}
						//增配子方案开户的操作
						else if(pro.getAddMatchId()!=null){
							FuProgram primaryPro=fuProgramService.get(pro.getAddMatchId());//主方案
							if(pro.getOpenTriggerId()==null){
								Integer triggerId=zhongqiService.addTriggerBySonProgram(primaryPro.getFuUser(), primaryPro, pro, primaryPro.getFuServer());
								if(triggerId>0){
									pro.setOpenTriggerId(triggerId);
									fuProgramService.save(pro);
								}
							}
							Map<String, Object> map4=zhongqiService.findResultByInvestorTriggerId(fuServer, pro.getOpenTriggerId());
							if(map4.containsKey("result")&&Integer.valueOf(map4.get("result").toString())>0&&map4.get("result").toString().equals("1")){
								fuProgramService.saveCheckProgramTask(fuUser, pro);
								System.out.println("增配子方案开户成功");
							}
						}
						//主方案开户成功的操作
						else{
							fuProgramService.saveCheckProgramTask(fuUser, pro);
							System.out.println("主方案开户成功");
						}
					}else{
						pro.setStatus(3);
						pro.setTradeAccount(null);
						pro.setTradePassword(null);
						pro.setTradeServiceName(null);
						pro.setTradeIp(null);
						pro.setTradePort(null);
						pro.setFuServer(null);
						pro.setRoleId(null);
						pro.setGroupId(null);
						fuProgramService.save(pro);
						if(pro.getSubMatchId()!=null){//减配方案开户失败的操作
							FuProgram primaryPro=fuProgramService.get(pro.getSubMatchId());//主方案
							fuServer.setServerMoney(fuServer.getServerMoney().subtract(primaryPro.getMatchMoney().subtract(pro.getMatchMoney())));
						}else{//增配或主方案开户失败的操作
							fuServer.setServerMoney(fuServer.getServerMoney().add(pro.getMatchMoney()));
						}
						fuServerService.save(fuServer);
						System.out.println("方案开户失败");
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