package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.HhrStat;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrStatService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrStat get(Long id) ;
	
	public void save(HhrStat entity) ;
	
	public void delete(Long id) ;
	
	public Object[] findStatDataByMap(Map<String, Object> map);
	
	public HhrStat findHhrStatByUser(Long userId);
	
	public HhrStat findStatDataByMap2(Map<String, Object> map);
	
	public List<HhrStat> findUserByParentId(Long parentId);
	
	public List<HhrStat> queryHhrStatList(int i, int j, Map<String, Object> map);
	
	public Integer countHhrStat(Map<String, Object> map);
	
	public void updateHhrIncome(Long userId, BigDecimal money, Integer incomeType, Long programId);

	/**
	 * 根据 hhrParentID 查询用户信息 
	 * @param userId		用户id
	 * @param curPage		当前页
	 * @param pageSize		每页大小
	 * @return
	 */
	public List<HhrStat> findUserByParentId(long userId, int curPage, int pageSize);
	
}

