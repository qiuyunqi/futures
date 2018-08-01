package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuPromoteVisitDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuPromoteVisit;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuPromoteVisitService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuPromoteVisitServiceImpl implements FuPromoteVisitService {
	
	@Autowired
	private FuPromoteVisitDao fuPromoteVisitDao;
	@Autowired
	private FuUserDao fuUserDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuPromoteVisit get(Long id) {
		return fuPromoteVisitDao.get(id);
	}
	
	@Override
	public void save(FuPromoteVisit entity) {
		fuPromoteVisitDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuPromoteVisitDao.delete(id);
	}

	@Override
	public List<FuPromoteVisit> findByPromote(int i, int pageSize,
			Long promoteId) {
		return fuPromoteVisitDao.findByPromote(i, pageSize, promoteId);
	}

	@Override
	public FuPromoteVisit getByIP(Long promoteId, String IP) {
		return fuPromoteVisitDao.getByIP(promoteId, IP);
	}

	@Override
	public Integer getCountByPromote(Long promoteId) {
		return fuPromoteVisitDao.getCountByPromote(promoteId);
	}

	@Override
	public void savePromoteVisit(FuPromoteVisit promoteVisit, FuUser user) {
		fuPromoteVisitDao.save(promoteVisit);
		fuUserDao.save(user);
	}

	
}

