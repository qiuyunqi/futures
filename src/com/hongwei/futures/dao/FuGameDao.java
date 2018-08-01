package com.hongwei.futures.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuGameDao extends BaseDao<FuGame, Long> {

	public FuGame findGameByTime(Date time);
	
	public List<FuGame> findGameByUser(FuUser fuUser);
	
	public List<FuGame> findGameProgramList(int i, int pageSize,Map<String, Object> map);
	
	public Integer countGame(Map<String, Object> map);

	public List<FuGame> findGames(String queryDate);

	public List<FuGame> findGames(Long id);
	// 根据包含的当前值 查询出最大的那条数据
	public FuGame findMaxByGameNum(int num, int comNum);
	// 根据游戏的编号和用户查询 该用户是否报过名
	public List<FuGame> findGameByUser(FuUser fuUser, String gameId);

}

