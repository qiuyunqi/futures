package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuPromoteDao;
import com.hongwei.futures.model.FuPromote;
import com.hongwei.futures.service.FuPromoteService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuPromoteServiceImpl implements FuPromoteService {
	
	@Autowired
	private FuPromoteDao fuPromoteDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuPromote get(Long id) {
		return fuPromoteDao.get(id);
	}
	
	@Override
	public void save(FuPromote entity) {
		fuPromoteDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuPromoteDao.delete(id);
	}

	@Override
	public List<FuPromote> findByPromote(int i, int pageSize, Long promoteId) {
		return fuPromoteDao.findByPromote(i, pageSize, promoteId);
	}

	@Override
	public Integer getCountByPromote(Long promoteId) {
		return fuPromoteDao.getCountByPromote(promoteId);
	}

	@Override
	public FuPromote findBy(Long promotedId) {
		return fuPromoteDao.findBy(promotedId);
	}
	
}

