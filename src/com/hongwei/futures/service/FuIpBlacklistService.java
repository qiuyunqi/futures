package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuIpBlacklist;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuIpBlacklistService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuIpBlacklist get(Long id) ;
	
	public void save(FuIpBlacklist entity) ;
	
	public void delete(Long id) ;
	
	public FuIpBlacklist findBlackByRegIp(String registerIp);
	
	public Integer countIpBlack(Map<String, Object> map);
	
	public List<FuIpBlacklist> findIpBlackList(int i, int pageSize, Map<String, Object> map);

	/**
	 * 根据手机号码或者ip查询 fuIpBlackList对象
	 * @param phone	手机号码
	 * @param ip		IP地址
	 * @return	FuIpBlacklist 集合
	 */
	public List<FuIpBlacklist> getBlackListByPhoneOrIp(String phone, String ip);
	
}

