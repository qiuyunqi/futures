package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuArticleDao;
import com.hongwei.futures.model.FuArticle;
import com.hongwei.futures.service.FuArticleService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuArticleServiceImpl implements FuArticleService {

	@Autowired
	private FuArticleDao fuArticleDao;

	// ====================== 基本 C R U D 方法 ===========================
	@Override
	public FuArticle get(Long id) {
		return fuArticleDao.get(id);
	}

	@Override
	public void save(FuArticle entity) {
		fuArticleDao.save(entity);
	}

	@Override
	public void delete(Long id) {
		fuArticleDao.delete(id);
	}

	@Override
	public List<FuArticle> findArticleList(int i, int pageSize) {
		return fuArticleDao.findArticleList(i, pageSize);
	}

	@Override
	public Integer countArticle() {
		return fuArticleDao.countArticle();
	}

	// 根据文章的id 查询这个文章的前十条数据
	public List<FuArticle> findList(String articleId, int curPage, int pageNum) {
		return fuArticleDao.findList(articleId, curPage, pageNum);
	}

	public List<FuArticle> findHelpList(int i, int pageSize, Map<String, Object> map) {
		return fuArticleDao.findHelpList(i, pageSize, map);
	}

	public Integer getCount(Map<String, Object> map) {
		return fuArticleDao.getCount(map);
	}

	public List<FuArticle> findHelpListByMap(Map<String, Object> map) {
		return fuArticleDao.findHelpListByMap(map);
	}

}
