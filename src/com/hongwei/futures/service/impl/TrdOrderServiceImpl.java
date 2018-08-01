package com.hongwei.futures.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.dao.TrdOrderDao;
import com.hongwei.futures.dao.TrdTradeDao;
import com.hongwei.futures.dao.TrdTradeParameterDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.TrdOrder;
import com.hongwei.futures.model.TrdTrade;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.TrdOrderService;
import com.hongwei.futures.util.HttpRemoteUtil;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 *
 */
@SuppressWarnings("all")
@Service
public class TrdOrderServiceImpl implements TrdOrderService {
	
	@Autowired
	private TrdOrderDao trdOrderDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private TrdTradeParameterDao trdTradeParameterDao;
	@Autowired
	private TrdTradeDao trdTradeDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdOrder get(Long id) {
		return trdOrderDao.get(id);
	}
	
	public void save(TrdOrder entity) {
		trdOrderDao.save(entity);
	}
	
	public void delete(Long id) {
		trdOrderDao.delete(id);
	}

	@Override
	public TrdOrder findByOrderNum(String orderNum) {
		return trdOrderDao.findByOrderNum(orderNum);
	}

	@Override
	public List<TrdOrder> findTrdOrders() {
		return trdOrderDao.findTrdOrders();
	}

	@Override
	public List<TrdOrder> findAllOrders() {
		return trdOrderDao.findAllOrders();
	}

	@Override
	public String findMaxOrder() {
		return trdOrderDao.findMaxOrder();
	}

	public List<TrdOrder> getOrderByState(long user_id, String state) {
		return trdOrderDao.getOrderByState(user_id, state);
	}

