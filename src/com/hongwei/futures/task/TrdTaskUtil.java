package com.hongwei.futures.task;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.TrdTradeParameterService;

public class TrdTaskUtil extends TimerTask{
	@Autowired
	private TrdTradeParameterService trdTradeParameterService;
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
 			Connection conn = null;
	    	PreparedStatement ps = null;
	    	ResultSet rs = null;
			InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
    		Properties properties = new Properties();
    		try {
				properties.load(in);
				Class.forName(properties.getProperty("dataSource.driverClass"));
				conn = DriverManager.getConnection(properties.getProperty("dataSource.url"), properties.getProperty("dataSource.user") , properties.getProperty("dataSource.password"));						
				List<TrdTradeParameter> params = trdTradeParameterService.findAllParams();
				if(params != null && params.size()>0){
					//遍历合约列表
					for(TrdTradeParameter param : params){
						//根据合约编号查询数据库中是否存在对应的表
						String type = param.getTradeVariety();
						String tableSql = "select table_name from information_schema.TABLES where TABLE_NAME = ?";
						ps = conn.prepareStatement(tableSql);
						ps.setString(1, "trd_"+type);
						rs = ps.executeQuery();
						rs.last();
						int tableRows = rs.getRow();
						if(tableRows>0){
							//每天根据各个合约配置的收盘时间,获取正确的行情数据,写入K线记录
							Date nowDate = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							String trdSql = " select trading_day, instrument_id, highest_price, lowest_price, close_price, open_price from trd_"+type+
											" where trading_day = ? and update_time = (select max(update_time) from trd_"+type+" where trading_day =?)";
							ps = conn.prepareStatement(trdSql);
							ps.setString(1, sdf.format(nowDate));
							ps.setString(2, sdf.format(nowDate));
							rs = ps.executeQuery();
							rs.last();
							int rows = rs.getRow();
							if(rows>0){
						        ps = conn.prepareStatement("INSERT INTO  trd_klines(trading_day ,  instrument_id ,  highest_price ,  lowest_price ,  close_price ,  open_price  ) VALUES (?,?,?,?,?,?);"); 
								ps.setString(1, rs.getString("trading_day"));
								ps.setString(2, rs.getString("instrument_id"));
								ps.setString(3, rs.getString("highest_price"));
								ps.setString(4, rs.getString("lowest_price"));
								ps.setString(5, rs.getString("close_price"));
								ps.setString(6, rs.getString("open_price"));
								ps.executeUpdate();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					conn.close();
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	        isRunning = false;
		}
	}
	
}
