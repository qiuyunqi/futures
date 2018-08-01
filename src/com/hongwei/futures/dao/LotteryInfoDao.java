package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.LiveLotteryInfo;

public interface LotteryInfoDao extends BaseDao<LiveLotteryInfo, Long>{

	/**
	 * 通过用户查询中奖用户记录
	 * @param pageSize 
	 * @param currPage 
	 * @param fuUser
	 * @param isWin
	 * @return
	 */
	public List<LiveLotteryInfo> findLottery(int currPage, int pageSize, FuUser fuUser, int isWin);

	/**
	 * 查询我总共中奖的记录数量
	 * @param fuUser
	 * @param isWin
	 * @return
	 */
	public Integer getCount(FuUser fuUser, int isWin);

}