	/**
	 * return  0 : 直接创建订单失败
	 * return  1 : 整体创建订单成功
	 * return  2 : 用户余额不足
	 * return  3 : 风控失败
	 * return  4 : 传入风控的数据不对
	 */
	public int saveOrder(String user_id, String orderNum,
			String instrument_id, String direction, String offset_flag,
			String volume, String stop_loss, String stop_profit, TrdTradeParameter trdTradeParameter) {

		// 判断是开仓还平仓
		
		TrdOrder newOrder = new TrdOrder();
		// 开多就是下手买单  扣掉保证金和管理费
		if(Integer.parseInt(offset_flag) == 0){ // 开仓
			BigDecimal safeMoney = trdTradeParameter.getSafeMoney();
			BigDecimal manageMoney = trdTradeParameter.getManageMoney();
			if(safeMoney == null || manageMoney ==null){
				return 2;
			}
			// 根据user_id 查询该用户
			FuUser fuUser = fuUserService.findFuUserById(Long.parseLong(user_id));
			if( fuUser.getAccountBalance().compareTo(safeMoney.add(manageMoney)) == -1){
				return 2;
			}
			// 冻结资金
			BigDecimal totelMoney = safeMoney.add(manageMoney);
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(totelMoney));//余额扣除风险保证金+管理费
			fuUserDao.save(fuUser);
			
			String html = ckeckOrder(user_id, orderNum, instrument_id, direction, offset_flag, volume, stop_loss, stop_profit);
	        if(null != html && !"".equals(html)){
	        	JSONObject objStr = new JSONObject().fromObject(html);
	        	String re = objStr.getString("is_success");
	        	if("1".equals(re)){ // 风控返回信息是成功的
	    			// app 扣费
	    			moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 20, totelMoney, fuUser.getAccountBalance(), false);
	    			// 插入订单开仓的费用和管理费
	    			newOrder.setSafeMoney(safeMoney);
	    			newOrder.setManageMoney(manageMoney);
	        	}else{
	        		// 返还冻结的资金
	        		fuUser.setAccountBalance(fuUser.getAccountBalance().add(totelMoney));
	    			fuUserDao.save(fuUser);
	        		return 3;
	        	}
        	
	        }else {
	        	return 4;
	        }
		}else { // 平仓
			String html = ckeckOrder(user_id, orderNum, instrument_id, direction, offset_flag, volume, stop_loss, stop_profit);
			 if(null != html && !"".equals(html)){
	        	JSONObject objStr = new JSONObject().fromObject(html);
	        	String re = objStr.getString("is_success");
	        	if(!"1".equals(re)){ // 风控返回信息是失败的
	        		return 3;
	        	}
			 }else {
				 return 4;
			 }
		}
		 
		//创建订单记录
		newOrder.setFuUser(fuUserDao.get(Long.parseLong(user_id)));
		newOrder.setOrderNum(orderNum);
		newOrder.setInstrumentId(instrument_id);
		newOrder.setDirection(Integer.parseInt(direction));
		newOrder.setOffsetFlag(Integer.parseInt(offset_flag));
		newOrder.setVolume(Integer.parseInt(volume));
		newOrder.setTradeDateTime(new Date());
		newOrder.setStopLoss(new BigDecimal(stop_loss));
		newOrder.setStopProfit(new BigDecimal(stop_profit));
		newOrder.setState(0);
		trdOrderDao.save(newOrder);
		return 1;
		
	}
	
	/**
	 * 交由风控检测订单
	 * @param user_id
	 * @param orderNum
	 * @param instrument_id
	 * @param direction
	 * @param offset_flag
	 * @param volume
	 * @param stop_loss
	 * @param stop_profit
	 * @return
	 */
	private static String ckeckOrder(String user_id, String orderNum,
			String instrument_id, String direction, String offset_flag,
			String volume, String stop_loss, String stop_profit){
		// 访问风控
		Map<String, Object> argsMap = new HashMap<String, Object>();
        argsMap.put("user_id", user_id);
        argsMap.put("order_num", orderNum);
        argsMap.put("instrument_id", instrument_id);
        argsMap.put("direction", direction);
        argsMap.put("offset_flag", offset_flag);
        argsMap.put("volume", volume);
        argsMap.put("stop_profit", stop_profit);
        argsMap.put("stop_loss", stop_loss);
        String html = null;
        try {
        	InputStream in = Client.class.getClassLoader().getResourceAsStream("trade.properties");
    		Properties properties = new Properties();
    		properties.load(in);
            html = HttpRemoteUtil.orderPostMethod(properties.getProperty("trade.url")+"order.htm", argsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return html;
	}

	/**
	 * 后台下单
	 * @return  
	 */
	@Override
	public int saveCreateOrder(Long user_id, String instrument_id, Integer direction, Integer offset_flag, BigDecimal stop_profit, BigDecimal stop_loss) {
		FuUser fuUser = fuUserDao.get(user_id);
		TrdTradeParameter ttp = trdTradeParameterDao.findByTradeVariety(instrument_id);
		BigDecimal actualRiskPercent = new BigDecimal(Math.abs(ttp.getActualRiskPercent().doubleValue()));
		if(actualRiskPercent.compareTo(ttp.getRiskPercent()) != -1){
			//行情波动较大,不允许下单
			return -8;
		}else{
			//判断用户余额是否足够扣保证金和管理费
			if(fuUser.getAccountBalance().compareTo(ttp.getSafeMoney().add(ttp.getManageMoney())) != -1){
				//系统生成订单号,初始值10000000,默认下单1手
		        String order_num = trdOrderDao.findMaxOrder();
				//创建订单记录
	    		TrdOrder newOrder = new TrdOrder();
				newOrder.setFuUser(fuUserDao.get(user_id));
				newOrder.setOrderNum(order_num);
				newOrder.setInstrumentId(instrument_id);
				newOrder.setDirection(direction);
				newOrder.setOffsetFlag(offset_flag);
				newOrder.setVolume(1);
				newOrder.setStopProfit(stop_profit);
				newOrder.setStopLoss(stop_loss);
				newOrder.setSafeMoney(ttp.getSafeMoney());
				newOrder.setManageMoney(ttp.getManageMoney());
				newOrder.setCreateTime(new Date());
				//扣保证金和管理费
				//写保证金流水
				moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 21, ttp.getSafeMoney(), fuUser.getAccountBalance().subtract(ttp.getSafeMoney()), false);
				//写管理费流水
				moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 22, ttp.getManageMoney(),fuUser.getAccountBalance().subtract(ttp.getSafeMoney()).subtract(ttp.getManageMoney()), false);
				fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(ttp.getSafeMoney()).subtract(ttp.getManageMoney()));
				fuUserDao.save(fuUser);
				
				//发送请求至风控
				Map<String, Object> argsMap = new HashMap<String, Object>();
		        argsMap.put("user_id", user_id);
		        argsMap.put("order_num", order_num);
		        argsMap.put("instrument_id", instrument_id);
		        argsMap.put("direction", direction);
		        argsMap.put("offset_flag", offset_flag);
		        argsMap.put("volume", 1);
		        argsMap.put("stop_profit", stop_profit);
		        argsMap.put("stop_loss", stop_loss);
		        String html = null;
		        try {
		        	InputStream in = Client.class.getClassLoader().getResourceAsStream("trade.properties");
		    		Properties properties = new Properties();
		    		properties.load(in);
		            html = HttpRemoteUtil.orderPostMethod(properties.getProperty("trade.url")+"order.htm", argsMap);
		            System.out.println("系统时间: "+ new Date().toLocaleString()+",下单返回: "+html);
		            if (html != null && !"".equals(html)) {
			        	JSONObject obj = JSONObject.fromObject(html);
			        	String res = obj.get("is_success").toString();
			        	if("1".equals(res)){
			        		newOrder.setState(0);    //风控返回成功,待成交
			        		trdOrderDao.save(newOrder);
			        	}else{
			        		newOrder.setState(-1);   //风控返回失败,下单失败
			        		trdOrderDao.save(newOrder);
			        		return -6;
			        	}
			        }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}else{
				return -7;
			}
			return 1;
		}
	}

	/**
	 * 后台平仓
	 * @return  
	 */
	@Override
	public int saveCloseTrade(Long user_id, String instrument_id, Integer direction, Integer offset_flag, TrdTrade trade) {
		TrdTradeParameter ttp = trdTradeParameterDao.findByTradeVariety(instrument_id);
		//未配置产品交易参数
		if(ttp == null){
			return -2;
		}
        String order_num = trdOrderDao.findMaxOrder();
		//创建平仓订单记录
		TrdOrder newOrder = new TrdOrder();
		newOrder.setFuUser(fuUserDao.get(user_id));
		newOrder.setOrderNum(order_num);
		newOrder.setInstrumentId(instrument_id);
		newOrder.setDirection(direction==0?1:0);
		newOrder.setOffsetFlag(offset_flag);
		newOrder.setVolume(1);
		newOrder.setStopProfit(null);
		newOrder.setStopLoss(null);
		newOrder.setSafeMoney(ttp.getSafeMoney());
		newOrder.setManageMoney(ttp.getManageMoney());
		newOrder.setCreateTime(new Date());
			
		//发送请求至风控
		Map<String, Object> argsMap = new HashMap<String, Object>();
        argsMap.put("user_id", user_id);
        argsMap.put("order_num", order_num);
        argsMap.put("instrument_id", instrument_id);
        argsMap.put("direction", direction==0?1:0);
        argsMap.put("offset_flag", offset_flag);
        argsMap.put("volume", 1);
        argsMap.put("stop_profit", "");
        argsMap.put("stop_loss", "");
        String html = null;
        try {
        	InputStream in = Client.class.getClassLoader().getResourceAsStream("trade.properties");
    		Properties properties = new Properties();
    		properties.load(in);
            html = HttpRemoteUtil.orderPostMethod(properties.getProperty("trade.url")+"order.htm", argsMap);
            if (html != null && !"".equals(html)) {
	        	JSONObject obj = JSONObject.fromObject(html);
	        	String res = obj.get("is_success").toString();
	        	if("1".equals(res)){
	        		newOrder.setState(0);    //风控返回成功,待成交
	        		trdOrderDao.save(newOrder);
	        	}else{
	        		newOrder.setState(-1);   //风控返回失败,下单失败
	        		trdOrderDao.save(newOrder);
	        		return -6;
	        	}
	        }
            //将平仓订单号等更新到持仓记录中
            trade.setCloseOrderNum(order_num);
            trdTradeDao.save(trade);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return 1;
	}

	@Override
	public List<TrdOrder> queryOrderList(int i, int pageSize, Map<String, Object> map) {
		return trdOrderDao.queryOrderList(i, pageSize, map);
	}

	@Override
	public Integer countOrders(Map<String, Object> map) {
		return trdOrderDao.countOrders(map);
	}

	
}

