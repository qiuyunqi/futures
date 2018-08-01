package com.hongwei.futures.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.util.CommonUtil;

public class SendMessageUtil extends TimerTask {
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuParameterService fuParameterService;

	private Object lock = new Object();
	private volatile boolean isRunning = false;

	/**
	 * 检查方案是否已经结束(20:50)
	 */
	@Override
	public void run() {
		synchronized (lock) {
			FuParameter fuParameter = fuParameterService.findParameter();
			if (null != fuParameter && fuParameter.getIsMessage() == 1) {// 短信启用
				System.out.println("短信发送任务--开始--" + new Date());
				if (isRunning) {
					System.out.println("短信发送任务--进行中--" + new Date());
					return;
				}

				/**
				 * 发送失败的重新发送，根据发送时间越早的先执行发送
				 */
				/*
				 * Map<String, Object> map=new HashMap<String, Object>();
				 * map.put("state", 2); List<FuSmsLog> logList =
				 * fuSmsLogService.findLogListByMap(map);//所有发送失败的短信
				 * if(logList!=null){ for (int i = 0; i < logList.size(); i++) {
				 * FuSmsLog log = logList.get(i); boolean
				 * f=CommonUtil.sendSmsProgram(log.getDestination(),
				 * log.getContent()); if(f){//发送成功 log.setSendTime(new Date());
				 * log.setState(1); }else{//发送失败 log.setSendTime(new Date());
				 * log.setState(2); } fuSmsLogService.save(log);
				 * System.out.println("----短信"
				 * +log.getId()+(log.getState()==1?"发送成功":"发送失败")+"----"); } }
				 */

				isRunning = true;
				/**
				 * 未发送的发送，根据发送时间越早的先执行发送
				 */
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("state", 0);
				List<FuSmsLog> logList2 = fuSmsLogService.findLogListByMap(map2);// 所有未发送的短信
				if (logList2 != null) {
					for (int i = 0; i < logList2.size(); i++) {
						FuSmsLog log = logList2.get(i);
						if (log.getType() != 2) {// 排除邮件
							boolean f = false;
							if (log.getType() == 4) {
								f = CommonUtil.sendSmsVoice(log.getDestination(), log.getRegCode());// 语音短信
							}
							if (log.getType() == 1 || log.getType() == 3) {
								f = CommonUtil.sendSmsProgram(log.getDestination(), log.getContent());// 文字短信
							}
							if (f) {// 发送成功
								log.setSendTime(new Date());
								log.setState(1);
							} else {// 发送失败
								log.setSendTime(new Date());
								log.setState(2);
							}
							fuSmsLogService.save(log);
							System.out.println("----短信" + log.getId() + (log.getState() == 1 ? "发送成功" : "发送失败") + "----");
						}
					}
				}
				isRunning = false;
				System.out.println("短信发送任务--结束--" + new Date());
			}
		}
	}

	public static void main(String args[]) {
		Long value = 1000283799l + 108000000l;
		System.out.println(value);
	}

}