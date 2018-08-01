package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.LiveGuessDao;
import com.hongwei.futures.model.LiveGuess;
import com.hongwei.futures.service.LiveGuessService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class LiveGuessServiceImpl implements LiveGuessService {
	
	@Autowired
	private LiveGuessDao liveGuessDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public LiveGuess get(Long id) {
		return liveGuessDao.get(id);
	}
	
	public void save(LiveGuess entity) {
		liveGuessDao.save(entity);
	}
	
	public void delete(Long id) {
		liveGuessDao.delete(id);
	}
	
	public List<LiveGuess> findAwardsUser(Long drawId, BigDecimal answer){
		return liveGuessDao.findAwardsUser(drawId, answer);
	}
	
	public Integer countLiveGuess(Map<String, Object> map){
		return liveGuessDao.countLiveGuess(map);
	}
	
	public List<LiveGuess> findLiveGuessList(int i, int j, Map<String, Object> map){
		return liveGuessDao.findLiveGuessList(i,j,map);
	}
}

