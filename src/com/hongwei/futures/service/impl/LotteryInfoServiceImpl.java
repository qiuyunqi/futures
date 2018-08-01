package com.hongwei.futures.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.LotteryInfoDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.LiveLotteryInfo;
import com.hongwei.futures.service.LotteryInfoService;

@Service
public class LotteryInfoServiceImpl implements LotteryInfoService {

	@Resource
	private LotteryInfoDao lotteryInfoDao;

	public LiveLotteryInfo get(Long id) {
		return lotteryInfoDao.get(id);
	}

	public void save(LiveLotteryInfo entity) {
		lotteryInfoDao.save(entity);
	}

	public void delete(Long id) {
		lotteryInfoDao.delete(id);
	}

	public List<LiveLotteryInfo> findLottery(int currPage, int pageSize, FuUser fuUser, int isWin) {
		return lotteryInfoDao.findLottery(currPage, pageSize, fuUser, isWin);
	}

	public Integer getCount(FuUser fuUser, int isWin) {
		return lotteryInfoDao.getCount(fuUser, isWin);
	}

}
