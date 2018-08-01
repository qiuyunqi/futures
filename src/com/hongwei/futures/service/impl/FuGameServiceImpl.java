package com.hongwei.futures.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuGameDao;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuGameService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuGameServiceImpl implements FuGameService {
	
	@Autowired
	private FuGameDao fuGameDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuGame get(Long id) {
		return fuGameDao.get(id);
	}
	
	@Override
	public void save(FuGame entity) {
		fuGameDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuGameDao.delete(id);
	}

	@Override
	public FuGame findGameByTime(Date time) {
		return fuGameDao.findGameByTime(time);
	}
	
	public List<FuGame> findGameByUser(FuUser fuUser){
		return fuGameDao.findGameByUser(fuUser);
	}
	
	public List<FuGame> findGameProgramList(int i, int pageSize,Map<String, Object> map){
		return fuGameDao.findGameProgramList(i, pageSize, map);
	}
	
	public Integer countGame(Map<String, Object> map) {
		return fuGameDao.countGame(map);
	}

	@Override
	public List<FuGame> findGames(String queryDate) {
		return fuGameDao.findGames(queryDate);
	}

	@Override
	public List<FuGame> findGames(Long userId) {
		return fuGameDao.findGames(userId);
	}

	@Override
	public List<FuGame> findGameByUser(FuUser fuUser, String gameId) {
		return fuGameDao.findGameByUser(fuUser, gameId);
	}
}

