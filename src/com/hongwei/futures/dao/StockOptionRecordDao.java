package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.StockOptionRecord;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface StockOptionRecordDao extends BaseDao<StockOptionRecord, Long> {

	public List<StockOptionRecord> findByUserId(Long userId, int isOption);

}

