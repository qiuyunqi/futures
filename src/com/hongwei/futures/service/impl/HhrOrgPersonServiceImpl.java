package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrOrgPersonDao;
import com.hongwei.futures.model.HhrOrgPerson;
import com.hongwei.futures.service.HhrOrgPersonService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrOrgPersonServiceImpl implements HhrOrgPersonService {
	
	@Autowired
	private HhrOrgPersonDao hhrOrgPersonDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrOrgPerson get(Long id) {
		return hhrOrgPersonDao.get(id);
	}
	
	@Override
	public void save(HhrOrgPerson entity) {
		hhrOrgPersonDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrOrgPersonDao.delete(id);
	}

	@Override
	public List<HhrOrgPerson> countByUserAndCer(String userName, String cerNum) {
		return hhrOrgPersonDao.countByUserAndCer(userName, cerNum);
	}

	@Override
	public void deleteAll() {
		hhrOrgPersonDao.deleteAll();
	}
	
}

