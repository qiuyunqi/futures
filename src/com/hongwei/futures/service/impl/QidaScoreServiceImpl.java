package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuDictionaryDao;
import com.hongwei.futures.dao.QidaScoreDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.QidaScore;
import com.hongwei.futures.service.QidaScoreService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaScoreServiceImpl implements QidaScoreService {
	@Resource
	private QidaScoreDao qidaScoreDao;
	@Resource
	private FuDictionaryDao fuDictionaryDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaScore get(Long id) {
		return qidaScoreDao.get(id);
	}

	public void save(QidaScore entity) {
		qidaScoreDao.save(entity);
	}

	public void delete(Long id) {
		qidaScoreDao.delete(id);
	}

	public List<QidaScore> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaScoreDao.findList(i, pageSize, map);
	}

	public Integer getCount(Map<String, Object> map) {
		return qidaScoreDao.getCount(map);
	}

	/**
	 * 奇答产生新的积分明细
	 * 
	 * @return
	 */
	public void saveScoreByQd(FuUser user, Integer dictionaryId, BigDecimal scoreNew, BigDecimal money, BigDecimal totalScore, Integer isIncome) {
		DecimalFormat sdf = new DecimalFormat("#,###,##0.00");
		QidaScore score = new QidaScore();
		score.setFuUser(user);
		score.setFuDictionary(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))));
		score.setScore(scoreNew);
		score.setMoney(money);
		score.setTotalScore(totalScore);// 操作后账户总积分
		score.setOrderNum(String.valueOf((int) ((Math.random() * 1000000 + 100000))));// 随机产生订单号
		score.setCreateTime(new Date());
		score.setIsIncome(isIncome);// 0支出，1收入
		score.setComment(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))).getName() + sdf.format(scoreNew) + "积分");
		qidaScoreDao.save(score);
	}

}
