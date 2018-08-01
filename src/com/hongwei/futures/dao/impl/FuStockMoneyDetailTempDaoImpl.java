package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuStockMoneyDetailTempDao;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class FuStockMoneyDetailTempDaoImpl extends BaseDaoImpl<FuStockMoneyDetailTemp, Long> implements FuStockMoneyDetailTempDao {

	@Override
	public Integer findMaxBatchNumber() {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select max(batch_num) as max_batch_num from stock_money_detail_temp");
		return list.get(0).get("max_batch_num") == null? 1: Integer.valueOf(list.get(0).get("max_batch_num").toString())+1;
	}

	@Override
	public List<FuStockMoneyDetailTemp> queryBatchList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from FuStockMoneyDetailTemp where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("batchId")){
			params.add(map.get("batchId"));
			hql=hql+" and batchNum=? ";
		}
		return this.findListByHQL(i, pageSize, hql,params);
	}

	@Override
	public Integer countBatchTemp(Map<String, Object> map) {
		String hql = "from FuStockMoneyDetailTemp where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("batchId")){
			params.add(map.get("batchId"));
			hql=hql+" and batchNum=? ";
		}
		return this.countQueryResult(hql,params);
	}

	@Override
	public void deleteByBatchId(String batchId) {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		if(batchId != null){
			jdbcTemplate.update("delete from stock_money_detail_temp where batch_num = "+Integer.valueOf(batchId));
		}else{
			jdbcTemplate.update("truncate table stock_money_detail_temp");
		}
	}

}

