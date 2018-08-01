package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.TrdTradeDao;
import com.hongwei.futures.model.TrdTrade;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class TrdTradeDaoImpl extends BaseDaoImpl<TrdTrade, Long> implements TrdTradeDao {
	
	@Override
	public TrdTrade findByOpenOrderNum(String openOrderNum) {
		String hql=" from TrdTrade where openOrderNum=? ";
		return this.findUniqueByHQL(hql, openOrderNum);
	}

	@Override
	public TrdTrade findByCloseOrderNum(String closeOrderNum) {
		String hql=" from TrdTrade where closeOrderNum=? ";
		return this.findUniqueByHQL(hql, closeOrderNum);
	}

	@Override
	public synchronized List<TrdTrade> findTrdTrades() {
		String hql=" from TrdTrade where state=0 order by updateTime desc ";
		return this.findAllByHQL(hql);
	}

	@Override
	public synchronized List<TrdTrade> findTrdTradesByUser(Long userId, Integer direction) {
		String hql = null;
		//多平,查询空开
		if(direction == 0){
			hql=" from TrdTrade where fuUser.id=? and state=0 and direction=1 and offsetFlag=0 order by openPrice desc ";
		}else{
			//空平,查询多开
			hql=" from TrdTrade where fuUser.id=? and state=0 and direction=0 and offsetFlag=0 order by openPrice asc ";
		}
		return this.findAllByHQL(hql, userId);
	}

//	根据用户ID和状态查询对应的持仓信息
	@SuppressWarnings("unchecked")
	public List<TrdTrade> getTradeyState(long user_id, String instrument_id) {
		String hql = "from TrdTrade where fuUser.id = :user_id and instrumentId = :instrument_id and state != 2 and state != 5";
		return this.getSession().createQuery(hql)//
				.setParameter("user_id", user_id)//
				.setParameter("instrument_id", instrument_id)//
				.list();
	}

//	根据用户id查询用户的结算信息
	@SuppressWarnings("unchecked")
	public List<TrdTrade> tlementQuery(long user_id, String instrument_id) {
		String hql = "from TrdTrade where fuUser.id = :user_id and instrumentId = :instrument_id and (state = 2 or state = 5)";
		return this.getSession().createQuery(hql)//
				.setParameter("user_id", user_id)//
				.setParameter("instrument_id", instrument_id)//
				.list();
	}
	
	//根据用户id和结算状态以及产品编号查询持仓信息
	public TrdTrade findTrdTradeByUserAndInstrumentId(long user_id,
			String instrument_id, String state) {
		String hql = "from TrdTrade where fuUser.id = :user_id and state = :state and instrument_id = :instrument_id";
		return (TrdTrade) this.getSession().createQuery(hql)//
				.setParameter("user_id", user_id)//
				.setParameter("state", Integer.parseInt(state))//
				.setParameter("instrument_id", instrument_id)//
				.uniqueResult();
	}

//	根据用户查询出用户盈利的结算单
	@SuppressWarnings("unchecked")
	public List<TrdTrade> findProfitOrder(long user_id) {
		String hql = "from TrdTrade where fuUser.id = :user_id and (state = 2 or state = 5) and closeProfit > 0";
		return this.getSession().createQuery(hql)//
				.setParameter("user_id", user_id)//
				.list();
	}

	@Override
	public List<TrdTrade> queryTradeList(int i, int pageSize, Map<String, Object> map) {
		String hql=" from TrdTrade where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountUserId")){
			params.add(map.get("accountUserId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("accountUserName")){
			params.add(map.get("accountUserName"));
			hql=hql+" and fuUser.userName=? ";
		}
		hql=hql+" order by openDateTime desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countTrades(Map<String, Object> map) {
		String hql = "from TrdTrade where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountUserId")){
			params.add(map.get("accountUserId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("accountUserName")){
			params.add(map.get("accountUserName"));
			hql=hql+" and fuUser.userName=? ";
		}
		hql=hql+" order by openDateTime desc ";
		return this.countQueryResult(hql,params);
	}
}

