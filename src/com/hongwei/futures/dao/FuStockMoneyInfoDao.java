package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuStockMoneyInfo;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuStockMoneyInfoDao extends BaseDao<FuStockMoneyInfo, Long> {
	public FuStockMoneyInfo findMoneyInfoByMap(Map<String, Object> map);

	public List<FuStockMoneyInfo> queryStockMoneyInfo(int current, int pageNum, Map<String, Object> map);

	public Integer countStockMoneyInfo(Map<String, Object> map);

	public FuStockMoneyInfo findStockMoneyInfoByUserId(Long userId);

}

