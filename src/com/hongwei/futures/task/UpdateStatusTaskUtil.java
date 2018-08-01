package com.hongwei.futures.task;

import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.service.FuStockAccountService;

public class UpdateStatusTaskUtil extends TimerTask{
	@Autowired
	private FuStockAccountService fuStockAccountService;

	private Object lock = new Object();
	private volatile boolean isRunning = false;
	
	/**
	 * 每日将各个交易品种的最新行情写入K线
	 */
	@Override
	public void run(){
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
    		try {
    			fuStockAccountService.updateTask();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        isRunning = false;
		}
	}
	
}
