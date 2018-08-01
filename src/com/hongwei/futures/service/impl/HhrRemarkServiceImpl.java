package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrRemarkDao;
import com.hongwei.futures.model.HhrRemark;
import com.hongwei.futures.service.HhrRemarkService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrRemarkServiceImpl implements HhrRemarkService {
	
	@Autowired
	private HhrRemarkDao hhrRemarkDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrRemark get(Long id) {
		return hhrRemarkDao.get(id);
	}
	
	public void save(HhrRemark entity) {
		hhrRemarkDao.save(entity);
	}
	
	public void delete(Long id) {
		hhrRemarkDao.delete(id);
	}
	
	public HhrRemark findRemarkByUserId(Long user_id,Long relevance_id){
		return hhrRemarkDao.findRemarkByUserId(user_id,relevance_id);
	}
	
}

