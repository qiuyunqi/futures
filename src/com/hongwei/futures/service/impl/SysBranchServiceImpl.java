package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.SysBranchDao;
import com.hongwei.futures.model.SysBranch;
import com.hongwei.futures.service.SysBranchService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class SysBranchServiceImpl implements SysBranchService {
	
	@Autowired
	private SysBranchDao sysBranchDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public SysBranch get(Long id) {
		return sysBranchDao.get(id);
	}
	
	@Override
	public void save(SysBranch entity) {
		sysBranchDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		sysBranchDao.delete(id);
	}

	@Override
	public List<SysBranch> findBranchByCity(Long bankId, Long cityId) {
		return sysBranchDao.findBranchByCity(bankId, cityId);
	}

	@Override
	public SysBranch findBy(Long bankId, Long cityId, String branchName,
			String branchAddress, String branchPhone) {
		return sysBranchDao.findBy(bankId, cityId, branchName, branchAddress, branchPhone);
	}

	@Override
	public SysBranch findByParams(Long bankId, Long cityId, String branchName) {
		return sysBranchDao.findByParams(bankId, cityId, branchName);
	}
	
}

