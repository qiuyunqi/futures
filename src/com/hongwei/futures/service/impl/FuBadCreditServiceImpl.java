package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuBadCreditDao;
import com.hongwei.futures.model.FuBadCredit;
import com.hongwei.futures.service.FuBadCreditService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuBadCreditServiceImpl implements FuBadCreditService {
	
	@Autowired
	private FuBadCreditDao fuBadCreditDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuBadCredit get(Long id) {
		return fuBadCreditDao.get(id);
	}
	
	@Override
	public void save(FuBadCredit entity) {
		fuBadCreditDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuBadCreditDao.delete(id);
	}

	@Override
	public Integer countBadCredit(Map<String, Object> map) {
		return fuBadCreditDao.countBadCredit(map);
	}

	@Override
	public List<FuBadCredit> findBadList(int i, int pageSize,
			Map<String, Object> map) {
		return fuBadCreditDao.findBadList(i,pageSize,map);
	}

	@Override
	public List<FuBadCredit> findListByProgram(Long programId) {
		return fuBadCreditDao.findListByProgram(programId);
	}
	
}

