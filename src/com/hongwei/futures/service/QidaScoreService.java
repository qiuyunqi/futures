package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.QidaScore;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface QidaScoreService {
	public QidaScore get(Long id);

	public void save(QidaScore entity);

	public void delete(Long id);

	public List<QidaScore> findList(int i, int pageSize, Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public void saveScoreByQd(FuUser user, Integer dictionaryId, BigDecimal scoreNew, BigDecimal money, BigDecimal totalScore, Integer isIncome);
}
