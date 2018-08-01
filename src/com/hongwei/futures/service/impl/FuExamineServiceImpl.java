package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuExamineDao;
import com.hongwei.futures.model.FuExamine;
import com.hongwei.futures.service.FuExamineService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuExamineServiceImpl implements FuExamineService {
	
	@Autowired
	private FuExamineDao fuExamineDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuExamine get(Long id) {
		return fuExamineDao.get(id);
	}
	
	public void save(FuExamine entity) {
		fuExamineDao.save(entity);
	}
	
	public void delete(Long id) {
		fuExamineDao.delete(id);
	}
	
}

