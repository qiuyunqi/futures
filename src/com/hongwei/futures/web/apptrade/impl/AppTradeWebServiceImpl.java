package com.hongwei.futures.web.apptrade.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.codehaus.xfire.client.Client;

import net.sf.json.JSONObject;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.TrdKLines;
import com.hongwei.futures.model.TrdOrder;
import com.hongwei.futures.model.TrdTrade;
import com.hongwei.futures.model.TrdTradeNum;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.TrdKLinesService;
import com.hongwei.futures.service.TrdOrderService;
import com.hongwei.futures.service.TrdTradeNumService;
import com.hongwei.futures.service.TrdTradeParameterService;
import com.hongwei.futures.service.TrdTradeService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.web.apptrade.AppTradeWebService;

@SuppressWarnings("all")
public class AppTradeWebServiceImpl implements AppTradeWebService {

	@Resource
	private TrdTradeParameterService parameterService;
	@Resource
	private TrdOrderService trdOrderService;
	@Resource
	private TrdKLinesService kLinesService;
	@Resource
	private FuUserService fuUserService;
	@Resource
	private TrdTradeService trdTradeService;
	@Resource
	private TrdTradeNumService trdTradeNumService;
	
	
	/**
	 * 查询产品列表
	 * @param sign 验证码
	 */
	public String queryProductList(String user_id, String sign) {
//		String result = DesUtil.webserviceSignVerify(sign);
		JSONObject obj = new JSONObject();
		try {
//			if("success".equals(result)){ // 验证码通过
				List<TrdTradeParameter> trdTradeList = parameterService.findAllParams();
				if(trdTradeList != null && trdTradeList.size() > 0){
					List<Object> products = new ArrayList<Object>(); 
					for (TrdTradeParameter product : trdTradeList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", product.getId() == null ? "" : product.getId());
						map.put("instrument_id", product.getTradeVariety() == null ? "" : product.getTradeVariety());
						map.put("instrument_name", product.getInstrumentName() == null ? "" : product.getInstrumentName());
						map.put("market", product.getMarket() == null ? "" : product.getMarket());
						// 单用户日基础交易笔数额度
						map.put("day_base_num", product.getDayBaseNum() == null ? "" : product.getDayBaseNum());
						// 单用户日交易笔数风控系数
						map.put("day_base_factor", product.getDayBaseFactor() == null ? "" : product.getDayBaseFactor());
						String tradeTime = product.getTradeTime();
						if(tradeTime == null ){
							tradeTime = "";
						}else{
							tradeTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(tradeTime, "HH:mm"), "HH:mm");
						}
						String closeTime = product.getCloseTime();
						if(closeTime == null ){
							closeTime = "";
						}else{
							closeTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(closeTime, "HH:mm"), "HH:mm");
						}
						map.put("day_time", tradeTime + " - " + closeTime);
//						map.put("open_time", product.getTradeTime() == null ? "" : product.getTradeTime());
//						map.put("close_time", product.getCloseTime() == null ? "" : product.getCloseTime());
						if(product.getIsOpenNight() == null || product.getIsOpenNight() == 0){
							String nightOpenTime = product.getNightOpenTime();
							if(nightOpenTime == null || "".equals(nightOpenTime)){
								nightOpenTime = "";
							}else{
								nightOpenTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(nightOpenTime, "HH:mm"), "HH:mm");
							}
							
							String nightCloseTime = product.getNightCloseTime();
							if(nightCloseTime == null || "".equals(nightCloseTime)){
								nightCloseTime = "";
							}else{
								nightCloseTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(nightCloseTime, "HH:mm"), "HH:mm");
							}
							map.put("night_time", nightOpenTime + " - "+ nightCloseTime);
						}else{
							map.put("night_time", "");
						}
//						map.put("night_open_time", product.getNightOpenTime() == null ? "" : product.getNightOpenTime());
//						map.put("night_close_time", product.getNightCloseTime() == null ? "" : product.getNightCloseTime());
						// 查询这个产品 这个用户在今天下了多少比单子
						List<TrdTrade> tradeList = trdTradeService.getTradeyState(Long.parseLong(user_id), product.getTradeVariety());
						int maxTradeNum = 0;
						if(tradeList != null){
							// 根据当前时间 当前用户 当前产品的编号 查询当前计数器
							String currentDate = DateUtil.getStrFromDate(new Date(), "yyyy-MM-dd");
							// 今天到现在交易的笔数
							TrdTradeNum tradeNum = trdTradeNumService.findNumByDateAndInstrumentId(Long.parseLong(user_id), product.getTradeVariety(), currentDate);
							// 用户今天最大的交易笔数
							maxTradeNum = product.getDayBaseFactor().multiply(new BigDecimal(product.getDayBaseNum())).intValue();
							// 当天交易笔数 = 最大交易笔数-现在已经交易的笔数
							Integer num = 0;
							if(tradeNum == null){
								num = 0;
							}else{
								num = tradeNum.getNum();
							}
							int canOpen = maxTradeNum-num;
							map.put("today_buy_volume", canOpen);
							// 主账户日最大可持仓
						}else{
							map.put("today_buy_volume", 0);
						}
						// 单用户日最大可交易次数  10
						map.put("today_buy_max_volume", maxTradeNum);
						
