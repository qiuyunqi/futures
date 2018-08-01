package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.dao.LiveDrawDao;
import com.hongwei.futures.dao.LiveGuessDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.LiveDraw;
import com.hongwei.futures.model.LiveGuess;
import com.hongwei.futures.service.LiveDrawService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class LiveDrawServiceImpl implements LiveDrawService {

	@Autowired
	private LiveDrawDao liveDrawDao;
	@Autowired
	private LiveGuessDao liveGuessDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;

	// ====================== 基本 C R U D 方法 ===========================
	public LiveDraw get(Long id) {
		return liveDrawDao.get(id);
	}

	public void save(LiveDraw entity) {
		liveDrawDao.save(entity);
	}

	public void delete(Long id) {
		liveDrawDao.delete(id);
	}

	public Integer countLive(Map<String, Object> map) {
		return liveDrawDao.countLive(map);
	}

	public List<LiveDraw> findLiveDrawList(int i, int j, Map<String, Object> map) {
		return liveDrawDao.findLiveDrawList(i, j, map);
	}

	public void savePublishAnswer(Long id, BigDecimal answer, BigDecimal money) {
		LiveDraw liveDraw = liveDrawDao.get(id);
		liveDraw.setAnswer(answer);
		liveDraw.setMoney(money);
		liveDraw.setIsPublish(1);
		liveDraw.setPublishTime(new Date());
		liveDrawDao.save(liveDraw);
		List<LiveGuess> list = liveGuessDao.findAwardsUser(id, answer);// 根据抽奖id和答案查询出最接近正确答案的10条记录，作为中奖用户
		for (LiveGuess liveGuess : list) {
			liveGuess.setIsWinning(1);
			liveGuess.setAwardsMoney(money.divide(new BigDecimal(10)));
			liveGuess.setAwardsTime(new Date());
			liveGuessDao.save(liveGuess);

			// 给用户发钱
			FuUser user = liveGuess.getFuUser();
			user.setAccountBalance(user.getAccountBalance().add(money.divide(new BigDecimal(10))));
			fuUserDao.save(user);

			// 写资金明细
			moneyDetailUtil.saveNewFuMoneyDetail(user, null, null, null, 37, money.divide(new BigDecimal(10)), user.getAccountBalance(), true);
		}
	}

}
