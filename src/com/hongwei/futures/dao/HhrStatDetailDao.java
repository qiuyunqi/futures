package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.HhrStatDetail;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface HhrStatDetailDao extends BaseDao<HhrStatDetail, Long> {

	HhrStatDetail findHhrStatDetailByUser(Long userId);

	HhrStatDetail findHhrStatDetailByUserAndDate(Long userId, String date);

	List<Object[]> findStatDataByMap(Map<String, Object> map);

	HhrStatDetail findHhrStatDetailByIncomeType(Long userId, Integer incomeType);

}

