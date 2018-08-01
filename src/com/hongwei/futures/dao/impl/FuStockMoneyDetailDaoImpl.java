package com.hongwei.futures.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuStockMoneyDetailDao;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.util.DateUtil;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("all")
@Repository
public class FuStockMoneyDetailDaoImpl extends BaseDaoImpl<FuStockMoneyDetail, Long> implements FuStockMoneyDetailDao {
	public FuStockMoneyDetail findMoneyDetailByAccount(Long stockId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String now = sdf.format(cal.getTime());// 昨日
		String sql = "select * from stock_money_detail as f where f.stock_id=? and f.tradetime='" + now + "'";
		if (this.findListBySQL(sql, stockId).size() > 0) {
			return this.findListBySQL(sql, stockId).get(0);
		} else {
			return null;
		}
	}

	public FuStockMoneyDetail findMoneyDetailByAccountId(Long stockId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.getDateFromString(now, "yyyy-MM-dd"));
		calendar.add(Calendar.DATE, -1);
		Date time = calendar.getTime();
		now = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
		String sql = "select * from stock_money_detail as f where f.stock_id=? and f.tradetime='" + now + "'";
		if (this.findListBySQL(sql, stockId).size() > 0) {
			return this.findListBySQL(sql, stockId).get(0);
		} else {
			return null;
		}
	}

	// 根据userid 查询stock_money_detail 列表
	public List<FuStockMoneyDetail> findDetailByUserId(Long userId, Integer num, Integer pageSize) {
		String hql = "FROM FuStockMoneyDetail AS fs WHERE fs.fuUser.id = :userId order by fs.tradeTime desc";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setFirstResult(num)//
				.setMaxResults(pageSize)//
				.list();

	}

	@Override
	public List<FuStockMoneyDetail> queryStockMoneyDetail(int current, int pageNum, Map<String, Object> map) {
		String hql = " from FuStockMoneyDetail where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("phone")) {
			params.add(map.get("phone"));
			hql = hql + " and fuUser.phone=? ";
		}
		if (map.containsKey("time1")) {
			params.add(map.get("time1"));
			hql = hql + " and tradeTime>=?";
		}
		if (map.containsKey("time2")) {
			params.add(map.get("time2"));
			hql = hql + " and tradeTime<=?";
		}
		if (map.containsKey("tradeTime")) {
			params.add(map.get("tradeTime"));
			hql = hql + " and tradeTime=?";
		}
		if (map.containsKey("beginDate")) {
			params.add(map.get("beginDate"));
			hql = hql + " and tradeTime>=?";
		}
		if (map.containsKey("endDate")) {
			params.add(map.get("endDate"));
			hql = hql + " and tradeTime<=?";
		}
		if (map.containsKey("dayKnockdown")) {
			params.add(map.get("dayKnockdown"));
			hql = hql + " and dayKnockdown>?";
		}
		if (map.containsKey("stockId")) {
			params.add(map.get("stockId"));
			hql = hql + " and fuStockAccount.id=?";
		}
		hql = hql + " order by tradeTime desc ";
		return this.findListByHQL(current, pageNum, hql, params);
	}

	@Override
	public Integer countStockMoneyDetail(Map<String, Object> map) {
		String hql = "from FuStockMoneyDetail where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("phone")) {
			params.add(map.get("phone"));
			hql = hql + " and fuUser.phone=? ";
		}
		if (map.containsKey("time1")) {
			params.add(map.get("time1"));
			hql = hql + " and tradeTime>=?";
		}
		if (map.containsKey("time2")) {
			params.add(map.get("time2"));
			hql = hql + " and tradeTime<=?";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuStockMoneyDetail> findDetailByUser(Long userId) {
		String hql = " from FuStockMoneyDetail where fuUser.id=? order by tradeTime desc ";
		return this.findAllByHQL(hql, userId);
	}

	public List<Object[]> findListByUser(Long userId) {
		String hql = "SELECT tradeTime,SUM(payFee) FROM FuStockMoneyDetail where fuUser.id=? GROUP BY tradeTime order by tradeTime desc";
		return this.findParamsListByHQL(hql, userId);
	}

	// 根据股票id 查询这只股票的所有详细记录
	public List<FuStockMoneyDetail> findByStockId(Long stockId) {
		String hql = "FROM FuStockMoneyDetail as fmd WHERE fmd.fuStockAccount.id = :stockId";
		return this.getSession().createQuery(hql)//
				.setParameter("stockId", stockId)//
				.list();
	}

	// 根据股票id 查询这只股票的收益总和
	public BigDecimal getSumProfit(Long stockId) {
		String hql = "SELECT SUM(now_profit) FROM stock_money_detail WHERE stock_id = :stockId";
		return (BigDecimal) this.getSession().createSQLQuery(hql)//
				.setParameter("stockId", stockId)//
				.uniqueResult();
	}

	// 根据用户id和股票账号id 查询 这个股票最新5天的数据
	public List<FuStockMoneyDetail> findByUserIdAndStockId(Long userId, Long stockId) {
		String hql = "FROM FuStockMoneyDetail AS fs WHERE fs.fuUser.id = :userId AND fs.fuStockAccount.id = :stockId ORDER BY fs.tradeTime desc";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setParameter("stockId", stockId)//
				.setFirstResult(0)//
				.setMaxResults(5)//
				.list();
	}

	// 根据股票id 查询这只股票的所有详细记录 (分页版本)
	public List<FuStockMoneyDetail> findByStockId(Long stockId, Long detailId) {
		String hql = "FROM FuStockMoneyDetail as fmd WHERE fmd.fuStockAccount.id = :stockId";
		if (null != detailId) {
			hql += " AND fmd.id < " + detailId;
		}
		hql += " ORDER BY fmd.createTime DESC";
		return this.getSession().createQuery(hql)//
				.setParameter("stockId", stockId)//
				.setFirstResult(0)//
				.setMaxResults(10)//
				.list();
	}

	public List<Object[]> findListByDate(Date tradeTime) {
		String sql = "SELECT SUM(total_value+total_cash) total from stock_money_detail where tradetime=?";
		List<Object> list = new ArrayList<Object>();
		list.add(tradeTime);
		return this.findBySqlGetArray(sql, list);
	}

	public List<Object[]> findListByTime(Date tradeTime) {
		String sql = "SELECT COUNT(day_knockdown) count1,SUM(total_value+total_cash) total from stock_money_detail where day_knockdown>0 and tradetime=?";
		List<Object> list = new ArrayList<Object>();
		list.add(tradeTime);
		return this.findBySqlGetArray(sql, list);
	}

}
