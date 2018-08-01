package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.TrdOrder;
import com.hongwei.futures.model.TrdTrade;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface TrdTradeService {
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdTrade get(Long id) ;
	
	public void save(TrdTrade entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 根据开仓订单号查找持仓记录
	 * @param openOrderNum
	 * @return
	 */
	public TrdTrade findByOpenOrderNum(String openOrderNum);
	
	/**
	 * 根据平仓订单号查找持仓记录
	 * @param closeOrderNum
	 * @return
	 */
	public TrdTrade findByCloseOrderNum(String closeOrderNum);
	
	/**
	 * 查询持仓
	 * @return
	 */
	public List<TrdTrade> findTrdTrades();
	
	/**
	 * 查询某用户持仓
	 * @return
	 */
	public List<TrdTrade> findTrdTradesByUser(Long userId, Integer direction);

	/**
	 * 根据用户ID和产品编号查询对应的持仓信息
	 * @param user_id		用户的ID
	 * @param instrument_id 产品编号
	 * @return
	 */
	public List<TrdTrade> getTradeyState(long user_id, String instrument_id);

	/**
	 * 根据用户id和产品编号查询持仓信息
	 * @param user_id
	 * @param instrument_id
	 * @param state 
	 * @return
	 */
	public TrdTrade findTrdTradeByUserAndInstrumentId(long user_id,
			String instrument_id, String state);
	/**
	 * 根据用户id和产品编号查询用户的结算信息
	 * @param use_id
	 * @return
	 */
	public List<TrdTrade> tlementQuery(long user_id, String instrument_id);
	
	/**
	 * 根据用户查询出用户盈利的结算单
	 * @param user_id
	 * @return
	 */
	public List<TrdTrade> findProfitOrder(long user_id);

	public List<TrdTrade> queryTradeList(int i, int pageSize, Map<String, Object> map);

	public Integer countTrades(Map<String, Object> map);
	
	public void saveCloseProcess(String instrumentId, BigDecimal closeMoney, String closeTime);
}

