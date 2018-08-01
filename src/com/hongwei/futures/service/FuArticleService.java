package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuArticle;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface FuArticleService {

	// ====================== 基本 C R U D 方法 ===========================
	public FuArticle get(Long id);

	public void save(FuArticle entity);

	public void delete(Long id);

	/**
	 * 查找文章
	 * 
	 * @param i
	 * @param pageSize
	 * @return
	 */
	public List<FuArticle> findArticleList(int i, int pageSize);

	/**
	 * 统计文章数目
	 * 
	 * @return
	 */
	public Integer countArticle();

	/**
	 * 根据文章的id 查询这个文章的前十条数据
	 * 
	 * @param articleId
	 * @param curPage
	 * @param pageNum
	 * @return
	 */
	public List<FuArticle> findList(String articleId, int curPage, int pageNum);

	public List<FuArticle> findHelpList(int i, int pageSize, Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	/**
	 * 根据条件查询帮助中心
	 * 
	 * @param map
	 * @return
	 */
	public List<FuArticle> findHelpListByMap(Map<String, Object> map);

}
