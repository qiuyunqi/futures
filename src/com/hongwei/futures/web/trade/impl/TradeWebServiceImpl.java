package com.hongwei.futures.web.trade.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.TrdOrder;
import com.hongwei.futures.model.TrdServerLog;
import com.hongwei.futures.model.TrdTrade;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.TrdOrderService;
import com.hongwei.futures.service.TrdServerLogService;
import com.hongwei.futures.service.TrdTradeParameterService;
import com.hongwei.futures.service.TrdTradeService;
import com.hongwei.futures.util.HttpRemoteUtil;
import com.hongwei.futures.util.MoneyDetailUtil;
import com.hongwei.futures.web.trade.TradeWebService;

public class TradeWebServiceImpl implements TradeWebService {

	@Autowired
	private TrdOrderService trdOrderService;
	
	@Autowired
	private TrdTradeService trdTradeService;
	
	@Autowired
	private TrdTradeParameterService trdTradeParameterService;
	
	@Autowired
	private TrdServerLogService trdServerLogService;
	
	@Autowired
	private FuUserService fuUserService;
	
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	
	@Autowired
	private FuGameService fuGameService;
	
	@Autowired
	private FuRateService fuRateService;
	
	@Autowired
	private FuProgramService fuProgramService;

		
	/**
	 * 报单回报
	 * @return
	 */
	@Override
	public String orderBack(Long userId, String orderNum, String instrumentId, Integer direction, Integer offsetFlag, Integer volume, String price, String money, 
			String tradeDateTime, Integer backType, String failureCode, String failureMsg, String sign) {
		JSONObject obj = new JSONObject();	
		JSONObject params = new JSONObject();
		params.put("user_id", userId);
		params.put("order_num", orderNum);
		params.put("instrument_id", instrumentId);
		params.put("direction", direction);
		params.put("offset_flag", offsetFlag);
		params.put("volume", volume);
		params.put("price", price);
		params.put("money", money);
		params.put("trade_datetime", tradeDateTime);
		params.put("back_type", backType);
		params.put("failure_code", failureCode);
		params.put("failure_msg", failureMsg);
		System.out.println(params);
		try {
			//String result = DesUtil.webserviceSignVerify(sign);
			String result = "success";
			if("success".equals(result)){
				if(orderNum == null || "".equals(orderNum)){
					obj.put("is_success", 0);
					obj.put("message", "订单号为空");
					System.out.println(obj);
					return obj.toString();
				}	
				if(backType == null){
					obj.put("is_success", 0);
					obj.put("message", "回报类型为空");
					System.out.println(obj);
					return obj.toString();
				}			
				FuUser fuUser = null;
				if(userId != null){
					fuUser = fuUserService.get(userId);
				}
				//根据订单号查询订单
				TrdOrder order = trdOrderService.findByOrderNum(orderNum);	
				if(order == null){
					if(backType == 0 || (backType == 1 && orderNum.indexOf("-") == -1)){
						obj.put("is_success", 0);
						obj.put("message", "订单不存在");
						System.out.println(obj);
						return obj.toString();
					}
				}
				//根据合约查询参数
				TrdTradeParameter trdParam = trdTradeParameterService.findByTradeVariety(instrumentId);
				//回报成功
				if("0".equals(failureCode)){
					money = money.replaceAll(" ", "");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
					TrdTrade trade = null;
					//报单回报
					if(backType == 0){
						//将订单状态设为:报单成功
						order.setState(1);
					}
					//成交回报
					if(backType == 1){
						//开仓成交,创建持仓记录
						if(offsetFlag == 0){
							trade = new TrdTrade();
							trade.setFuUser(fuUserService.get(userId));
							trade.setInstrumentId(instrumentId);
							trade.setDirection(direction);
							trade.setOffsetFlag(offsetFlag);
							trade.setOpenOrderNum(orderNum);
							trade.setOpenDateTime(sdf.parse(tradeDateTime));
							trade.setOpenVolume(volume);
							trade.setOpenPrice(new BigDecimal(price));
							trade.setOpenMoney(new BigDecimal(money));
							trade.setState(0);
							trade.setCreateTime(new Date());
							trade.setSafeMoney(trdParam.getSafeMoney());
							trade.setManageMoney(trdParam.getManageMoney());
							trdTradeService.save(trade);
						}
						//正常平仓成交
						if(offsetFlag == 1 && (orderNum.indexOf("-") == -1)){
							order.setFuUser(fuUserService.get(userId));
							order.setInstrumentId(instrumentId);
							order.setDirection(direction);
							order.setOffsetFlag(offsetFlag);
							order.setVolume(volume);
							order.setPrice(new BigDecimal(price));
							order.setMoney(new BigDecimal(money));
							order.setTradeDateTime(sdf.parse(tradeDateTime));
							order.setSafeMoney(trdParam.getSafeMoney());
							order.setManageMoney(trdParam.getManageMoney());
							if(failureCode != null && !"".equals(failureCode)){
								order.setFailureCode(failureCode);
							}
							if(failureMsg != null && !"".equals(failureMsg)){
								order.setFailureMsg(failureMsg);
							}
							//根据平仓订单号查询持仓
							trade = trdTradeService.findByCloseOrderNum(orderNum);
							if(trade == null){
								obj.put("is_success", 0);
								obj.put("message", "持仓不存在");
								System.out.println(obj);
								return obj.toString();
							}
							//更新持仓
							trade.setFuUser(fuUserService.get(userId));
							trade.setInstrumentId(instrumentId);
							trade.setCloseDateTime(sdf.parse(tradeDateTime));
							trade.setCloseVolume(volume);
							trade.setClosePrice(new BigDecimal(price));
							trade.setCloseMoney(new BigDecimal(money));
							trade.setState(2);
							trade.setUpdateTime(new Date());
							trade.setCloseType(1);
							trade.setBackDirection(direction);
							trade.setBackOffsetFlag(offsetFlag);
							/* 计算平仓盈亏 
							 * 多单: (平仓成交价格-开仓成交价格)*点位单价
							 * 空单: (开仓成交价格-平仓成交价格)*点位单价
							 */
							BigDecimal close_profit = null;
							if(trade.getDirection() == 0){            //多单
								close_profit = ((new BigDecimal(price)).subtract(trade.getOpenPrice())).multiply(trdParam.getSmallestPriceChange());
							}else if(trade.getDirection() == 1){      //空单
								close_profit = 	(trade.getOpenPrice().subtract(new BigDecimal(price))).multiply(trdParam.getSmallestPriceChange());			
							}
							trade.setCloseProfit(close_profit);
							//将持仓状态设为:平仓成功
							trade.setState(2);
							//平仓返还保证金和盈利
							if(trdParam != null){
								//保证金流水
								moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 23, trdParam.getSafeMoney(), fuUser.getAccountBalance().add(trdParam.getSafeMoney()), true);
								//平仓订单盈亏流水
								if(close_profit.compareTo(BigDecimal.ZERO) != -1){
									moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 24, close_profit,fuUser.getAccountBalance().add(close_profit), true);
								}else{
									moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 25, close_profit,fuUser.getAccountBalance().add(close_profit), false);
								}
								fuUser.setAccountBalance(fuUser.getAccountBalance().add(trdParam.getSafeMoney()).add(close_profit));
								fuUserService.save(fuUser);
							}
							trdTradeService.save(trade);
						}
						//止盈止损成交
						if(offsetFlag == 1 && (orderNum.indexOf("-") != -1)){
							//创建强平订单
							/*TrdOrder breakOrder = new TrdOrder();
							breakOrder.setFuUser(fuUserService.get(userId));
							breakOrder.setOrderNum(orderNum);
							breakOrder.setInstrumentId(instrumentId);
							breakOrder.setDirection(direction);
							breakOrder.setOffsetFlag(offsetFlag);
							breakOrder.setVolume(volume);
							breakOrder.setPrice(new BigDecimal(price));
							breakOrder.setMoney(new BigDecimal(money));
							breakOrder.setTradeDateTime(sdf.parse(tradeDateTime));
							breakOrder.setState(2);
							breakOrder.setSafeMoney(trdParam.getSafeMoney());
							breakOrder.setManageMoney(trdParam.getManageMoney());
							if(failureCode != null && !"".equals(failureCode)){
								breakOrder.setFailureCode(failureCode);
							}
							if(failureMsg != null && !"".equals(failureMsg)){
								breakOrder.setFailureMsg(failureMsg);
							}
							trdOrderService.save(breakOrder);*/		
							//查询用户持仓
							List<TrdTrade> trades = trdTradeService.findTrdTradesByUser(userId, direction);
							if(trades != null && trades.size()>0){
								//得到最早的用户持仓
								TrdTrade firstTrade = trades.get(0);
								if(firstTrade != null){
									//更新持仓记录:将强平订单更新到持仓记录中
									firstTrade.setFuUser(fuUserService.get(userId));
									firstTrade.setInstrumentId(instrumentId);
									firstTrade.setCloseOrderNum(orderNum);
									firstTrade.setCloseDateTime(sdf.parse(tradeDateTime));
									firstTrade.setCloseVolume(volume);
									firstTrade.setClosePrice(new BigDecimal(price));
									firstTrade.setCloseMoney(new BigDecimal(money));
									firstTrade.setState(2);
									firstTrade.setUpdateTime(new Date());
									firstTrade.setBackDirection(direction);
									firstTrade.setBackOffsetFlag(offsetFlag);
									BigDecimal close_profit = null;
									if(firstTrade.getDirection() == 0){            //多单
										close_profit = ((new BigDecimal(price)).subtract(firstTrade.getOpenPrice())).multiply(trdParam.getSmallestPriceChange());
									}else if(firstTrade.getDirection() == 1){      //空单
										close_profit = 	(firstTrade.getOpenPrice().subtract(new BigDecimal(price))).multiply(trdParam.getSmallestPriceChange());			
									}
									if(close_profit.compareTo(BigDecimal.ZERO) == 1){
										firstTrade.setCloseType(2);
									}else{
										firstTrade.setCloseType(3);
									}
									firstTrade.setCloseProfit(close_profit);
									//将持仓状态设置为:强平成功
									firstTrade.setState(5);
									//强平返还保证金和盈利
									if(trdParam != null){
										//保证金流水
										moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 23, trdParam.getSafeMoney(), fuUser.getAccountBalance().add(trdParam.getSafeMoney()), true);
										//平仓订单盈亏流水
										if(close_profit.compareTo(BigDecimal.ZERO) != -1){
											moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 24, close_profit,fuUser.getAccountBalance().add(close_profit), true);
										}else{
											moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 25, close_profit,fuUser.getAccountBalance().add(close_profit), false);
										}
										fuUser.setAccountBalance(fuUser.getAccountBalance().add(trdParam.getSafeMoney()).add(close_profit));
										fuUserService.save(fuUser);
									}
									trdTradeService.save(firstTrade);
								}
							}
						}
						//将订单状态设为:已成交
						if(order != null){
							order.setPrice(new BigDecimal(price));
							order.setMoney(new BigDecimal(money));
							order.setTradeDateTime(sdf.parse(tradeDateTime));
							order.setUpdateTime(new Date());
							order.setState(2);
							trdOrderService.save(order);
						}
					}
					//收盘强平回报
					if(backType == 2){
						//查询持仓中
						List<TrdTrade> trades = trdTradeService.findTrdTrades();
						if(trades != null && trades.size()>0){
							for(TrdTrade tt:trades){
								if(tt != null){
									tt.setCloseOrderNum("0");
									tt.setCloseDateTime(new Date());
									tt.setCloseVolume(1);
									tt.setClosePrice(new BigDecimal(price));
									tt.setCloseMoney(new BigDecimal(price).multiply(trdParam.getSmallestPriceChange()));
									//将持仓状态设置为:强平成功
									tt.setState(5);
									tt.setUpdateTime(new Date());
									tt.setCloseType(4);
									tt.setBackDirection(tt.getDirection()==0?1:0);
									tt.setBackOffsetFlag(1);
									BigDecimal close_profit = null;
									if(tt.getDirection() == 0){            //多单
										close_profit = ((new BigDecimal(price)).subtract(tt.getOpenPrice())).multiply(trdParam.getSmallestPriceChange());
									}else if(tt.getDirection() == 1){      //空单
										close_profit = 	(tt.getOpenPrice().subtract(new BigDecimal(price))).multiply(trdParam.getSmallestPriceChange());			
									}
									tt.setCloseProfit(close_profit);
									//强平返还保证金和盈利
									if(trdParam != null){
										FuUser tradeUser = tt.getFuUser();
										//保证金流水
										moneyDetailUtil.saveNewFuMoneyDetail(tradeUser, null, null, null, 23, trdParam.getSafeMoney(), tradeUser.getAccountBalance().add(trdParam.getSafeMoney()), true);
										//平仓订单盈亏流水, 盈亏为0则不记流水
										if(close_profit.compareTo(BigDecimal.ZERO) != 0){
											if(close_profit.compareTo(BigDecimal.ZERO) == 1){
												moneyDetailUtil.saveNewFuMoneyDetail(tradeUser, null, null, null, 24, close_profit, tradeUser.getAccountBalance().add(trdParam.getSafeMoney()).add(close_profit), true);
											}else{
												moneyDetailUtil.saveNewFuMoneyDetail(tradeUser, null, null, null, 25, close_profit, tradeUser.getAccountBalance().add(trdParam.getSafeMoney()).add(close_profit), false);
											}
										}
										tradeUser.setAccountBalance(tradeUser.getAccountBalance().add(trdParam.getSafeMoney()).add(close_profit));
										fuUserService.save(tradeUser);
									}
									trdTradeService.save(tt);
								}
							}
						}
					}
					obj.put("is_success", 1);		
					obj.put("message", "报单回报操作成功");
				}else{
					//订单无法成交,退还保证金和管理费
					//写保证金流水
					moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 26, trdParam.getSafeMoney(), fuUser.getAccountBalance().add(trdParam.getSafeMoney()), true);
					//写管理费流水
					moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 27, trdParam.getManageMoney(),fuUser.getAccountBalance().add(trdParam.getSafeMoney()).add(trdParam.getManageMoney()), true);
					fuUser.setAccountBalance(fuUser.getAccountBalance().add(trdParam.getSafeMoney()).add(trdParam.getManageMoney()));
					fuUserService.save(fuUser);
					obj.put("is_success", 0);		
					obj.put("message", "报单回报失败");
				}
			}else{
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();			
	}

	/**
	 * 行情
	 * @return
	 */
	@Override
	public String quotation(String trading_day, String instrument_id, String exchange_id, String exchange_inst_id, String last_price,
			String pre_settlement_price, String pre_close_price, String pre_open_interest, String open_price, String highest_price,
			String lowest_price, String volume, String turnover, String open_interest, String close_price, String settlement_price,
			String upper_limit_price, String lower_limit_price, String pre_delta, String curr_delta, String update_time,
			String update_millisec, String bid_price1, String bid_volume1, String ask_price1, String ask_volume1, String bid_price2,
			String bid_volume2, String ask_price2, String ask_volume2, String bid_price3, String bid_volume3, String ask_price3,
			String ask_volume3, String bid_price4, String bid_volume4, String ask_price4, String ask_volume4, String bid_price5,
			String bid_volume5, String ask_price5, String ask_volume5, String average_price, String cdate_time, String sign) {
		JSONObject obj = new JSONObject();	
		Connection conn = null;
    	PreparedStatement ps = null;
		try {
			//String result = DesUtil.webserviceSignVerify(sign);
			String result = "success";
			if("success".equals(result)){
				if(trading_day == null || "".equals(trading_day)){
					obj.put("is_success", 0);
					obj.put("message", "交易日为空");
					System.out.println(obj);
					return obj.toString();
				}	
				if(instrument_id == null || "".equals(instrument_id)){
					obj.put("is_success", 0);
					obj.put("message", "合约为空");
					System.out.println(obj);
					return obj.toString();
				}	
	    		InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
	    		Properties properties = new Properties();
	    		properties.load(in);
				Class.forName(properties.getProperty("dataSource.driverClass"));
				conn = DriverManager.getConnection(properties.getProperty("dataSource.url"), properties.getProperty("dataSource.user") , properties.getProperty("dataSource.password"));
				String sql = " CREATE TABLE If Not Exists trd_" + instrument_id + " ( " +
                             " id  bigint(20) NOT NULL AUTO_INCREMENT, " +
                             " trading_day  varchar(30) DEFAULT NULL,  " +
                             " instrument_id  varchar(30) DEFAULT NULL," + 
                             " exchange_id  varchar(30) DEFAULT NULL,  " +
                             " exchange_inst_id  varchar(30) DEFAULT NULL, "+
                             " last_price  varchar(30) DEFAULT NULL, " +
                             " pre_settlement_price  varchar(30) DEFAULT NULL, "+
                             " pre_close_price  varchar(30) DEFAULT NULL, "+
                             " pre_open_interest  varchar(30) DEFAULT NULL, "+
                             " open_price  varchar(30) DEFAULT NULL, "+
                             " highest_price  varchar(30) DEFAULT NULL, "+
                             " lowest_price  varchar(30) DEFAULT NULL, "+
                             " volume  varchar(30) DEFAULT NULL, "+
                             " turnover  varchar(30) DEFAULT NULL, "+
                             " open_interest  varchar(30) DEFAULT NULL, "+
                             " close_price  varchar(30) DEFAULT NULL, "+
                             " settlement_price  varchar(30) DEFAULT NULL,"+
                             " upper_limit_price  varchar(30) DEFAULT NULL,"+
                             " lower_limit_price  varchar(30) DEFAULT NULL,"+
                             " pre_delta  varchar(30) DEFAULT NULL,"+
                             " curr_delta  varchar(30) DEFAULT NULL,"+
                             " update_time  varchar(30) DEFAULT NULL,"+
                             " update_millisec  varchar(30) DEFAULT NULL,"+
                             " bid_price1  varchar(30) DEFAULT NULL,"+
                             " bid_volume1  varchar(30) DEFAULT NULL,"+
                             " ask_price1  varchar(30) DEFAULT NULL,"+
                             " ask_volume1  varchar(30) DEFAULT NULL,"+
                             " bid_price2  varchar(30) DEFAULT NULL,"+
                             " bid_volume2  varchar(30) DEFAULT NULL,"+
                             " ask_price2  varchar(30) DEFAULT NULL,"+
                             " ask_volume2  varchar(30) DEFAULT NULL,"+
                             " bid_price3  varchar(30) DEFAULT NULL,"+
                             " bid_volume3  varchar(30) DEFAULT NULL,"+
                             " ask_price3  varchar(30) DEFAULT NULL,"+
                             " ask_volume3  varchar(30) DEFAULT NULL,"+
                             " bid_price4  varchar(30) DEFAULT NULL,"+
                             " bid_volume4  varchar(30) DEFAULT NULL,"+
                             " ask_price4  varchar(30) DEFAULT NULL,"+
                             " ask_volume4  varchar(30) DEFAULT NULL,"+
                             " bid_price5  varchar(30) DEFAULT NULL,"+
                             " bid_volume5  varchar(30) DEFAULT NULL,"+
                             " ask_price5  varchar(30) DEFAULT NULL,"+
                             " ask_volume5  varchar(30) DEFAULT NULL,"+
                             " average_price  varchar(30) DEFAULT NULL,"+
                             " cdate_time  datetime DEFAULT NULL,"+
                             " PRIMARY KEY ( id ) ) " +
                             " ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行情信息';";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				ps = conn.prepareStatement("INSERT INTO  trd_"+instrument_id+"(trading_day ,  instrument_id ,  exchange_id ,  exchange_inst_id ,  last_price ,  pre_settlement_price ,  pre_close_price ,  pre_open_interest ,  open_price ,  highest_price ,  lowest_price ,  volume ,  turnover ,  open_interest ,  close_price ,  settlement_price ,  upper_limit_price ,  lower_limit_price ,  pre_delta ,  curr_delta ,  update_time ,  update_millisec ,  bid_price1 ,  bid_volume1 ,  ask_price1 ,  ask_volume1 ,  bid_price2 ,  bid_volume2 ,  ask_price2 ,  ask_volume2 ,  bid_price3 ,  bid_volume3 ,  ask_price3 ,  ask_volume3 ,  bid_price4 ,  bid_volume4 ,  ask_price4 ,  ask_volume4 ,  bid_price5 ,  bid_volume5 ,  ask_price5 ,  ask_volume5 ,  average_price ,  cdate_time ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"); 
				ps.setString(1, trading_day);
				ps.setString(2, instrument_id);
				ps.setString(3, exchange_id);
				ps.setString(4, exchange_inst_id);
				ps.setString(5, last_price);
				ps.setString(6, pre_settlement_price);
				ps.setString(7, pre_close_price);
				ps.setString(8, pre_open_interest);
				ps.setString(9, open_price);
				ps.setString(10, highest_price);
				ps.setString(11, lowest_price);
				ps.setString(12, volume);
				ps.setString(13, turnover);
				ps.setString(14, open_interest);
				ps.setString(15, close_price);
				ps.setString(16, settlement_price);
				ps.setString(17, upper_limit_price);
				ps.setString(18, lower_limit_price);
				ps.setString(19, pre_delta);
				ps.setString(20, curr_delta);
				ps.setString(21, update_time);
				ps.setString(22, update_millisec);
				ps.setString(23, bid_price1);
				ps.setString(24, bid_volume1);
				ps.setString(25, ask_price1);
				ps.setString(26, ask_volume1);
				ps.setString(27, bid_price2);
				ps.setString(28, bid_volume2);
				ps.setString(29, ask_price2);
				ps.setString(30, ask_volume2);
				ps.setString(31, bid_price3);
				ps.setString(32, bid_volume3);
				ps.setString(33, ask_price3);
				ps.setString(34, ask_volume3);
				ps.setString(35, bid_price4);
				ps.setString(36, bid_volume4);
				ps.setString(37, ask_price4);
				ps.setString(38, ask_volume4);
				ps.setString(39, bid_price5);
				ps.setString(40, bid_volume5);
				ps.setString(41, ask_price5);
				ps.setString(42, ask_volume5);
				ps.setString(43, average_price);
				ps.setString(44, cdate_time);
				ps.executeUpdate();
				//根据品种算出实际涨跌幅
				TrdTradeParameter ttp = trdTradeParameterService.findByTradeVariety(instrument_id);
				BigDecimal a = new BigDecimal(last_price).subtract(new BigDecimal(pre_settlement_price));
			    BigDecimal actualRiskPercent = a.divide(new BigDecimal(pre_settlement_price),2,BigDecimal.ROUND_HALF_UP);
			    System.out.println("last_price: "+last_price+", pre_settlement_price: "+pre_settlement_price+", actualRiskPercent: "+actualRiskPercent);
				//ttp.setActualRiskPercent(new BigDecimal(Math.abs(actualRiskPercent.doubleValue())));
				ttp.setActualRiskPercent(actualRiskPercent);
				trdTradeParameterService.save(ttp);
				obj.put("is_success", 1);		
				obj.put("instrument_id", instrument_id);		
				obj.put("bid_price1", bid_price1);		
				obj.put("update_time", update_time+"."+update_millisec);		
				obj.put("actual_risk_percent", actualRiskPercent);		
				obj.put("message", "行情保存成功");
			}else{
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		} finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(obj);
		return obj.toString();	
	}

	/**
	 * 订单查询
	 * @return
	 */
	public String orderQuery(String state, String sign) {
		JSONObject obj = new JSONObject();
		try {
			//String result = DesUtil.webserviceSignVerify(sign);
			String result = "success";
			if("success".equals(result)){
				if(state == null || "".equals(state)){
					obj.put("is_success", 0);
					obj.put("message", "状态为空");
					System.out.println(obj);
					return obj.toString();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
				List<Object> orders = new ArrayList<Object>();
				List<Object> trades = new ArrayList<Object>();
				if("0".equals(state)){
					//0表示待成交, 查询订单
					List<TrdOrder> orderList = trdOrderService.findTrdOrders();
					if (orderList != null && orderList.size()>0) {
						for (TrdOrder order : orderList) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", order.getId());
							map.put("user_id", order.getFuUser().getId()==null?"":order.getFuUser().getId());
							map.put("instrument_id", order.getInstrumentId()==null?"":order.getInstrumentId());
							map.put("direction", order.getDirection()==null?"":order.getDirection());
							map.put("offset_flag", order.getOffsetFlag()==null?"":order.getOffsetFlag());
							map.put("order_num", order.getOrderNum()==null?"":order.getOrderNum());
							map.put("volume", order.getVolume()==null?"":order.getVolume());
							map.put("price", order.getPrice()==null?"":order.getPrice());	
							map.put("money", order.getMoney()==null?"":order.getMoney());
							map.put("trade_datetime", order.getTradeDateTime()==null?"":sdf.format(order.getTradeDateTime()));
							map.put("failure_code", order.getFailureCode()==null?"":order.getFailureCode());
							map.put("failure_msg", order.getFailureMsg()==null?"":order.getFailureMsg());
							orders.add(map);
						}
						obj.put("is_success", 1);	
						obj.put("message", "订单查询成功");
						obj.put("orders", orders);
					} else {
						obj.put("is_success", 1);
						obj.put("message", "无订单数据");
					}
				}
				if("1".equals(state)){
					//1表示成交, 查询持仓
					List<TrdTrade> tradeList = trdTradeService.findTrdTrades();
					if (tradeList != null && tradeList.size()>0) {
						for (TrdTrade trade : tradeList) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", trade.getId());
							map.put("user_id", trade.getFuUser().getId()==null?"":trade.getFuUser().getId());
							map.put("instrument_id", trade.getInstrumentId()==null?"":trade.getInstrumentId());
							map.put("direction", trade.getDirection()==null?"":trade.getDirection());
							map.put("offset_flag", trade.getOffsetFlag()==null?"":trade.getOffsetFlag());
							//开仓
							if(trade.getOffsetFlag() == 0){
								map.put("order_num", trade.getOpenOrderNum()==null?"":trade.getOpenOrderNum());
								map.put("volume", trade.getOpenVolume()==null?"":trade.getOpenVolume());
								map.put("price", trade.getOpenPrice()==null?"":trade.getOpenPrice());	
								map.put("money", trade.getOpenMoney()==null?"":trade.getOpenMoney());
								map.put("trade_datetime", trade.getOpenDateTime()==null?"":sdf.format(trade.getOpenDateTime()));
							} 
							//平仓
							if(trade.getOffsetFlag() == 1){
								map.put("order_num", trade.getCloseOrderNum()==null?"":trade.getCloseOrderNum());
								map.put("volume", trade.getCloseVolume()==null?"":trade.getCloseVolume());
								map.put("price", trade.getClosePrice()==null?"":trade.getClosePrice());	
								map.put("money", trade.getCloseMoney()==null?"":trade.getCloseMoney());
								map.put("trade_datetime", trade.getCloseDateTime()==null?"":sdf.format(trade.getCloseDateTime()));
							}
							map.put("failure_code", "");
							map.put("failure_msg", "");
							trades.add(map);
						}
						obj.put("is_success", 1);	
						obj.put("message", "持仓查询成功");
						obj.put("trades", trades);
					} else {
						obj.put("is_success", 1);
						obj.put("message", "无持仓数据");
					}
				}
			}else{
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();
	}

	/**
	 * 配置参数查询
	 * @return
	 */
	@Override
	public String queryParameter(String sign) {
		JSONObject obj = new JSONObject();	
		try {
			//String result = DesUtil.webserviceSignVerify(sign);
			String result = "success";
			if("success".equals(result)){
				List<TrdTradeParameter> pList = trdTradeParameterService.findAllParams();
				List<Object> parameters = new ArrayList<Object>();			
				if (pList != null && pList.size()>0) {
					for(TrdTradeParameter p : pList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", p.getId());
						map.put("trade_variety", p.getTradeVariety()==null?"":p.getTradeVariety());
						map.put("day_trade_num", p.getDayTradeNum()==null?"":p.getDayTradeNum());
						map.put("safe_money", p.getSafeMoney()==null?"":p.getSafeMoney());
						map.put("manage_money", p.getManageMoney()==null?"":p.getManageMoney());
						map.put("product_percent", p.getProductPercent()==null?"":p.getProductPercent());
						map.put("trade_time", p.getTradeTime()==null?"":p.getTradeTime());
						map.put("close_time", p.getBreakCloseTime()==null?"":p.getBreakCloseTime());
						map.put("risk_percent", p.getRiskPercent()==null?"":p.getRiskPercent());
						map.put("stop_loss_percent", p.getStopLossPercent()==null?"":p.getStopLossPercent());
						map.put("stop_profit_percent", p.getStopProfitPercent()==null?"":p.getStopProfitPercent());
						map.put("day_base_num", p.getDayBaseNum()==null?"":p.getDayBaseNum());
						map.put("day_base_factor", p.getDayBaseFactor()==null?"":p.getDayBaseFactor());
						map.put("main_position", p.getMainPosition()==null?"":p.getMainPosition());
						map.put("main_safe_percent", p.getMainSafePercent()==null?"":p.getMainSafePercent());
						map.put("user_add_times", p.getUserAddTimes()==null?"":p.getUserAddTimes());
						parameters.add(map);
					}
					obj.put("is_success", 1);	
					obj.put("message", "配置参数查询成功");
					obj.put("parameters", parameters);
				} else {
					obj.put("is_success", 1);
					obj.put("message", "无配置参数");
				}
			}else{
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		return obj.toString();			
	}

	/**
	 * 修改配置参数
	 * @return
	 */
	@Override
	public String changeParameter(Long id, String trade_variety, Integer day_trade_num, BigDecimal safe_money,
			BigDecimal manage_money, BigDecimal product_percent, String trade_time, String close_time, BigDecimal risk_percent,
			BigDecimal stop_loss_percent, BigDecimal stop_profit_percent, Integer day_base_num, BigDecimal day_base_factor, String update_time,
			Integer main_position, BigDecimal main_safe_percent, Integer user_add_times, String sign) {
        JSONObject obj = new JSONObject();
        JSONObject res = new JSONObject();
		try {
			//String result = DesUtil.webserviceSignVerify(sign);
			String result = "success";
			if("success".equals(result)){
				if(id == null){
					obj.put("is_success", 0);
					obj.put("message", "id为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(trade_variety == null || "".equals(trade_variety)){
					obj.put("is_success", 0);
					obj.put("message", "交易品种为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(day_trade_num == null){
					obj.put("is_success", 0);
					obj.put("message", "主账户日交易笔数为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(safe_money == null){
					obj.put("is_success", 0);
					obj.put("message", "用户保证金为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(manage_money == null){
					obj.put("is_success", 0);
					obj.put("message", "产品管理费为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(product_percent == null){
					obj.put("is_success", 0);
					obj.put("message", "产品盈利分成比例为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(trade_time == null || "".equals(trade_time)){
					obj.put("is_success", 0);
					obj.put("message", "开始交易时间为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(close_time == null || "".equals(close_time)){
					obj.put("is_success", 0);
					obj.put("message", "收盘强平时间为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(risk_percent == null){
					obj.put("is_success", 0);
					obj.put("message", "涨跌幅风控线为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(stop_loss_percent == null){
					obj.put("is_success", 0);
					obj.put("message", "止损风控线为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(stop_profit_percent == null){
					obj.put("is_success", 0);
					obj.put("message", "止盈风控线为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(day_base_num == null){
					obj.put("is_success", 0);
					obj.put("message", "单用户日基础交易笔数额度为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(day_base_factor == null){
					obj.put("is_success", 0);
					obj.put("message", "单用户日交易笔数风控系数为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(update_time == null || "".equals(update_time)){
					obj.put("is_success", 0);
					obj.put("message", "更新时间为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(main_position == null){
					obj.put("is_success", 0);
					obj.put("message", "主账户日最大可持仓为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(main_safe_percent == null){
					obj.put("is_success", 0);
					obj.put("message", "主账户安全持仓比例为空");
					System.out.println(obj);
					return obj.toString();
				}
				if(user_add_times == null){
					obj.put("is_success", 0);
					obj.put("message", "用户单边虚增倍数为空");
					System.out.println(obj);
					return obj.toString();
				}
				
				Map<String, Object> argsMap = new HashMap<String, Object>();
		        argsMap.put("id", id);
		        argsMap.put("trade_variety", trade_variety);
		        argsMap.put("day_trade_num", day_trade_num);
		        argsMap.put("safe_money", safe_money);
		        argsMap.put("manage_money", manage_money);
		        argsMap.put("product_percent", product_percent);
		        argsMap.put("trade_time", trade_time);
		        argsMap.put("close_time", close_time);
		        argsMap.put("risk_percent", risk_percent);
		        argsMap.put("stop_loss_percent", stop_loss_percent);
		        argsMap.put("stop_profit_percent", stop_profit_percent);
		        argsMap.put("day_base_num", day_base_num);
		        argsMap.put("day_base_factor", day_base_factor);
		        argsMap.put("update_time", update_time);
		        argsMap.put("main_position", main_position);
		        argsMap.put("main_safe_percent", main_safe_percent);
		        argsMap.put("user_add_times", user_add_times);
		        String html = null;
		        try {
		        	InputStream in = Client.class.getClassLoader().getResourceAsStream("trade.properties");
		    		Properties properties = new Properties();
		    		properties.load(in);
		            html = HttpRemoteUtil.orderPostMethod(properties.getProperty("trade.url")+"changeParameter.htm", argsMap);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        if (html != null && !"".equals(html)) {
		        	res = JSONObject.fromObject(html);
		        	String flag = res.get("is_success").toString();
		        	if("1".equals(flag)){
		        		obj.put("message", "配置参数修改成功");
		        	}else{
		        		obj.put("message", "配置参数修改失败");
		        	}
		        	res.put("label", "修改配置参数风控返回结果");
		        }
			}else{
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj);
		System.out.println(res);
		return obj.toString();			
	}
	
	/**
	 * 推送服务错误信息
	 * @return
	 */
	@Override
	public String pushServerLog(String errorCode, String errorMsg, String sign) {
		JSONObject obj = new JSONObject();	
		try {
			//String result = DesUtil.webserviceSignVerify(sign);
			String result = "success";
			if("success".equals(result)){
				if(errorCode == null || "".equals(errorCode)){
					obj.put("is_success", 0);
					obj.put("message", "错误码为空");
					System.out.println(obj);
					return obj.toString();
				}	
				TrdServerLog log = new TrdServerLog();
				log.setErrorCode(errorCode);
				log.setLog_time(new Date());
				if(errorMsg != null && !"".equals(errorMsg)){
					log.setErrorMsg(errorMsg);
					//log.setErrorMsg(new String(errorMsg.getBytes(), "UTF-8"));
				}
				trdServerLogService.save(log);
				obj.put("is_success", 1);	
				obj.put("message", "推送服务信息成功");
			}else{
				obj.put("is_success", 0);
				obj.put("message", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		//System.out.println(obj);
		return obj.toString();		
	}
	
}
