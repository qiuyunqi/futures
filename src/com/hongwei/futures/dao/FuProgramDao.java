package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuProgram;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuProgramDao extends BaseDao<FuProgram, Long> {
	
	public List<FuProgram> findProgramUpByProgram(Long id);
	
	public List<FuProgram> findSonProgramByProgram(Long id);
	
	public List<FuProgram> findProgramIsAcrossCabin();

	public List<FuProgram> findToTradeByUser(Long userId, int programType);

	public Integer countProgramByUser(Map<String, Object> map);

	public List<FuProgram> findProgramByUser(int i, int pageSize,
			Map<String, Object> map);

	public List<FuProgram> findAllTradeProgram();
	
	public List<FuProgram> findOpenProgramByParams();
	
	public List<FuProgram> findAllTradeProgram(Map<String, Object> map);

	public Integer countProgram(Map<String, Object> map);

	public List<FuProgram> findProgramList(int i, int pageSize,
			Map<String, Object> map);

	public FuProgram findGameProgram(Long userId, Long gameId);

	public List<FuProgram> findGameOrder(Long gameId, int flag);

	public Integer countGameProgram(Map<String, Object> map);

	public List<FuProgram> findGameProgramList(int i, int pageSize,
			Map<String, Object> map);

	public List<FuProgram> findOverProgramList(Map<String, Object> map);

	public Integer countWaitOverProgram(Map<String, Object> map);

	public List<FuProgram> findWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map);
	
	public Integer countOfflineWaitOverProgram(Map<String, Object> map);

	public List<FuProgram> findOfflineWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map);

	public Integer countTradeInfo(Map<String, Object> map);

	public List<FuProgram> findTradeInfoList(int i, int pageSize, Map<String, Object> map);
	
	public List<FuProgram> findTradeInfoList2(Map<String, Object> map);
	
	public List<FuProgram> findAllTradeInfoList();

	public Long findCountMatchMoneyByTrade(Integer tradeAccount);
	
	public List<FuProgram> findProgramByParams(Map<String, Object> map);
	
	public Integer countProgramByUser2(Map<String, Object> map);
	
	public List<FuProgram> findAllDueProgram();
	
	public List<FuProgram> findAllTradeSonProgram();
	
	/**
	 * 已删除方案数目
	 */
	public Integer countDeleteProgram(Map<String, Object> map);
	/**
	 * 已删除方案列表
	 */
	public List<FuProgram> findDeleteProgramList(int i, int pageSize,Map<String, Object> map);
	
	public Integer countWaitProgram(Map<String, Object> map);
	
	public List<FuProgram> findWaitProgramList(int i, int pageSize, Map<String, Object> map);

	public List<FuProgram> findProgramsByDate(String queryDate);

	public List<FuProgram> findToTradeByUser(Long userId);
	
}

