package com.hongwei.futures.dao.impl;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.TrdTradeNumDao;
import com.hongwei.futures.model.TrdTradeNum;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class TrdTradeNumDaoImpl extends BaseDaoImpl<TrdTradeNum, Long> implements TrdTradeNumDao {

//	根据当前时间 当前用户 当前产品的编号 查询当前计数器
	public TrdTradeNum findNumByDateAndInstrumentId(long userId, String instrument_id, String currentDate) {
		String hql = "from TrdTradeNum as f where f.userId=:userId and f.instrumentId=:instrument_id and f.dateTime=:dateTime";
		return (TrdTradeNum) this.getSession().createQuery(hql)//
					.setParameter("userId", userId)//
					.setParameter("instrument_id", instrument_id)//
					.setParameter("dateTime", currentDate)
					.uniqueResult();
	}

}

