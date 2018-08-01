package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrQualiRegisterDao;
import com.hongwei.futures.model.HhrQualiRegister;
import com.hongwei.futures.service.HhrQualiRegisterService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrQualiRegisterServiceImpl implements HhrQualiRegisterService {
	
	@Autowired
	private HhrQualiRegisterDao hhrQualiRegisterDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrQualiRegister get(Long id) {
		return hhrQualiRegisterDao.get(id);
	}
	
	@Override
	public void save(HhrQualiRegister entity) {
		hhrQualiRegisterDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrQualiRegisterDao.delete(id);
	}

	@Override
	public List<HhrQualiRegister> findQualiByUser(Long userId, String type) {
		return hhrQualiRegisterDao.findQualiByUser(userId, type);
	}

	@Override
	public List<HhrQualiRegister> queryQualiList(int i, int pageSize, Map<String, Object> map) {
		return hhrQualiRegisterDao.queryQualiList(i, pageSize, map);
	}

	@Override
	public Integer countQuali(Map<String, Object> map) {
		return hhrQualiRegisterDao.countQuali(map);
	}

	@Override
	public int countQualiPerson(Map<String, Object> map) {
		return hhrQualiRegisterDao.countQualiPerson(map);
	}

	@Override
	public List<HhrQualiRegister> countByUserAndType(Long userId, Integer type) {
		return hhrQualiRegisterDao.countByUserAndType(userId, type);
	}

	@Override
	public List<HhrQualiRegister> countByQualiNum(String qualiNum) {
		return hhrQualiRegisterDao.countByQualiNum(qualiNum);
	}
	
}

