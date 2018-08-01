package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuPromoteVisit;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuPromoteVisitService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuPromoteVisit get(Long id) ;
	
	public void save(FuPromoteVisit entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 查询IP访问记录
	 * @param promoteId 推广用户Id
	 * @param IP  用户访问IP
	 * */
	public FuPromoteVisit getByIP(Long promoteId,String IP);
	
	/**
	 * 查询推广用户的所有访问记录
	 * @param i  
	 * @param pageSize
	 * @param promoteId 推广用户Id
	 * */
	public List<FuPromoteVisit> findByPromote(int i,int pageSize,Long promoteId);
	public Integer getCountByPromote(Long promoteId);
	
	
	/**
	 * 保存推广访问记录,更新用户的访问IP和访问次数
	 * @param
	 * @return
	 */
	public void savePromoteVisit(FuPromoteVisit promoteVisit,FuUser user);

	
}

