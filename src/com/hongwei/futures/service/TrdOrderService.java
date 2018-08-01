package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.TrdOrder;
import com.hongwei.futures.model.TrdTrade;
import com.hongwei.futures.model.TrdTradeParameter;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface TrdOrderService {
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdOrder get(Long id) ;
	
	public void save(TrdOrder entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 根据订单号查找记录
	 * @param orderNum
	 * @return
	 */
	public TrdOrder findByOrderNum(String orderNum);
	
	/**
	 * 查询订单
	 * @return
	 */
	public List<TrdOrder> findTrdOrders();
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<TrdOrder> findAllOrders();
	
	/**
	 * 查询最大订单号
	 * @return
	 */
	public String findMaxOrder();

	/**
	 * 根据订单的状态查询订单列表
	 * @param user_id 	用户ID
	 * @param state  	订单状态 
	 * @return
	 */
	public List<TrdOrder> getOrderByState(long user_id, String state);

	/**
	 * 创建订单
	 * @param user_id		用户对象
	 * @param stop_profit 
	 * @param stop_loss 
	 * @param volume 
	 * @param offset_flag 
	 * @param direction 
	 * @param instrument_id 
	 * @param orderNum 
	 * @param trdTradeParameter 
	 * @return 
	 */
	public int saveOrder(String user_id, String orderNum, String instrument_id, String direction, String offset_flag,
			String volume, String stop_loss, String stop_profit, TrdTradeParameter trdTradeParameter);
	
	/**
	 * 后台下单
	 * @return  
	 */
	public int saveCreateOrder(Long user_id, String instrument_id, Integer direction, Integer offset_flag, BigDecimal stop_profit, BigDecimal stop_loss);
	
	/**
	 * 后台平仓
	 * @return  
	 */
	public int saveCloseTrade(Long user_id, String instrument_id, Integer direction, Integer offset_flag, TrdTrade trade);

	public List<TrdOrder> queryOrderList(int i, int pageSize, Map<String, Object> map);

	public Integer countOrders(Map<String, Object> map);
}

