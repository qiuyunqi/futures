package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuPromote;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuPromoteService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuPromote get(Long id) ;
	
	public void save(FuPromote entity) ;
	
	public void delete(Long id) ;
	
	
	
	/**
	 * 查询推广用户的所有推广记录
	 * @param i  
	 * @param pageSize
	 * @param promoteId 推广用户Id
	 * */
	public List<FuPromote> findByPromote(int i,int pageSize,Long promoteId);
	public Integer getCountByPromote(Long promoteId);
	
	/**
	 * 查询下线用户的对应的推广记录
	 * @param promotedId 被推广用户Id
	 * */
	public FuPromote findBy(Long promotedId);
	
}

