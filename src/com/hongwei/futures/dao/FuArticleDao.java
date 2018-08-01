package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.FuArticle;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 弘威
 */
public interface FuArticleDao extends BaseDao<FuArticle, Long> {

	public List<FuArticle> findArticleList(int i, int pageSize);

	public Integer countArticle();

	// 根据文章的id 查询这个文章的前十条数据
	public List<FuArticle> findList(String articleId, int curPage, int pageNum);

	public List<FuArticle> findHelpList(int i, int pageSize, Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	/**
	 * 根据条件查询文章
	 * 
	 * @param map
	 * @return
	 */
	public List<FuArticle> findHelpListByMap(Map<String, Object> map);

}
