package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.SysDistrictDao;
import com.hongwei.futures.model.SysDistrict;
import com.hongwei.futures.service.SysDistrictService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class SysDistrictServiceImpl implements SysDistrictService {
	
	@Autowired
	private SysDistrictDao sysDistrictDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public SysDistrict get(Long id) {
		return sysDistrictDao.get(id);
	}
	
	@Override
	public void save(SysDistrict entity) {
		sysDistrictDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		sysDistrictDao.delete(id);
	}
	
}

