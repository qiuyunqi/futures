package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.HhrStatDetailDao;
import com.hongwei.futures.model.HhrStatDetail;
import com.hongwei.futures.service.HhrStatDetailService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrStatDetailServiceImpl implements HhrStatDetailService {
	
	@Autowired
	private HhrStatDetailDao hhrStatDetailDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrStatDetail get(Long id) {
		return hhrStatDetailDao.get(id);
	}
	
	@Override
	public void save(HhrStatDetail entity) {
		hhrStatDetailDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrStatDetailDao.delete(id);
	}

	@Override
	public HhrStatDetail findHhrStatDetailByUser(Long userId) {
		return hhrStatDetailDao.findHhrStatDetailByUser(userId);
	}

	@Override
	public HhrStatDetail findHhrStatDetailByUserAndDate(Long userId, String date) {
		return hhrStatDetailDao.findHhrStatDetailByUserAndDate(userId, date);
	}

	@Override
	public List<Object[]> findStatDataByMap(Map<String, Object> map) {
		return hhrStatDetailDao.findStatDataByMap(map);
	}

	@Override
	public HhrStatDetail findHhrStatDetailByUserAndIncomeType(Long userId, Integer incomeType) {
		return hhrStatDetailDao.findHhrStatDetailByIncomeType(userId, incomeType);
	}
	
}

