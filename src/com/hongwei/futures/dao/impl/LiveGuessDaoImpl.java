package com.hongwei.futures.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.LiveGuessDao;
import com.hongwei.futures.model.LiveGuess;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class LiveGuessDaoImpl extends BaseDaoImpl<LiveGuess, Long> implements LiveGuessDao {
	public List<LiveGuess> findAwardsUser(Long drawId, BigDecimal answer){
		String sql="SELECT * FROM live_guess WHERE draw_id="+drawId+" ORDER BY ABS(guess_answer-"+answer+") ASC";
		return this.findListBySQL(0, 10, sql);
	}
	
	public Integer countLiveGuess(Map<String, Object> map){
		List<Object> params =new ArrayList<Object>();
		String hql=" from LiveGuess where 1=1 ";
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("isWinning")){
			params.add(map.get("isWinning"));
			hql=hql+" and isWinning=? ";
		}
		if(map.containsKey("user_id")){
			params.add(map.get("user_id"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("liveDrawId")){
			params.add(map.get("liveDrawId"));
			hql=hql+" and liveDraw.id=? ";
		}
		return this.countQueryResult(hql, params);
	}
	
	public List<LiveGuess> findLiveGuessList(int i, int j, Map<String, Object> map){
		List<Object> params =new ArrayList<Object>();
		String hql=" from LiveGuess where 1=1 ";
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("isWinning")){
			params.add(map.get("isWinning"));
			hql=hql+" and isWinning=? ";
		}
		if(map.containsKey("user_id")){
			params.add(map.get("user_id"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("liveDrawId")){
			params.add(map.get("liveDrawId"));
			hql=hql+" and liveDraw.id=? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, j, hql, params);
	}
}

