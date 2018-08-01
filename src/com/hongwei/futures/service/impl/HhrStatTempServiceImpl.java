package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrStatTempDao;
import com.hongwei.futures.model.HhrStatTemp;
import com.hongwei.futures.service.HhrStatTempService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrStatTempServiceImpl implements HhrStatTempService {
	
	@Autowired
	private HhrStatTempDao hhrStatTempDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrStatTemp get(Long id) {
		return hhrStatTempDao.get(id);
	}
	
	@Override
	public void save(HhrStatTemp entity) {
		hhrStatTempDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrStatTempDao.delete(id);
	}

	@Override
	public Integer findMaxBatchNumber() {
		return hhrStatTempDao.findMaxBatchNumber();
	}

	@Override
	public List<HhrStatTemp> queryHhrStatTempList(int i, int j,
			Map<String, Object> map) {
		return hhrStatTempDao.queryHhrStatTempList(i, j, map);
	}

	@Override
	public Integer countHhrStatTemp(Map<String, Object> map) {
		return hhrStatTempDao.countHhrStatTemp(map);
		
	}

	@Override
	public void deleteByBatchId(String batchId) {
		hhrStatTempDao.deleteByBatchId(batchId);
	}

	@Override
	public List<Map<String, Object>> findAllTemps() {
		return hhrStatTempDao.findAllTemps();
	}
	
}

