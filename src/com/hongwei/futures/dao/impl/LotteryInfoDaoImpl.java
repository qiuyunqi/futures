package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.LotteryInfoDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.LiveLotteryInfo;

@SuppressWarnings("unchecked")
@Repository
public class LotteryInfoDaoImpl extends BaseDaoImpl<LiveLotteryInfo, Long> implements LotteryInfoDao {

	public Integer getCount(FuUser fuUser, int isWin) {
		String hql = "FROM LiveLotteryInfo WHERE fuUser.id = :userId AND isWin = :isWin ORDER BY drawTime DESC";
		List<LiveLotteryInfo> list = this.getSession().createQuery(hql)//
				.setParameter("userId", fuUser.getId())//
				.setParameter("isWin", isWin)//
				.list();
		return null != list && list.size() > 0 ? list.size() : 0;
	}

	public List<LiveLotteryInfo> findLottery(int currPage, int pageSize,
			FuUser fuUser, int isWin) {
		String hql = "FROM LiveLotteryInfo WHERE fuUser.id = :userId AND isWin = :isWin ORDER BY drawTime DESC";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", fuUser.getId())//
				.setParameter("isWin", isWin)//
				.setFirstResult(currPage)//
				.setMaxResults(pageSize)//
				.list();
	}

}
