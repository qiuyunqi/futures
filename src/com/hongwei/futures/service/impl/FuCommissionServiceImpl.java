package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuCommissionDao;
import com.hongwei.futures.dao.FuPromoteDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuCommission;
import com.hongwei.futures.model.FuPromote;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuCommissionService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuCommissionServiceImpl implements FuCommissionService {
	
	@Autowired
	private FuCommissionDao fuCommissionDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private FuPromoteDao fuPromoteDao;
	@Autowired
	private MoneyDetailUtil MoneyDetailUtil;
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuCommission get(Long id) {
		return fuCommissionDao.get(id);
	}
	
	@Override
	public void save(FuCommission entity) {
		fuCommissionDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuCommissionDao.delete(id);
	}

	@Override
	public List<FuCommission> findList(int i, int pageSize,
			Map<String, Object> map) {
		return fuCommissionDao.findList(i, pageSize, map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		return fuCommissionDao.getCount(map);
	}

	@Override
	public void saveCommission(FuCommission commission, FuUser user,
			FuPromote promote) {
		fuCommissionDao.save(commission);
		fuUserDao.save(user);
		fuPromoteDao.save(promote);
	}

	@Override
	public void saveCommissionExchange(FuCommission commission, FuUser user) {
		fuCommissionDao.save(commission);
		fuUserDao.save(user);
	}
	
}

