package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuIndexDataDao;
import com.hongwei.futures.model.FuIndexData;
import com.hongwei.futures.service.FuIndexDataService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuIndexDataServiceImpl implements FuIndexDataService {
	
	@Autowired
	private FuIndexDataDao fuIndexDataDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuIndexData get(Long id) {
		return fuIndexDataDao.get(id);
	}
	
	@Override
	public void save(FuIndexData entity) {
		fuIndexDataDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuIndexDataDao.delete(id);
	}
	
	@Override
	public List<FuIndexData> findAllByFuindexData() {
		return fuIndexDataDao.findAllByFuIndexData();
	}
	
	
	@Override
	public List<FuIndexData> findListBy(int i, int pageSize,
			Map<String, Object> map) {
		return fuIndexDataDao.findListBy(i, pageSize, map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		return fuIndexDataDao.getCount(map);
	}
	
	public List<FuIndexData> findTop10(){
		return fuIndexDataDao.findTop10();
	}
}

