package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.HhrQualiRegister;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrQualiRegisterService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrQualiRegister get(Long id) ;
	
	public void save(HhrQualiRegister entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 判断用户是否已认证职业资格
	 * @param userId
	 * @return
	 */
	public List<HhrQualiRegister> findQualiByUser(Long userId, String type);

	public List<HhrQualiRegister> queryQualiList(int i, int pageSize, Map<String, Object> map);

	public Integer countQuali(Map<String, Object> map);

	public int countQualiPerson(Map<String, Object> map);
	
	public List<HhrQualiRegister> countByUserAndType(Long userId, Integer type);
	
	public List<HhrQualiRegister> countByQualiNum(String qualiNum);
	
	
	
}

