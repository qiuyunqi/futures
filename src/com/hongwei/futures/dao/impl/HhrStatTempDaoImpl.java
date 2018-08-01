package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.HhrStatTempDao;
import com.hongwei.futures.model.HhrStatTemp;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class HhrStatTempDaoImpl extends BaseDaoImpl<HhrStatTemp, Long> implements HhrStatTempDao {

	@Override
	public Integer findMaxBatchNumber() {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select max(batch_num) as max_batch from hhr_stat_temp");
		return list.get(0).get("max_batch") == null? 1: Integer.valueOf(list.get(0).get("max_batch").toString())+1;
	}

	@Override
	public List<HhrStatTemp> queryHhrStatTempList(int i, int j,
			Map<String, Object> map) {
		String hql = "from HhrStatTemp where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("batchNum")){
			params.add(map.get("batchNum"));
			hql=hql+" and batchNum=? ";
		}
		return this.findListByHQL(i, j, hql,params);
	}

	@Override
	public Integer countHhrStatTemp(Map<String, Object> map) {
		String hql = "from HhrStatTemp where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("batchNum")){
			params.add(map.get("batchNum"));
			hql=hql+" and batchNum=? ";
		}
		return this.countQueryResult(hql,params);
	}

	@Override
	public void deleteByBatchId(String batchId) {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		if(batchId != null){
			jdbcTemplate.update("delete from hhr_stat_temp where batch_num = "+Integer.valueOf(batchId));
		}else{
			jdbcTemplate.update("truncate table hhr_stat_temp");
		}
	}

	@Override
	public List<Map<String, Object>> findAllTemps() {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate(); 
		String sql = "select user_id, money from hhr_stat_temp";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
}

