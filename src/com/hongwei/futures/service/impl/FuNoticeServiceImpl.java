package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuNoticeDao;
import com.hongwei.futures.model.FuNotice;
import com.hongwei.futures.service.FuNoticeService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuNoticeServiceImpl implements FuNoticeService {
	
	@Autowired
	private FuNoticeDao fuNoticeDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuNotice get(Long id) {
		return fuNoticeDao.get(id);
	}
	
	@Override
	public void save(FuNotice entity) {
		fuNoticeDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuNoticeDao.delete(id);
	}

	@Override
	public List<FuNotice> findNoticeList(int i, int j) {
		return fuNoticeDao.findNoticeList(i,j);
	}

	@Override
	public Integer countNotice() {
		return fuNoticeDao.countNotice();
	}
	
}

