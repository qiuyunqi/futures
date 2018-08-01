package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuYjbDao;
import com.hongwei.futures.model.FuYjb;
import com.hongwei.futures.service.FuYjbService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuYjbServiceImpl implements FuYjbService {
	
	@Autowired
	private FuYjbDao fuYjbDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuYjb get(Long id) {
		return fuYjbDao.get(id);
	}
	
	public void save(FuYjb entity) {
		fuYjbDao.save(entity);
	}
	
	public void delete(Long id) {
		fuYjbDao.delete(id);
	}

	// 查询所有的余劵宝产品
	public List<FuYjb> findAll() {
		return fuYjbDao.findAll();
	}

	@Override
	public List<FuYjb> findYqbList(int i, int pageSize, Map<String, Object> map) {
		return fuYjbDao.findYqbList(i, pageSize, map);
	}
	
}

