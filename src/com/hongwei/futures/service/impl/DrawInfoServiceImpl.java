package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.DrawInfoDao;
import com.hongwei.futures.model.DrawInfo;
import com.hongwei.futures.service.DrawInfoService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class DrawInfoServiceImpl implements DrawInfoService {
	
	@Autowired
	private DrawInfoDao drawInfoDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public DrawInfo get(Long id) {
		return drawInfoDao.get(id);
	}
	
	public void save(DrawInfo entity) {
		drawInfoDao.save(entity);
	}
	
	public void delete(Long id) {
		drawInfoDao.delete(id);
	}

	public DrawInfo getByUserId(Long userId) {
		return drawInfoDao.getByUserId(userId);
	}

	// 查询出所有中奖信息
	public List<DrawInfo> getDrawInfo() {
		return drawInfoDao.getDrawInfo();
	}
	
}

