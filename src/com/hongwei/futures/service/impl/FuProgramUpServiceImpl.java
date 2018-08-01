package com.hongwei.futures.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuProgramUpDao;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuProgramUp;
import com.hongwei.futures.service.FuProgramUpService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuProgramUpServiceImpl implements FuProgramUpService {
	
	@Autowired
	private FuProgramUpDao fuProgramUpDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuProgramUp get(Long id) {
		return fuProgramUpDao.get(id);
	}
	
	@Override
	public void save(FuProgramUp entity) {
		fuProgramUpDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuProgramUpDao.delete(id);
	}

	@Override
	public List<FuProgramUp> findProgramUpList(int type, int status, Date closeTime) {
		return fuProgramUpDao.findProgramUpList(type,status,closeTime);
	}

	@Override
	public List<FuProgramUp> findProgramUpByPidAndNoDeal(Long programId) {
		return fuProgramUpDao.findProgramUpByPidAndNoDeal(programId);
	}
	
}

