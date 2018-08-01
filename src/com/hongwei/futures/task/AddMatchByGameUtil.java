package com.hongwei.futures.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuServerService;
import com.hongwei.futures.zhongqi.ZhongqiService;

public class AddMatchByGameUtil extends TimerTask {
	@Autowired
	private FuGameService fuGameService;
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
		System.out.println("大赛入金任务开始"+new Date());
		addMargin();
		System.out.println("大赛入金任务结束"+new Date());
	}
	
	/**
	 * 活动开始时间给所有参赛用户重置资金为100万
	 */
	public void addMargin(){
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("isTrade", 0);
			List<FuGame> list=fuGameService.findGameProgramList(0, Integer.MAX_VALUE, map);//所有未重置金额的参赛的记录
			for (int i=0;i<list.size();i++) {
				FuGame fuGame=list.get(i);
				FuServer fuServer=fuServerService.findServerByUserTypeId(2);
				BigDecimal balance=zhongqiService.findBalance(fuServer, fuGame.getTradeAccount());//动态权益
				BigDecimal addMoney;
				if(balance.compareTo(new BigDecimal(1000000))==-1){
					addMoney=new BigDecimal(1000000).subtract(balance);
				}else{
					addMoney=balance.subtract(new BigDecimal(1000000)).multiply(new BigDecimal(-1));
				}
				Integer paymentId=0;
				if(fuGame.getPaymentId()==null||fuGame.getPaymentId()==0){
					while(true){
						paymentId=zhongqiService.gameResetMoney(fuServer, fuGame.getTradeAccount(), addMoney);
						if(paymentId>0){
							fuGame.setPaymentId(paymentId);
							fuGameService.save(fuGame);
							break;
						}
					}
				}else{
					paymentId=fuGame.getPaymentId();
				}
				//查询
				Map<String, Object> map2=zhongqiService.findResultByInvestorPaymentId(fuServer, paymentId);
				if(map2.containsKey("result") && Integer.valueOf(map2.get("result").toString())>0){
					if(map2.get("result").toString().equals("1")){
						fuGame.setIsTrade(1);//入金成功
						fuGameService.save(fuGame);
						System.out.println("期指大赛重置账户"+fuGame.getTradeAccount()+"资金成功");
					}else{
						fuGame.setIsTrade(0);//入金失败
						fuGameService.save(fuGame);
						System.out.println("期指大赛重置账户"+fuGame.getTradeAccount()+"资金失败");
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