package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.StockStatusRecordDao;
import com.hongwei.futures.model.StockStatusRecord;
import com.hongwei.futures.util.DateUtil;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class StockStatusRecordDaoImpl extends BaseDaoImpl<StockStatusRecord, Long> implements StockStatusRecordDao {

	/**
	 * 根据股票id查询最新的一条的记录信息
	 * @param id
	 * @return
	 */
	public StockStatusRecord findStatusByAccountId(Long stockAccountId) {
		String hql = "FROM StockStatusRecord AS ss WHERE ss.fuStockAccount.id = :stockAccountId order by ss.id desc";
		return (StockStatusRecord) this.getSession().createQuery(hql)//
				.setParameter("stockAccountId", stockAccountId)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	// 根据表的id 和当前时间查询 是否有这条记录
	public StockStatusRecord findStatusByCurrentDate(long stockId, String currentDate, String maxDate) {
		Date curr = DateUtil.getDateFromString(currentDate, "yyyy-MM-dd");
		Date maxTime = DateUtil.getDateFromString(maxDate, "yyyy-MM-dd");
		String hql = "FROM StockStatusRecord AS ss WHERE ss.fuStockAccount.id = :stockId AND ss.changeTime >= :currentDate and ss.changeTime < :maxDate order by ss.id desc";
		return (StockStatusRecord) this.getSession().createQuery(hql)//
				.setParameter("stockId", stockId)//
				.setParameter("currentDate", curr)//
				.setParameter("maxDate", maxTime)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	// 判断今天是否有操作过状态的记录
	@SuppressWarnings("unchecked")
	public List<StockStatusRecord> findStockStatusByTodayAndIsoperation( 
			Date currDate, Date maxDa, int isOperation) {
		String hql = "FROM StockStatusRecord AS ss WHERE ss.changeTime >= :currentDate and ss.changeTime < :maxDate and ss.isOperation = :isOperation order by ss.id desc";
		return  this.getSession().createQuery(hql)//
				.setParameter("currentDate", currDate)//
				.setParameter("maxDate", maxDa)//
				.setParameter("isOperation", isOperation)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.list();
	}

	@Override
	public List<StockStatusRecord> queryStockStatusRecord(int i, int pageSize, Map<String, Object> map) {
		String hql=" from StockStatusRecord where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			hql=hql+" and fuUser.phone=? ";
		}
		if(map.containsKey("capitalAccount")){
			params.add(map.get("capitalAccount"));
			hql=hql+" and fuStockAccount.capitalAccount=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and changeTime>=?";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and changeTime<=?";
		}		
		if(map.containsKey("time")){
			params.add(map.get("time"));
			hql=hql+" and date_format(changeTime, '%Y-%m-%d')=? ";
		}
		if(map.containsKey("type")){
			params.add(map.get("type"));
			hql=hql+" and afterStatus=? ";
		}
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countStockStatusRecord(Map<String, Object> map) {
		String hql = "from StockStatusRecord where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			hql=hql+" and fuUser.phone=? ";
		}
		if(map.containsKey("capitalAccount")){
			params.add(map.get("capitalAccount"));
			hql=hql+" and fuStockAccount.capitalAccount=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and changeTime>=?";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and changeTime<=?";
		}		
		if(map.containsKey("time")){
			params.add(map.get("time"));
			hql=hql+" and date_format(changeTime, '%Y-%m-%d')=? ";
		}
		if(map.containsKey("type")){
			params.add(map.get("type"));
			hql=hql+" and afterStatus=? ";
		}
		return this.countQueryResult(hql,params);
	}

}

