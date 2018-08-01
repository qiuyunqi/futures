package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.TrdServerLogDao;
import com.hongwei.futures.model.TrdServerLog;
import com.hongwei.futures.service.TrdServerLogService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class TrdServerLogServiceImpl implements TrdServerLogService {
	
	@Autowired
	private TrdServerLogDao trdServerLogDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdServerLog get(Long id) {
		return trdServerLogDao.get(id);
	}
	
	public void save(TrdServerLog entity) {
		trdServerLogDao.save(entity);
	}
	
	public void delete(Long id) {
		trdServerLogDao.delete(id);
	}
	
}

