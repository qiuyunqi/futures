package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.SysProvinceDao;
import com.hongwei.futures.model.SysProvince;
import com.hongwei.futures.service.SysProvinceService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class SysProvinceServiceImpl implements SysProvinceService {
	
	@Autowired
	private SysProvinceDao sysProvinceDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public SysProvince get(Long id) {
		return sysProvinceDao.get(id);
	}
	
	@Override
	public void save(SysProvince entity) {
		sysProvinceDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		sysProvinceDao.delete(id);
	}

	@Override
	public List<SysProvince> findAllProvince() {
		return sysProvinceDao.findAllProvince();
	}

	@Override
	public SysProvince findByName(String provinceName) {
		return sysProvinceDao.findByName(provinceName);
	}
	
}