						// 产品Logo
						map.put("product_ico", product.getProductIco() == null ? "" : product.getProductIco());
						BigDecimal safeMoney = product.getSafeMoney();
						// 止损线
						map.put("stop_loss_percent", product.getStopLossPercent().multiply(safeMoney) == null ? "" : (product.getStopLossPercent().multiply(safeMoney)).multiply(new BigDecimal(-1)));
						// 止盈线
						map.put("stop_profit_percent", product.getStopProfitPercent().multiply(safeMoney) == null ? "" : product.getStopProfitPercent().multiply(safeMoney));
						// 强制平仓的时间
						map.put("break_close_time", product.getBreakCloseTime() == null ? "" : product.getBreakCloseTime());
						map.put("manage_money", product.getManageMoney());
						map.put("safe_money", product.getSafeMoney());
						// 乘数
						map.put("smallest_price_change", product.getSmallestPriceChange() == null ? "" : product.getSmallestPriceChange());
						// 开盘的时间段
						String timeSlot = product.getTimeSlot();
//						String timeSlot = "9:15-11:30#13:30-15:30#21:00-01:30";
						if(timeSlot != null){
							String[] times = timeSlot.split("#");
							map.put("times", times);
						}else{
							map.put("times", "");
						}
						products.add(map);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询成功");
					obj.put("products", products);
				}else {
					obj.put("is_success", 2);
					obj.put("message", "无产品数据");
				}
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}
	
	/**
	 * 根据产品的编号查询该产品的历史行情
	 * 分时
	 * @param instrumentId  产品编号
	 * @param sign	验证码
	 */
	public String queryTodayTick(String instrumentId, String a) {
		JSONObject obj = new JSONObject();	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
//			String result = DesUtil.webserviceSignVerify(sign);
//			if("success".equals(result)){
				if(instrumentId == null || "".equals(instrumentId)){
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
				String date = DateUtil.getStrFromDate(new Date(), "yyyyMMdd");
				List<Object> ticks = new ArrayList<Object>();
				BigDecimal bigDecimal = null;
				int index = 0;
				do{
					String sql = "select * from trd_"+instrumentId+ " where DATE_FORMAT(str_to_date(update_time, '%H:%i:%s'), '%s') = '00' AND  trading_day="+date;
					System.out.println("sql=====>"+sql);
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if(rs.next()){
						obj.put("trading_day", rs.getString("trading_day"));
					}else {
						obj.put("trading_day", "");
					}
					while (rs.next()) {
						Map<String, Object> map = new HashMap<String, Object>();
		                map.put("bid_price1", rs.getString("bid_price1") == null ? "" : rs.getString("bid_price1"));
		                map.put("bid_volume1", rs.getString("bid_volume1") == null ? "" : rs.getString("bid_volume1"));
		                map.put("update_time", rs.getString("update_time") == null ? "" : rs.getString("update_time"));
		                bigDecimal = rs.getBigDecimal("pre_settlement_price");
		                ticks.add(map);
			        }
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(DateUtil.getDateFromString(date, "yyyyMMdd"));
					calendar.add(Calendar.DATE, -1);
					Date time = calendar.getTime();
					date = DateUtil.getStrFromDate(time, "yyyyMMdd");
					index ++;
					if(null != ticks && ticks.size() > 0) {
						index = 40;
					}
					//index  = 40;
					if(index > 30){
						break;
					}
				}while(ticks == null || ticks.size() <= 0);
				obj.put("pre_settlement_price", bigDecimal == null ? 0 : bigDecimal);
//				根据产品编号查询查询产品参数表  获取到产品的开始交易-结束交易时间
				// 行情时间   白天交易的时间  几点到几点
				TrdTradeParameter product = parameterService.findByTradeVariety(instrumentId);
				if(null == product) {
					obj.put("is_success", 0);
					obj.put("message", "产品不存在");
					obj.put("is_success", 0);
					
				}
				String tradeTime = product.getTradeTime();
				if(null != ticks && ticks.size() > 0) {
					Map<String, Object> m = (Map)ticks.get(0);
					tradeTime = (String) m.get("update_time");
					if(null == tradeTime){
						tradeTime = "";
					}else{
						tradeTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(tradeTime, "HH:mm"), "HH:mm");
					}
				}else {
					tradeTime = "";
				}
				String closeTime = product.getCloseTime();
				if(closeTime == null ){
					closeTime = "";
				}else{
					closeTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(closeTime, "HH:mm"), "HH:mm");
				}
				obj.put("day_time", tradeTime + " - " + closeTime);
				String nightOpenTime = product.getNightOpenTime();
				if(nightOpenTime == null ){
					nightOpenTime = "";
				}else{
					nightOpenTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(nightOpenTime, "HH:mm"), "HH:mm");
				}
				
				String nightCloseTime = product.getNightCloseTime();
				if(nightCloseTime == null ){
					nightCloseTime = "";
				}else{
					nightCloseTime = DateUtil.getStrFromDate(DateUtil.getDateFromString(nightCloseTime, "HH:mm"), "HH:mm");
				}
				obj.put("night_time", nightOpenTime + " - "+ nightCloseTime);
				
				
				obj.put("is_success", 1);
				obj.put("message", "历史行情查询成功");
				obj.put("ticks", ticks);
