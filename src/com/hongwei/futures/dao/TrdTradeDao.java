package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.TrdTrade;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface TrdTradeDao extends BaseDao<TrdTrade, Long> {

	public TrdTrade findByOpenOrderNum(String openOrderNum);

	public TrdTrade findByCloseOrderNum(String closeOrderNum);

	public List<TrdTrade> findTrdTrades();

	public List<TrdTrade> findTrdTradesByUser(Long userId, Integer direction);
	
//	根据用户ID和状态查询对应的持仓信息
	public List<TrdTrade> getTradeyState(long user_id, String instrument_id);

	//根据用户id和结算状态以及产品编号查询持仓信息
	public TrdTrade findTrdTradeByUserAndInstrumentId(long user_id,
			String instrument_id, String state);

//	根据用户id查询用户的结算信息
	public List<TrdTrade> tlementQuery(long user_id, String instrument_id);
//	根据用户查询出用户盈利的结算单
	public List<TrdTrade> findProfitOrder(long user_id);

	public List<TrdTrade> queryTradeList(int i, int pageSize, Map<String, Object> map);

	public Integer countTrades(Map<String, Object> map);

}

