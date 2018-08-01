package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrAppTemplateDao;
import com.hongwei.futures.model.HhrAppTemplate;
import com.hongwei.futures.service.HhrAppTemplateService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrAppTemplateServiceImpl implements HhrAppTemplateService {
	
	@Autowired
	private HhrAppTemplateDao hhrAppTemplateDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrAppTemplate get(Long id) {
		return hhrAppTemplateDao.get(id);
	}
	
	public void save(HhrAppTemplate entity) {
		hhrAppTemplateDao.save(entity);
	}
	
	public void delete(Long id) {
		hhrAppTemplateDao.delete(id);
	}
	
}

