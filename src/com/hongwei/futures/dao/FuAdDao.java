package com.hongwei.futures.dao;

import java.util.HashMap;
import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuAd;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuAdDao extends BaseDao<FuAd, Long> {

	// 根据位置获取全部可用的轮播图片
	public List<FuAd> getList(int position, int isDelete);
	// 查询全部的广告位
	public List<FuAd> findAll(int currentPage, int pageSize, HashMap<String, Object> map);
	// 查询最大的顺序的那个条数据
	public FuAd getMaxOrderAd();

}

