package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.WqqOptions;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface WqqOptionsDao extends BaseDao<WqqOptions, Long> {

	public List<WqqOptions> queryOptionsList(int i, int pageSize, Map<String, Object> map);

	public Integer countOptions(Map<String, Object> map);

	/**
	 * 根据userId 查询用户的所有的订单
	 * @param userId	用户id
	 * @return
	 */
	public List<WqqOptions> getInfoByUserId(Long userId);

}

