package com.hongwei.futures.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuAdDao;
import com.hongwei.futures.model.FuAd;
import com.hongwei.futures.service.FuAdService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuAdServiceImpl implements FuAdService {
	
	@Autowired
	private FuAdDao fuAdDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuAd get(Long id) {
		return fuAdDao.get(id);
	}
	
	public void save(FuAd entity) {
		fuAdDao.save(entity);
	}
	
	public void delete(Long id) {
		fuAdDao.delete(id);
	}

	// 根据位置获取全部可用的轮播图片
	public List<FuAd> getList(int position) {
		int isDelete = 0;
		return fuAdDao.getList(position, isDelete);
	}

	// 查询全部的广告位
	public List<FuAd> findAll(int currentPage, int pageSize, HashMap<String, Object> map) {
		return fuAdDao.findAll(currentPage, pageSize, map);
	}

	// 查询最大的顺序的那个条数据
	public FuAd getMaxOrderAd() {
		return fuAdDao.getMaxOrderAd();
	}
	
}

