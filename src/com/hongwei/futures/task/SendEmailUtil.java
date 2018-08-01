package com.hongwei.futures.task;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.util.MailEngine;

public class SendEmailUtil extends TimerTask {
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private MailEngine mailEngine;
	@Autowired
	private FuParameterService fuParameterService;

	private Object lock = new Object();
	private volatile boolean isRunning = false;
	//private static final Log log = LogFactory.getLog(SendEmailUtil.class);
	private static Logger logger = Logger.getLogger(SendEmailUtil.class); 
	
	@Override
	public void run() {
		synchronized (lock) {
			logger.info("@@@@@@@@@@@@@@@@@ 开始发送邮件");
			if (isRunning) {
				return;
			}
			isRunning = true;
			FuParameter param = fuParameterService.findParameter();
			List<FuSmsLog> logList = fuSmsLogService.findMailLogList();
			if(param != null && !"".equals(param.getServiceEmail())){
				if (logList != null && logList.size() > 0) {
					for (FuSmsLog smsLog: logList) {
						try {
							mailEngine.sendMessage(param.getServiceEmail().split(","), "资产管理平台<service@hhr360.com>", smsLog.getContent()==null?"":smsLog.getContent(), smsLog.getReason()+"通知", null, null);
							smsLog.setSendTime(new Date());
							smsLog.setState(1);
							fuSmsLogService.save(smsLog);
							logger.info("邮件: "+smsLog.getContent()+" 发送成功");
						} catch (MessagingException e) {
							smsLog.setSendTime(new Date());
							smsLog.setState(2);
							fuSmsLogService.save(smsLog);
							logger.info("邮件: "+smsLog.getContent()+" 发送失败");
							e.printStackTrace();
						}
					}
				}
			}
			isRunning = false;
			logger.info("@@@@@@@@@@@@@@@@@ 邮件发送完成");
		}
	}

}