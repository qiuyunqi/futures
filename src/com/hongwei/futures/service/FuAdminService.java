package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuAdmin;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuAdminService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuAdmin get(Long id) ;
	
	public void save(FuAdmin entity) ;
	
	public void delete(Long id) ;
	/**
	 * 根据账号查找管理员
	 * @param account
	 * @return
	 */
	public FuAdmin findAdminByAccount(String account);
	
	/**
	 * 是否已登录
	 * @param token
	 * @return
	 */
	public FuAdmin findLoginByToken(String token);
	
	/**
	 * 管理员列表
	 * @param account
	 * @return
	*/
	public List<Object[]> findList(int i,int pageSize,Map<String, Object> map);
	public Integer getCount(Map<String,Object> map);
}