//			}else{
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
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
		return obj.toString();		
	}

	/**
	 * 返回日K线历史数据
	 * @param instrument_id         产品编号
	 * @param sign 					验证码
	 */
	public String queryKLine(String instrument_id, String sign) {
//		String result = DesUtil.webserviceSignVerify(sign);
		JSONObject obj = new JSONObject();
		try {
//			if("success".equals(result)){
				Map<String , Object> mapQuery = new HashMap<String, Object>();
				if(instrument_id == null || "".equals(instrument_id)){
					obj.put("is_success", 0);
					obj.put("message", "合约为空");
					System.out.println(obj);
					return obj.toString();
				}else{
					mapQuery.put("instrumentId", instrument_id);
				}
				
				mapQuery.put("orderby", "order By tradingDay DESC");
				List<TrdKLines> klineList = kLinesService.queryKlinesList(0, 60, mapQuery);
				List<Object> klines = new ArrayList<Object>();
				for (TrdKLines trdKLines : klineList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("trading_day", trdKLines.getTradingDay() == null ? "" : trdKLines.getTradingDay());
					map.put("open_price", trdKLines.getOpenPrice() == null ? "" : trdKLines.getOpenPrice());
					map.put("highest_price", trdKLines.getHighestPrice() == null ? "" : trdKLines.getHighestPrice());
					map.put("lowest_price", trdKLines.getLowestPrice() == null ? "" : trdKLines.getLowestPrice());
					map.put("close_price", trdKLines.getClosePrice() == null ? "" : trdKLines.getClosePrice());
					klines.add(map);
				}
				obj.put("is_success", 1);
				obj.put("message", "日K线历史数据查询成功");
				obj.put("instrument_id", instrument_id);
			    obj.put("klines", klines);
//			}else{
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}

	/**
	 * 提交订单到服务端。报单分开仓报单和平仓报单，分别产生唯一订单号
	 * @param user_id                 用户编号(必填)
	 * @param instrument_id           合约产品编号(必填)
	 * @param direction               买0卖1 (必填)  
	 * @param offset_flag             开0 平1 (必填)
	 * @param volume                  手数(必填) 默认是1
	 * @param stop_profit			     止盈           默认是0
	 * @param stop_loss               止损           默认是0
	 * @param sign                    验证码
	 * @return
	 */
	public String order(String trdTradeId, String user_id, String instrument_id, String direction,
			String offset_flag, String volume, String stop_profit,
			String stop_loss, String sign) {
		
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try {
//			if("success".equals(result)){
				if(user_id == null || "".equals(user_id)){
					obj.put("is_success", 0);
					obj.put("message", "用户没有登录");
					return obj.toString();
				}
				if(instrument_id == null || "".equals(instrument_id)){
					obj.put("is_success", 0);
					obj.put("message", "合约为空");
					return obj.toString();
				}
				if(direction == null || "".equals(direction)){
					obj.put("is_success", 0);
					obj.put("message", "买卖为空");
					return obj.toString();
				}
				if(offset_flag == null || "".equals(offset_flag)){
					obj.put("is_success", 0);
					obj.put("message", "请选择开仓还是平仓");
					return obj.toString();
				}
				if(volume == null || "".equals(volume)){
					obj.put("is_success", 0);
					obj.put("message", "手数为空");
					return obj.toString();
				}
				if(stop_profit == null || "".equals(stop_profit)){
					stop_profit = "0";
				}
				if(stop_loss == null || "".equals(stop_loss)){
					stop_loss = "0";
				}
//			           根据产品 instrument_id 查询产品现在是否在交易时间
				TrdTradeParameter trdTradeParameter = parameterService.findByTradeVariety(instrument_id);
				if(null == trdTradeParameter || "".equals(trdTradeParameter)){
					obj.put("is_success", 0);
					obj.put("message", instrument_id+"产品不存在");
					return obj.toString();
				}
				long openTime = DateUtil.getDateFromString(trdTradeParameter.getTradeTime(), "HH:mm:ss").getTime();
				long closeTime = DateUtil.getDateFromString(trdTradeParameter.getCloseTime(), "HH:mm:ss").getTime();
				long nightOpenTime = DateUtil.getDateFromString(trdTradeParameter.getNightOpenTime(), "HH:mm:ss").getTime();
				long nightCloseTime = DateUtil.getDateFromString(trdTradeParameter.getNightCloseTime(), "HH:mm:ss").getTime();
				long curreDate = DateUtil.getDateFromString(DateUtil.getStrFromDate(new Date(), "HH:mm:ss"), "HH:mm:ss").getTime();
				
				// 09:15-11:30#13:00-15:15
				String timeSlot = trdTradeParameter.getTimeSlot();
				if(timeSlot != null){
					String[] times = timeSlot.split("#");
					List<String> list = new ArrayList<String>();
					// list "09:15-11:30", 1:"13:00-15:15"
					for (String time : times) {
						list.add(time);
					}
					List<HashMap> t = new ArrayList<HashMap>();
					if(null != list && list.size() > 0){
						for(String time : list) {
							String[] ts = time.split("-");
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("solt1", ts[0]);
							map.put("solt2", ts[1]);
							t.add(map);
						}
					}
					
					if(null != t && t.size() > 0) {
						boolean falg = true;
						for(Map time : t) {
							String solt1 = (String) time.get("solt1");
							String solt2 = (String) time.get("solt2");
							long start = DateUtil.getDateFromString(solt1, "HH:mm").getTime();
							long end = DateUtil.getDateFromString(solt2, "HH:mm").getTime();
							long curr = DateUtil.getDateFromString(DateUtil.getStrFromDate(new Date(), "HH:mm"), "HH:mm").getTime();

							if(!(curr > start && curr < end)){
								falg = false;
							}else {
								falg = true;
								break;
							}
						}
						if(!falg){
							obj.put("is_success", 0);
							obj.put("message", "没有到交易时间");
							System.out.println(obj);
							return obj.toString();
						}
					}
				}
				
				//判断强平的时间后 不能下单 
				long breakCloseTime = DateUtil.getDateFromString(trdTradeParameter.getBreakCloseTime(), "HH:mm:ss").getTime();
				if(curreDate >= breakCloseTime){
					obj.put("is_success", 0);
					obj.put("message", instrument_id+"交易时间已经到了");
					return obj.toString();
				}
				if(!((curreDate > openTime && curreDate < closeTime) || (curreDate > nightOpenTime && curreDate < nightCloseTime))){
					obj.put("is_success", 0);
					obj.put("message", instrument_id+"还没有到交易时间");
					return obj.toString();
				}
				// 查询这个产品 这个用户在今天下了多少比单子
				List<TrdTrade> tradeList = trdTradeService.getTradeyState(Long.parseLong(user_id), trdTradeParameter.getTradeVariety());
				int num = tradeList.size(); // 已经持仓的产品数目
				Integer mainPosition = trdTradeParameter.getMainPosition();
				BigDecimal mainSafePercent = trdTradeParameter.getMainSafePercent();
				Integer userAddTimes = trdTradeParameter.getUserAddTimes();
				// tradeParam.mainPosition*tradeParam.mainSafePercent*tradeParam.userAddTimes
				// 用户最大可持仓(总) 25 
				int positionNum = mainSafePercent.multiply(new BigDecimal(mainPosition*userAddTimes)).intValue();
				int ret = 0;
//				String orderNum = trdOrderService.findMaxOrder();
				if(Integer.parseInt(offset_flag) == 0){// 开仓
					// 当天交易笔数
					int canOpen = positionNum-num;
					if(canOpen <= 0){
						obj.put("is_success", 0);
						obj.put("message", "到达当前最大的持仓");
						return obj.toString();
					}
					// tradeParam.dayBaseNum*tradeParam.dayBaseFactor
					// 单用户日最大可交易次数  10
					int maxTradeNum = trdTradeParameter.getDayBaseFactor().multiply(new BigDecimal(trdTradeParameter.getDayBaseNum())).intValue();
					// 根据当前时间 当前用户 当前产品的编号 查询当前计数器
					String currentDate = DateUtil.getStrFromDate(new Date(), "yyyy-MM-dd");
					TrdTradeNum tradeNum = trdTradeNumService.findNumByDateAndInstrumentId(Long.parseLong(user_id), instrument_id, currentDate);
					int hah = 0;
					if(tradeNum != null){
						hah = tradeNum.getNum();
					}
					if(maxTradeNum- hah<= 0){
						obj.put("is_success", 0);
						obj.put("message", "到达最大交易笔数");
						return obj.toString();
					}
//					saveCreateOrder(Long user_id, String instrument_id, Integer direction, Integer offset_flag, BigDecimal , BigDecimal stop_loss);
					ret = trdOrderService.saveCreateOrder(Long.parseLong(user_id), instrument_id, Integer.parseInt(direction), Integer.parseInt(offset_flag), new BigDecimal(stop_profit), new BigDecimal(stop_loss));
					if(ret == 1 && tradeNum != null){
						tradeNum.setNum(tradeNum.getNum()+1);
						trdTradeNumService.save(tradeNum);
					}
					if(ret == 1 && tradeNum == null){
						TrdTradeNum trdNum = new TrdTradeNum();
						trdNum.setDateTime(DateUtil.getStrFromDate(new Date(), "yyyy-MM-dd"));
						trdNum.setInstrumentId(instrument_id);
						trdNum.setNum(1);
						trdNum.setUserId(Long.parseLong(user_id));
						trdTradeNumService.save(trdNum);
					}
					
				}else {
					if(trdTradeId == null || "".equals(trdTradeId)){
						obj.put("is_success", 0);
						obj.put("message", "trd_trade_id不能为空");
						return obj.toString();
					}
					TrdTrade trade = trdTradeService.get(Long.parseLong(trdTradeId));
					if(trade.getCloseProfit() != null){
						obj.put("is_success", 0);
						obj.put("message", trade.getId()+"订单已经交易过,请刷新");
						return obj.toString();
					}
					Integer direction1 = trade.getDirection();
					int offsetFlag = 1 ;
					ret = trdOrderService.saveCloseTrade(Long.parseLong(user_id), instrument_id, direction1, offsetFlag, trade);
				}
				/**
				 * return  0 : 直接创建订单失败
				 * return  1 : 整体创建订单成功
				 * return  2 : 用户余额不足
				 * return  3 : 风控失败
				 * return  4 : 传入风控的数据不对
				 */
//				int ret = trdOrderService.saveOrder(user_id, orderNum, instrument_id, direction, offset_flag, volume, stop_loss, stop_profit, trdTradeParameter);
				
				if(ret == 1){
					obj.put("is_success", 1);
					obj.put("message", "创建订单成功");
				}
				
				if(ret == -6){
					obj.put("is_success", 0);
					obj.put("message", "创建订单失败");
				}
				if(ret == -7){
					obj.put("is_success", 0);
					obj.put("message", "费用不足,请充值");
				}
				
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		} 
		return obj.toString();
	}

	@Override
	public String orderQuery(String user_id, String sign, String state) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
//		if("success".equals(result)){
			try {
				if(user_id == null || "".equals(user_id)){
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					return obj.toString();
				}
				if(state == null || "".equals(state)){
					obj.put("is_success", 0);
					obj.put("message", "订单状态为空");
					return obj.toString();
				}
				if(Integer.parseInt(state) != 0){
					obj.put("is_success", 0);
					obj.put("message", "参数值不正确");
					return obj.toString();
				}
				
				List<TrdOrder> orderList = trdOrderService.getOrderByState(Long.parseLong(user_id), state);
				if(null != orderList && orderList.size() > 0){
					List<Object> orders = new ArrayList<Object>();
					for (TrdOrder trdOrder : orderList) {
						Map<String , Object> map = new HashMap<String, Object>();
						map.put("id", trdOrder.getId() == null ? "" : trdOrder.getId());
						map.put("user_id", trdOrder.getFuUser().getId() == null ? "" : trdOrder.getFuUser().getId());
						map.put("order_num", trdOrder.getOrderNum() == null ? "" : trdOrder.getOrderNum());
						map.put("instrument_id", trdOrder.getInstrumentId() == null ? "" : trdOrder.getInstrumentId());
						map.put("direction", trdOrder.getDirection() == null ? "" : trdOrder.getDirection());
						map.put("offset_flag", trdOrder.getOffsetFlag() == null ? "" : trdOrder.getOffsetFlag());
						map.put("volume", trdOrder.getVolume() == null ? "" : trdOrder.getVolume());
						map.put("price", trdOrder.getPrice() == null ? "" : trdOrder.getPrice());
						map.put("money", trdOrder.getMoney() == null ? "" : trdOrder.getMoney());
						map.put("trade_datetime", trdOrder.getTradeDateTime() == null ? "" : trdOrder.getTradeDateTime());
						map.put("failure_code", trdOrder.getFailureCode() == null ? "" : trdOrder.getFailureCode());
						map.put("failure_msg", trdOrder.getFailureMsg() == null ? "" : trdOrder.getFailureMsg());
						orders.add(map);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询订单成功");
					obj.put("orders", orders);
				}else {
					obj.put("is_success", 2);
					obj.put("message", "没有该状态下的订单");
				}
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("is_success", 0);
				obj.put("message", "invalid request");
			}
//		}else {
//			obj.put("is_success", 0);
//			obj.put("message", result);
//		}
		return obj.toString();
	}

	/**
	 *  查询该用户的持仓信息
	 *  结算查询
	 *  state  1 代表持有   2  代表结算
	 *  0：持仓中，
           1：平仓中，
		   2：平仓成功，
		   3：平仓失败，
		   4：强平中，
		   5：强平成功，
		   6：强平失败

	 */
	public String trdTradeQuery(String user_id, String instrument_id, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
			if(user_id == null || "".equals(user_id)){
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}
			if(instrument_id == null || "".equals(instrument_id)){
				obj.put("is_success", 0);
				obj.put("message", "产品编号不存在!");
				return obj.toString();
			}
//			if(result != null && "success".equals(result)){
				// 查询用户对应的持仓信息
				List<TrdTrade> tradeList = trdTradeService.getTradeyState(Long.parseLong(user_id), instrument_id);
				if(null != tradeList && tradeList.size() > 0){
					List<Object> trades = new ArrayList<Object>();
					for (TrdTrade trdTrade : tradeList) {
						Map<String, Object> map = new HashMap<String, Object>();
						// 根据持仓信息的产品id查询产品参数表里面的产品名称
						TrdTradeParameter tradeParameter = parameterService.findByTradeVariety(trdTrade.getInstrumentId());
						map.put("product_name", tradeParameter.getInstrumentName() == null ? "" : tradeParameter.getInstrumentName());
						map.put("trade_id", trdTrade.getId() == null ? "" : trdTrade.getId());
						map.put("instrument_id", trdTrade.getInstrumentId() == null ? "" : trdTrade.getInstrumentId());
						map.put("direction", trdTrade.getDirection() == null ? "" : trdTrade.getDirection());
						map.put("offset_flag", trdTrade.getOffsetFlag() == null ? "" : trdTrade.getOffsetFlag());
						// 多少手
						map.put("open_volume", trdTrade.getOpenVolume() == null ? "" : trdTrade.getOpenVolume());
						// 止损线
						BigDecimal stopLossPercent = tradeParameter.getStopLossPercent();
						BigDecimal safeMoney = trdTrade.getSafeMoney();
						if(safeMoney != null && stopLossPercent != null){
							BigDecimal stopLossPrice = safeMoney.multiply(stopLossPercent);
							map.put("stop_loss_percent", stopLossPrice);
						}else {
							obj.put("is_success", 0);
							obj.put("message", "订单没有设置保证金或者没有设置止损风控线");
							return obj.toString();
						}
						map.put("open_price", trdTrade.getOpenPrice() == null ? "" : trdTrade.getOpenPrice());
						trades.add(map);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询持仓信息单成功");
					obj.put("trades", trades);
				}else {
					obj.put("is_success", 2);
					obj.put("message", "没有该状态下的持仓信息");
				}
//			}else{
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}

	/**
	 * 结算详情
	 * @param user_id			用户id
	 * @param instrument_id		产品编号
	 * @param sign				验证码
	 * @return
	 */
	public String setTlement(String user_id, String trade_id, String instrument_id, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
			if(user_id == null || "".equals(user_id)){
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}
			if(instrument_id == null || "".equals(instrument_id)){
				obj.put("is_success", 0);
				obj.put("message", "合约为空");
				return obj.toString();
			}
//		/	if("success".equals(result)){
				TrdTrade trdTrade = trdTradeService.get(Long.parseLong(trade_id));
				if(null != trdTrade){
					TrdTradeParameter tradeParameter = parameterService.findByTradeVariety(trdTrade.getInstrumentId());
					Map<String, Object> map = new HashMap<String, Object>();
					// 交易单号  IF150810438313
					map.put("close_order_num", trdTrade.getCloseOrderNum() == null ? "" : trdTrade.getCloseOrderNum());
					// 下单人      懂小球
					FuUser fuUser = fuUserService.get(Long.parseLong(user_id));
					map.put("user_name", fuUser.getUserName() == null ? fuUser.getNickName() : fuUser.getUserName());
					// 投资人       超级合伙人联盟
					map.put("investor", tradeParameter.getInvestor() == null ? "" : tradeParameter.getInvestor());
					// 交易品种   IF1508
					map.put("instrument_id", instrument_id);
					// 交易数量   1手
					map.put("volume", trdTrade.getCloseVolume() == null ? "" : trdTrade.getCloseVolume());
					// 交易方向   看多
					map.put("direction", trdTrade.getDirection() == null ? "" : trdTrade.getDirection());
					map.put("offset_flag", trdTrade.getOffsetFlag() == null ? "" : trdTrade.getOffsetFlag());
					// 买入价    
					map.put("open_price", trdTrade.getOpenPrice() == null ? "" : trdTrade.getOpenPrice());
					// 卖出价    
					map.put("close_price", trdTrade.getClosePrice() == null ? "" : trdTrade.getClosePrice());
					// 买入时间    08-10 09:30:30
					map.put("open_dataTime", DateUtil.getStrFromDate(trdTrade.getOpenDateTime(), "yyyy-MM-dd HH:mm") == null ? "" : DateUtil.getStrFromDate(trdTrade.getOpenDateTime(), "yyyy-MM-dd HH:mm"));
					// 卖出时间    08-12 10:30:39
					map.put("close_dataTime", DateUtil.getStrFromDate(trdTrade.getCloseDateTime(), "yyyy-MM-dd HH:mm") == null ? "" : DateUtil.getStrFromDate(trdTrade.getCloseDateTime(), "yyyy-MM-dd HH:mm"));
					// 买入类型    市价买入
					map.put("open_type", tradeParameter.getOpenType() == null ? "" : tradeParameter.getOpenType());
					// 卖出类型    到时间终止
					map.put("close_type", trdTrade.getCloseType() == null ? "" : trdTrade.getCloseType());
					// 交易盈亏
					map.put("close_profit", trdTrade.getCloseProfit() == null ? "" : trdTrade.getCloseProfit());
					// 盈利分配
					BigDecimal closeProfit = trdTrade.getCloseProfit();
					if(closeProfit.compareTo(BigDecimal.ZERO) == 1){ // >0
						map.put("ylfp",closeProfit.multiply(tradeParameter.getProductPercent()));
					}else {
						map.put("ylfp", 0);
					}
					obj.put("is_success", 1);
					obj.put("message", "结算单详情查询成功");
					obj.put("trdTrade", map);
				}else {
					obj.put("is_success", 2);
					obj.put("message", "没有结算单");
				}
				
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.out.println(obj.toString());
		return obj.toString();
	}

	// 查询用户的结算信息
	public String tlementQuery(String user_id, String instrument_id, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
//		String result = "success";
		try{
			if(user_id == null || "".equals(user_id)){
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}
//			if(result != null && "success".equals(result)){
				// 查询用户对应的结算单信息
				List<TrdTrade> tradeList = trdTradeService.tlementQuery(Long.parseLong(user_id), instrument_id);
				if(null != tradeList && tradeList.size() > 0){
					List<Object> trades = new ArrayList<Object>();
					for (TrdTrade trdTrade : tradeList) {
						Map<String, Object> map = new HashMap<String, Object>();
						// 根据持仓信息的产品id查询产品参数表里面的产品名称
						TrdTradeParameter tradeParameter = parameterService.findByTradeVariety(trdTrade.getInstrumentId());
						map.put("product_name", tradeParameter.getInstrumentName() == null ? "" : tradeParameter.getInstrumentName());
						map.put("trade_id", trdTrade.getId() == null ? "" : trdTrade.getId());
						map.put("instrument_id", trdTrade.getInstrumentId() == null ? "" : trdTrade.getInstrumentId());
						map.put("direction", trdTrade.getDirection() == null ? "" : trdTrade.getDirection());
						map.put("offset_flag", trdTrade.getOffsetFlag() == null ? "" : trdTrade.getOffsetFlag());
						// 多少手
						map.put("close_volume", trdTrade.getCloseVolume() == null ? "" : trdTrade.getCloseVolume());
						// 交易盈亏
						map.put("close_profit", trdTrade.getCloseProfit() == null ? "" : trdTrade.getCloseProfit());
						// 盈利分配
						BigDecimal closeProfit = trdTrade.getCloseProfit();
						BigDecimal subtractMoney = closeProfit.subtract(trdTrade.getSafeMoney());
						if(subtractMoney.compareTo(BigDecimal.ZERO) == 1){ // >0
							map.put("ylfp",closeProfit.multiply(tradeParameter.getProductPercent()));
						}else {
							map.put("ylfp", 0);
						}
						trades.add(map);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询结算信息单成功");
					obj.put("trades", trades);
				}else {
					obj.put("is_success", 2);
					obj.put("message", "没有该状态下的结算信息");
				}
//			}else{
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}

	// 查询所有结算单的盈利 以及盈利的笔数 
	public String sumProfit(String user_id, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
			if(user_id == null || "".equals(user_id)){
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				return obj.toString();
			}
//			if(result != null && "success".equals(result)){
				// 查询用户对应的结算单信息
				List<TrdTrade> tradeList = trdTradeService.findProfitOrder(Long.parseLong(user_id));
				BigDecimal sumProfit = new BigDecimal(0);
				if(null != tradeList && tradeList.size() > 0){
					int num = 0;
					for (TrdTrade trdTrade : tradeList) {
						//TrdTradeParameter tradeParameter = parameterService.findByTradeVariety(trdTrade.getInstrumentId());
						BigDecimal closeProfit = trdTrade.getCloseProfit();
						if(closeProfit.compareTo(BigDecimal.ZERO) == 1){
							num += 1;
						}
						sumProfit = sumProfit.add(closeProfit);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询盈利信息成功!");
					obj.put("sum_profit", sumProfit);
					obj.put("sum_volume", num);
				}else {
					obj.put("is_success", 1);
					obj.put("message", "查询盈利信息成功!");
					obj.put("sum_profit", 0.00);
					obj.put("sum_volume", 0);
				}
//			}else{
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}

}