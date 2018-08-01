package com.hongwei.futures.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.LiveGuess;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface LiveGuessDao extends BaseDao<LiveGuess, Long> {
	public List<LiveGuess> findAwardsUser(Long drawId, BigDecimal answer);
	
	public Integer countLiveGuess(Map<String, Object> map);
	
	public List<LiveGuess> findLiveGuessList(int i, int j, Map<String, Object> map);
}

