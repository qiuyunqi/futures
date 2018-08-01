package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.FuIpBlacklistDao;
import com.hongwei.futures.model.FuIpBlacklist;
import com.hongwei.futures.service.FuIpBlacklistService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuIpBlacklistServiceImpl implements FuIpBlacklistService {
	
	@Autowired
	private FuIpBlacklistDao fuIpBlacklistDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuIpBlacklist get(Long id) {
		return fuIpBlacklistDao.get(id);
	}
	
	public void save(FuIpBlacklist entity) {
		fuIpBlacklistDao.save(entity);
	}
	
	public void delete(Long id) {
		fuIpBlacklistDao.delete(id);
	}
	
	public FuIpBlacklist findBlackByRegIp(String registerIp){
		return fuIpBlacklistDao.findBlackByRegIp(registerIp);
	}
	
	public Integer countIpBlack(Map<String, Object> map){
		return fuIpBlacklistDao.countIpBlack(map);
	}
	
	public List<FuIpBlacklist> findIpBlackList(int i, int pageSize, Map<String, Object> map){
		return fuIpBlacklistDao.findIpBlackList(i,pageSize,map);
	}

	// 根据手机号码或者ip查询 fuIpBlackList对象
	public List<FuIpBlacklist> getBlackListByPhoneOrIp(String phone, String ip) {
		return fuIpBlacklistDao.getBlackListByPhoneOrIp(phone, ip);
	}
	
}

