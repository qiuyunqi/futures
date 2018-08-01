package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.dao.TrdTradeDao;
import com.hongwei.futures.dao.TrdTradeParameterDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.TrdTrade;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.TrdTradeService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class TrdTradeServiceImpl implements TrdTradeService {
	
	@Autowired
	private TrdTradeDao trdTradeDao;
	
	@Autowired
	private TrdTradeParameterDao trdTradeParameterDao;
	
	@Autowired
	private FuUserDao fuUserDao;
	
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
		
	//====================== 基本 C R U D 方法  ===========================
	public TrdTrade get(Long id) {
		return trdTradeDao.get(id);
	}
	
	public void save(TrdTrade entity) {
		trdTradeDao.save(entity);
	}
	
	public void delete(Long id) {
		trdTradeDao.delete(id);
	}

	@Override
	public TrdTrade findByOpenOrderNum(String openOrderNum) {
		return trdTradeDao.findByOpenOrderNum(openOrderNum);
	}

	@Override
	public TrdTrade findByCloseOrderNum(String closeOrderNum) {
		return trdTradeDao.findByCloseOrderNum(closeOrderNum);
	}

	@Override
	public List<TrdTrade> findTrdTrades() {
		return trdTradeDao.findTrdTrades();
	}

	@Override
	public List<TrdTrade> findTrdTradesByUser(Long userId, Integer direction) {
		return trdTradeDao.findTrdTradesByUser(userId, direction);
	}

	/**
	 * 根据用户ID和状态查询对应的持仓信息
	 * @param user_id		用户的ID
	 * @return
	 */
	public List<TrdTrade> getTradeyState(long user_id, String instrument_id) {
		return trdTradeDao.getTradeyState(user_id, instrument_id);
	}

	/**
	 * 根据用户id和产品编号查询持仓信息
	 * @param user_id
	 * @param instrument_id
	 * @param state 
	 * @return
	 */
	public TrdTrade findTrdTradeByUserAndInstrumentId(long user_id,
			String instrument_id, String state) {
		if(state == null){
			state = "2";
		}
		return trdTradeDao.findTrdTradeByUserAndInstrumentId(user_id, instrument_id, state);
	}

	/**
	 * 根据用户id查询用户的结算信息
	 * @param parseLong
	 * @return
	 */
	public List<TrdTrade> tlementQuery(long user_id, String instrument_id) {
		return trdTradeDao.tlementQuery(user_id, instrument_id);
	}

	/**
	 * 根据用户查询出用户盈利的结算单
	 * @param user_id
	 * @return
	 */
	public List<TrdTrade> findProfitOrder(long user_id) {
		return trdTradeDao.findProfitOrder(user_id);
	}

	@Override
	public List<TrdTrade> queryTradeList(int i, int pageSize, Map<String, Object> map) {
		return trdTradeDao.queryTradeList(i, pageSize, map);
	}

	@Override
	public Integer countTrades(Map<String, Object> map) {
		return trdTradeDao.countTrades(map);
	}

	@Override
	public void saveCloseProcess(String instrumentId, BigDecimal closeMoney, String closeTime) {
		//查询所有持仓
		List<TrdTrade> trades = trdTradeDao.findTrdTrades();
		TrdTradeParameter trdParam = trdTradeParameterDao.findByTradeVariety(instrumentId);
		if(trades != null && trades.size()>0){
			for(TrdTrade tt:trades){
				if(tt != null){
					tt.setCloseOrderNum("0");
					tt.setCloseVolume(1);
					tt.setCloseMoney(closeMoney);
					tt.setState(2);
					tt.setUpdateTime(new Date());
					tt.setCloseType(4);
					tt.setBackDirection(tt.getDirection()==0?1:0);
					tt.setBackOffsetFlag(1);
					/* 计算平仓盈亏 
					 * 多单: (平仓成交金额-开仓成交金额)*点位单价
					 * 空单: (开仓成交金额-平仓成交金额)*点位单价
					 */
					BigDecimal close_profit = null;
					if(tt.getDirection() == 0){            //多单
						close_profit = (closeMoney.subtract(tt.getOpenMoney())).multiply(trdParam.getSmallestPriceChange());
					}else if(tt.getDirection() == 1){      //空单
						close_profit = 	(tt.getOpenMoney().subtract(closeMoney)).multiply(trdParam.getSmallestPriceChange());				
					}
					tt.setCloseProfit(close_profit);
					//将持仓状态设置为:强平成功
					tt.setState(5);
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
						fuUserDao.save(tradeUser);
					}
					trdTradeDao.save(tt);
				}
			}
		}
	}
}

