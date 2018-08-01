package com.hongwei.futures.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuGameService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuGame get(Long id) ;
	
	public void save(FuGame entity) ;
	
	public void delete(Long id) ;

	public FuGame findGameByTime(Date date);
	
	public List<FuGame> findGameByUser(FuUser fuUser, String gameId);
	
	public List<FuGame> findGameProgramList(int i, int pageSize,Map<String, Object> map);
	
	public Integer countGame(Map<String, Object> map);
	
	public List<FuGame> findGames(String queryDate);
	
	public List<FuGame> findGames(Long userId);

	public List<FuGame> findGameByUser(FuUser fuUser);
	
}

