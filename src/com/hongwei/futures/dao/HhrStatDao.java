package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.HhrStat;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface HhrStatDao extends BaseDao<HhrStat, Long> {
	public Object[] findStatDataByMap(Map<String, Object> map);
	
	public HhrStat findStatDataByMap2(Map<String, Object> map);
	
	public List<HhrStat> findUserByParentId(Long parentId);

	public HhrStat findHhrStatByUser(Long userId);

	public List<HhrStat> queryHhrStatList(int i, int j, Map<String, Object> map);

	public Integer countHhrStat(Map<String, Object> map);

	/**
	 * 根据 hhrParentID 查询用户信息 
	 * @param userId		用户id
	 * @param curPage		当前页
	 * @param pageSize		每页大小
	 * @return
	 */
	public List<HhrStat> findUserByParentId(long userId, int curPage, int pageSize);

}

