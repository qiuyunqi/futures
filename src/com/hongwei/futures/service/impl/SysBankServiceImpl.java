package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.SysBankDao;
import com.hongwei.futures.model.SysBank;
import com.hongwei.futures.service.SysBankService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class SysBankServiceImpl implements SysBankService {
	
	@Autowired
	private SysBankDao sysBankDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public SysBank get(Long id) {
		return sysBankDao.get(id);
	}
	
	@Override
	public void save(SysBank entity) {
		sysBankDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		sysBankDao.delete(id);
	}

	@Override
	public List<SysBank> findAllBank() {
		return sysBankDao.findAllBank();
	}

	@Override
	public SysBank findBy(String name) {
		return sysBankDao.findBy(name);
	}
	
}

