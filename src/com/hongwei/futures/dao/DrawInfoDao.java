package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.DrawInfo;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface DrawInfoDao extends BaseDao<DrawInfo, Long> {

	// 根据用户id 查询抽奖人的基本信息
	public DrawInfo getByUserId(Long userId);

	// 查询出所有中奖信息
	public List<DrawInfo> getDrawInfo();

}

