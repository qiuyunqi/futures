package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.LiveDraw;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface LiveDrawService {

	// ====================== 基本 C R U D 方法 ===========================
	public LiveDraw get(Long id);

	public void save(LiveDraw entity);

	public void delete(Long id);

	public Integer countLive(Map<String, Object> map);

	public List<LiveDraw> findLiveDrawList(int i, int j, Map<String, Object> map);

	public void savePublishAnswer(Long id, BigDecimal answer, BigDecimal money);

}
