package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.WqqContentsDao;
import com.hongwei.futures.model.WqqContents;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.WqqContentsService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class WqqContentsServiceImpl implements WqqContentsService {
	
	@Autowired
	private WqqContentsDao wqqContentsDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public WqqContents get(Long id) {
		return wqqContentsDao.get(id);
	}
	
	public void save(WqqContents entity) {
		wqqContentsDao.save(entity);
	}
	
	public void delete(Long id) {
		wqqContentsDao.delete(id);
	}
	
	@Override
	public List<WqqContents> findContentsByMap(int i, int pageSize, Map<String, Object> map) {
		return wqqContentsDao.findContentsByMap(i, pageSize, map);
	}

	@Override
	public Integer countContents(Map<String, Object> map) {
		return wqqContentsDao.countContents(map);
	}
	
}

