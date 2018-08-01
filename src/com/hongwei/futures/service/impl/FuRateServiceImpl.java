package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuRateDao;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.service.FuRateService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuRateServiceImpl implements FuRateService {
	
	@Autowired
	private FuRateDao fuRateDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuRate get(Long id) {
		return fuRateDao.get(id);
	}
	
	@Override
	public void save(FuRate entity) {
		fuRateDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuRateDao.delete(id);
	}
	
	@Override
	public FuRate getByUserID(Long userid){
		return fuRateDao.findRateByUserID(userid);
	}
	
	@Override
	public List<FuRate> findRateList(int i, int pageSize, Map<String, Object> map) {
		return fuRateDao.findRateList(i,pageSize, map);
	}
	
	@Override
	public Integer countRate(Map<String, Object> map) {
		return fuRateDao.countRate(map);
	}
	
}

