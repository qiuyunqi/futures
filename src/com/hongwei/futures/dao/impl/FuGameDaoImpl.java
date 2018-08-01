package com.hongwei.futures.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuGameDao;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuGameDaoImpl extends BaseDaoImpl<FuGame, Long> implements FuGameDao {

	@Override
	public FuGame findGameByTime(Date time) {
		String hql=" from FuGame where gameTime = ? ";
		return this.findUniqueByHQL(hql, time);
	}
	
	public List<FuGame> findGameByUser(FuUser fuUser){
		String hql=" from FuGame where fuUser = ? ";
		return this.findAllByHQL(hql, fuUser);
	}
	
	public List<FuGame> findGameProgramList(int i, int pageSize,Map<String, Object> map){
		List<Object> params =new ArrayList<Object>();
		String hql=" from FuGame where 1=1 ";
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("isTrade")){
			params.add(map.get("isTrade"));
			hql=hql+" and isTrade=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and gameTime>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and gameTime<=? ";
		}
		if(map.containsKey("competitionNum")){
			params.add(map.get("competitionNum"));
			hql=hql+" and competitionNum=? ";
		}
		
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}
	
	public Integer countGame(Map<String, Object> map){
		List<Object> params =new ArrayList<Object>();
		String hql=" from FuGame where 1=1 ";
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and gameTime>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and gameTime<=? ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuGame> findGames(String queryDate) {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		SimpleDateFormat sdfHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		try {
			Date date = sdf.parse(queryDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, -1);
			Date begin = sdfHms.parse(sdf.format(c.getTime()) + " 15:30:00");
			Date end = sdfHms.parse(queryDate + " 15:30:00");
			String hql= " from FuGame where gameTime > ? and gameTime <= ? ";
			return this.findAllByHQL(hql, begin, end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;  
	}

	@Override
	public List<FuGame> findGames(Long userId) {
		String hql= " from FuGame where fuUser.id=? ";
		return this.findAllByHQL(hql, userId);
	}

	@SuppressWarnings("unchecked")
	public FuGame findMaxByGameNum(int num, int comNum) {
		String hql = "from FuGame as f where SUBSTR(f.tradeAccount, 1, 3) = :num and f.competitionNum = :comNum order by f.id DESC";
		List<FuGame> list = this.getSession().createQuery(hql)//
		.setParameter("num", num)//
		.setParameter("comNum", comNum)//
		.setMaxResults(1)//
		.list();
		return (FuGame) (list == null ? null : list.get(0));
	}

	@SuppressWarnings("unchecked")
	public List<FuGame> findGameByUser(FuUser fuUser, String gameId) {
		Long id = fuUser.getId();
		String hql = "from FuGame as f where SUBSTR(f.tradeAccount, 1, 3) = :gameId and f.fuUser.id=:id";
		return this.getSession().createQuery(hql)//
				.setParameter("gameId", gameId)//
				.setParameter("id", id)//
				.list();
	}

}

