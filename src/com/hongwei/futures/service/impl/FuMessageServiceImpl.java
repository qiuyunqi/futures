package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuMessageDao;
import com.hongwei.futures.model.FuMessage;
import com.hongwei.futures.service.FuMessageService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuMessageServiceImpl implements FuMessageService {
	
	@Autowired
	private FuMessageDao fuMessageDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuMessage get(Long id) {
		return fuMessageDao.get(id);
	}
	
	@Override
	public void save(FuMessage entity) {
		fuMessageDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuMessageDao.delete(id);
	}

	@Override
	public Integer countMessage(Long userId) {
		return fuMessageDao.countMessage(userId);
	}

	@Override
	public List<FuMessage> findMessageList(int i, int pageSize, Long userId) {
		return fuMessageDao.findMessageList(i,pageSize,userId);
	}

	@Override
	public Integer countAllMessage(Map<String, Object> map) {
		return fuMessageDao.countAllMessage(map);
	}

	@Override
	public List<FuMessage> findAllMessage(int i, int pageSize,Map<String, Object> map) {
		return fuMessageDao.findAllMessage(i,pageSize,map);
	}

	@Override
	public List<FuMessage> findMessageByUser(Long userId) {
		return fuMessageDao.findMessageByUser(userId);
	}
	
}

