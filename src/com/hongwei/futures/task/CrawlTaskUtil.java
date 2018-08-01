package com.hongwei.futures.task;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.HhrOrgInfo;
import com.hongwei.futures.model.HhrOrgPerson;
import com.hongwei.futures.service.HhrOrgPersonService;
import com.hongwei.futures.util.CrawlDataUtil;

public class CrawlTaskUtil extends TimerTask{
	@Autowired
	private HhrOrgPersonService hhrOrgPersonService;
	
	private Object lock = new Object();
	private volatile boolean isRunning = false;
	
	/**
	 * 更新期货、证券从业人员信息(每周六 03:00)
	 */
	@Override
	public void run(){
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			System.out.println("每周六凌晨更新期货和证券从业人员信息库, 处理开始...");
			long startTime = System.currentTimeMillis();  
			String[] orgType = new String[] {"10","11","12","13","14"};
			String[] sqlKey = new String[] {"SEARCH_FINISH_PUBLICITY", "SEARCH_FINISH_OTHER_PUBLICITY"};
			List<HhrOrgPerson> allPersonList = new ArrayList<HhrOrgPerson>();
			
			//1.期货
			List<HhrOrgInfo> futuresOrgList = CrawlDataUtil.getFuturesOrgList();
	    	for(int i=0; i< futuresOrgList.size(); i++){
	    		List<HhrOrgPerson> futuresPersonList = CrawlDataUtil.getFuturesOrgPersonById(futuresOrgList.get(i).getOrgId());
	    		allPersonList.addAll(futuresPersonList);
	    	}
	    	
	    	//2.证券
	    	List<HhrOrgInfo> securityOrgList = CrawlDataUtil.getSecurityOrgList(orgType);
	    	for(int j=0; j< securityOrgList.size(); j++){
	    		List<HhrOrgPerson> securityPersonList = CrawlDataUtil.getSecurityOrgPersonById(securityOrgList.get(j).getOrgId(), sqlKey);
	    		allPersonList.addAll(securityPersonList);
	    	}
			
	    	//3.清空表
	    	hhrOrgPersonService.deleteAll();
	    	
	    	Connection conn = null;
	    	PreparedStatement ps = null;
	    	try {
	    		 InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
	    		 Properties properties = new Properties();
	    		 properties.load(in);
				 Class.forName("com.mysql.jdbc.Driver");
				 conn = DriverManager.getConnection(properties.getProperty("dataSource.url"), properties.getProperty("dataSource.user") , properties.getProperty("dataSource.password"));
				 ps = conn.prepareStatement("insert into hhr_org_person(user_name,cer_num,zx_num,org_type) values(?,?,?,?)"); 
				 for(int k=0; k<allPersonList.size(); k++) 
	             { 
					 ps.setString(1, allPersonList.get(k).getUserName());
					 ps.setString(2, allPersonList.get(k).getCerNum());
					 ps.setString(3, allPersonList.get(k).getZxNum());
					 ps.setString(4, allPersonList.get(k).getOrgType());
	                 ps.addBatch();                                  
	             }
				 ps.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				release(ps, conn);
			}
	  
	    	long endTime = System.currentTimeMillis();  
	        System.out.println("处理结束...");  
	        System.out.println("总计耗时:"+(endTime-startTime)/1000/60+"分钟");
	        isRunning = false;
		}
	}
	
	public void release(PreparedStatement ps, Connection conn) {
        try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
