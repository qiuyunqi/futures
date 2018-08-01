package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.HhrStatTemp;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface HhrStatTempDao extends BaseDao<HhrStatTemp, Long> {

	public Integer findMaxBatchNumber();

	public List<HhrStatTemp> queryHhrStatTempList(int i, int j,
			Map<String, Object> map);

	public Integer countHhrStatTemp(Map<String, Object> map);

	public void deleteByBatchId(String batchId);

	public List<Map<String, Object>> findAllTemps();

}

