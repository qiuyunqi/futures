package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.LiveGuess;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface LiveGuessService {
	
	//====================== 基本 C R U D 方法  ===========================
	public LiveGuess get(Long id) ;
	
	public void save(LiveGuess entity) ;
	
	public void delete(Long id) ;
	
	public List<LiveGuess> findAwardsUser(Long drawId, BigDecimal answer);
	
	public Integer countLiveGuess(Map<String, Object> map);
	
	public List<LiveGuess> findLiveGuessList(int i, int j, Map<String, Object> map);
	
}

