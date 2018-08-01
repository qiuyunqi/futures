package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuDictionaryDao;
import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.service.FuDictionaryService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuDictionaryServiceImpl implements FuDictionaryService {
	
	@Autowired
	private FuDictionaryDao fuDictionaryDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuDictionary get(Long id) {
		return fuDictionaryDao.get(id);
	}
	
	public void save(FuDictionary entity) {
		fuDictionaryDao.save(entity);
	}
	
	public void delete(Long id) {
		fuDictionaryDao.delete(id);
	}
	
	public List<FuDictionary> findListByMap(int i, int j, Map<String, Object> map){
		return fuDictionaryDao.findListByMap( i, j, map);
	}
	
	public Integer countFuDictionary(Map<String, Object> map){
		return fuDictionaryDao.countFuDictionary(map);
	}

	public List<FuDictionary> getByPid(long pid, int flag) {
		return fuDictionaryDao.getByPid(pid, flag);
	}

	@Override
	public List<FuDictionary> findBaseDictionaries() {
		return fuDictionaryDao.findBaseDictionaries();
	}

	@Override
	public String findDictionaryTree(Long longId) {
		return fuDictionaryDao.findDictionaryTree(longId);
	}
	
}

